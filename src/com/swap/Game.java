package com.swap;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.SoundManager.SoundSeries;
import com.swap.TextureManager.TextureSeries;

public class Game {
	private static enum Mode
	{
		prepreview, preview, pause, play, death, win, uberwin
	}
	private static int hue = 1;
	private static long playTime = 25, previewTime = 17, pauseTime = 5000;
	private static long timer = 0;
	private static Mode mode;
	
	public static final String[] winTexts = new String[] { "nice!", "duuuude!", "epic!", "bruh!", "whoa!", "woah!", "whuuuut?", "how even?", "fantastic!" };
	public static final String[] loseTexts = new String[] { "siiiiigh...", "oops", "derp", "try again", "denied", "nooooo..." ,"aww..." };
	public static final String[] preTexts = new String[] { "pay attention...", "watch carefully...", "look at this...", "watch and learn" };
	public static final String[] readyTexts = new String[] { "ready?", "get ready...", "prepare yourself!", "runners to your marks" };
	
	private static int currentWinText;
	private static int currentLoseText;
	private static int currentPreText;
	private static int currentReadyText;
	
	private static int currentLevel = 0;
	private static int[] levelSequence;
	
	public static void initialize(){
		
	}
	
	private static Player player;
	
	public static void start(){
		Random rand = new Random();
		currentPreText = rand.nextInt(preTexts.length);
		player = new Player();
		hue = 1;
		timer = 0;
		mode = Mode.prepreview;
		fillLevels();
		currentLevel = 0;
		
		if (levelSequence.length == 0)
		{
			HvlMenu.setCurrent(MenuManager.menuPreview);
		}
	}
	
