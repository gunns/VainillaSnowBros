package capsulas;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;


public class CapsulaRango extends Capsula{
		
		public CapsulaRango(int valor)
		{
			super(valor);
			this.setAppearance(new Rectangle(Color.yellow, 18, 18));
			
		}
}
