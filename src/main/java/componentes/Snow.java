package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Direccion;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.events.constants.Key;

public class Snow extends GameComponent<SnowBrosScene> {
	
	private boolean playState = true;
	private Dimension gameDimension;
	private double velocity;
	private Double distance;
	private Double falling;
	private Double end;
	private Direccion dir;
	private Bros bros;
	//String sprite
	
	public Snow(Dimension dim, Integer velocity, double x, double y , Bros bros) {
		this.bros = bros;
		this.setAppearance(new Circle(Color.white, 10));
		this.playState = true;
		this.gameDimension = dim;
		this.velocity = velocity;
		this.distance = 70.0;
		this.falling = 20.0;
		this.end = 30.0;
		this.setX(x);
		this.setY(y);
		this.dir = bros.getDir();
	}
	
	
	
	

	@Override
	public void update(DeltaState deltaState) 
		{
			if(!this.getScene().getSystemPause())
			{
				if(puedeRecorrer()){
					avanzaDisparo();		
									} 
			else
				{
				this.destroy();
				}
			}
		}
	
	//RECORRIDO HACIA LA DERECHA -- MODIFICAR -- HARDCODE
	//public boolean puedeRecorrerDerecha(){
		//return ((this.gameDimension.getWidth() > this.getX()) || (this.gameDimension.getHeight() > this.getY()))
		
	public boolean puedeRecorrer(){
		// TODO
		return this.bros.puedeRecorrer(gameDimension, this);
		//this,
	}
	//Agregar caso en el que choca con el piso.. y no con el limite 
	//inferior de la pantalla.
	//seria un !this.getEscene.hayColisionConAlgo()
	//colision con muro, suelo o mob
	
	//RECORRIDO HACIA LA IZQUIERDA -- MODIFICAR -- HARDCODE
	//public boolean puedeRecorrerIzquierda(){
		//return ((this.getX() > 0) || (this.gameDimension.getHeight() > this.getY()))
	
	
	
	
	
	/*   DISPARO HACIA LA DERECHA
	public void avanzaDisparoDerecha(){
	if(this.distance > 0){
		this.setX(this.getX() + 1);
		this.distance = distance - 1;
						 }
	else
		{
		if(this.falling > 0){
		this.setX(this.getX() + 1);
		this.setY(this.getY() + 1);
		}
		else
			{
			this.setY(this.getY() + 1);
			}
		}
	}
	
	*/
	
	/*   DISPARO HACIA LA IZQUIERDA
	public void avanzaDisparoIzquierda(){
	if(this.distance > 0){
		this.setX(this.getX() - 1);
		this.distance = distance - 1;
						 }
	else
		{
		if(this.falling > 0){
		this.setX(this.getX() - 1);
		this.setY(this.getY() + 1);
		}
		else
			{
			this.setY(this.getY() + 1);
			}
		}
	}
	
	*/
	
	public void avanzaDisparo(){
		this.dir.avanzaDisparo(this);
	}
	
	





	public Direccion getDir() {
		return dir;
	}





	public void setDir(Direccion dir) {
		this.dir = dir;
	}





	public Double getFalling() {
		return falling;
	}





	public void setFalling(Double falling) {
		this.falling = falling;
	}





	public Double getDistance() {
		return distance;
	}





	public void setDistance(Double distance) {
		this.distance = distance;
	}





	public Double getEnd() {
		return end;
	}





	public void setEnd(Double end) {
		this.end = end;
	}
	
	
}
