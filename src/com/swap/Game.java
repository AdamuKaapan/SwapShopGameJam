package com.swap;

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
				//HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), SpriteSheetUtil.getSpriteSheetPart(0));
			}
		}
		player.update(delta);
	}
	
}
