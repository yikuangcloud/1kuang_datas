package com.ruoyi.framework.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @Description: 处理消息的handler
 * TextWebSocketFrame: 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 *
 */
@Component
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	// 用于记录和管理所有客户端的channel，把相应的channel都保存到group组里面去
	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	//存储ip和channel的容器
	private static ConcurrentMap<String, List<Channel>> channelMap = new ConcurrentHashMap<>();

	//存储channel和key的容器
	private static ConcurrentMap<String, String> keyMap = new ConcurrentHashMap<>();


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// 获取客户端传输过来的消息
		String content = msg.text();
		System.out.println("msg===="+content);

		JSONObject obj = JSONObject.parseObject(content);
		if(obj.get("action").toString().equals("connect")){
			List<Channel> channelList = channelMap.get(obj.get("key").toString());
			if(channelList == null ){

				channelList = new ArrayList<Channel>();
			}
			channelList.add(ctx.channel());
			channelMap.put(obj.get("key").toString(),channelList);
			keyMap.put(ctx.channel().id().asShortText(),obj.get("key").toString());
		}else if(obj.get("action").toString().equals("keep")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("action","keep");
			jsonObject.put("result","pong");
			clients.find(ctx.channel().id()).writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(jsonObject)));
		}
	}

	/**
	 * 当客户端连接服务端之后（打开连接）
	 * 获取客户端channel，并且放到ChannelGroup中去进行管理
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// 获取当前客户端对应的channel通道
		System.out.println("*******"+ctx.channel().id().asShortText());
		clients.add(ctx.channel());
		System.out.println("size======="+clients.size());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端被移除，channelId为：" + channelId);

		// 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
		String key = keyMap.get(channelId);
		if(key != null && !key.isEmpty()){
			List<Channel> channelList = channelMap.get(key);
			if(channelList != null && !channelList.isEmpty()){
				channelList.remove(ctx.channel());
				channelMap.put(key,channelList);
			}
			keyMap.remove(channelId);
		}

		clients.remove(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		// 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
		// 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端被移除，channelId为：" + channelId);
		String key = keyMap.get(channelId);
		if(key != null && !key.isEmpty()){
			List<Channel> channelList = channelMap.get(key);
			if(channelList != null && !channelList.isEmpty()){
				channelList.remove(ctx.channel());
				channelMap.put(key,channelList);
			}
			keyMap.remove(channelId);
		}
		clients.remove(ctx.channel());
		ctx.channel().close();

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx)throws Exception {
		System.out.println("^^^^^^^^^^^^");
	}
	/**
	 * 群发消息
	 */
	public void sendMessageAll(String message) {
		System.out.println("服务端主动给客户端推送消息......");
		//String message="您好，这是主动推送信息";
		clients.writeAndFlush(new TextWebSocketFrame(message));
		System.err.println(clients);
		System.out.println("服务端主动给客户端推送消息结束......");
	}
	
	/**
	 * 用户推送给运维的工单的推送
	 */
	public void toOpera(String message) {
		//String message="您有新的工单推送！";
		clients.writeAndFlush(new TextWebSocketFrame(message));
		System.err.println(clients);
	}
	
	/**
	 * 运维推送给用户的工单的推送
	 */
	public void toCust(String message) {
		//String message="您的工单已回复，请注意查看！";
		clients.writeAndFlush(new TextWebSocketFrame(message));
		System.err.println(clients);
	}

	/**
	 * 接口推送给组件的数据
	 * @param message
	 */
	public void toComponent(String message) {
		JSONObject obj = JSONObject.parseObject(message);
		String key = obj.getString("key");
		String result = obj.getString("result");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action","chat");
		jsonObject.put("result",result);

		List<Channel> channelList = channelMap.get(key);
		if(channelList != null && !channelList.isEmpty()){
			for (Channel channel: channelList
			) {
				clients.find(channel.id()).writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(jsonObject)));
			}
		}

	}
}
