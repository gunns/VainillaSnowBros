package estadoBros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

import componentes.Bros;

public class CayendoBros extends EstadoBros {

	public CayendoBros(double yInicial,Bros bros){
		this.setyInicial(yInicial);
		this.setBros(bros);
	}
	public void update(DeltaState deltaState){
		if(!this.getBros().getScene().hayColisionConUnPiso(this.getBros()) ){
		this.getBros().setY(this.getBros().getY()+(this.getBros().getScene().getVelocity()+ (this.getBros().getScene().getVelocity()/4))* deltaState.getDelta());
		}
		else{
			if(deltaState.isKeyPressed(Key.A)){
				this.getBros().setEstado(new SubiendoBros(this.getBros().getY(),this.getBros()));
			}
		}
	}
}
