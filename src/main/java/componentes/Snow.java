package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Direccion;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Snow extends GameComponent<SnowBrosScene> {
	
	private Dimension gameDimension;
	private Double distance;
	private Double falling;
	private Double end;
	private Direccion dir;
	private Bros bros;
	private Double initialX;
	private boolean isPotencia;
	private boolean isRango;
	
	public Snow(Dimension dim, double x, double y , Bros bros, boolean isPotencia, boolean isRango) {
		this.bros = bros;
		if(isPotencia)
			{
			this.setAppearance(new Circle(Color.white, 20));
			}
			else
			{
				this.setAppearance(new Circle(Color.white, 10));	
			}
			
		this.gameDimension = dim;
		if(isRango)
			{
			this.distance = 0.80;
			}
			else
			{
			this.distance = 1.60;
			}
		this.falling = 50.0;
		this.end = 3.0;
		this.setX(x);
		this.setY(y);
		this.initialX=x;
		this.dir = bros.getDir();
		this.isPotencia = isPotencia;
		this.isRango = isRango;
	}
	
	public Double getInitialX() {return initialX;}

	public void setInitialX(Double initialX) {this.initialX = initialX;}
	
	public boolean puedeRecorrer() {return this.bros.puedeRecorrer(gameDimension, this);}
	
	public void avanzaDisparo() {this.dir.avanzaDisparo(this);}
	
	public Direccion getDir() {return dir;}

	public void setDir(Direccion dir) {this.dir = dir;}
	
	public Double getFalling() {return falling;}
	
	public void setFalling(Double falling) {this.falling = falling;}
	
	public Double getDistance() {return distance;}

	public void setDistance(Double distance) {this.distance = distance;}

	public Double getEnd() {return end;}
	
	public void setEnd(Double end) {this.end = end;}
	
	@Override
	public void update(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
				if(puedeRecorrer()) {avanzaDisparo();} 
				else {this.destroy();}
		}
	}

	public boolean isPotencia() {
		return isPotencia;
	}

	public void setPotencia(boolean isPotencia) {
		this.isPotencia = isPotencia;
	}

	public boolean isRango() {
		return isRango;
	}

	public void setRango(boolean isRango) {
		this.isRango = isRango;
	}

	public Bros getBros() {
		return bros;
	}

	public void setBros(Bros bros) {
		this.bros = bros;
	}
	
	
	

	//RECORRIDO HACIA LA DERECHA -- MODIFICAR -- HARDCODE
	//public boolean puedeRecorrerDerecha(){
		//return ((this.gameDimension.getWidth() > this.getX()) || (this.gameDimension.getHeight() > this.getY()))
		
	
	//Agregar caso en el que choca con el piso.. y no con el limite 
	//inferior de la pantalla.
	//seria un !this.getEscene.hayColisionConAlgo()
	//colision con muro, suelo o mob
	
	//RECORRIDO HACIA LA IZQUIERDA -- MODIFICAR -- HARDCODE
	//public boolean puedeRecorrerIzquierda(){
		//return ((this.getX() > 0) || (this.gameDimension.getHeight() > this.getY()))
	
	
}
