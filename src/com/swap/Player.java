package com.swap;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.KeybindManager.ActionType;
import com.swap.TextureManager.TextureSeries;

public class Player {

	public static final float friction = 4f, acceleration = 10f, speed = 2.3f, bounce = 0.9f;
	public static final float playerSize = 24;
	
	public static final double deathDamage = 7.5;
	public static final double maxDamagePerSecond = 34;
	
	private float x, y, xs, ys;
	private double damage;
	
	public Player(){
		x = Display.getWidth()/2;//TODO something other than this
		y = Display.getHeight()/2;
	}
	
	public void update(long delta){
		HvlPainter2D.hvlDrawQuad(x - (playerSize/2), y - (playerSize/2), playerSize, playerSize, TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(Game.getBackground()));
		if(KeybindManager.getActionValue(ActionType.MOVEHORIZONTAL) != 0) xs = HvlMath.stepTowards(xs, acceleration*(float)((double)delta/1000), KeybindManager.getActionValue(ActionType.MOVEHORIZONTAL) * speed);
		if(KeybindManager.getActionValue(ActionType.MOVEVERTICAL) != 0) ys = HvlMath.stepTowards(ys, acceleration*(float)((double)delta/1000), -KeybindManager.getActionValue(ActionType.MOVEVERTICAL) * speed);
		xs *= (x + xs > 896 - (playerSize/2) ? -bounce : 1) * (x + xs < 384 + (playerSize/2) ? -bounce : 1);
		ys *= (y + ys > 616 - (playerSize/2) ? -bounce : 1) * (y + ys < 104 + (playerSize/2)? -bounce : 1);
		x = Math.max(384 + (playerSize/2), Math.min(896 - (playerSize/2), x + (float)(xs*((double)delta/1000)*55.555f)));
		y = Math.max(104 + (playerSize/2), Math.min(616 - (playerSize/2), y + (float)(ys*((double)delta/1000)*55.555f)));
		xs = HvlMath.stepTowards(xs, friction*(float)((double)delta/1000), 0);
		ys = HvlMath.stepTowards(ys, friction*(float)((double)delta/1000), 0);
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
