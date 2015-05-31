package com.swap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.menu.HvlButton;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.KeybindManager.ActionType;
import com.swap.TextureManager.TextureSeries;

public class MenuManager {

	public static HvlMenu menuMain, menuCredits, menuPreview, menuGame, menuPaused, menuOptions, menuTutorial, menuWin;
	public static HvlButton buttonMainCredits, buttonMainPlay, buttonMainQuit, buttonMainOptions, buttonMainTutorial, 
	buttonCreditsMain, 
	buttonPreviewRefresh, buttonPreviewStart, buttonPreviewBack,
	buttonPausedQuit, buttonPausedResume,
	buttonOptionsMain, buttonOptionsMute, 
	buttonTutorialMain, 
	buttonWinMain;

	private static double currentDifficulty;
	
	private static HvlFontPainter2D fontPainter;

	public static void initialize(Main main){
		fontPainter = new HvlFontPainter2D(TextureManager.getTexture(TextureSeries.MISC, 1), HvlFontLayout.DEFAULT, 2048, 2048, 112, 144, 18);



		//MAIN MENU
		menuMain = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "main menu", 100, 10, 0.5f, new Color(1f, 1f, 1f));
				drawCrazyWord(delta, ".saturate", Display.getWidth()/32*3, Display.getHeight()/8*3, 1f, new Color(1f, 1f, 1f), 0.5f);
			}
		};
		buttonMainPlay = new HvlButton(main.getWidth()/8, main.getHeight()/8, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuPreview);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "play", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainPlay);
		buttonMainCredits = new HvlButton(main.getWidth()/8, main.getHeight()/8*6, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuCredits);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "credits", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainCredits);
		buttonMainQuit = new HvlButton(main.getWidth()/8, main.getHeight()/8*7, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				Display.destroy();
				System.exit(0);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "quit", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainQuit);
		buttonMainOptions = new HvlButton(main.getWidth()/8, main.getHeight()/8*5, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuOptions);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "options", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainOptions);
		buttonMainTutorial = new HvlButton(main.getWidth()/8, main.getHeight()/4, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuTutorial);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "tutorial", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainTutorial);



		//CREDITS MENU
		menuCredits = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "credits", 100, 10, 0.5f, Color.white);
				drawCrazyWord(delta, "game idea   calvin weaver", 100, 125, 0.25f, Color.white, 0.15f);
				drawCrazyWord(delta, "programming   calvin weaver", 100, 200, 0.25f, Color.white, 0.15f);
				drawCrazyWord(delta, "and adam krpan", 575, 250, 0.25f, Color.white, 0.3f);
				drawCrazyWord(delta, "art   harris nakajima", 100, 325, 0.25f, Color.white, 0.15f);
				drawCrazyWord(delta, "and calvin weaver", 575, 375, 0.25f, Color.white, 0.15f);
				drawCrazyWord(delta, "music   harris nakajima", 100, 450, 0.25f, Color.white, 0.15f);
				drawCrazyWord(delta, "made in one day for mini ld 59 (swap shop)", 100, 525, 0.25f, Color.white, 0.15f);
			}
		};
		buttonCreditsMain = new HvlButton(main.getWidth()/8, 600, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "back", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuCredits.addButton(buttonCreditsMain);



		//OPTIONS MENU
		menuOptions = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "options", 100, 10, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonOptionsMain = new HvlButton(main.getWidth()/8, main.getHeight()/4, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "back", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuOptions.addButton(buttonOptionsMain);
		buttonOptionsMute = new HvlButton(main.getWidth()/8, main.getHeight()/8, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				SoundManager.setMuted(!SoundManager.getMuted());
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, SoundManager.getMuted() ? "unmute audio" : "mute audio", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuOptions.addButton(buttonOptionsMute);
		
		
		
		//TUTORIAL MENU
		menuTutorial = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "how to play", 100, 10, 0.5f, new Color(1f, 1f, 1f));
				drawCrazyWord(delta, "wasd to move", Display.getWidth()/16*6, Display.getHeight()/16*3, 0.25f, new Color(1f, 1f, 1f), 0.15f);
				drawCrazyWord(delta, "watch the level preview", Display.getWidth()/8, Display.getHeight()/32*11, 0.25f, new Color(1f, 1f, 1f), 0.15f);
				drawCrazyWord(delta, "then avoid the traps!", Display.getWidth()/8, Display.getHeight()/32*15, 0.25f, new Color(1f, 1f, 1f), 0.15f);
				drawCrazyWord(delta, "keep an eye on the pointer", Display.getWidth()/16*6, Display.getHeight()/8*5, 0.25f, new Color(1f, 1f, 1f), 0.15f);
				drawCrazyWord(delta, "for the current color!", Display.getWidth()/32*15, Display.getHeight()/4*3, 0.25f, new Color(1f, 1f, 1f), 0.15f);
				
				HvlPainter2D.hvlDrawQuad(Display.getWidth()/4, Display.getHeight()/16*9, Display.getWidth()/16, Display.getHeight()/4, TextureManager.getTexture(TextureSeries.GAME, 0));
				HvlPainter2D.hvlDrawQuad(Display.getWidth()/64*17, (Display.getHeight()/16*11) + (float)(Math.sin((double)total/1000)*Display.getHeight()/8) - 16, 32, 32, TextureManager.getTexture(TextureSeries.GAME, 1));
				
				for(int x = 0; x < 4; x++){
					for(int y = 0; y < 4; y++){
						HvlPainter2D.hvlDrawQuad((Display.getWidth()/4*3) + (x*32), (Display.getHeight()/16*5) + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.MISC, 0), new Color((float)x/8, (float)y/8, 0.5f));
						HvlPainter2D.hvlDrawQuad((Display.getWidth()/4*3) + (x*32), (Display.getHeight()/16*5) + (y*32), 32, 32, TextureManager.getTexture(TextureSeries.PARTICLE, 0), new Color(1f - (float)x/8, 1f - (float)y/8, 0.5f, (float)(Math.sin((double)total/((x + 1)*(y + 1)*100)))));
					}
				}
				
			}
		};
		buttonTutorialMain = new HvlButton(main.getWidth()/8, main.getHeight()/8*7, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "back", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuTutorial.addButton(buttonTutorialMain);
		
		
		
		//MAP SELECT MENU
		menuPreview = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "map select", 100, 10, 0.5f, new Color(1f, 1f, 1f));
				if(SpriteSheetUtil.getSpriteSheet() == null) newSpriteSheet();
				HvlPainter2D.hvlDrawQuad(Display.getWidth()/8, Display.getHeight()/4, 128, 128, SpriteSheetUtil.getSpriteSheet());
				drawCrazyWord(delta, getDifficultyName(currentDifficulty), Display.getWidth()/8, Display.getHeight()/2, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonPreviewRefresh = new HvlButton(main.getWidth()/8, main.getHeight()/8*5, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				newSpriteSheet();
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "refresh", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPreview.addButton(buttonPreviewRefresh);
		buttonPreviewStart = new HvlButton(main.getWidth()/8, main.getHeight()/8*6, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuGame);
				Game.start();
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "start", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPreview.addButton(buttonPreviewStart);
		buttonPreviewBack = new HvlButton(main.getWidth()/8, main.getHeight()/8*7, main.getWidth()/4*3, main.getHeight()/32*2, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "back", getX(), getY() + (getYLength()/6), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPreview.addButton(buttonPreviewBack);
		
		
		
		//IN GAME
		menuGame = new HvlMenu(){
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, TextureManager.getTexture(TextureSeries.MISC, 0), Game.getBackground());
				super.draw(delta);
				Game.update(delta);
				if(KeybindManager.getActionValue(ActionType.PAUSE) == 1) HvlMenu.setCurrent(menuPaused);
			}
		};
		
		
		
		//PAUSE MENU
		menuPaused = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				drawCrazyWord(delta, "paused", 100, 10, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonPausedQuit = new HvlButton(main.getWidth()/8, main.getHeight()/8*6, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "quit", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPaused.addButton(buttonPausedQuit);
		buttonPausedResume = new HvlButton(main.getWidth()/8, main.getHeight()/8*5, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuGame);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "resume", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPaused.addButton(buttonPausedResume);
		
		// WIN SCREEN
		menuWin = new HvlMenu()
		{
			@Override
			public void draw(long delta)
			{
				super.draw(delta);
				String text = "you win!"; //112 144
				//drawCrazyWord(delta, text, (Display.getWidth() / 2) - (text.length() * 112 * 0.5f), (Display.getHeight() / 2) - (text.length() * 144 * 0.5f), 1.0f, Color.white);
				drawCrazyWord(delta, text, 200, 300, 1.0f, Color.white);
			}
		};
		buttonWinMain = new HvlButton(main.getWidth()/8, main.getHeight()/8*5, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), ColorUtils.invertColor(backgroundColor));
				drawCrazyWord(delta, "back to main menu", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f), 0.5f);
			}
		};
		menuWin.addButton(buttonWinMain);
		
		HvlMenu.setCurrent(menuMain);
	}

	public static void draw(long delta){
		if(HvlMenu.getCurrent() != menuGame){
			updateColors();
			drawFillerBackground();
		}
		HvlMenu.updateMenus(delta);
		total += delta;
	}
	
	private static long total = 0;
	private static Color backgroundColor;
	
	public static void updateColors(){
		int rgb = java.awt.Color.HSBtoRGB((float) (total/10)%360 / 360, 1, 1);
		int r = (rgb >>> 16) & 0xFF;
		int g = (rgb >>> 8) & 0xFF;
		int b = (rgb >>> 0) & 0xFF;
		backgroundColor = new Color(r, g, b, 255);
	}
	
	public static void drawFillerBackground(){
		HvlPainter2D.hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), TextureManager.getTexture(TextureSeries.MISC, 0), backgroundColor);
	}
	
	public static void drawCrazyWord(long delta, String word, float x, float y, float scale, Color color){
		float modifier = (float)(Math.sin((double)total/250)*7);
		float modifier2 = (float)(Math.cos((double)total/300)/30);
		HvlPainter2D.hvlRotate(x + (112 /2*word.length()*(scale + modifier2)), y + (114/4*word.length()*(scale + modifier2)), modifier);
		fontPainter.hvlDrawWord(word, x, y, scale + modifier2, ColorUtils.invertColor(color));
		HvlPainter2D.hvlResetRotation();
		fontPainter.hvlDrawWord(word, x, y, scale, color);
	}
	
	public static void drawCrazyWord(long delta, String word, float x, float y, float scale, Color color, float amplitude){
		float modifier = (float)(Math.sin((double)total/250)*7);
		float modifier2 = (float)(Math.cos((double)total/300)/30)*amplitude;
		HvlPainter2D.hvlRotate(x + (112 /2*word.length()*(scale + modifier2)), y + (114/4*word.length()*(scale + modifier2)), modifier*amplitude);
		fontPainter.hvlDrawWord(word, x, y, scale + modifier2, ColorUtils.invertColor(color));
		HvlPainter2D.hvlResetRotation();
		fontPainter.hvlDrawWord(word, x, y, scale, color);
	}
	
	public static void newSpriteSheet(){
		while(!SpriteSheetUtil.downloadSpritesheet()){}
		currentDifficulty = 0;
		for(int i = 0; i < 64; i++) currentDifficulty += SpriteSheetUtil.getSpriteSheetPart(i).getDifficulty();
		currentDifficulty /= 64;
	}
	
	public static String getDifficultyName(double difficultyArg){
		if(difficultyArg < 0.5f) return "super easy";
		else if(difficultyArg < 0.55f) return "very easy";
		else if(difficultyArg < 0.6f) return "easy";
		else if(difficultyArg < 0.65f) return "mild";
		else if(difficultyArg < 0.7f) return "medium";
		else if(difficultyArg < 0.75f) return "slightly hard";
		else if(difficultyArg < 0.8f) return "hard";
		else if(difficultyArg < 0.85f) return "very hard";
		else if(difficultyArg < 0.9f) return "super hard";
		else return "insane";
	}

}
