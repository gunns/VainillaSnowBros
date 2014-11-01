package estadoBros;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public class SubiendoBros extends EstadoBros {

	public SubiendoBros(double yInicial,Bros bros){

		this.setBros(bros);
		this.setyInicial(yInicial);
	}
	
	public boolean realizandoSalto(){
		return true;
	}
	
	public void update(DeltaState deltaState){
		if(this.getBros().getY()>=this.getyInicial()-100){
			this.getBros().setY(this.getBros().getY()-(this.getBros().getScene().getVelocity()+ (this.getBros().getScene().getVelocity()*2))* deltaState.getDelta());
			}else if(this.getBros().getY()<=this.getyInicial()-50){
				this.getBros().setEstado(new CayendoBros(this.getyInicial(),this.getBros()));
			}
	}
}