package org.rs2.game.player;

import org.jboss.netty.channel.Channel;
import org.rs2.core.net.ISAACCipher;
import org.rs2.core.net.PacketSender;
import org.rs2.core.net.packet.Packet;
import org.rs2.game.Entity;
import org.rs2.game.Location;
import org.rs2.game.World;

/**
 * Represents a connected player.
 * 
 * @author Michael P
 *
 */
public class Player extends Entity {

	/**
	 * Constructs a new player.
	 * 
	 * @param channel
	 *            The <code>Channel</code> of the player.
	 * @param inCipher
	 *            The inChipher.
	 * @param outCipher
	 *            The outCipher.
	 */
	public Player(Channel channel, ISAACCipher inCipher, ISAACCipher outCipher) {
		this.channel = channel;
		this.inCipher = inCipher;
		this.outCipher = outCipher;
		this.setLocation(Location.create(3232, 3232));
	}
	
	/**
	 * The <code>Channel</code> of the player.
	 */
	private final Channel channel;
	
	/**
	 * The inCipher of the player.
	 */
	private final ISAACCipher inCipher;
	
	/**
	 * The outCipher of the player.
	 */
	private final ISAACCipher outCipher;
	
	/**
	 * The packet sender.
	 */
	private final PacketSender packetSender = new PacketSender(this); 
	
	/**
	 * Writes a packet for the player.
	 */
	public void write(Packet packet) {
		channel.write(packet);
	}
	
	/**
	 * Finishes a player log-in.
	 */
	public void finishLogin() {
		packetSender.sendLogin();
		World.getWorld().register(this);
	}
	
	/**
	 * Gets the player's <code>Channel</code>
	 * @return The <code>Channel</code> of the player.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * Gets the player's packet sender.
	 * @return The packet sender of the player.
	 */
	public PacketSender getPacketSender() {
		return packetSender;
	}
	
	/**
	 * Gets the inCipher of the player.
	 * @return The inCipher of the player.
	 */
	public ISAACCipher getInCipher() {
		return inCipher;
	}
	
	/**
	 * Gets the outCipher of the player.
	 * @return The outCipher of the player.
	 */
	public ISAACCipher getOutCipher() {
		return outCipher;
	}
	
}
