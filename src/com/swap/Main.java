package com.swap;

import org.newdawn.slick.openal.SoundStore;

import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.loader.HvlSoundLoader;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;

public class Main extends HvlTemplateInteg2DBasic {
	
	public static void main(String[] args) {
		new Main();
	}
	
	public static HvlSoundLoader soundLoader;
	
	public Main(){
		super(60, 1280, 720, "Swap Shop MiniLD#59", 20, HvlDisplayMode.DEFAULT);
	}
	
	@Override
	public void initialize() {
		soundLoader = new HvlSoundLoader(5);
		soundLoader.loadResource("Swapsong");
		SpriteSheetUtil.downloadSpritesheet();
		TextureManager.initialize();
		MenuManager.initialize(this);
		Game.initialize();
		KeybindManager.initialize();
		soundLoader.getResource(0).playAsMusic(1, 1, true);
	}

	@Override
	public void update(long delta) {
		MenuManager.draw(delta);
		SoundStore.get().poll(0);
	}

}
