package org.rs2.game;

/**
 * Represents a single location in the game world.
 * 
 * @author Michael P
 *
 */
public class Location {

	/**
	 * Constructs a new location.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * @param z
	 *            The Z coordinate.
	 */
	private Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a new location.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * @param z
	 *            The Z coordinate.
	 * @return The new location.
	 */
	public static Location create(int x, int y, int z) {
		return new Location(x, y, z);
	}
	
	/**
	 * Creates a new location with the Z coordinate of 0.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * @return The new location.
	 */
	public static Location create(int x, int y) {
		return new Location(x, y, 0);
	}
	
	/**
	 * The X coordinate.
	 */
	private final int x;
	
	/**
	 * The Y coordinate.
	 */
	private final int y;
	
	/**
	 * The Z coordinate.
	 */
	private final int z;
	
	/**
	 * Gets the X coordinate of the location.
	 * @return The X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the Y coordinate of the location.
	 * @return The Y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the Z coordinate of the location.
	 * @return The Z coordinate.
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Gets the local x coordinate relative to this region.
	 * @return The local x coordinate relative to this region.
	 */
	public int getLocalX() {
		return getLocalX(this);
	}
	
	/**
	 * Gets the local y coordinate relative to this region.
	 * @return The local y coordinate relative to this region.
	 */
	public int getLocalY() {
		return getLocalY(this);
	}
	
	/**
	 * Gets the local x coordinate relative to a specific region.
	 * @param l The region the coordinate will be relative to.
	 * @return The local x coordinate.
	 */
	public int getLocalX(Location l) {
		return x - 8 * l.getRegionX();
	}
	
	/**
	 * Gets the local y coordinate relative to a specific region.
	 * @param l The region the coordinate will be relative to.
	 * @return The local y coordinate.
	 */
	public int getLocalY(Location l) {
		return y - 8 * l.getRegionY();
	}
	
	/**
	 * Gets the region X coordinate.
	 * @return The region X coordinate.
	 */
	public int getRegionX() {
		return (x >> 3) - 6;
	}
	
	/**
	 * Gets the region Y coordinate.
	 * @return The region Y coordinate.
	 */
	public int getRegionY() {
		return (y >> 3) - 6;
	}
	
	@Override
	public String toString() {
		return "["+ x +", "+ y +", "+ z +"]";
	}
	
	@Override
	public int hashCode() {
		return z << 30 | x << 15 | y;
	}
	
}
