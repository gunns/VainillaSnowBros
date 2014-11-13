package estadoBros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

import componentes.Bros;
import componentes.Mob;


public class SiendoArrastrado extends EstadoBros{
	
	public Mob esfera;

	
	public void update(DeltaState deltaState){
		if(!this.getBros().getScene().hayColisionConUnPiso(this.getBros())) {
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
					this.getBros().setTiempoInvencible(1000);
					//this.getBros().setEstado(new SubiendoBros(this.getBros().getY(),this.getBros()));
					}
			}
	}


	@Override
	public void cambiarMovimiento(Bros bros) {
		//HARDCODE por un error en getYInicial
		//bros.setEstado(new SubiendoBros(this.getyInicial(),this.getBros()));
		
		bros.setY(bros.getY() - 60);
		bros.setEstado(new CayendoBros(bros.getY() ,this.getBros()));
		//bros.saltar();
		//this.getBros().setEstado(new CayendoBros(this.getyInicial(),this.getBros()));
		
	}

	public boolean siendoArrastrado(){
		return true;
	}
}
