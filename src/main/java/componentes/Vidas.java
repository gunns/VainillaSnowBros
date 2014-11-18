package componentes;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class Vidas extends GameComponent<SnowBrosScene>{
	
	Integer cantidadVidas;
	Bros bros;
	
	public Vidas(Bros bros)
	{
		this.bros = bros;
		cantidadVidas = 2;
		
		Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
		Label label =new Label(font ,Color.green ,"bros:" +(this.cantidadVidas).toString());

		this.setAppearance(label);
		
		this.setX(20);
		this.setY(40);
				//Font.ITALIC, Color.red, ((Integer)this.puntos).toString()));

	}

	public Integer getCantidadVidas() {
		return cantidadVidas;
	}

	public void setCantidadVidas(Integer cantidadVidas) {
		this.cantidadVidas = cantidadVidas;
	}

	public Bros getBros() {
		return bros;
	}

	public void setBros(Bros bros) {
		this.bros = bros;
	}
	
	
	
}
