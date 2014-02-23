package org.rs2.core.net.login.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.rs2.Constants;
import org.rs2.core.net.ISAACCipher;
import org.rs2.core.net.packet.Packet;
import org.rs2.core.net.packet.Packet.Type;

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
	 *            The cipher.
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
			int opcode = (buffer.readByte() & 0xFF - cipher.getNextValue()) & 0xFF;
			int size = Constants.PACKET_SIZES[opcode];
			if (size == -1)
				size = buffer.readByte() & 0xFF;
			if (buffer.readableBytes() >= size) {
				final byte[] data = new byte[size];
				buffer.readBytes(data);
				final ChannelBuffer payload = ChannelBuffers.buffer(size);
				payload.writeBytes(data);
				return new Packet(opcode, Type.FIXED, payload);
			}
		}
		return null;
	}

}
