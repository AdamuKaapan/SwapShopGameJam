package com.swap;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.loader.HvlTextureLoader;

public class TextureManager {
	
	private static HvlTextureLoader miscTextureLoader = new HvlTextureLoader(20);
	private static HvlTextureLoader particleTextureLoader = new HvlTextureLoader(20);
	private static HvlTextureLoader gameTextureLoader = new HvlTextureLoader(20);
	
	public enum TextureSeries{
		MISC, PARTICLE, GAME
	}
	
	public static void initialize(){
		miscTextureLoader.loadResource("White");
		miscTextureLoader.loadResource("Font");
		
		particleTextureLoader.loadResource("Damage");
		
		particleTextureLoader.loadResource("Spectrum");
		particleTextureLoader.loadResource("Pointer");
	}
	
	public static Texture getTexture(TextureSeries seriesArg, int indexArg){
		switch(seriesArg){
		case MISC: return miscTextureLoader.getResource(indexArg);
		case PARTICLE: return particleTextureLoader.getResource(indexArg);
		case GAME: return gameTextureLoader.getResource(indexArg);
		default: return null;
		}
	}
	
}
