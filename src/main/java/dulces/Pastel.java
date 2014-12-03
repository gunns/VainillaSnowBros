package dulces;

import com.uqbar.vainilla.appearances.Sprite;

public class Pastel extends Dulce {

	public Pastel(int valor) {
		super(valor);
		Sprite sprite = Sprite.fromImage("Pastel.png");
		this.setAppearance(sprite);
	}

}
