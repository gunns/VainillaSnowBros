package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class CartelSiguienteNivel extends GameComponent<SnowBrosScene>{
	Integer tiempoDeCartel;
	
	public CartelSiguienteNivel(Dimension dim, Integer numeroNivel)
	{
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		String cadena = new String("           Floor " + numeroNivel.toString());
		Label label =new Label(font ,Color.blue ,cadena); 

		this.setAppearance(label);
		
		this.setX(dim.getWidth()/3);
		this.setY(dim.getHeight()/3);	
		
	this.tiempoDeCartel = 200;	
	}
	
	public void update(DeltaState deltaState)
	{
		if(this.tiempoDeCartel > 0)
			{
			this.tiempoDeCartel = this.tiempoDeCartel -1;
			}
		else
			{
			this.destroy();
			}
	}
}
