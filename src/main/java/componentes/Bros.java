package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import tesoros.Tesoro;
import estadoBros.CayendoBros;
import estadoBros.EstadoBros;
import estadoBros.SiendoArrastrado;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;


public class Bros extends GameComponent<SnowBrosScene>{

	//MOVIMIENTO
	public boolean invencible = true;
	public int tiempoInvencible = 1000;
	public EstadoBros estado;
	public boolean realizandoSalto= false;
	public Dimension gameDimension;
	public boolean playState = true;
	private int ancho = 10;
	private int alto = 25;
	private double velocity;
	
	//DISPARO
	public Direccion dir;
	
	
	public Bros(Dimension dim, boolean playState, double velocity){
		this.setAppearance(new Rectangle(Color.white,ancho,alto));
		this.dir =  new Derecha();
		this.gameDimension= dim;
		this.playState = playState;
		this.setEstado(new CayendoBros(this.getY(),this));
		this.setX(this.gameDimension.getWidth()/2-this.getAppearance().getWidth()/2);
		this.setY(this.gameDimension.getHeight()-(this.getAppearance().getHeight())-25);
		this.setZ(1);
		this.velocity = velocity;
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
				this.getScene().tomarTesoro(this);
			//Colision con enemigos (rectangulos)
			//restar el tiempo de invencibilidad
			if(this.invencible && !this.getEstado().siendoArrastrado()){
				this.tiempoInvencible--;
				if(this.tiempoInvencible < 1)
					{
					this.invencible = false;
					}
			}
			
		if (!this.getScene().enemigoColisionaConBros(this) || (this.getScene().enemigoColisionaConBros(this) && this.invencible)){
			//TODO Agarrar tesoro
			
			if((this.getScene().esferaRodanteColisionaConBros(this)))
				{
				this.setEstado(new SiendoArrastrado());
				this.getEstado().setBros(this);
				this.getEstado().update(deltaState);
				}
			else{
					
			//COMANDO SALTAR
			if(deltaState.isKeyPressed(Key.A)) {this.saltar();}
			this.getEstado().update(deltaState);
			
			//COMANDO MOVER
			if (deltaState.isKeyBeingHold(Key.RIGHT)) {
				this.moverALaDerecha(deltaState);
				
				}
			else
				if(deltaState.isKeyBeingHold(Key.LEFT)) 
					{
					this.moverALaIzquierda(deltaState);
					}
			
			//COMANDO:DISPARAR			
			if(deltaState.isKeyPressed(Key.S))
						{
						this.CongelaOEmpuja(deltaState);
						}
			//
			
			}
				}
			else
				{
				this.getScene().cartelLose();
				}
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
		Double alturaDeDisparo = this.getY() - ( (this.getAppearance().getHeight())/10);
		Snow snow = new Snow(gameDimension, (this.getX()), alturaDeDisparo, this);
		this.getScene().addComponent(snow);
	}
	
	
	
	
	
	
	
	
	
	

	public void saltar() {this.getEstado().saltar();}
	
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
					this.getScene().moverEsfera(this, deltaState);
				}
			}
		}
	}
	
	private void moverALaIzquierda(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Izquierda();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()- (this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()-(this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
					this.getScene().moverEsfera(this, deltaState);
				}
			}
		}
	}
	
	private boolean noLlegoAlFinal() {return this.getX()+this.getAppearance().getWidth()<= gameDimension.getWidth();}
	
	private boolean noLlegoAlComienzo() {return this.getX()>=0;}
	
	
	//MOVIMIENTO NIEVE
	public boolean puedeRecorrer(Dimension dim, Snow snow) {return this.dir.puedeRecorrer(dim, snow);}

	public Direccion getDir() {return dir;}

	public void setDir(Direccion dir) {this.dir = dir;}

	public boolean isInvencible() {
		return invencible;
	}

	public void setInvencible(boolean invencible) {
		this.invencible = invencible;
	}

	public int getTiempoInvencible() {
		return tiempoInvencible;
	}

	public void setTiempoInvencible(int tiempoInvencible) {
		this.tiempoInvencible = tiempoInvencible;
	}

	public void sumarPuntaje(Tesoro drop) {
		//TODO Modificar
		drop.destroy();
		
	}
	
	
	
}
