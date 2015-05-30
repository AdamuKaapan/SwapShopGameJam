package com.swap;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;
import com.osreboot.ridhvl.painter.painter2d.*;
import com.swap.TextureManager.TextureSeries;

public class Main extends HvlTemplateInteg2DBasic{

	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		super(60, 1280, 720, "Swap Shop MiniLD#59", 20, HvlDisplayMode.DEFAULT);
	}

	@Override
	public void initialize() {
		TextureManager.initialize();
	}

	@Override
	public void update(long delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 1f));
	}

}
