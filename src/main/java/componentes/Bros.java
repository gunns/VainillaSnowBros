package componentes;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Bros extends GameComponent<SnowBrosScene>{

	public boolean cayendo = true;
	public double ySalto;
	public boolean realizandoSalto= false;
	public Dimension gameDimension;
	public boolean playState = true;
	
public Bros(Dimension dim, boolean playState){
		
		
		
		this.setAppearance(new Rectangle(Color.white,10,25));
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
		if (deltaState.isKeyBeingHold(Key.RIGHT)){
			this.moverALaDerecha(deltaState);
		}
		else if(deltaState.isKeyBeingHold(Key.LEFT)){
			this.moverALaIzquierda(deltaState);
		}
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
		return this.getX()+this.getAppearance().getWidth()<=gameDimension.getWidth();
	}
	private boolean noLlegoAlComienzo(){
		return this.getX()>=0;
	}
	
}
