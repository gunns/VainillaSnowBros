package dulces;

import tesoros.Tesoro;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Dulce extends Tesoro{
	 

	public Dulce(int valor)
	{
	super(valor);
	Sprite sprite = Sprite.fromImage("Dulce.png");
	this.setAppearance(sprite);
	}

}
