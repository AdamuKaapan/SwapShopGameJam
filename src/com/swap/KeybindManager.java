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
		case MOVEVERTICAL: return (Keyboard.isKeyDown(Keyboard.KEY_W) ? 1f : 0f) + (Keyboard.isKeyDown(Keyboard.KEY_S) ? -1f : 0f);
		case MOVEHORIZONTAL: return (Keyboard.isKeyDown(Keyboard.KEY_D) ? 1f : 0f) + (Keyboard.isKeyDown(Keyboard.KEY_A) ? -1f : 0f);
		default: return 0;
		}
	}
	
}
