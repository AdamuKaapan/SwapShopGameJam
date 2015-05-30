package com.swap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SpritesheetDownloader {
	public static final String downloadPath = "res/CurrentSpritesheet.png";
	
	public boolean downloadSpritesheet()
	{
		URL website;
		try {
			website = new URL("");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("res/CurrentSpritesheet.png");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
