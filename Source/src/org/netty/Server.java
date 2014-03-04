package org.netty;

import org.netty.rs2.RS2Server;

/**
 * The core class of Netty #317.
 * 
 * @author Michael P
 *
 */
public class Server {

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            The command arguments.
	 */
	public static void main(String[] args) {
		new RS2Server().bind().start();
	}
	
}
