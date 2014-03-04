package org.netty.rs2.network;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.netty.rs2.network.codec.RS2Encoder;
import org.netty.rs2.network.codec.RS2LoginDecoder;

/**
 * The <code>ChannelPipelineFactory</code> of the RS2Server's bootstrap.
 * 
 * @author Michael P
 *
 */
public final class PipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipe = Channels.pipeline();
		pipe.addLast("encoder", new RS2Encoder());
		pipe.addLast("decoder", new RS2LoginDecoder());
		pipe.addLast("handler", new ChannelHandler());
		return pipe;
	}

}
