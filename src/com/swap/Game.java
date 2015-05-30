package com.swap;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.TextureManager.TextureSeries;

public class Game {

	public static void initialize(){
		
	}
	
	private static Player player;
	
	public static void start(){
		player = new Player();
	}
	
	public static void update(long delta){
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				//HvlPainter2D.hvlDrawQuad(384 + (x*64), 104 + (y*64), 64, 64, TextureManager.getTexture(TextureSeries.MISC, 0), SpriteSheetUtil.getSpriteSheetPart(x, y).get);
			}
		}
		player.update(delta);
	}
	
}
