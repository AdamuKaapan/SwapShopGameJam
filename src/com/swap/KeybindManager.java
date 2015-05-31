package com.swap;

import org.lwjgl.input.Keyboard;

public class KeybindManager {

	public enum ActionType{
		MOVEVERTICAL, MOVEHORIZONTAL,  PAUSE
	}
	
	public static void initialize(){
		
	}
	
	public static void update(){
		
	}
	
	public static float getActionValue(ActionType actionArg){
		switch(actionArg){
		case PAUSE: return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ? 1f : 0f;
		case MOVEVERTICAL: return (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? 1f : 0f) + (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) ? -1f : 0f);
		case MOVEHORIZONTAL: return (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ? 1f : 0f) + (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? -1f : 0f);
		default: return 0;
		}
	}
	
}
