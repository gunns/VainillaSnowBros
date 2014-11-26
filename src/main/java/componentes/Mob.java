package componentes;

import java.awt.Dimension;
import java.util.Random;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import estadoMob.CayendoMob;
import estadoMob.EstadoMob;
import mobConNieve.EstadoNieve;
import mobConNieve.SinNieve;
import agresividad.EstadoAgresividad;
import agresividad.Pasivo;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Mob extends GameComponent<SnowBrosScene> {
	
	public Dimension gameDimension;
	public boolean playState = true;
	private int ancho = 10;
	private int alto = 25;
	public EstadoNieve estadoNieve;
	public EstadoMob estado;
	public double velocity;
	Random r = new Random();
	public EstadoAgresividad estadoAgresividad;
	public boolean derecha = true;

	public EstadoAgresividad getEstadoAgresividad() {
		return estadoAgresividad;
	}

	public void setEstadoAgresividad(EstadoAgresividad estadoAgresividad) {
		this.estadoAgresividad = estadoAgresividad;
	}

	//MOVIMIENTO
	public Direccion dir;
	
	public Mob(Dimension dim, boolean playState, double velocity){
		Sprite sprite = Sprite.fromImage("MobIzq.png");
		this.setAppearance(sprite);
		this.dir =  new Derecha();
		this.gameDimension= dim;
		this.playState = playState;
		this.setEstadoAgresividad(new Pasivo(this));
		this.setEstado(new CayendoMob(this.getY(),this));
		this.setX(r.nextInt(gameDimension.width-(int)this.getAppearance().getWidth()));
		this.setY(r.nextInt(gameDimension.height)-60);
		this.setZ(0);
		this.setEstadoNieve(new SinNieve(this, 0));
		this.velocity = velocity;
	}
	
	public EstadoMob getEstado() {return estado;}
	
	public void setEstado(EstadoMob estado) {this.estado = estado;}
	
	public int getAncho() {return ancho;}
	
	public void setAncho(int ancho) {this.ancho = ancho;}
	
	public int getAlto() {return alto;}
	
	public void setAlto(int alto) {this.alto = alto;}
	
	public EstadoNieve getEstadoNieve() {return estadoNieve;}

	public void setEstadoNieve(EstadoNieve estadoNieve) {this.estadoNieve = estadoNieve;}
	
	public void update(DeltaState deltaState){
		if(this.getScene().getPlayState()){
		this.getEstado().update(deltaState);
		this.getEstadoNieve().update(deltaState);
		this.getEstadoAgresividad().update(deltaState);
		
		 //A veces el bros se cae solo(vaya a saber quien el por que), para que esto no pase, se posicionara siempre en la parte superior
		  //del suelo mas bajo
		  if(this.getY() > this.gameDimension.getHeight())
			  {
			  this.setY(this.gameDimension.getHeight() - 40);
			  }
		  
		}
	}
	
	public boolean colisionConNieve() {
		this.getScene().colisionConNieve(this);
		return false;
	}

	//public void empujar(Direccion dir, DeltaState deltaState) {
		// TODO Auto-generated method stub
		//mover esfera hacia esa direccion
		//dir.empujar(this, deltaState);
		
	//}
	
	
	public boolean noLlegoAlFinal() {return this.getX()+this.getAppearance().getWidth()<= gameDimension.getWidth();}
	
	public boolean noLlegoAlComienzo() {return this.getX()>=0;}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public boolean esPeligroso(){
		return this.getEstadoNieve().esPeligroso();
	}
	
	
	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	public void arrolla(Mob mob) {
		// TODO Auto-generated method stub
		this.getEstadoNieve().arrolla(mob);
	}

	public void primerAvanceIzquierdaEsfera() {
		this.setX(this.getX()-((this.getScene().getVelocity()*1.5 + (this.getScene().getVelocity()/4)) + 15));
		
	}
	
	public void primerAvanceDerechaEsfera() {
		this.setX(this.getX()+((this.getScene().getVelocity()*1.5 + (this.getScene().getVelocity()/4)) - 15));
		
	}
	
	public void moverEsferaALaDerecha(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Derecha();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getVelocity()*1.5+ (this.getVelocity()/4 - 4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlFinal()){
					this.setX(this.getX()+(this.getVelocity()+ (this.getVelocity()/4 - 4))* deltaState.getDelta());
				}
			}
		}
	}
	
	public void moverEsferaALaIzquierda(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Izquierda();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()- (this.getVelocity()*1.5 + (this.getVelocity()/4 - 4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlComienzo()){
					this.setX(this.getX()-(this.getVelocity() + (this.getVelocity()/4) - 4)* deltaState.getDelta());
				}
			}
		}
	}
	
	
	
	
}
