package com.swap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;

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
	
	public static Texture splitSpritesheet(int tile)
	{
		int tileX = tile % 8;
		int tileY = tile / 8;
		
		try {
			BufferedImage img = ImageIO.read(new File("res/" + downloadPath));
			BufferedImage subimg = img.getSubimage(tileX * 16, tileY * 16, 16, 16);
			return BufferedImageUtil.getTexture("SpriteSheetPart" + tile, subimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static Texture splitSpritesheet(int tileX, int tileY)
	{
		return splitSpritesheet((tileY * 8) + tileX);
	}
}
