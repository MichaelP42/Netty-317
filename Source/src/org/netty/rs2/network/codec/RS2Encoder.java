package org.netty.rs2.network.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Encodes packets sent from the client.
 * 
 * @author Michael P
 *
 */
public class RS2Encoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext chc, Channel chan, Object message) throws Exception {
		return ((ChannelBuffer) message);
	}

}