	public static void update(long delta){		
		if (mode != Mode.uberwin)
		{
			for(int x = 0; x < 16; x++){
				for(int y = 0; y < 16; y++){
					HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getColor(x, y));
					Color color = ColorUtils.invertColor(Game.getBackground());
					//if(SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y) > 0) HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, 0), new Color(color.r, color.g, color.b, (float)SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y)));
					if(mode == Mode.preview || mode == Mode.play) if(SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y) > 0) HvlPainter2D.hvlDrawQuad(384 + (x*32), 104 + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, mode == Mode.preview ? 1 : 0), new Color(color.r, color.g, color.b, (float)SpriteSheetUtil.getSpriteSheetPart(levelSequence[currentLevel]).getDamage(hue, x, y)));
				}
			}
		}
		else
		{
			HvlPainter2D.hvlDrawQuad(Display.getWidth() / 2 - 128, Display.getHeight() / 2 - 128, 256, 256, 0, 0, 1, 1, SpriteSheetUtil.getSpriteSheet(), Color.white);
		}
		
		switch (mode)
		{
		case prepreview:
			String pt = preTexts[currentPreText];
			MenuManager.drawCrazyWord(delta, pt, (Display.getWidth() / 2) - (112 * pt.length() * 0.25f * 0.5f) + 16, (Display.getHeight()) - (144 * 0.25f) - 32, 0.25f, Color.white);
			
			timer += delta;
			
			if (timer >= 2500)
			{
				mode = Mode.preview;
				hue = 1;
				timer = 0;
			}
			
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			break;
		case preview:
			String pt2 = preTexts[currentPreText];
			MenuManager.drawCrazyWord(delta, pt2, (Display.getWidth() / 2) - (112 * pt2.length() * 0.25f * 0.5f) + 16,(Display.getHeight()) - (144 * 0.25f) - 32, 0.25f, Color.white);			
			if (hue < 360)
			{
				timer += delta;
				
				if (timer >= previewTime)
				{
					hue++;
					timer = 0;
				}
				
				HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
			}
			else
			{
				mode = Mode.pause;
				Random rand = new Random();
				currentReadyText = rand.nextInt(readyTexts.length);
				hue = 1;
				timer = 0;
			}
			break;
		case pause:
			String pauset = readyTexts[currentReadyText];
			MenuManager.drawCrazyWord(delta, pauset, (Display.getWidth() / 2) - (112 * pauset.length() * 0.25f * 0.5f) + 16, (Display.getHeight()) - (144 * 0.25f) - 32, 0.25f, Color.white);
			
			timer += delta;
			if (timer >= pauseTime)
			{
				mode = Mode.play;
				timer = 0;
			}
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
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
			
				HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
				
				HvlPainter2D.hvlDrawQuad(Display.getWidth()/64*49, Display.getHeight()/16*15 - (((float)hue/360)*(Display.getHeight()/4*3)) - 16, 32, 32, TextureManager.getTexture(TextureSeries.GAME, 1));
				
				if (player.getDamage() >= Player.deathDamage)
				{
					mode = Mode.death;
					Random rand = new Random();
					currentLoseText = rand.nextInt(loseTexts.length);
					//SoundManager.getSound(SoundSeries.LOSE, SoundManager.loseSoundMap.get(loseTexts[currentLoseText])).playAsSoundEffect(1.0f, 1.0f, false);
				}
			}
			else
			{
				Random rand = new Random();
				mode = Mode.win;
				currentWinText = rand.nextInt(winTexts.length);
				//SoundManager.getSound(SoundSeries.WIN, SoundManager.winSoundMap.get(winTexts[currentWinText])).playAsSoundEffect(1.0f, 1.0f, false);
			}
			break;
		case death:
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			String lt = loseTexts[currentLoseText];
			MenuManager.drawCrazyWord(delta, lt, (Display.getWidth() / 2) - (112 * lt.length() * 0.75f * 0.5f), (Display.getHeight() / 2) - (144 * 0.75f * 0.5f), 0.75f, Color.white);
			
			timer += delta;
			
			if (timer >= 5000)
			{
				Random rand = new Random();
				currentPreText = rand.nextInt(preTexts.length);				
				mode = Mode.prepreview;
				timer = 0;
				hue = 1;
				currentLevel = Math.max(currentLevel - 1, 0);
				player.setDamage(0);
			}
			break;
		case win:
			HvlPainter2D.hvlDrawQuad(0f, 0f, (1 - (float)(player.getDamage()/Player.deathDamage))*Display.getWidth(), Display.getHeight()/16f, TextureManager.getTexture(TextureSeries.MISC, 0));
			
			String wt = winTexts[currentWinText];
			MenuManager.drawCrazyWord(delta, wt, (Display.getWidth() / 2) - (112 * wt.length() * 0.75f * 0.5f), (Display.getHeight() / 2) - (144 * 0.75f * 0.5f), 0.75f, Color.white);
			
			timer += delta;
			
			if (timer >= 5000)
			{
				Random rand = new Random();
				currentPreText = rand.nextInt(preTexts.length);
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
			HvlMenu.setCurrent(MenuManager.menuWin);
			MenuManager.levels = levelSequence.length;
			break;
		}
		
		HvlPainter2D.hvlDrawQuad(Display.getWidth()/4*3, Display.getHeight()/16*3, Display.getWidth()/16, Display.getHeight()/4*3, TextureManager.getTexture(TextureSeries.GAME, 0));
		
		player.update(delta);


		String text = (currentLevel + 1) + " of " + levelSequence.length;
		
		HvlPainter2D.hvlRotate(128, 128, -90);
		MenuManager.drawCrazyWord(delta, text, -228, 32, 0.25f, Color.white);
		HvlPainter2D.hvlResetRotation();
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
			
			if (SpriteSheetUtil.getSpriteSheetPart(lev).getClumpDifficulty() >= SpriteSheetUtil.threshold || SpriteSheetUtil.getSpriteSheetPart(lev).getRangeDifficulty() >= SpriteSheetUtil.threshold) continue;
			
			list.add(lev);
		}
		
		levelSequence = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++)
		{
			levelSequence[i] = list.get(i);
		}
	}
}
