package com.swap;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.TextureManager.TextureSeries;

public class Game {
	private static enum Mode
	{
		prepreview, preview, pause, play, death, win, uberwin
	}
	private static int hue = 1;
	private static long playTime = 35, previewTime = 10, pauseTime = 5000;
	private static long timer = 0;
	private static Mode mode;
	
	private static String[] winTexts = new String[] { "nice!", "duuuude!", "epic!", "bruh!", "whoa!", "woah!", "whuuuut?", "how even?" };
	private static String[] loseTexts = new String[] { "siiiiigh...", "oops", "derp", "try again", "denied", "nooooo..." ,"aww..." };
	
	private static int currentWinText;
	private static int currentLoseText;
	
	private static int currentLevel = 0;
	private static int[] levelSequence;
	
	public static void initialize(){
		
	}
	
	private static Player player;
	
	public static void start(){
		player = new Player();
		hue = 1;
		timer = 0;
		mode = Mode.prepreview;
		currentLevel = 0;
		fillLevels();
	}
	
	public static void update(long delta){
		for(int x = 0; x < 16; x++){
			for(int y = 0; y < 16; y++){
				HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getColor(x, y));
				Color color = ColorUtils.invertColor(Game.getBackground());
				//if(SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y) > 0) HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, 0), new Color(color.r, color.g, color.b, (float)SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y)));
				if(mode == Mode.preview || mode == Mode.play) if(SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y) > 0) HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, mode == Mode.preview ? 1 : 0), new Color(color.r, color.g, color.b, (float)SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y)));
			}
		}
		
		switch (mode)
		{
		case prepreview:
			timer += delta;
			
			if (timer >= 2500)
			{
				mode = Mode.preview;
				hue = 1;
				timer = 0;
			}
			
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			break;
		case preview:
			if (hue < 360)
			{
				timer += delta;
				
				if (timer >= previewTime)
				{
					hue++;
					timer = 0;
				}
				
				HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
			}
			else
			{
				mode = Mode.pause;
				hue = 1;
				timer = 0;
			}
			break;
		case pause:
			timer += delta;
			if (timer >= pauseTime)
			{
				mode = Mode.play;
				timer = 0;
			}
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			break;
		case play:
			if (hue < 360)
			{
				timer += delta;
				
				if (timer >= playTime)
				{
					hue++;
					timer = 0;
				}
				
				int tileX = Math.round((player.getX() - 400) / 32);
				int tileY = Math.round((player.getY() - 120) / 32);
				player.incDamage((double) SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, tileX, tileY) * Player.maxDamagePerSecond * ((double) delta / 1000));
			
				HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
				
				HvlPainter2D.hvlDrawQuad(Display.getWidth()/64*49, Display.getHeight()/16*15 - (((float)hue/360)*(Display.getHeight()/4*3)) - 16, 32, 32, TextureManager.getTexture(TextureSeries.GAME, 1));
				
				if (player.getDamage() >= Player.deathDamage)
				{
					mode = Mode.death;
					Random rand = new Random();
					currentLoseText = rand.nextInt(loseTexts.length);
				}
			}
			else
			{
				Random rand = new Random();
				mode = Mode.win;
				currentWinText = rand.nextInt(winTexts.length);
			}
			break;
		case death:
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			String lt = loseTexts[currentLoseText];
			MenuManager.drawCrazyWord(delta, lt, (Display.getWidth() / 2) - (112 * lt.length() * 0.75f * 0.5f), (Display.getHeight() / 2) - (144 * 0.75f * 0.5f), 0.75f, Color.white);
			
			timer += delta;
			
			if (timer >= 5000)
			{
				mode = Mode.prepreview;
				timer = 0;
				hue = 1;
				currentLevel = Math.max(currentLevel - 1, 0);
				player.setDamage(0);
			}
			break;
		case win:
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/8f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			String wt = winTexts[currentWinText];
			MenuManager.drawCrazyWord(delta, wt, (Display.getWidth() / 2) - (112 * wt.length() * 0.75f * 0.5f), (Display.getHeight() / 2) - (144 * 0.75f * 0.5f), 0.75f, Color.white);
			
			timer += delta;
			
			if (timer >= 5000)
			{
				mode = Mode.prepreview;
				timer = 0;
				hue = 1;
				currentLevel++;
				player.setDamage(0);
				if (currentLevel >= levelSequence.length)
					mode = Mode.uberwin;
			}
			
			break;
		case uberwin:
			
			break;
		}
		
		HvlPainter2D.hvlDrawQuad(Display.getWidth()/4*3, Display.getHeight()/16*3, Display.getWidth()/16, Display.getHeight()/4*3, TextureManager.getTexture(TextureSeries.GAME, 0));
		
		player.update(delta);
	}
	
	public static Color getBackground()
	{
		int rgb = java.awt.Color.HSBtoRGB((float) hue / 360, 1, 1);
		int r = (rgb >>> 16) & 0xFF;
		int g = (rgb >>> 8) & 0xFF;
		int b = (rgb >>> 0) & 0xFF;
		return new Color(r, g, b, 255);
	}
	
	public static void fillLevels()
	{
		Random rand = new Random();
		
		boolean[] chosen = new boolean[64];

		List<Integer> list = new LinkedList<Integer>();
		
		for (int i = 0; i < 64; i++)
		{
			int lev;
			do
			{
				lev = rand.nextInt(64);
			} while (chosen[lev]);
			
			chosen[lev] = true;
			
			if (SpriteSheetUtil.getSpriteSheetPart(lev).getDifficulty() > 0.95) continue;
			
			list.add(lev);
		}
		
		levelSequence = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++)
		{
			levelSequence[i] = list.get(i);
		}
	}
}
