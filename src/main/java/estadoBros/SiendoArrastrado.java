package estadoBros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

import componentes.Bros;
import componentes.Mob;


public class SiendoArrastrado extends EstadoBros{
	
	public Mob esfera;
	public Bros bros;
	
	public SiendoArrastrado(Mob mob, Bros bros){
		this.esfera = mob;
		this.bros = bros;
	}

	
	public void update(DeltaState deltaState){
		//if(!bros.getScene().hayColisionConUnPiso(bros)) {
			//double incrementoVelocidad = bros.getScene().getVelocity()/4*2;
			//double velocidadIncremento = bros.getScene().getVelocity() + incrementoVelocidad;
			//double velocidadIncrementadaPorDelta = velocidadIncremento * deltaState.getDelta();
			//double incrementoY = bros.getY() + velocidadIncrementadaPorDelta;
			//bros.setY(incrementoY);
		//}
			//else
			//{
				if(deltaState.isKeyPressed(Key.A))
					{
					this.saltar();
					//this.getBros().setEstado(new SubiendoBros(this.getBros().getY(),this.getBros()));
					}
			//}
		
		esfera.getDir().desplazarBrosArrastrado(bros, esfera, deltaState);
		bros.setInvencible(true);
		//this.bros.setX(this.esfera.getX() + esfera.getAppearance().getWidth()/2);
		//this.bros.setY(this.esfera.getY());
			
			
			
	}


	@Override
	public void cambiarMovimiento(Bros bros) {
		//HARDCODE por un error en getYInicial
		//bros.setEstado(new SubiendoBros(this.getyInicial(),this.getBros()));
		
		bros.setY(bros.getY() - 60);
		//bros.setEstado(new CayendoBros(bros.getY() ,bros));
		bros.setEstado(new CayendoBros(this.getyInicial(),bros));
		//bros.saltar();
		//this.getBros().setEstado(new CayendoBros(this.getyInicial(),this.getBros()));
		
	}

	public boolean siendoArrastrado(){
		return true;
	}
	
	public void saltar(){
		this.cambiarMovimiento(bros);
		bros.setTiempoInvencible(250);
	}
}
