package com.swap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.KeybindManager.ActionType;
import com.swap.TextureManager.TextureSeries;

public class Player {

	public static final double deathDamage = 25;
	public static final double maxDamagePerSecond = 10;
	
	private float x, y, xs, ys;
	private double damage;
	
	public Player(){
		x = Display.getWidth()/2;//TODO something other than this
		y = Display.getHeight()/2;
	}
	
	public void update(long delta){
		HvlPainter2D.hvlDrawQuad(x - 16, y - 16, 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), new Color(0f, 0f, 1f));
		x = Math.max(384, Math.min(896, x + (KeybindManager.getActionValue(ActionType.MOVEHORIZONTAL)*delta/4)));
		y = Math.max(104, Math.min(616, y - (KeybindManager.getActionValue(ActionType.MOVEVERTICAL)*delta/4)));
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
	
	public double getDamage(double d)
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
