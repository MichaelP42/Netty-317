package org.netty.rs2.utility;

import org.jboss.netty.buffer.ChannelBuffer;

public class Misc {

	public static String getRS2String(final ChannelBuffer buf) {
		final StringBuilder bldr = new StringBuilder();
		byte b;
		while (buf.readable() && (b = buf.readByte()) != 10)
			bldr.append((char) b);
		return bldr.toString();
	}
	
	public static String formatPlayerName(String str) {
		str = ucFirst(str);
		str.replace("_", " ");
		return str;
	}
	
	public static String ucFirst(String str) {
		str = str.toLowerCase();
		if (str.length() > 1) {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str.toUpperCase();
		}
		return str;
	}
	
}
