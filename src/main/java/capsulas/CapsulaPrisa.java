package capsulas;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
import componentes.Bros;

public class CapsulaPrisa extends Capsula {
	
	public CapsulaPrisa(int valor)
	{
		super(valor);
		this.setAppearance(new Rectangle(Color.red, 18, 18));
		
	}

	public void sumarPuntaje(Bros bros) {
		//TODO agregar solo puntaje para los dulces, las capsulas se redefinen
		bros.getEstadoCapsula().setPrisa(true);
	}
	
}
