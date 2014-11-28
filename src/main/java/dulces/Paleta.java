package dulces;

//import java.awt.Color;

//import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Paleta extends Dulce{

	public Paleta(int valor) {
		super(valor);
		Sprite sprite = Sprite.fromImage("Paleta.png");
		this.setAppearance(sprite);
		// TODO Auto-generated constructor stub
	}

}
