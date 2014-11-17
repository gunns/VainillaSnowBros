package componentes;

import java.awt.Color;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class Piso extends GameComponent<SnowBrosScene>{
	
	private int ancho;
	private int alto;
	
	public Piso(double coordY, double coordX, Color col, int ancho, int alto){
		this.ancho = ancho;
		this.alto = alto;
//		this.setAppearance(Sprite.fromImage("Users/JulianV/Pictures/prueba.jpg"));
		this.setAppearance(new Rectangle(Color.black,ancho,alto));
		this.setX(coordX);
		this.setY(coordY);
	}	
	
	public int getAncho() {return ancho;}
		
	public int getAlto() {return alto;}
}