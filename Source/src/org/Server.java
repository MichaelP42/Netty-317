package org;

import org.rs2.RS2Server;

/**
 * The core class of the RS2 game server.
 * 
 * @author Michael P
 *
 */
public class Server {

	/**
	 * Starts the server.
	 * 
	 * @param args
	 *            The command arguments.
	 */
	public static void main(String[] args) {
		/*	We bind and start the RS2Server	*/
		new RS2Server().bind().start();
	}
	
}
