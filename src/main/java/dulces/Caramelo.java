package dulces;

//import java.awt.Color;

//import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Caramelo extends Dulce{

	public Caramelo(int valor) {
		
		super(valor);
		Sprite sprite = Sprite.fromImage("Caramelo.png");
		this.setAppearance(sprite);
		// TODO Auto-generated constructor stub
	}

}
