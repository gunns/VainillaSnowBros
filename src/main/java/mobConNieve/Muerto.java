package mobConNieve;

//import java.awt.Color;

import tesoros.Tesoro;
import tesoros.Tesoros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import componentes.Bros;
import componentes.Mob;
import componentes.Snow;

public class Muerto extends EstadoNieve {

	Bros bros;

	public Muerto(Mob mob, Bros bros, double duracionMuerto) {
		super(mob, duracionMuerto);
		this.getMob()
				.setAppearance(this.getMob().getApariencia().getMobMuere());
		this.bros = bros;

		Sound sonidoMuerto = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("mobDie.wav"));
		sonidoMuerto.play();
	}

	@Override
	public void derritiendoNieve() {

	}

	@Override
	public void agregandoNieve(Snow snow) {
	}

	public void update(DeltaState deltaState) {
		if (this.getDuracionNieve() < 1) {
			Tesoros tesoros = new Tesoros();
			Tesoro premio = (Tesoro) tesoros.dropear();
			premio.setX(this.getMob().getX());
			premio.setY(this.getMob().getY()
					+ (this.getMob().getAppearance().getHeight() / 2));
			this.getMob().getScene().addComponent(premio);

			this.getBros().sumarPuntaje(300);
			this.getMob().destroy();
		} else {
			this.setDuracionNieve(this.getDuracionNieve() - 1);
		}
	}

	public Bros getBros() {
		return bros;
	}

	public void setBros(Bros bros) {
		this.bros = bros;
	}

	public void arrolla(Mob mob) {

	}

}
