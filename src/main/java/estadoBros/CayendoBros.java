package estadoBros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import componentes.Bros;

public class CayendoBros extends EstadoBros {

	Sound sonidoSalto;

	public CayendoBros(double yInicial, Bros bros) {
		sonidoSalto = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("jump_02.wav"));
		this.setyInicial(yInicial);
		this.setBros(bros);
	}

	public void update(DeltaState deltaState) {
		if (!this.getBros().getScene().hayColisionConUnPiso(this.getBros())
				&& !this.getBros().getScene()
						.hayColisionConUnaEsfera(this.getBros())) {
			double incrementoVelocidad = this.getBros().getScene()
					.getVelocity() / 4;
			double velocidadIncremento = this.getBros().getScene()
					.getVelocity()
					+ incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento
					* deltaState.getDelta();
			double incrementoY = this.getBros().getY()
					+ velocidadIncrementadaPorDelta;
			this.getBros().setY(incrementoY);
		} else {
			if (deltaState.isKeyPressed(Key.A)) {
				this.cambiarMovimiento(this.getBros());
			}
		}
	}

	@Override
	public void cambiarMovimiento(Bros bros) {
		this.getBros().setEstado(
				new SubiendoBros(this.getBros().getY(), this.getBros()));
		this.sonidoSalto.play();
	}
}
