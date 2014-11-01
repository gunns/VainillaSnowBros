package others;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public class Subiendo extends EstadoBros {

	public Subiendo(double yInicial,Bros bros){
		this.setyInicial(yInicial);
		this.setBros(bros);
	}
	
	public boolean realizandoSalto(){
		return true;
	}
	
	public void update(DeltaState deltaState){
		if(this.getBros().getY()>=this.getyInicial()-100){
			this.getBros().setY(this.getBros().getY()-(this.getBros().getScene().getVelocity()+ (this.getBros().getScene().getVelocity()*2))* deltaState.getDelta());
			}else if(this.getBros().getY()<=this.getyInicial()-50){
				this.getBros().setEstado(new Cayendo(this.getyInicial(),this.getBros()));
			}
	}
}