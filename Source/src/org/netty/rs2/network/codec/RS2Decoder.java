package org.netty.rs2.network.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.netty.rs2.Constants;
import org.netty.rs2.network.ISAACCipher;
import org.netty.rs2.network.packet.IncomingPacket;

/**
 * Decodes information sent from the client.
 * 
 * @author Michael P
 *
 */
public class RS2Decoder extends FrameDecoder {
	
	/**
	 * Constructs a new RS2Decoder.
	 * 
	 * @param cipher
	 *            The inCipher.
	 */
	public RS2Decoder(ISAACCipher cipher) {
		this.cipher = cipher;
	}
	
	/**
	 * The cipher.
	 */
	private final ISAACCipher cipher;
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() > 0) {
			int opcode = (buffer.readUnsignedByte() - cipher.getNextValue()) & 0xFF;
			int size = Constants.PACKET_SIZES[opcode];
			if (size == -1)
				size = buffer.readUnsignedByte();
			if (buffer.readableBytes() >= size) {
				final byte[] data = new byte[size];
				buffer.readBytes(data);
				final ChannelBuffer payload = ChannelBuffers.buffer(size);
				payload.writeBytes(data);
				return new IncomingPacket(opcode, size, payload);
			}
		}
		return null;
	}

}
