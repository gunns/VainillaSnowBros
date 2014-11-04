package estadoBros;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public class SubiendoBros extends EstadoBros {

	public SubiendoBros(double yInicial, Bros bros){
		this.setBros(bros);
		this.setyInicial(yInicial);
	}
	
	public void update(DeltaState deltaState){
		if(this.getBros().getY()>=this.getyInicial()-100){
			double incrementoVelocidad = this.getBros().getScene().getVelocity()*2;
			double velocidadIncremento = this.getBros().getScene().getVelocity() + incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			double incrementoY = this.getBros().getY() - velocidadIncrementadaPorDelta;
			this.getBros().setY(incrementoY);
			}
		else if(this.getBros().getY()<=this.getyInicial()-50){
				this.getBros().setEstado(new CayendoBros(this.getyInicial(),this.getBros()));
		}
	}
	
	public boolean realizandoSalto() {return true;}
}