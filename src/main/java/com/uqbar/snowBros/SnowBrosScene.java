package com.uqbar.snowBros;

import java.awt.Color;
import java.awt.Dimension;

import componentes.Bros;
import componentes.Piso;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;

import componentes.Suelo;

public class SnowBrosScene extends GameScene{
	private Dimension gameDimension;
	private double velocity;
	private GameComponent<GameScene> backGround;
	private boolean playState = false;
	private boolean systemPause = false;
	
	
	private Bros bros;
	private Suelo suelo;
	
	
	public boolean getPlayState(){
		return this.playState;
	}
	
	protected void setPlayState(boolean playState){
		this.playState = playState;
	}
	
	public boolean getSystemPause() {
		return this.systemPause;
	}
	public void setSystemPause(boolean SysPause){
		this.systemPause = SysPause;
	}
	public double getVelocity() {
		return this.velocity;
	}
	
	public SnowBrosScene(Dimension dim, double velocity){
		super();
		
		//this.collisionDetector = new CollisionDetector;
		this.gameDimension= dim;
		this.velocity=velocity;
		this.buildBackground(Color.blue);
		this.bros=new Bros(dim,this.playState);
		this.addComponent(this.bros);
		this.suelo= new Suelo(this.gameDimension);
		this.addComponents(suelo.getSuelos());
	}
	
	private void buildBackground(Color color) {
		if (backGround != null) {
			this.removeComponent(this.backGround);
		}
		this.backGround = new GameComponent<GameScene>(new Rectangle(color,800,600),0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
	}

	public Bros getBros(){
		return this.bros;
	}
	
//	public boolean hayColisionConUnPiso(){
//		boolean b = false;
//		boolean pb = false;
//		Piso piso;
//		//boolean encontre = false;
//		for(int n = 0;n < this.suelo.getSuelos().size(); n++){
//			piso = this.suelo.getSuelos().get(n);
////			if(piso.getY())
//			if(piso.getY()>=this.bros.getY()+this.bros.getAlto()&&
//					piso.getY()+piso.getAppearance().getHeight()<=this.bros.getY()+this.bros.getAlto()&&
//					piso.getX()<=this.bros.getX()&&piso.getX()+piso.getAncho()>=this.bros.getX()){
////			if(this.ladrilloSeRompe(this.ladrillos.getLadrillos().get(n)))
////				{
//				//encontre = true;
////				this.ladrilloEnColision = this.ladrillos.getLadrillos().get(n);
//				b = true;
//				}
//			}
//		return b;
//	}
	
	public boolean hayColisionConUnPiso(GameComponent<SnowBrosScene> c){
		boolean b = false;
		Piso piso;
		for(int n = 0;n < this.suelo.getSuelos().size(); n++){
			piso = this.suelo.getSuelos().get(n);
			//utilizar detector de coliciones entre rectangulos
			
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(c.getX(), (c.getY()+c.getAppearance().getHeight()-1),
					(int) (c.getAppearance().getWidth()), (int) (1),
					piso.getX(), piso.getY(),(int) (piso.getAppearance().getWidth()),(int) (1))){
				b = true;
			}
		}
		return b;
	}
}
