package Cartel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class CartelLevelComplete extends GameComponent<SnowBrosScene>{
	Integer tiempoDeCartel;
	Dimension gameDimension;
	Integer numeroNivel;
	double velocity;
	
	public CartelLevelComplete(Dimension dim){
		this.gameDimension = dim;
		
		
		//Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		//Label label =new Label(font ,Color.green ,"      GANASTE!\n Presione P para Juego Nuevo");
		//this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
		//voz inicio de nivel
		
	}
	
	public CartelLevelComplete(Dimension dim, Integer numeroNivel){
		this.gameDimension = dim;
		//this.velocity = velocity;

		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("           Level" + numeroNivel.toString()  + "\n        Complete!           " );
		Label label =new Label(font ,Color.black ,cadena); 

		this.setAppearance(label);
		
		this.setX(dim.getWidth()/3);
		this.setY(dim.getHeight()/3);
		
		tiempoDeCartel = 700;
		
		
		
	}
	
	public void update(DeltaState deltaState) {
		if(tiempoDeCartel > 0)
			{
			tiempoDeCartel = tiempoDeCartel - 1;
			}
		else
			{
			
			
			this.destroy();
			this.getScene().siguienteNivel();
			}
	}
}

	


