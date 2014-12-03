package Cartel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class CartelPresioneStart extends GameComponent<SnowBrosScene>
{
	Integer tiempoDeCartel;
	Dimension gameDimension;
	Integer numeroNivel;
	double velocity;
	public boolean cartelVisible;
	
	public CartelPresioneStart(Dimension dim){
		this.gameDimension = dim;

		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 25);
		
		String cadena = new String("          *Bienvenido*      \n         Press Start (P)       \n           Good Luck    " );
		Label label =new Label(font ,Color.white ,cadena); 

		this.setAppearance(label);
		
		this.setX(dim.getWidth()/3 - 44);
		this.setY(dim.getHeight()/3) ;
		
		tiempoDeCartel = 200;
		cartelVisible = true;
	}
	
	public void update(DeltaState deltaState) {
		if(tiempoDeCartel > 0)
			{
			tiempoDeCartel = tiempoDeCartel - 1;
			}
		else
			{
			this.cambiarImagen();
			this.tiempoDeCartel = 200;
			}
	}

	private void cambiarImagen() {
		if(!this.cartelVisible)
			{
			Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
			String cadena = new String("          Press Start (P)       \n                          " );
			Label label =new Label(font ,Color.white ,cadena); 

			this.setAppearance(label);
			this.cartelVisible = true;

			}
		else
			{
			Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
			String cadena = new String("                                  " );
			Label label =new Label(font ,Color.white ,cadena); 

			this.setAppearance(label);
			this.cartelVisible = false;

			}
		
	}
}