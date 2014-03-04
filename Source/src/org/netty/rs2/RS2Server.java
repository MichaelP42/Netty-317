package org.netty.rs2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.netty.rs2.network.PipelineFactory;
import org.netty.rs2.task.TaskManager;
import org.netty.rs2.task.impl.Processor;

/**
 * Starts up the RS2Server.
 * 
 * @author Michael P
 *
 */
public class RS2Server {

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(RS2Server.class.getName());
	
	/**
	 * The RS2Server bootstrap.
	 */
	private static final ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
	
	/**
	 * Binds the RS2Server port.
	 * @return The RS2Server instance, for chaining.
	 */
	public RS2Server bind() {
		logger.info("Binding to port "+ Constants.PORT +"...");
		bootstrap.setPipelineFactory(new PipelineFactory());
		bootstrap.bind(new InetSocketAddress(Constants.PORT));
		logger.info("Binded to port "+ Constants.PORT +".");
		return this;
	}
	
	/**
	 * Start's the RS2Server after binding it.
	 */
	public void start() {
		// TODO World loading.
		TaskManager.submit(new Processor());
		logger.info("Netty #317 is now ready!");
	}
	
}
