package com.swap;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;
import com.swap.TextureManager.TextureSeries;

public class Main extends HvlTemplateInteg2DBasic{

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		super(60, 1280, 720, "Swap Shop MiniLD#59", 20, HvlDisplayMode.DEFAULT);
	}

	public static HvlFontPainter2D fontPainter;
	
	@Override
	public void initialize() {
		TextureManager.initialize();
		
		fontPainter = new HvlFontPainter2D(TextureManager.getTexture(TextureSeries.MISC, 1), HvlFontLayout.DEFAULT, 2048, 2048, 112, 144, 18);
	}

	@Override
	public void update(long delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, TextureManager.getTexture(TextureSeries.MISC, 0), new Color(0f, 1f, 1f));
		fontPainter.hvlDrawWord("test yay it works!!!...", 100, 100, 0.25f, new Color(1f, 1f, 1f));
	}

}
