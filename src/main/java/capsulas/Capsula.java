package capsulas;

import com.uqbar.vainilla.appearances.Sprite;

import tesoros.Tesoro;

public class Capsula extends Tesoro{

	public Capsula(int valor) {
		super(valor);
		Sprite sprite = Sprite.fromImage("Capsula.png");
		this.setAppearance(sprite);
	}

}
