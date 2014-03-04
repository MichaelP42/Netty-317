package org.netty.rs2.network.packet;

import org.netty.rs2.Constants;
import org.netty.rs2.game.Player;

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
	 *            The player to construct the sender for.
	 */
	public PacketSender(Player player) {
		this.player = player;
	}
	
	/**
	 * The player to send to packets for.
	 */
	private final Player player;
	
	/**
	 * Sends a response code.
	 * 
	 * @param code
	 *            The response code.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendLoginResponse(int code) {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(3);
		out.writeByte(code);
		out.writeByte(0); // TODO Staff rights
		out.writeByte(0);
        player.send(out.getBuffer());
		return this;
	}
	
	/**
	 * Sends all the login packet's.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendLogin() {
		sendCameraReset();
		sendDetails();
		sendMessage("Welcome to "+ Constants.NAME +".");
		return this;
	}
	
	/**
	 * Sends a player detail.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendDetails() {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(4);
		out.writeHeader(player.getOutCipher(), 249);
		out.writeByte(1, StreamBuffer.ValueType.A);
		out.writeShort(player.getIndex(), StreamBuffer.ValueType.A, StreamBuffer.ByteOrder.LITTLE);
		player.send(out.getBuffer());
		return this;
	}
	
	/**
	 * Sends a camera reset.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendCameraReset() {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(1);
		out.writeHeader(player.getOutCipher(), 107);
		player.send(out.getBuffer());
		return this;
	}
	
	/**
	 * Sends a server message such as "Welcome to RuneScape.".
	 * 
	 * @param message
	 *            The message to send.
	 * @return The packet sender instance, for chaining.
	 */
	public PacketSender sendMessage(String message) {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(message.length() + 3);
		out.writeVariablePacketHeader(player.getOutCipher(), 253);
		out.writeString(message);
		out.finishVariablePacketHeader();
		player.send(out.getBuffer());
		return this;
	}
	
}
