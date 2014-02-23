package org.rs2.game;

import org.rs2.Constants;
import org.rs2.core.net.packet.PacketManager;
import org.rs2.core.task.Task;
import org.rs2.core.task.TaskScheduler;
import org.rs2.game.player.Player;

/**
 * Manages the in-game world.
 * 
 * @author Michael P
 *
 */
public class World {

	/**
	 * Constructs and loads a new game world.
	 */
	public World() {
		PacketManager.load();
	}
	
	/**
	 * The world instance.
	 */
	private static World instance;
	
	/**
	 * An array of all the registered players.
	 */
	private final Player[] players = new Player[Constants.MAX_PLAYERS];
	
	/**
	 * The game worlds task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();
	
	/**
	 * Sets the world instance.
	 */
	public static void setInstace(World world) {
		if (World.instance != null)
			throw new IllegalArgumentException("Instance already set!");
		World.instance = world;
	}
	
	/**
	 * Processes the game world.
	 */
	public void process() {
		
	}
	
	/**
	 * Registers a player to the game world.
	 * 
	 * @param player
	 *            The player to register.
	 */
	public void register(Player player) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				players[i] = player;
				player.setIndex(i);
				return;
			}
		}
	}
	
	/**
	 * Unregisters a player from the game world.
	 * 
	 * @param player
	 *            The player to unregister.
	 */
	public void unregister(Player player) {
		players[player.getIndex()] = null;
		player.setIndex(-1);
	}
	
	/**
	 * Checks to see if the player is already online.
	 * 
	 * @param player
	 *            The player to check for.
	 * @return If the player is already online <code>true</code>,
	 * if the player isn't already online <code>false</code>.
	 */
	public boolean alreadyOnline(Player player) {
		for (Player p : players) {
			return p == player;
		}
		return false;
	}
	
	/**
	 * Submits a new <code>Task</code>.
	 * 
	 * @param task
	 *            The <code>Task</code> to submit.
	 */
	public void submit(Task task) {
		scheduler.schedule(task);
	}
	
	/**
	 * Gets the world instance.
	 * @return The world instance, for chaining.
	 */
	public static World getWorld() {
		return instance;
	}
	
}
