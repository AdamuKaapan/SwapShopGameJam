package com.swap;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;

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
		Game.initialize();
		KeybindManager.initialize();
	}

	@Override
	public void update(long delta) {
		MenuManager.draw(delta);
	}

}
