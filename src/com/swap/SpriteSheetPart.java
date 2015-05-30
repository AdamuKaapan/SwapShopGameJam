package com.swap;

import org.newdawn.slick.opengl.Texture;

public class SpriteSheetPart
{
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
}