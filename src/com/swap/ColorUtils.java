package com.swap;

import org.newdawn.slick.Color;


public class ColorUtils {
	
	public double getHue(double ratio) {
		return ratio * 360;
	}
	
	public static double getHueFromRGB(int r, int g, int b)
	{
		return java.awt.Color.RGBtoHSB(r, g, b, null)[0];
	}
	
	public static Color invertColor(Color color)
	{
		return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), color.a);
	}
}
