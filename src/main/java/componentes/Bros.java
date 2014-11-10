package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import estadoBros.CayendoBros;
import estadoBros.EstadoBros;
import com.uqbar.snowBros.SnowBrosScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;


public class Bros extends GameComponent<SnowBrosScene>{

	//MOVIMIENTO
	public EstadoBros estado;
	public boolean realizandoSalto= false;
	public Dimension gameDimension;
	public boolean playState = true;
	private int ancho = 10;
	private int alto = 25;
	
	//DISPARO
	public Direccion dir;
	
	
	public Bros(Dimension dim, boolean playState){
		this.setAppearance(new Rectangle(Color.white,ancho,alto));
		this.dir =  new Derecha();
		this.gameDimension= dim;
		this.playState = playState;
		this.setEstado(new CayendoBros(this.getY(),this));
		this.setX(this.gameDimension.getWidth()/2-this.getAppearance().getWidth()/2);
		this.setY(this.gameDimension.getHeight()-(this.getAppearance().getHeight())-20);
		this.setZ(1);
	}

	protected boolean getPlayState() {return this.playState;}
	
	protected void setPlayState(boolean playState) {this.playState = playState;}
	
	public EstadoBros getEstado() {return estado;}

	public void setEstado(EstadoBros estado) {this.estado = estado;}

	public int getAncho(){return ancho;}
	
	public int getAlto(){return alto;}
	
	public void update(DeltaState deltaState) {
		if(this.getScene().getPlayState())
		{
		//Colision con enemigos (rectangulos)
		if (!this.getScene().enemigoColisionaConBros(this)){
					
			//COMANDO SALTAR
			if(deltaState.isKeyPressed(Key.A)) {this.saltar();}
			this.getEstado().update(deltaState);
			
			//COMANDO MOVER
			if (deltaState.isKeyBeingHold(Key.RIGHT)) {this.moverALaDerecha(deltaState);}
			else if(deltaState.isKeyBeingHold(Key.LEFT)) {this.moverALaIzquierda(deltaState);}
			
			//COMANDO:DISPARAR			
			if(deltaState.isKeyPressed(Key.S)){
			this.CongelaOEmpuja(deltaState);
			}
		}
		else {this.getScene().cartelLose();}		
		}
	}
	
	//COMPROBAR CASO SI EMPUJA BOLA DE NIEVE O SOLO DISPARA NIEVE
	public void CongelaOEmpuja(DeltaState deltaState){
		if(this.getScene().puedeEmpujarBolaDeNieve(this,deltaState))
		{
		this.getScene().empujar(this, deltaState);
		}
		else
			this.disparar();
	}
	
	
	//DISPARAR NIEVE
	private void disparar() {
		
		
		
		Double alturaDeDisparo = this.getY() - ( (this.getAppearance().getHeight())/10 );
		Snow snow = new Snow(gameDimension, (this.getX()), alturaDeDisparo, this);
		this.getScene().addComponent(snow);	
	}
	
	
	
	
	
	
	
	
	
	

	private void saltar() {this.getEstado().saltar();}
	
	private void moverALaDerecha(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Derecha();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}
		}
	}
	
	private void moverALaIzquierda(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Izquierda();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()- (this.getScene().getVelocity() + (this.getScene().getVelocity()/4) )* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()-(this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}
		}
	}
	
	private boolean noLlegoAlFinal() {return this.getX()+this.getAppearance().getWidth()<= gameDimension.getWidth();}
	
	private boolean noLlegoAlComienzo() {return this.getX()>=0;}
	
	
	//MOVIMIENTO NIEVE
	public boolean puedeRecorrer(Dimension dim, Snow snow) {return this.dir.puedeRecorrer(dim, snow);}
	
	//public void avanzaDisparo(Snow snow)
	//{
	//this.dir.avanzaDisparo(snow);	
	//}

	public Direccion getDir() {return dir;}

	public void setDir(Direccion dir) {this.dir = dir;}

	//public void empujar(GameComponent<?> bolaDeNieve, DeltaState deltaState) {
		//Mob mobTransformado = (Mob) bolaDeNieve;
		//mobTransformado.empujar(this.dir, deltaState);
		
	//}	
}
