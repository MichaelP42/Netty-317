package org.netty.rs2.network.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Represents an incoming packet.
 * 
 * @author Michael P
 *
 */
public final class IncomingPacket {
	
	/**
	 * Constructs a new incoming packet.
	 * 
	 * @param opcode
	 *            The packet opcode.
	 * @param size
	 *            The packet size.
	 * @param payload
	 *            The packet payload.
	 */
	public IncomingPacket(int opcode, int size, ChannelBuffer payload) {
		this.opcode = opcode;
		this.size = size;
		this.payload = payload;
	}
	
	/**
	 * The packet opcode.
	 */
	private final int opcode;
	
	/**
	 * The packet size.
	 */
	private final int size;
	
	/**
	 * The payload.
	 */
	private final ChannelBuffer payload;

	/**
	 * Gets the opcode.
	 * @return The opcode.
	 */
	public int getOpcode() {
		return opcode;
	}

	/**
	 * Gets the packet size.
	 * @return The packet size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the payload.
	 * @return The packet payload.
	 */
	public ChannelBuffer getPayload() {
		return payload;
	}
	
}
