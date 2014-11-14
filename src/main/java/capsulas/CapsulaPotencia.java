package capsulas;
import java.awt.Color;

import com.uqbar.vainilla.appearances.Rectangle;
		

public class CapsulaPotencia extends Capsula{
	

	public CapsulaPotencia(int valor)
			{
				super(valor);
				this.setAppearance(new Rectangle(Color.CYAN, 18, 18));
				
			}

}
