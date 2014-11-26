package estadoMob;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class CayendoMob extends EstadoMob{

	public CayendoMob(double yInicial, Mob mob){
		this.setMob(mob);
		this.setyInicial(yInicial);
	}
	
	public void update(DeltaState deltaState){
		if(!this.getMob().getScene().hayColisionConUnPiso(this.getMob())){
			double incrementoVelocidad = this.getMob().getScene().getVelocity()/4;
			double velocidadIncremento = this.getMob().getScene().getVelocity() + incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			double incrementoY = this.getMob().getY() + velocidadIncrementadaPorDelta;
			this.getMob().setY(incrementoY);
		}
	}
}
