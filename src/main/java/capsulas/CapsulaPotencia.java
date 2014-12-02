package capsulas;
//import java.awt.Color;

//import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;
		

public class CapsulaPotencia extends Capsula{
	

	public CapsulaPotencia(int valor)
			{
				super(valor);
				Sprite sprite = Sprite.fromImage("CapsulaPotencia.png");
				this.setAppearance(sprite);
			}
	
	public void sumarPuntaje(Bros bros) {
		//TODO agregar solo puntaje para los dulces, las capsulas se redefinen
		bros.getEstadoCapsula().setPotencia(true);
		this.destroy();
		//sonido capsula
		Sound sonidoCapsula = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("capsula.wav"));
		sonidoCapsula.play();
		
		bros.sumarPuntaje(500);
	}

}
