package com.swap;

import java.io.FileInputStream;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;
import com.swap.TextureManager.TextureSeries;

public class Main extends HvlTemplateInteg2DBasic {
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		super(60, 1280, 720, "Swap Shop MiniLD#59", 20, HvlDisplayMode.DEFAULT);
	}
	
	@Override
	public void initialize() {
		SpriteSheetUtil.downloadSpritesheet();
		TextureManager.initialize();
		MenuManager.initialize(this);
	}

	@Override
	public void update(long delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, TextureManager.getTexture(TextureSeries.MISC, 0), new Color(0f, 1f, 1f));
		MenuManager.draw(delta);
	}

}
