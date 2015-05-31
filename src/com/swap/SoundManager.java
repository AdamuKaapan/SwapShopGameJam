package com.swap;

import org.newdawn.slick.openal.Audio;

import com.osreboot.ridhvl.loader.HvlSoundLoader;

public class SoundManager {

	private static HvlSoundLoader songSoundLoader = new HvlSoundLoader(5);
	private static HvlSoundLoader effectSoundLoader = new HvlSoundLoader(20);
	
	public enum SoundSeries{
		SONG, EFFECT
	}
	
	public static void initialize(){
		songSoundLoader.loadResource("Swapsong");
		
		effectSoundLoader.loadResource("losing sound");
		effectSoundLoader.loadResource("winning sound");
	}
	
	public static Audio getSound(SoundSeries seriesArg, int indexArg){
		switch(seriesArg){
		case SONG: return songSoundLoader.getResource(indexArg);
		case EFFECT: return effectSoundLoader.getResource(indexArg);
		default: return null;
		}
	}
	
}
