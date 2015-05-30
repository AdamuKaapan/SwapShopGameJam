package com.swap;

import java.awt.Color;

public class ColorUtils {
	
	public double getHue(double ratio) {
		return ratio * 360;
	}
	
	public static double getHueFromRGB(int r, int g, int b)
	{
		return Color.RGBtoHSB(r, g, b, null)[0];
	}
}
