package capsulas;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
import componentes.Bros;


public class CapsulaRango extends Capsula{
		
		public CapsulaRango(int valor)
		{
			super(valor);
			this.setAppearance(new Rectangle(Color.yellow, 18, 18));
			
		}
		
		public void sumarPuntaje(Bros bros) {
			//TODO agregar solo puntaje para los dulces, las capsulas se redefinen
			bros.getEstadoCapsula().setRango(true);
			this.destroy();
		}
}
