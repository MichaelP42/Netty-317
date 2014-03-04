package org.netty.rs2.game;

import org.jboss.netty.channel.Channel;
import org.netty.rs2.network.ISAACCipher;
import org.netty.rs2.network.packet.PacketSender;

/**
 * Represents a connected player controlled entity.
 * 
 * @author Michael P
 *
 */
public class Player extends Entity {

	/**
	 * Constructs a new player.
	 * 
	 * @param channel
	 *            The channel of the player.
	 */
	public Player(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * The channel of the player.
	 */
	private final Channel channel;
	
	/**
	 * The inCipher of the player.
	 */
	private ISAACCipher inCipher;
	
	/**
	 * The outCipher of the player.
	 */
	private ISAACCipher outCipher;
	
	/**
	 * The packet sender of the player.
	 */
	private final PacketSender packetSender = new PacketSender(this);
	
	/**
	 * The name of the player.
	 */
	private String name;
	
	/**
	 * The password of the player.
	 */
	private String pass;
	
	/**
	 * Sends a packet.
	 * 
	 * @param packet
	 *           The packet to send.
	 */
	public void send(Object packet) {
		channel.write(packet);
	}
	
	/**
	 * Finishes a player login.
	 */
	public void finishLogin() {
		World.getWorld().register(this);
		packetSender.sendLogin();
		System.out.println("Registerd player ["+ name +", "+ pass +"]");
	}
	
	/**
	 * Sets the inCipher.
	 * 
	 * @param cip
	 *            The inCipher.
	 */
	public void setInCipher(ISAACCipher cip) {
		this.inCipher = cip;
	}
	
	/**
	 * Sets the outCipher.
	 * 
	 * @param cip
	 *            The outCipher.
	 */
	public void setOutCipher(ISAACCipher cip) {
		this.outCipher = cip;
	}
	
	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 *            The name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the pass of the player.
	 * 
	 * @param pass
	 *            The pass of the player.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	 
	/**
	 * Gets the channel of the player.
	 * @return The players channel.
	 */
	public Channel getChannel() {
		return channel;
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
	
	/**
	 * Gets the packet sender.
	 * @return The packet sender of the player.
	 */
	public PacketSender getPacketSender() {
		return packetSender;
	}
	
	/**
	 * Gets the name of the player.
	 * @return The name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the password of the player.
	 * @return The player password.
	 */
	public String getPass() {
		return pass;
	}
	
}
