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
	
	public Snow(Dimension dim, double x, double y , Bros bros) {
		this.bros = bros;
		this.setAppearance(new Circle(Color.white, 10));
		this.gameDimension = dim;
		this.distance = 60.0;
		this.falling = 50.0;
		this.end = 3.0;
		this.setX(x);
		this.setY(y);
		this.initialX=x;
		this.dir = bros.getDir();
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
