package componentes;

import java.awt.Color;
import java.awt.Dimension;

import others.Derecha;
import others.Direccion;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

import estadoMob.CayendoMob;
import estadoMob.EstadoMob;

public class Mob extends GameComponent<SnowBrosScene> {
	
		//MOVIMIENTO
			public EstadoMob estado;
			public EstadoMob getEstado() {
				return estado;
			}
	
			public void setEstado(EstadoMob estado) {
				this.estado = estado;
			}
	
			public int getAncho() {
				return ancho;
			}
	
			public void setAncho(int ancho) {
				this.ancho = ancho;
			}
	
			public int getAlto() {
				return alto;
			}
	
			public void setAlto(int alto) {
				this.alto = alto;
			}
	
			public Dimension gameDimension;
			public boolean playState = true;
			private int ancho = 10;
			private int alto = 25;
			
			
			//DISPARO
			public Direccion dir;
			
	public Mob(Dimension dim, boolean playState){
			
			
			
			this.setAppearance(new Rectangle(Color.red,ancho,alto));
			//TODO
			this.dir =  new Derecha();
			
			this.gameDimension= dim;
			this.playState = playState;
			this.setEstado(new CayendoMob(this.getY(),this));
			this.setX(12);
			this.setY(10);
			this.setZ(1);
		}
	
	public void update(DeltaState deltaState){
		this.getEstado().update(deltaState);
	}
}
