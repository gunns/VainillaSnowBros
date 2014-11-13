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
		if(!this.getBros().getScene().hayColisionConUnPiso(this.getBros()) && !this.getBros().getScene().hayColisionConUnaEsfera(this.getBros())) {
			double incrementoVelocidad = this.getBros().getScene().getVelocity()/4;
			double velocidadIncremento = this.getBros().getScene().getVelocity() + incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			double incrementoY = this.getBros().getY() + velocidadIncrementadaPorDelta;
			this.getBros().setY(incrementoY);
		}
			else
			{
				if(deltaState.isKeyPressed(Key.A))
					{
					this.cambiarMovimiento(this.getBros());
					//this.getBros().setEstado(new SubiendoBros(this.getBros().getY(),this.getBros()));
					}
			}
		}

	@Override
	public void cambiarMovimiento(Bros bros) {
		this.getBros().setEstado(new SubiendoBros(this.getBros().getY(),this.getBros()));	
	}
}
