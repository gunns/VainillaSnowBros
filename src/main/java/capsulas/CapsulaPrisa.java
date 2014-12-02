package capsulas;

//import java.awt.Color;

//import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;

public class CapsulaPrisa extends Capsula {
	
	public CapsulaPrisa(int valor)
	{
		super(valor);
		Sprite sprite = Sprite.fromImage("CapsulaPrisa.png");
		this.setAppearance(sprite);
		
	}

	public void sumarPuntaje(Bros bros) {
		//TODO agregar solo puntaje para los dulces, las capsulas se redefinen
		if(!bros.getEstadoCapsula().isPrisa())
			{
			bros.getEstadoCapsula().setPrisa(true);
			bros.setVelocity(bros.getVelocity()*1.5);
			}
			this.destroy();
		//sonido capsula
				Sound sonidoCapsula = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("capsula.wav"));
				sonidoCapsula.play();
				// TODO Auto-generated constructor stub
				bros.sumarPuntaje(500);
	}
	
}
