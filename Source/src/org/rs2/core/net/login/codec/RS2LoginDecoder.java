package org.rs2.core.net.login.codec;

import java.security.SecureRandom;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.rs2.Constants;
import org.rs2.core.net.ISAACCipher;
import org.rs2.core.net.login.LoginManager;
import org.rs2.core.net.packet.PacketBuilder;
import org.rs2.game.player.Player;

/**
 * Decodes a player log-in.
 * 
 * @author Michael P
 *
 */
public class RS2LoginDecoder extends FrameDecoder {

	/**
	 * The stage of the log-in decode.
	 */
	private int stage;
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (!channel.isConnected()) {
			return null;
		}
		switch (stage) {
		case 0:
			if (buffer.readableBytes() < 2)
				return null;
			int request = buffer.readUnsignedByte();
			if (request != 14) {
				System.err.println("Invalid login request: " + request);
				channel.close();
				return null;
			}
			buffer.readUnsignedByte();
			channel.write(new PacketBuilder().putLong(0).put((byte) 0).putLong(new SecureRandom().nextLong()).toPacket());
			stage = 1;
			return null;
		case 1:
			if (buffer.readableBytes() > 2) {
				buffer.readByte();
				int blockLength = buffer.readByte() & 0xff;
				if (buffer.readableBytes() < blockLength) {
					return null;
				}
				buffer.readByte();
				int protocol = buffer.readShort();
				if (protocol != Constants.PROTOCOL) {
					channel.close();
					return null;
				}
				buffer.readByte();

				for (int i = 0; i < 9; i++)
					buffer.readInt();

				buffer.readByte();

				int rsaOpcode = buffer.readByte();
				if (rsaOpcode != 10) {
					System.err.println("Unable to decode RSA block properly!");
					channel.close();
					return null;
				}
				final long clientHalf = buffer.readLong();
				final long serverHalf = buffer.readLong();
				final int[] isaacSeed = { (int) (clientHalf >> 32), (int) clientHalf, (int) (serverHalf >> 32), (int) serverHalf };
				final ISAACCipher inCipher = new ISAACCipher(isaacSeed);
				for (int i = 0; i < isaacSeed.length; i++)
					isaacSeed[i] += 50;
				final ISAACCipher outCipher = new ISAACCipher(isaacSeed);
				final Player player = new Player(channel, inCipher, outCipher);
				LoginManager.manageLogin(player);
			}
			return null;
		}
		return null;
	}

}
