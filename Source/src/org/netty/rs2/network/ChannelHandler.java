package org.netty.rs2.network;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.netty.rs2.game.Player;
import org.netty.rs2.network.packet.IncomingPacket;

/**
 * A channel handler which handles connected channel's.
 * 
 * @author Michael P
 *
 */
public class ChannelHandler extends SimpleChannelHandler {

	/**
	 * The player to handle the channel for.
	 */
	@SuppressWarnings("unused")
	private Player player;
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("connected on "+ ctx.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("closed on "+ ctx.getChannel());
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		if (!e.getChannel().isConnected())
			return;
		if (e.getMessage() instanceof Player) {
			this.player = (Player) e.getMessage();
		} else if (e.getMessage() instanceof IncomingPacket) {
			// TODO Handle incoming packet's.
		}
	}
	
}
