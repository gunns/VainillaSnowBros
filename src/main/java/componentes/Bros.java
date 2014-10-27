package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Derecha;
import others.Direccion;
import others.Izquierda;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Bros extends GameComponent<SnowBrosScene>{

	//MOVIMIENTO
	public boolean cayendo = true;
	public double ySalto;
	public boolean realizandoSalto= false;
	public Dimension gameDimension;
	public boolean playState = true;
	
	//DISPARO
	public Direccion dir;
	
	
public Bros(Dimension dim, boolean playState){
		
		
		
		this.setAppearance(new Rectangle(Color.white,10,25));
		//TODO
		this.dir =  new Derecha();
		
		this.gameDimension= dim;
		this.playState = playState;
		this.ySalto=this.getY();
		this.realizandoSalto=false;
		this.setX(this.gameDimension.getWidth()/2-this.getAppearance().getWidth()/2);
		this.setY(this.gameDimension.getHeight()-(this.getAppearance().getHeight())-5);
		this.setZ(1);
	}

	protected boolean getPlayState() {
		return this.playState;
	}
	protected void setPlayState(boolean playState){
		this.playState = playState;
	}
	
	public void update(DeltaState deltaState) {
		
		
		//COMANDO MOVER
		if (deltaState.isKeyBeingHold(Key.RIGHT)){
			this.moverALaDerecha(deltaState);
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.moverALaIzquierda(deltaState);
		}
		
		//COMANDO SALTAR
		if (!this.realizandoSalto && deltaState.isKeyPressed(Key.A)){
			this.ySalto=this.getY();
			this.cayendo=false;
			this.realizandoSalto=true;
		}
		if(this.cayendo){
			this.caer(deltaState);
		}
		else{
			this.saltando(deltaState);
		}
		
		//COMANDO:DISPARAR
		
		if(deltaState.isKeyPressed(Key.S)){
			this.Disparar();
		}
		
		
		
	}
	
		//DISPARAR NIEVE
	
	private void Disparar() {
		Double alturaDeDisparo = this.getY() - ( (this.getAppearance().getHeight())/10 );
		Snow snow = new Snow(gameDimension, 1, (this.getX()), alturaDeDisparo, this);
		this.getScene().addComponent(snow);
		//this.getScene().addComponent();
		
	}

	private void saltando(DeltaState deltaState){
		if(!this.cayendo&&this.getY()>=this.ySalto-100){
		this.setY(this.getY()-(this.getScene().getVelocity()+ (this.getScene().getVelocity()*2))* deltaState.getDelta());
		}else if(this.getY()<=this.ySalto-50){
			this.cayendo=true;
		}
	}	
	private void caer(DeltaState deltaState){
			if(this.cayendo&&this.getY()<=this.ySalto-0.25){
				this.setY(this.getY()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
			}
			else{
				this.realizandoSalto=false;
			}
	}
	private void moverALaDerecha(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			//TODO
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
			//TODO
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
	private boolean noLlegoAlFinal() {
		return this.getX()+this.getAppearance().getWidth()<= gameDimension.getWidth();
	}
	private boolean noLlegoAlComienzo(){
		return this.getX()>=0;
	}
	
	
	//MOVIMIENTO NIEVE
	
	public boolean puedeRecorrer(Dimension dim, Snow snow){
		return this.dir.puedeRecorrer(dim, snow);	
		}
	
	//public void avanzaDisparo(Snow snow)
	//{
	//this.dir.avanzaDisparo(snow);	
	//}

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}
		
	
	
	
	
	
}
