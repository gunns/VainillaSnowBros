package suelos;

import java.awt.Color;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Piso extends GameComponent<SnowBrosScene> {

	private int ancho;
	private int alto;

	private boolean siguienteNivel;

	public Piso(double coordY, double coordX, Color col, int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		Sprite sprite = Sprite.fromImage("Suelo.png");
		this.setAppearance(sprite);
		this.setX(coordX);
		this.setY(coordY);
		this.siguienteNivel = false;
	}

	public void asignarPiso(SnowBrosScene s) {
		if (s.nivelBoss) {
			Sprite sprite = Sprite.fromImage("SueloIce.png");
			this.setAppearance(sprite);
		} else if (s.getNumeroNivel() >= 15) {
			Sprite sprite = Sprite.fromImage("SueloIce2.png");
			this.setAppearance(sprite);
		} else if (s.getNumeroNivel() >= 10) {
			Sprite sprite = Sprite.fromImage("Suelo2.png");
			this.setAppearance(sprite);
		} else if (s.getNumeroNivel() >= 5) {
			Sprite sprite = Sprite.fromImage("Suelo3.png");
			this.setAppearance(sprite);
		}
	}

	public void update(DeltaState deltaState) {
		if (this.siguienteNivel) {
			this.siguienteNivel = false;
			this.destroy();
		}
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public boolean isSiguienteNivel() {
		return siguienteNivel;
	}

	public void setSiguienteNivel(boolean siguienteNivel) {
		this.siguienteNivel = siguienteNivel;
	}

}
