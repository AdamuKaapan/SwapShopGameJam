package com.swap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SpriteSheetUtil {
	public static final String downloadPath = "CurrentSpritesheet.png";
	
	public static boolean downloadSpritesheet()
	{
		URL website;
		try {
			website = new URL("http://swapshop.pixelsyntax.com/api/randomImage");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("res/" + downloadPath);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
