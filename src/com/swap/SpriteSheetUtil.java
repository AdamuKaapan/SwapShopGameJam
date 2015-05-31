package com.swap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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

	public static final double threshold = 0.9;
	
	private static Texture currentSpriteSheet = null;
	private static SpriteSheetPart[] spriteSheetParts = new SpriteSheetPart[64];
	
	public static Texture getSpriteSheet()
	{
		return currentSpriteSheet;
	}
	
	public static SpriteSheetPart getSpriteSheetPart(int i)
	{
		return spriteSheetParts[i];
	}
	
	public static SpriteSheetPart getSpriteSheetPart(int x, int y)
	{
		return spriteSheetParts[(y * 8) + x];
	}
	
	public static boolean downloadSpritesheet() {
		
		URL website;
		try {
			website = new URL("http://swapshop.pixelsyntax.com/api/randomImage");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("res/" + downloadPath);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();

			currentSpriteSheet = TextureLoader.getTexture("PNG", new FileInputStream("res/" + downloadPath));
			BufferedImage img = ImageIO.read(new File("res/" + downloadPath));
			for (int i = 0; i < 64; i++)
			{
				spriteSheetParts[i] = splitSpritesheet(img, i);
			}
			
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private static SpriteSheetPart splitSpritesheet(BufferedImage img, int tile) {
		int tileX = tile % 8;
		int tileY = tile / 8;

		try {
			SpriteSheetPart part = new SpriteSheetPart();
			
			BufferedImage subimg = img.getSubimage(tileX * 16, tileY * 16, 16,
					16);
			part.texture = BufferedImageUtil.getTexture("SpriteSheetPart" + tile,
					subimg);
			
			for (int x = 0; x < 16; x++)
			{
				for (int y = 0; y < 16; y++)
				{
					int rgb = subimg.getRGB(x, y);
					part.alphas[(y * 16) + x] = (rgb >>> 24) & 0xFF;
					part.reds[(y * 16) + x] = (rgb >>> 16) & 0xFF;
					part.greens[(y * 16) + x] = (rgb >>> 8) & 0xFF;
					part.blues[(y * 16) + x] = (rgb >>> 0) & 0xFF;
				}
			}
			
			return part;
		} catch (IOException e) {
			return null;
		}
	}
	
	public int getViableLevels()
	{
		int count = 0;
		for (int i = 0; i < 64; i++)
		{
			if (getSpriteSheetPart(i).getDifficulty() < threshold)
				count++;
		}
		
		return count;
	}
}
