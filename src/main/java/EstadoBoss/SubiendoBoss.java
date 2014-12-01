package EstadoBoss;

import com.uqbar.vainilla.DeltaState;
import componentes.Bros;

import estadoBros.CayendoBros;
import boss.Boss;

public class SubiendoBoss extends EstadoBoss{

	public SubiendoBoss(double yInicial, Boss unBoss) {
		super(yInicial, unBoss);
		
		// TODO Auto-generated constructor stub
	}

	
	
	public void update(DeltaState deltaState){
		if(this.getBoss().getY()>=this.getyInicial()-200){
			double incrementoVelocidad = this.getBoss().getScene().getVelocity()*2;
			double velocidadIncremento = this.getBoss().getScene().getVelocity() + incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			double incrementoY = this.getBoss().getY() - velocidadIncrementadaPorDelta;
			this.getBoss().setY(incrementoY);
			}
		else if(this.getBoss().getY()<=this.getyInicial()-50){
				this.getBoss().getEstado().cambiarMovimiento(this.getBoss());
				//this.getBros().setEstado(new CayendoBros(this.getyInicial(),this.getBros()));
		}
	}
	
	public boolean realizandoSalto() {return true;}

	@Override
	public void cambiarMovimiento(Boss boss) {
		boss.setEstado(new CayendoBoss(this.getyInicial(),boss));
		
		
	}
}
	
