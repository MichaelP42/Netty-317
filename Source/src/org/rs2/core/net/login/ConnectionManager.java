package org.rs2.core.net.login;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.rs2.core.net.packet.Packet;
import org.rs2.core.net.packet.PacketManager;
import org.rs2.game.World;
import org.rs2.game.player.Player;

/**
 * Manages client connections.
 * 
 * @author Michael P
 *
 */
public final class ConnectionManager extends SimpleChannelHandler {
	
	/**
	 * The player to manage the connection for.
	 */
	private Player player;
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("connected on "+ ctx.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Player player = (Player) ctx.getChannel();
		System.out.println("closed on "+ player.getChannel());
		World.getWorld().unregister(player);
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		/*
		 * If the message is a player, then we set the player.
		 */
		if (e.getMessage() instanceof Player) {
			player = (Player) e.getMessage();
		} 
		
		/*
		 * If the message is a packet, then we handle the packet.
		 */
		else if (e.getMessage() instanceof Packet) {
			PacketManager.handlePacket(player, (Packet) e.getMessage());
		}
	}
}
