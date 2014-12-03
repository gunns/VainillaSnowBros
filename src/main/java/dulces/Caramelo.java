package dulces;

import com.uqbar.vainilla.appearances.Sprite;

public class Caramelo extends Dulce {

	public Caramelo(int valor) {

		super(valor);
		Sprite sprite = Sprite.fromImage("Caramelo.png");
		this.setAppearance(sprite);
	}

}
