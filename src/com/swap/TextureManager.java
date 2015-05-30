package com.swap;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.loader.HvlTextureLoader;

public class TextureManager {
	
	private static HvlTextureLoader miscTextureLoader = new HvlTextureLoader(20);
	
	public enum TextureSeries{
		MISC
	}
	
	public static void initialize(){
		miscTextureLoader.loadResource("White");
		miscTextureLoader.loadResource("Font");
	}
	
	public static Texture getTexture(TextureSeries seriesArg, int indexArg){
		switch(seriesArg){
		case MISC: return miscTextureLoader.getResource(indexArg);
		default: return null;
		}
	}
	
}
