package com.swap;

import java.awt.Color;

import org.newdawn.slick.opengl.Texture;

public class SpriteSheetPart
{
	public static final int sensitivity = 5;
	
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
		return reds[(y * 16) + x];
	}

	public int getHue(int x, int y)
	{
		return (int)(Color.RGBtoHSB(getRed(x, y), getGreen(x, y), getBlue(x, y), null)[0] * 360);
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
}