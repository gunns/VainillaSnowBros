package com.uqbar.snowBros;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import mobConNieve.Empujado;
import componentes.Bros;
import componentes.Enemigos;
import componentes.Mob;
import componentes.Piso;
import componentes.Cartel;
import componentes.Snow;
import componentes.Suelo;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;


public class SnowBrosScene extends GameScene{
	private Dimension gameDimension;
	private double velocity;
	private GameComponent<GameScene> backGround;
	private boolean playState = true;
	private boolean systemPause = false;
	
	private Bros bros;
	private Enemigos enemigos;
	private Suelo suelo;
	
	public SnowBrosScene(Dimension dim, double velocity){
		super();
		this.velocity=velocity;
		this.gameDimension= dim;
		this.buildBackground(Color.blue);
		this.bros=new Bros(dim,this.playState, velocity);
		this.addComponent(this.bros);
		this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity());
		this.addComponents(this.enemigos.getEnemigos());
		this.suelo= new Suelo(this.gameDimension);
		this.addComponents(suelo.getSuelos());
	}
	
	private void buildBackground(Color color) {
		if (backGround != null) {this.removeComponent(this.backGround);}
		this.backGround = new GameComponent<GameScene>(new Rectangle(color,800,600),0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
	}
	
	public boolean getPlayState() {return this.playState;}
	
	public void setPlayState(boolean playState) {this.playState = playState;}
	
	public boolean getSystemPause() {return this.systemPause;}
	
	public void setSystemPause(boolean SysPause) {this.systemPause = SysPause;}
	
	public double getVelocity() {return this.velocity;}

	public Bros getBros() {return this.bros;}
	
	
	public boolean hayColisionConUnPiso(GameComponent<SnowBrosScene> componenteRectangular){
		boolean hayColision = false;
		Piso piso;
		for(int n = 0; n < this.suelo.getSuelos().size(); n++){
			piso = this.suelo.getSuelos().get(n);
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(componenteRectangular.getX(),
					(componenteRectangular.getY() + componenteRectangular.getAppearance().getHeight()-1),
					(int) (componenteRectangular.getAppearance().getWidth()), (int) (1),
					piso.getX(), piso.getY(),(int) (piso.getAppearance().getWidth()),(int) (2))){
				hayColision = true;
			}
		}
		return hayColision;
	}
	
	public boolean enemigoColisionaConBros(GameComponent<SnowBrosScene> componenteRectangular){
		boolean hayColision = false;
		Mob mob;
		for(int n = 0; n < this.enemigos.getEnemigos().size(); n++){
			mob= this.enemigos.getEnemigos().get(n);
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(componenteRectangular.getX(),
					componenteRectangular.getY(),(int) componenteRectangular.getAppearance().getWidth(),
					(int) componenteRectangular.getAppearance().getHeight(),
					mob.getX(), mob.getY(),(int) mob.getAppearance().getWidth(), (int) mob.getAppearance().getHeight())){
				
				if(mob.getEstadoNieve().esPeligroso()){
				hayColision = true;
			}
		}
		}
		return hayColision;
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
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(each.getClass().equals(Snow.class)){
				if(this.colisionNieveConMob(each, mob)){
					mob.getEstadoNieve().agregandoNieve();
					}
				}
			}
	}	

	private boolean colisionNieveConMob(GameComponent<?> each, Mob mob2) {
		boolean hayColision = false;
		GameComponent<?> nieve = each;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(nieve.getX(), nieve.getY(),
				nieve.getAppearance().getWidth()/2,mob2.getX(), mob2.getY(),
				mob2.getAppearance().getWidth(), mob2.getAppearance().getHeight())){
			hayColision = true;
			each.destroy();
		}
		return hayColision;
	}	
	
	public boolean puedeEmpujarBolaDeNieve(Bros bros, DeltaState deltaState){
		
		
		boolean puedeEmpujar = false;
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(each.getClass().equals(Mob.class)){
				Mob mob = (Mob) each;
				if(this.colisionBolaNieveConBros(each, bros) && mob.getEstadoNieve().PuedoEmpujar())
						{
					puedeEmpujar = true;
					//bros.empujar(each, deltaState);
					}
				}
			}
		return puedeEmpujar;
	}
	
	public void empujar(Bros bros, DeltaState deltaState)
	{
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(each.getClass().equals(Mob.class)){
				Mob mob = (Mob) each;
				if(this.colisionBolaNieveConBros(each, bros) && mob.getEstadoNieve().PuedoEmpujar())
						{
					    mob.setEstadoNieve(new Empujado(mob, 0, bros.getDir()));
					}
				}
			}	
	}
	
	
	public boolean colisionBolaNieveConBros(GameComponent<?> each, Bros bros){
		boolean hayColision = false;
		GameComponent<?> mob = each;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(mob.getX(), mob.getY(),
				mob.getAppearance().getWidth()/2,bros.getX(), bros.getY(),
				bros.getAppearance().getWidth(), bros.getAppearance().getHeight())){
			hayColision = true;
			
			
			
		}
		return hayColision;
	}

	public void moverEsfera(Bros bros, DeltaState deltaState) {
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
		{
		if(each.getClass().equals(Mob.class)){
			Mob mob = (Mob) each;
			if(this.colisionBolaNieveConBros(mob, bros) && mob.getEstadoNieve().PuedoEmpujar() && this.hayColisionConUnPiso(bros))
					{
				    bros.getDir().moverEsfera(mob, deltaState);
					}
			}
		}	
		
	}	

	public boolean hayColisionConUnaEsfera(Bros bros){
		boolean hayColision = false;
		Mob mob;
		List<GameComponent<?>> c = this.getComponents();
		//for(int n = 0; n < this.suelo.getSuelos().size(); n++){
		for(GameComponent<?> each : c ){
			if(each.getClass().equals(Mob.class))
			{
				mob = (Mob) each;
			if(this.colisionBolaNieveConBros(mob, bros) && mob.getEstadoNieve().PuedoEmpujar())
			{
				hayColision = true;
			}
		}
		
		}
		return hayColision;
	}
	
}
