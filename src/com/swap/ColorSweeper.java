package com.swap;

import java.awt.Color;

public class ColorSweeper {
	
	public void getHue(double ratio) {
		
	}
	
	public static double getHueFromRGB(int r, int g, int b)
	{
		return Color.RGBtoHSB(r, g, b, null)[0];
	}
}
