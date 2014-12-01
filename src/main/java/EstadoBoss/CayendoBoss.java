package EstadoBoss;

import boss.Boss;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;
import estadoBros.SubiendoBros;

public class CayendoBoss extends EstadoBoss{

	public CayendoBoss(double yInicial, Boss unBoss) {
		super(yInicial, unBoss);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cambiarMovimiento(Boss boss) {
		this.getBoss().setEstado(new SubiendoBoss(this.getBoss().getY(), this.getBoss()));
		
	}
	
	public void update(DeltaState deltaState){
										//piso inferior, abajo de todo
		if(!this.getBoss().getScene().tocoFondo(this.getBoss())) 
				{
			double incrementoVelocidad = this.getBoss().getScene().getVelocity()/4;
			double velocidadIncremento = this.getBoss().getScene().getVelocity() + incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			double incrementoY = this.getBoss().getY() + velocidadIncrementadaPorDelta;
			this.getBoss().setY(incrementoY);
		}
			else
			{
				//EL BOSS CAYÓ, toco el piso y es el momento de desatar el evento:
				if(!this.getBoss().isAcabaDeTocarElPiso())
					{
					this.getBoss().golpeoElPiso();
					this.setyInicial(this.getBoss().getY());
					}
			}
		}

}