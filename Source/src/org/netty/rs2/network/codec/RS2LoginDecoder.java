package org.netty.rs2.network.codec;

import java.security.SecureRandom;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.netty.rs2.Constants;
import org.netty.rs2.game.Player;
import org.netty.rs2.network.AccountManager;
import org.netty.rs2.network.ISAACCipher;
import org.netty.rs2.network.packet.StreamBuffer;
import org.netty.rs2.utility.Misc;

/**
 * Decodes a RS2 log-in.
 * 
 * @author Michael P
 *
 */
public class RS2LoginDecoder extends FrameDecoder {

	/**
	 * The stage of the decode.
	 */
	private int stage;
	
	@Override
	protected Object decode(ChannelHandlerContext arg0, Channel channel, ChannelBuffer buffer) throws Exception {
		if (!channel.isConnected()) {
			return null;
		}
		switch (stage) {
			case 0:
				if (buffer.readableBytes() < 2)
					return null;
				int request = buffer.readUnsignedByte();
				if (request != 14) {
					System.err.println("Wrong log-in request! Request: "+ request);
					channel.close();
					return null;
				}
				buffer.readUnsignedByte();
				StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(17);
				out.writeLong(0);
				out.writeByte(0);
				out.writeLong(new SecureRandom().nextLong());
				channel.write(out.getBuffer());
				stage = 1;
				break;
			case 1:
				if (buffer.readableBytes() < 2)
					return null;
				int type = buffer.readByte();
				if (type != 16 && type != 18) {
					System.err.println("Wrong log-in type! Type: "+ type);
					channel.close();
					return null;
				}
				int blockLength = buffer.readUnsignedByte();
				if (buffer.readableBytes() < blockLength) 
					return null;
				buffer.readByte();
				int protocol = buffer.readShort();
				if (protocol != Constants.PROTOCOL) {
					System.err.println("Wrong protocol version! Protocol: "+ protocol);
					channel.close();
					return null;
				}
				buffer.readByte();
				for (int i = 0; i < 9; i++)
					buffer.readInt();
				buffer.readByte();
				int rsaCode = buffer.readByte();
				if (rsaCode != 10) {
					System.err.println("Unable to decode RSA block!");
					channel.close();
					return null;
				}
				long clientSessionKey = buffer.readLong();
				long serverSessionKey = buffer.readLong();
				final int[] isaacSeed = { (int) (clientSessionKey >> 32), (int) clientSessionKey, (int) (serverSessionKey >> 32), (int) serverSessionKey };
				final ISAACCipher inCipher = new ISAACCipher(isaacSeed);
				for (int i = 0; i < isaacSeed.length; i++)
					isaacSeed[i] += 50;
				final ISAACCipher outCipher = new ISAACCipher(isaacSeed);
				buffer.readInt();
				String name = Misc.formatPlayerName(Misc.getRS2String(buffer));
				String pass = Misc.getRS2String(buffer);
				return finalCheck(channel, inCipher, outCipher, name, pass);
		}
		return null;
	}
	
	/**
	 * Does the final checking before we switch from log-in decoding to packet decoding.
	 * 
	 * @param channel
	 *            	The channel.
	 * @param in
	 *              The inCipher.
	 * @param out
	 *              The outCipher.
	 * @param name
	 *              The player name.
	 * @param pass
	 *              The player pass.
	 * @return The account manager.
	 */
	private Player finalCheck(Channel channel, ISAACCipher in, ISAACCipher out, String name, String pass) {
		channel.getPipeline().remove("decoder");
		channel.getPipeline().addFirst("decoder", new RS2Decoder(in));
		Player player = new Player(channel);
		player.setInCipher(in);
		player.setOutCipher(out);
		player.setName(name);
		player.setPass(pass);
		return AccountManager.manageLogin(player);
	}

}
