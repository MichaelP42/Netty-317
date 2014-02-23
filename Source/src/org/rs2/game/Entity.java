package org.rs2.game;

/**
 * Represents an in-game entity.
 * 
 * @author Michael P
 *
 */
public abstract class Entity {

	/**
	 * The index of the entity.
	 */
	private int index = -1;
	
	/**
	 * The location of the entity.
	 */
	private Location location;
	
	/**
	 * Sets the entity's index.
	 * 
	 * @param index
	 *            The index of the entity.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Sets the location of the entity.
	 * 
	 * @param loc
	 *            The location to set the entity.
	 */
	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	/**
	 * Gets the entity's index.
	 * @return The entity's index.
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the location of the entity.
	 * @return The location of the entity.
	 */
	public Location getLocation() {
		return location;
	}
	
}
