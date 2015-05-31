package com.swap;

import org.newdawn.slick.openal.SoundStore;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.menu.HvlButton;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;
import com.swap.SoundManager.SoundSeries;

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
		SoundManager.initialize();
		SoundManager.getSound(SoundSeries.SONG, 0).playAsMusic(1, 1, true);
	}

	@Override
	public void update(long delta) {
		MenuManager.draw(delta);
		SoundStore.get().poll(0);
		HvlButton.updateButtons();
	}

}
