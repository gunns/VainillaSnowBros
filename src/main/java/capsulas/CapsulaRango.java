package capsulas;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;


public class CapsulaRango extends Capsula{
		
		public CapsulaRango(int valor)
		{
			super(valor);
			Sprite sprite = Sprite.fromImage("CapsulaRango.png");
			this.setAppearance(sprite);
			
		}
		
		public void sumarPuntaje(Bros bros) {
			bros.getEstadoCapsula().setRango(true);
			this.destroy();
			Sound sonidoCapsula = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("capsula.wav"));
			sonidoCapsula.play();
			
			bros.sumarPuntaje(500);
		}
}
