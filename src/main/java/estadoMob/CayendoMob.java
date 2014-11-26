package estadoMob;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Mob;
import estadoBros.SubiendoBros;

public class CayendoMob extends EstadoMob{

	Sound sonidoSalto;
	
	public CayendoMob(double yInicial, Mob mob){
		
		//sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_02.wav"));
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

	public void saltar(){
		this.cambiarMovimiento(this.getMob());
	}
	@Override
	public void cambiarMovimiento(Mob mob) {
		this.getMob().setEstado(new SubiendoMob(this.getMob().getY(),this.getMob()));	
		//this.sonidoSalto.play();
		
	}
}