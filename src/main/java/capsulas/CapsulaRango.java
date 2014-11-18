package capsulas;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Bros;


public class CapsulaRango extends Capsula{
		
		public CapsulaRango(int valor)
		{
			super(valor);
			Sprite sprite = Sprite.fromImage("CapsulaRango.png");
			this.setAppearance(sprite);
			
		}
		
		public void sumarPuntaje(Bros bros) {
			//TODO agregar solo puntaje para los dulces, las capsulas se redefinen
			bros.getEstadoCapsula().setRango(true);
			this.destroy();
		}
}
