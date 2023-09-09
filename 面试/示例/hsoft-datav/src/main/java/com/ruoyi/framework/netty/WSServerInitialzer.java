package com.ruoyi.framework.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 
 * @Description： 初始化器，channel注册后，会执行里面相应的初始化方法
 *
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		// websocket基于http协议，所以要有http编解码器
		pipeline.addLast(new HttpServerCodec());
		// 对写大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());
		// 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
		// 几乎在netty中的编程，都会使用到此handler
		pipeline.addLast(new HttpObjectAggregator(1024 * 24));

		// ======================= 以上是用于支持http协议 ======================

		/**
		 * websocket服务器处理的协议，用于指定给客户端连接访问的路由: /TEMP 
		 * 本 handler会帮助处理一些繁重的复杂的事 
		 * 会帮助处理我手动作: handshaking(close, ping, pong) ping + pong = 心跳
		 * 对于websocket来讲，都是以frames进行传输的，不同是数据类型对应的frames也不同
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		// 自定义的handler
		pipeline.addLast(new ChatHandler());

	}

}
