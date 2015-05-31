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
		//System.out.println(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
		return new Color(1f - color.a, 1f - color.g, 1f - color.b, color.a);
	}
}
