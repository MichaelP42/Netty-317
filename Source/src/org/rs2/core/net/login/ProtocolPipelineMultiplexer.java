package org.rs2.core.net.login;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.rs2.core.net.login.codec.RS2Encoder;
import org.rs2.core.net.login.codec.RS2LoginDecoder;

/**
 * ProtocolPipelineMultiplexer.
 * 
 * @author Michael P
 *
 */
public final class ProtocolPipelineMultiplexer implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("encoder", new RS2Encoder());
		pipeline.addLast("decoder", new RS2LoginDecoder());
		pipeline.addLast("connection", new ConnectionManager());
		return pipeline;
	}

}
