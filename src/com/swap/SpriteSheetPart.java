package com.swap;



import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class SpriteSheetPart
{
	public static final int sensitivity = 2;
	public static final int damageRange = 10;
	
	public Texture texture;
	public int[] reds = new int[256];
	public int[] greens = new int[256];
	public int[] blues = new int[256];
	public int[] alphas = new int[256];
	
	public int getRed(int x, int y)
	{
		return reds[(y * 16) + x];
	}
	
	public int getGreen(int x, int y)
	{
		return greens[(y * 16) + x];
	}
	
	public int getBlue(int x, int y)
	{
		return blues[(y * 16) + x];
	}
	
	public int getAlpha(int x, int y)
	{
		return alphas[(y * 16) + x];
	}

	public int getHue(int x, int y)
	{
		return (int)(java.awt.Color.RGBtoHSB(getRed(x, y), getGreen(x, y), getBlue(x, y), null)[0] * 360);
	}
	
	public Color getColor(int x, int y)
	{
		return new Color(getRed(x, y), getGreen(x, y), getBlue(x, y), getAlpha(x, y));
	}
	
	public double getDifficulty()
	{
		UnionFind uf = new UnionFind(256);
		
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 16; y++)
			{
				int hue = getHue(x, y);
				
				if (x > 0) // We can go to the left
				{
					if (Math.abs(getHue(x - 1, y) - hue) < sensitivity)
						uf.union((y * 16) + x, (y * 16) + (x - 1));
				}
				if (x < 15) // We can go to the right
				{
					if (Math.abs(getHue(x + 1, y) - hue) < sensitivity)
						uf.union((y * 16) + x, (y * 16) + (x + 1));
				}
				if (y > 0) // We can go up
				{
					if (Math.abs(getHue(x, y - 1) - hue) < sensitivity)
						uf.union((y * 16) + x, ((y - 1) * 16) + x);
				}
				if (y < 15) // We can go down
				{
					if (Math.abs(getHue(x, y + 1) - hue) < sensitivity)
						uf.union((y * 16) + x, ((y + 1) * 16) + x);
				}
			}
		}
		
		int maxSize = 0;
		
		for (int i = 0; i < 256; i++)
		{
			maxSize = Math.max(maxSize, uf.getSize(i));
		}
		
		return (double) maxSize / 256.0;
	}

	public double getDamage(int hue, int x, int y)
	{
		int tile = getHue(x, y);
		int diff = Math.abs(hue - tile);
		
		int temp = Math.max(0, damageRange - diff);
		
		return (double) temp / damageRange;
	}
}