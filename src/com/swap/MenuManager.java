package com.swap;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.menu.HvlButton;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.swap.TextureManager.TextureSeries;

public class MenuManager {

	public static HvlMenu menuMain, menuCredits, menuPreview;
	public static HvlButton buttonMainCredits, buttonMainPlay, 
	buttonCreditsMain, 
	buttonPreviewRefresh, buttonPreviewStart, buttonPreviewBack;

	private static HvlFontPainter2D fontPainter;

	public static void initialize(Main main){
		fontPainter = new HvlFontPainter2D(TextureManager.getTexture(TextureSeries.MISC, 1), HvlFontLayout.DEFAULT, 2048, 2048, 112, 144, 18);



		menuMain = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				fontPainter.hvlDrawWord("main menu", 100, 10, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonMainPlay = new HvlButton(main.getWidth()/8, main.getHeight()/8, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuPreview);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("play", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainPlay);
		buttonMainCredits = new HvlButton(main.getWidth()/8, main.getHeight()/4, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuCredits);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("credits", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuMain.addButton(buttonMainCredits);



		menuCredits = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				fontPainter.hvlDrawWord("credits", 100, 10, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonCreditsMain = new HvlButton(main.getWidth()/8, main.getHeight()/8, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				HvlMenu.setCurrent(menuMain);
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("back", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuCredits.addButton(buttonCreditsMain);



		menuPreview = new HvlMenu(){
			@Override
			public void draw(long delta){
				super.draw(delta);
				fontPainter.hvlDrawWord("map select", 100, 10, 0.5f, new Color(1f, 1f, 1f));
			}
		};
		buttonPreviewRefresh = new HvlButton(main.getWidth()/8, main.getHeight()/8*5, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				//TODO refresh
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("refresh", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPreview.addButton(buttonPreviewRefresh);
		buttonPreviewStart = new HvlButton(main.getWidth()/8, main.getHeight()/8*6, main.getWidth()/4*3, main.getHeight()/32*3, main.getHeight()) {
			@Override
			public void onTriggered(){
				//TODO start
			}
			@Override
			public void draw(long delta){
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("start", getX(), getY() + (getYLength()/3), 0.25f, new Color(1f, 1f, 1f));
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
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getXLength(), getYLength(), TextureManager.getTexture(TextureSeries.MISC, 0), new Color(1f, 1f, 0f));
				fontPainter.hvlDrawWord("back", getX(), getY() + (getYLength()/6), 0.25f, new Color(1f, 1f, 1f));
			}
		};
		menuPreview.addButton(buttonPreviewBack);

		HvlMenu.setCurrent(menuMain);
	}

	public static void draw(long delta){
		HvlMenu.updateMenus(delta);
	}

}
