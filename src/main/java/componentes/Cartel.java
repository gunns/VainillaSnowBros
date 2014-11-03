package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;

public class Cartel extends GameComponent<SnowBrosScene>{
	
Dimension gameDimension;
	
	
	public Cartel(Dimension dim){
		this.gameDimension = dim;
		
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		Label label =new Label(font ,Color.green ,"      GANASTE!\n Presione P para Juego Nuevo");
		this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
		
	}
	
	public Cartel(Dimension dim, Integer puntaje){
		this.gameDimension = dim;

		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("              Game Over\n       ");
		Label label =new Label(font ,Color.magenta ,cadena); 

		this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
		
	}
	
	
	public void update(DeltaState deltaState) {
		}
}
