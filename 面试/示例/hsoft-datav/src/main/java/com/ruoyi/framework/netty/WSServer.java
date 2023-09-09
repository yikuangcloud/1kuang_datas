package com.ruoyi.framework.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @Description: 实现客户端发送一个请求，服务器会返回 hello netty
 *
 */
@Component
public class WSServer {

	private static class SingletionWSServer {
		static final WSServer instance = new WSServer();
	}
	
	public static WSServer getInstance() {
		return SingletionWSServer.instance;
	}
	
	private EventLoopGroup parentGroup;
	private EventLoopGroup childGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	
	public WSServer() {
		parentGroup = new NioEventLoopGroup();
		childGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		
		server.group(parentGroup, childGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WSServerInitialzer());
	}
	
	public void start() {
		this.future = server.bind(2021);
		System.err.println("Netty WebSocket Server is start...");
	}
	
	
}
