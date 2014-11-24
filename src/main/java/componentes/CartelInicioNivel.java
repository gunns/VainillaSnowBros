package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class CartelInicioNivel extends GameComponent<SnowBrosScene>{
	
	Integer tiempoDeCartel;
	Dimension gameDimension;
	Integer numeroNivel;
	double velocity;
	
	public CartelInicioNivel(Dimension dim){
		this.gameDimension = dim;
		
		
		//Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		//Label label =new Label(font ,Color.green ,"      GANASTE!\n Presione P para Juego Nuevo");
		//this.setAppearance(label);
		
		this.setX(dim.getHeight()/2);
		this.setY(dim.getWidth()/2);
		//voz inicio de nivel
		Sound vozMotivacion = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("ready.wav"));
		vozMotivacion.play();
		
	}
	
	public CartelInicioNivel(Dimension dim, Integer numeroNivel, double velocity){
		this.gameDimension = dim;
		this.velocity = velocity;

		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("           Floor " + numeroNivel.toString() + "\n         Prepárate" );
		Label label =new Label(font ,Color.red ,cadena); 

		this.setAppearance(label);
		
		this.setX(dim.getWidth()/3);
		this.setY(dim.getHeight()/3);
		
		tiempoDeCartel = 700;
		
		
	}
	
	public void update(DeltaState deltaState) {
		if(tiempoDeCartel == 400)
			{
			//voz inicio de nivel
			Sound vozMotivacion = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("ready2.wav"));
			vozMotivacion.play();
			tiempoDeCartel = tiempoDeCartel - 1;
			}
		if(tiempoDeCartel > 1)
		{
		tiempoDeCartel = tiempoDeCartel - 1;
		}
		else
			{
			this.destroy();
			this.getScene().comenzarNivel(gameDimension, velocity);
			}
	}
}

	


