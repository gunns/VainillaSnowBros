package com.uqbar.snowBros;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import componentes.Bros;
import componentes.Enemigos;
import componentes.Mob;
import componentes.Piso;
import componentes.Cartel;
import componentes.Snow;

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
	private Enemigos enemigos;
	private Suelo suelo;
	//private Mob mob;
	
	public boolean getPlayState(){
		return this.playState;
	}
	
	public void setPlayState(boolean playState){
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
		
		this.gameDimension= dim;
		this.velocity=velocity;
		this.buildBackground(Color.blue);
		this.bros=new Bros(dim,this.playState);
		this.addComponent(this.bros);
		this.enemigos=new Enemigos(this.gameDimension,this.playState);
		this.addComponents(this.enemigos.getEnemigos());;
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
	
	
	public boolean hayColisionConUnPiso(GameComponent<SnowBrosScene> c){
		boolean b = false;
		Piso piso;
		for(int n = 0;n < this.suelo.getSuelos().size(); n++){
			piso = this.suelo.getSuelos().get(n);
			//utilizar detector de coliciones entre rectangulos
			
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(c.getX(), (c.getY()+c.getAppearance().getHeight()-1),
					(int) (c.getAppearance().getWidth()), (int) (1),
					piso.getX(), piso.getY(),(int) (piso.getAppearance().getWidth()),(int) (2))){
				b = true;
			}
		}
		return b;
	}
	public boolean enemigoColisionaConBros(GameComponent<SnowBrosScene> c){
		Mob mob;
		boolean b = false;
		for(int n = 0;n < this.enemigos.getEnemigos().size(); n++){
			mob = this.enemigos.getEnemigos().get(n);
			
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(c.getX(), c.getY(),
					(int) (c.getAppearance().getWidth()),(int) (c.getAppearance().getHeight()),
					mob.getX(), mob.getY(),(int) (mob.getAppearance().getWidth()), (int) (mob.getAppearance().getHeight()))){
				b = true;
				this.cartelLose();
				
			}
		}
		return b;
	}
	
	public void stop() {
		this.velocity= 0d;
		this.playState=false;
		
	}
	public void cartelLose(){
		this.buildBackground(Color.black);
		this.addComponent(new Cartel(this.gameDimension,0));
		this.setPlayState(false);
		this.stop();
	}
	
	public void colisionConNieve(Mob mob) {
		// TODO Auto-generated method stub
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(each.getClass().equals(Snow.class))
				{
				if(this.colisionNieveConMob(each, mob))
					{
					mob.getEstadoNieve().agregandoNieve();
					}
				}
			}
		
	}
	
	
	
	

	private boolean colisionNieveConMob(GameComponent<?> each, Mob mob2) {
		// TODO Auto-generated method stub
		
		/*
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
		return b;*/
		boolean b = false;
		GameComponent<?> nieve = each;
		//for(int n = 0; n < )
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nieve.getX(), nieve.getY(),
				nieve.getAppearance().getWidth()/2,
				mob2.getX(), mob2.getY(), mob2.getAppearance().getWidth(), mob2.getAppearance().getHeight())){
			b = true;
			each.destroy();
			
	}
		return b;
}
	
	
}
