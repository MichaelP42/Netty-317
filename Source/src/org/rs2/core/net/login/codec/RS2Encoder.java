package org.rs2.core.net.login.codec;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.rs2.core.net.packet.Packet;

/**
 * Encodes information from a connected client.
 * 
 * @author Michael P
 *
 */
public class RS2Encoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object message) throws Exception {
		return ((Packet) message).getPayload();
	}

}
