package org.rs2.core.net.login;

import org.rs2.core.net.login.codec.RS2Decoder;
import org.rs2.game.World;
import org.rs2.game.player.Player;

/**
 * Manages a log-in.
 * 
 * @author Michael P
 *
 */
public final class LoginManager {

	/**
	 * Manages a player log-in.
	 * 
	 * @param player
	 *            The player to log-in.
	 */
	public static void manageLogin(Player player) {
		if (World.getWorld().alreadyOnline(player)) {
			player.getPacketSender().sendLoginResponse(ALREADY_ONLINE);
			player.getChannel().close();
			return;
		}
		player.getPacketSender().sendLoginResponse(LOGIN_OK);
		player.finishLogin();
		player.getChannel().getPipeline().replace("decoder", "decoder", new RS2Decoder(player.getInCipher()));
	}
	
	/**
	 * The log-in is O.K.
	 */
	public static final int LOGIN_OK = 2;
	
	/**
	 * The player is already online.
	 */
	public static final int ALREADY_ONLINE = 5;
	
}
