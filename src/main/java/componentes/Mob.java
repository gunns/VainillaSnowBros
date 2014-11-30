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
import agresividad.Agresivo;
import agresividad.EstadoAgresividad;
import agresividad.Pasivo;
import apariencias.AparienciaMob;

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
	public Integer tiempoDeReaccion;
	public Integer tiempoDeReaccionActual;
	
	public AparienciaMob apariencia;
	// = new AparienciaMob("MobDrc.png", "MobIzq.png", "MobSaltaDrc.png", "MobSaltaIzq.png", "MobCae.png", "MobMuerto.png");


	
	
	
	public EstadoAgresividad getEstadoAgresividad() {
		return estadoAgresividad;
	}

	public void setEstadoAgresividad(EstadoAgresividad estadoAgresividad) {
		this.estadoAgresividad = estadoAgresividad;
	}

	//MOVIMIENTO
	public Direccion dir;
	
	public Mob(Dimension dim, boolean playState, double velocity, AparienciaMob apariencia){
		//Sprite sprite = Sprite.fromImage("MobIzq.png");
		Sprite sprite = apariencia.getMobIzquierda();
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
		this.velocity = velocity/2;
		
		this.tiempoDeReaccion = 100;
		this.tiempoDeReaccionActual = 100;
		
		this.apariencia = apariencia;
		
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
		  if(this.getY() > this.gameDimension.getHeight() + 10)
			  {
			  this.setY(this.gameDimension.getHeight() - 45);
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
	//TODO RECORDAR: COLOCAR SPRITE DEL BROS MOVIENDO LA ESFERA
	public void moverEsferaALaDerecha(Bros bros, DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Derecha();
				if (this.noLlegoAlFinal()){
					//Bros se mueve a la derecha, por lo que hay que asegurarse que el bros está a la izquierda
					if(bros.getX()< this.getX())
					{
					this.setX(this.getX()+ (bros.getVelocity() + (bros.getVelocity()/4))* deltaState.getDelta());
					}
				}
			}
		}
	
	//TODO RECORDAR COLOCAR SPRITE DEL BROS MOVIENDO LA ESFERA
	public void moverEsferaALaIzquierda(Bros bros, DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Izquierda();
				if (this.noLlegoAlComienzo())
					{
					//Bros se mueve a la izquierda, por lo que hay que aseurarse que el bros está a la derecha
					if(bros.getX() > this.getX())
						{
						this.setX(this.getX()- (bros.getVelocity() + (bros.getVelocity()/4))* deltaState.getDelta());
						}
					}
			}
	}

	public Integer getTiempoDeReaccion() {
		return tiempoDeReaccion;
	}

	public void setTiempoDeReaccion(Integer tiempoDeReaccion) {
		this.tiempoDeReaccion = tiempoDeReaccion;
	}

	public Integer getTiempoDeReaccionActual() {
		return tiempoDeReaccionActual;
	}

	public void setTiempoDeReaccionActual(Integer tiempoDeReaccionActual) {
		this.tiempoDeReaccionActual = tiempoDeReaccionActual;
	}

	public AparienciaMob getApariencia() {
		return apariencia;
	}

	public void setApariencia(AparienciaMob apariencia) {
		this.apariencia = apariencia;
	}
	
	
	public void volverseAgresivo(){
		this.setEstadoAgresividad(new Agresivo(this));
	}

	public boolean brosCercanoEstaVivo() {
			// TODO Auto-generated method stub
			return (this.getScene().brosCercanoEstaVivo(this));
		}

	public void volversePasivo() {
		this.setEstadoAgresividad(new Pasivo(this));
		
	}
	
}
