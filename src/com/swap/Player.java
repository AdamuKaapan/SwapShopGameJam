package com.swap;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.KeybindManager.ActionType;
import com.swap.TextureManager.TextureSeries;

public class Player {

	public static final float friction = 0.9999f, speed = 0.013f, bounce = 0.8f;
	public static final float playerSize = 24;
	
	public static final double deathDamage = 7.5;
	public static final double maxDamagePerSecond = 20;
	
	private float x, y, xs, ys;
	private double damage;
	
	public Player(){
		x = Display.getWidth()/2;//TODO something other than this
		y = Display.getHeight()/2;
	}
	
	public void update(long delta){
		HvlPainter2D.hvlDrawQuad(x - (playerSize/2), y - (playerSize/2), playerSize, playerSize, TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(Game.getBackground()));
		xs += (KeybindManager.getActionValue(ActionType.MOVEHORIZONTAL)*delta*speed);
		ys += (-KeybindManager.getActionValue(ActionType.MOVEVERTICAL)*delta*speed);
		xs *= (x + xs > 896 - (playerSize/2) ? -bounce : 1) * (x + xs < 384 + (playerSize/2) ? -bounce : 1);
		ys *= (y + ys > 616 - (playerSize/2) ? -bounce : 1) * (y + ys < 104 + (playerSize/2)? -bounce : 1);
		x = Math.max(384 + (playerSize/2), Math.min(896 - (playerSize/2), x + xs));
		y = Math.max(104 + (playerSize/2), Math.min(616 - (playerSize/2), y + ys));
		xs *= friction*((float)delta/18);
		ys *= friction*((float)delta/18);
	}
	
	public void setX(float xArg){
		x = xArg;
	}
	
	public void setY(float yArg){
		y = yArg;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setDamage(double d)
	{
		damage = d;
	}
	
	public void incDamage(double d)
	{
		damage += d;
	}
	
	public double getDamage()
	{
		return damage;
	}
	
	public void setXSpeed(float xsArg){
		xs = xsArg;
	}
	
	public void setYSpeed(float ysArg){
		ys = ysArg;
	}
	
	public float getXSpeed(){
		return xs;
	}
	
	public float getYSpeed(){
		return ys;
	}
}
