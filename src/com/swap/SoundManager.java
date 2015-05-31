package com.swap;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import com.osreboot.ridhvl.loader.HvlSoundLoader;

public class SoundManager {

	private static HvlSoundLoader songSoundLoader = new HvlSoundLoader(5);
	private static HvlSoundLoader effectSoundLoader = new HvlSoundLoader(20);
	
	private static boolean muted = false;
	
	public enum SoundSeries{
		SONG, EFFECT
	}
	
	public static void initialize(){
		songSoundLoader.loadResource("Swapsong");
		
		effectSoundLoader.loadResource("losing sound");
		effectSoundLoader.loadResource("winning sound");
	}
	
	public static void update(){
		if(muted){
			if(getSound(SoundSeries.SONG, 0).isPlaying()) getSound(SoundSeries.SONG, 0).stop();
		}else{
			if(!getSound(SoundSeries.SONG, 0).isPlaying()) getSound(SoundSeries.SONG, 0).playAsMusic(1, 1, false);
		}
		SoundStore.get().poll(0);
	}
	
	public static Audio getSound(SoundSeries seriesArg, int indexArg){
		switch(seriesArg){
		case SONG: return songSoundLoader.getResource(indexArg);
		case EFFECT: return effectSoundLoader.getResource(indexArg);
		default: return null;
		}
	}
	
	public static void setMuted(boolean mutedArg){
		muted = mutedArg;
	}
	
	public static boolean getMuted(){
		return muted;
	}
	
}
