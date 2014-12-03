package capsulas;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;

public class CapsulaPrisa extends Capsula {

	public CapsulaPrisa(int valor) {
		super(valor);
		Sprite sprite = Sprite.fromImage("CapsulaPrisa.png");
		this.setAppearance(sprite);

	}

	public void sumarPuntaje(Bros bros) {
		if (!bros.getEstadoCapsula().isPrisa()) {
			bros.getEstadoCapsula().setPrisa(true);
			bros.setVelocity(bros.getVelocity() * 1.5);
		}
		this.destroy();
		Sound sonidoCapsula = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("capsula.wav"));
		sonidoCapsula.play();
		bros.sumarPuntaje(500);
	}

}
