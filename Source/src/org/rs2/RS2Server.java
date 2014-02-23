package org.rs2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.rs2.core.GameEngine;
import org.rs2.core.net.login.ProtocolPipelineMultiplexer;
import org.rs2.game.World;

/**
 * RuneScape 2 server.
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
	 * The bootstrap of the RS2Server.
	 */
	private static final ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

	/**
	 * Binds the RS2Server to the given port.
	 * @return The RS2Server instance, for chaining.
	 */
	public RS2Server bind() {
		logger.info("Binding to port "+ Constants.PORT +"...");
		bootstrap.setPipelineFactory(new ProtocolPipelineMultiplexer());
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		bootstrap.bind(new InetSocketAddress(Constants.PORT));
		logger.info("Listening on port "+ Constants.PORT +".");
		return this;
	}
	
	/**
	 * Starts the RS2Server.
	 */
	public void start() {
		/*	We start the loading of the game world.	*/
		World.setInstace(new World());
		
		/*	Now we finally start the game engine.	*/
		GameEngine.startEngine();
		
		logger.info(Constants.NAME +" is now ready!");
	}
}
