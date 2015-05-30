package com.swap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.TextureManager.TextureSeries;

public class Game {	
	private static int hue = 0;
	private static long timeBetweenSwitch = 50;
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
				Color color = ColorUtils.invertColor(Game.getBackground());
				if(SpriteSheetUtil.getSpriteSheetPart(0, 0).getDamage(hue, x, y) > 0) HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, 0), new Color(color.r, color.g, color.b, (float)SpriteSheetUtil.getSpriteSheetPart(0, 0).getDamage(hue, x, y)));
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
				
		HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
		
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
