package capsulas;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;

public class CapsulaPrisa extends Capsula {
	
	public CapsulaPrisa(int valor)
	{
		super(valor);
		this.setAppearance(new Rectangle(Color.red, 18, 18));
		
	}

}
