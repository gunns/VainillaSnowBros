package com.uqbar.snowBros;

import java.awt.Dimension;

import com.uqbar.vainilla.GameScene;

public class SnowBrosScene extends GameScene{
	private Dimension gameDimension;
	private double velocity;
	
	public SnowBrosScene(Dimension dim, double velocity){
		super();
		
		this.gameDimension= dim;
		this.velocity=velocity;
	}

}
