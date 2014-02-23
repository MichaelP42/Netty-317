package org.rs2.core;

import org.rs2.core.task.Task;
import org.rs2.game.World;

/**
 * The main game engine of the <code>RS2Server</code>.
 * 
 * @author Michael P
 *
 */
public class GameEngine extends Task {
	
	/**
	 * Is the engine running?
	 */
	private static boolean running;
	
	/**
	 * Starts the engine.
	 */
	public static void startEngine() {
		running = true;
		World.getWorld().submit(new GameEngine());
	}
	
	/**
	 * Stops the engine.
	 */
	public static void stopEngine() {
		running = false;
	}

	@Override
	protected void execute() {
		/*
		 * If the engine has been killed in anyway, rather if it's just to pause processing, 
		 * or to stop it completely, then we stop the task.
		 */
		if (!running) {
			this.stop();
			return;
		}
		
		/*	Start the world processing	*/
		World.getWorld().process();
	}

}
