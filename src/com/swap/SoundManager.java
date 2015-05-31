package com.swap;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import com.osreboot.ridhvl.loader.HvlSoundLoader;

public class SoundManager {

	private static HvlSoundLoader songSoundLoader = new HvlSoundLoader(5);
	private static HvlSoundLoader effectSoundLoader = new HvlSoundLoader(20);
	private static HvlSoundLoader winSoundLoader = new HvlSoundLoader(20);
	private static HvlSoundLoader loseSoundLoader = new HvlSoundLoader(20);
	
	public static Map<String, Integer> winSoundMap, loseSoundMap;
	
	private static boolean muted = false;
	
	public enum SoundSeries{
		SONG, EFFECT, WIN, LOSE
	}
	
	public static void initialize(){
		winSoundMap = new HashMap<String, Integer>();
		loseSoundMap = new HashMap<String, Integer>();
		
		songSoundLoader.loadResource("Swapsong");
		
		effectSoundLoader.loadResource("losing sound");
		effectSoundLoader.loadResource("winning sound");
		
		for (int i = 0; i < Game.winTexts.length; i++)
		{
			winSoundLoader.loadResource(Game.winTexts[i].replaceAll("\\?", "").replaceAll("\\.", ""));
			winSoundMap.put(Game.winTexts[i], i);
		}
		
		for (int i = 0; i < Game.loseTexts.length; i++)
		{
			loseSoundLoader.loadResource(Game.loseTexts[i].replaceAll("\\?", "").replaceAll("\\.", ""));
			loseSoundMap.put(Game.loseTexts[i], i);
		}
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
		case WIN: return winSoundLoader.getResource(indexArg);
		case LOSE: return loseSoundLoader.getResource(indexArg);
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
