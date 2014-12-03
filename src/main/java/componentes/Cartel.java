package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class Cartel extends GameComponent<SnowBrosScene> {

	Dimension gameDimension;
	Integer tiempoEvento;
	boolean desataElEvento;

	public Cartel(Dimension dim) {

		this.gameDimension = dim;

		this.setX(dim.getHeight() / 2);
		this.setY(dim.getWidth() / 2);

	}

	public Cartel(Dimension dim, Integer puntaje) {
		tiempoEvento = 300;
		desataElEvento = false;
		this.gameDimension = dim;

		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("           Game   Over\n");
		Label label = new Label(font, Color.magenta, cadena);

		this.setAppearance(label);

		this.setX(dim.getWidth() / 3);
		this.setY(dim.getHeight() / 3);

	}

	public void update(DeltaState deltaState) {
		if (this.getScene().nivelBoss) {
			if (this.tiempoEvento > 0) {
				tiempoEvento = tiempoEvento - 1;
			} else {
				if (!desataElEvento) {
					Sound sonidoExplosion = new SoundBuilder().buildSound(this
							.getClass().getClassLoader()
							.getResourceAsStream("evilLaught.wav"));
					sonidoExplosion.play();
					desataElEvento = true;
				}
			}
		}
	}
}