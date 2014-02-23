package org.rs2.core.net;

import org.rs2.Constants;
import org.rs2.core.net.packet.Packet.Type;
import org.rs2.core.net.packet.PacketBuilder;
import org.rs2.game.player.Player;

/**
 * Sends Server -> Client packets.
 * 
 * @author Michael P
 *
 */
public class PacketSender {

	/**
	 * Constructs a new packet sender.
	 * 
	 * @param player
	 *            The player to construct the packet sender for.
	 */
	public PacketSender(Player player) {
		this.player = player;
	}
	
	/**
	 * The player to send the packet's to.
	 */
	private final Player player;
	
	/**
	 * Sends the log-in response.
	 * 
	 * @param code
	 *            The return code.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendLoginResponse(int code) {
		PacketBuilder pb = new PacketBuilder();
		pb.put((byte) code);
		player.write(pb.toPacket());
		return this;
	}
	
	/**
	 * Sends a log-in.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendLogin() {
		sendDetails();
		return this;
	}
	
	/**
	 * Sends the player membership flag and player list index.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendDetails() {
		player.write(new PacketBuilder(249).putByteA(1).putLEShortA(player.getIndex()).toPacket());
		player.write(new PacketBuilder(107).toPacket());
		return this;
	}
	
	/**
	 * Sends a message to the chat-box.
	 * 
	 * @param message
	 *            The message to send.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendMessage(String message) {
		player.write(new PacketBuilder(253, Type.VARIABLE).putRS2String(message).toPacket());
		return this;
	}
	
}
