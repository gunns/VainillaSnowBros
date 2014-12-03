package dulces;

import com.uqbar.vainilla.appearances.Sprite;

public class Paleta extends Dulce {

	public Paleta(int valor) {
		super(valor);
		Sprite sprite = Sprite.fromImage("Paleta.png");
		this.setAppearance(sprite);
	}

}
