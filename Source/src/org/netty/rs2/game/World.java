package org.netty.rs2.game;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages the game world.
 * 
 * @author Michael P
 *
 */
public class World {

	/**
	 * The world instance.
	 */
	private static final World world = new World();
	
	/**
	 * A listing of registered players.
	 */
	private static final List<Player> players = new LinkedList<Player>();
	
	/**
	 * Registers a new player.
	 * 
	 * @param player
	 *            The player to register.
	 */
	public void register(Player player) {
		players.add(player);
		player.setIndex(players.indexOf(player));
	}
	
	/**
	 * Gets the world.
	 * @return The world instance, for chaining.
	 */
	public static World getWorld() {
		return world;
	}
	
}
