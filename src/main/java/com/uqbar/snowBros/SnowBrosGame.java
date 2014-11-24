package com.uqbar.snowBros;

import java.awt.Dimension;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;


public class SnowBrosGame extends Game {

	private Dimension dimension;
	private GameScene scene;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800 , 600);
	}	

	@Override
	protected void setUpScenes() {
		try{
		this.scene = new SnowBrosScene(dimension,200);
		this.setCurrentScene(scene);
		}
		catch (Exception e){}
	}
	
	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "SnowBros";
	}
	
	public static void main(String[] args) {
		new DesktopGameLauncher(new SnowBrosGame()).launch();
	}
}
