package componentes;

import java.awt.Color;
import java.awt.Dimension;


import com.uqbar.snowBros.SnowBrosScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Piso extends GameComponent<SnowBrosScene>{
	
	private Dimension gameDimension;
	
	
		public Piso( Dimension dim, double coordY, double coordX, Color col, int ancho, int alto){
			this.setAppearance(new Rectangle(Color.black,ancho,alto));
//			this.setAppearance(new Rectangle(col,50,25));
			this.gameDimension= dim;
			this.setX(coordX);// -this.getAppearance().getWidth());
			this.setY(coordY);// - this.getAppearance().getHeight());
		}
		
		

		
		

}