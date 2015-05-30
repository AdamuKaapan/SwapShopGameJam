package com.swap;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.TextureManager.TextureSeries;

public class Game {	
	private static int hue = 0;
	private static long timeBetweenSwitch = 250;
	private static long timer = 0;
	
	public static void initialize(){
		
	}
	
	private static Player player;
	
	public static void start(){
		player = new Player();
	}
	
	public static void update(long delta){
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 16; y++){
				HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), SpriteSheetUtil.getSpriteSheetPart(0, 0).getColor(x, y));
			}
		}
		
		if (hue < 360)
		{
			timer += delta;
			
			if (timer >= timeBetweenSwitch)
			{
				hue++;
				timer = 0;
			}
		}
		
		int tileX = Math.round((player.getX() - 400) / 32);
		int tileY = Math.round((player.getY() - 120) / 32);
		
		player.incDamage((double) SpriteSheetUtil.getSpriteSheetPart(0, 0).getDamage(hue, tileX, tileY) * Player.maxDamagePerSecond * ((double) delta / 1000));
				
		player.update(delta);
	}
	
	public static Color getBackground()
	{
		int rgb = java.awt.Color.HSBtoRGB((float) hue / 360, 1, 1);
		int r = (rgb >>> 16) & 0xFF;
		int g = (rgb >>> 8) & 0xFF;
		int b = (rgb >>> 0) & 0xFF;
		return new Color(r, g, b, 255);
	}
}
