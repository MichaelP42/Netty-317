package org.netty.rs2.game;

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
	 * Sets the entity index.
	 * 
	 * @param index
	 *            The index to set the entity.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Gets the entity index.
	 * @return The entity's index.
	 */
	public int getIndex() {
		return index;
	}
	
}
