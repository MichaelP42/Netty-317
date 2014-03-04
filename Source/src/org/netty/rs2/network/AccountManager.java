package org.netty.rs2.network;

import org.netty.rs2.game.Player;

/**
 * Manages connecting/connected accounts.
 * 
 * @author Michael P
 *
 */
public final class AccountManager {

	/**
	 * Manages a player login.
	 * 
	 * @param player
	 *            The player to manage.
	 * @return The player we managed.
	 */
	public static Player manageLogin(Player player) {
		// TODO Load the player save & account checking.
		player.getPacketSender().sendLoginResponse(GOOD_LOGIN);
		player.finishLogin();
		return player;
	}
	
	/**
	 * The good log-in response code.
	 */
	public static final int GOOD_LOGIN = 2;
	
}
