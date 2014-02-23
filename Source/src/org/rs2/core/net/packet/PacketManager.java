package org.rs2.core.net.packet;

import java.util.logging.Logger;

import org.rs2.game.player.Player;

/**
 * Manages Client -> Server packets.
 * 
 * @author Michael P
 *
 */
public class PacketManager {
	
	/**
	 * Logger instance.
	 */
	private static final Logger logger = Logger.getLogger(PacketManager.class.getName());

	/**
	 * An array of all the packet handlers.
	 */
	private static PacketHandler[] handlers = new PacketHandler[256];
	
	/**
	 * Loads the <code>PacketHandler</code>s.
	 * 
	 * Will re-do this method soon...
	 */
	public static void load() {
		logger.info("Loading packets...");
		int size = 0;
		for (PacketHandler h : handlers) {
			if (h != null)
				size++;
		}
		logger.info("Loaded "+ size +" packets.");
	}
	
	/**
	 * Handles a called packet.
	 * 
	 * @param player
	 *            The <code>Player</code> to handle the packet for.
	 * @param packet
	 *            The <code>Packet</code> to handle.
	 */
	public static void handlePacket(Player player, Packet packet) {
		PacketHandler packetHandler = handlers[packet.getOpcode()];
		if (packetHandler == null) {
			System.out.println("Unhandled packet opcode = " + packet.getOpcode() + " length = " + packet.getLength());
			return;
		}
		if (packet.getOpcode() <= 0) {
			return;
		}
		try {
			if(packet.getOpcode() != 202) {
			
			}
			packetHandler.handle(player, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Represents a packet handler.
	 * 
	 * @author Michael P
	 *
	 */
	public interface PacketHandler {
		
		/**
		 * Handles a packet.
		 * 
		 * @param player
		 *            The player to handle the packet for.
		 * @param packet
		 *            The packet to handle.
		 */
		public void handle(Player player, Packet packet);
		
	}
	
}
