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
		this.getMob().setY(this.getMob().getY()+(this.getMob().getScene().getVelocity()+ (this.getMob().getScene().getVelocity()/4))* deltaState.getDelta());
		}
		/*else{
			if(deltaState.isKeyPressed(Key.A)){
				this.getMob().setEstado(new SubiendoMob());
			}
		}*/
	}
}
