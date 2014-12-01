package others;

import java.awt.Dimension;

import mobs.DisparoFuego;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;
import agresividad.EstadoAgresividad;
import boss.Boss;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;

import componentes.Bros;
import componentes.Mob;
import componentes.Snow;

public abstract class Direccion {
	
	public abstract void avanzaDisparo(Snow snow);
	
	public abstract boolean puedeRecorrer(Dimension dim, Snow snow);

	public void rodar(Mob mob, DeltaState deltaState) {
		// TODO Auto-generated method stub
		
	}
	protected double disparoExtendido(Snow snow){
		double i = 0;
		if (snow.isRango()){
			i= i + 0.40;
		}
		return i;
	}
	public Direccion getDir() {
		return null;
	}

	public void setDir(Direccion dir) {
	}

	public abstract void moverEsfera(Bros bros, Mob mob, DeltaState deltaState);



	public abstract Direccion direccionContraria();

	public void primerMovimiento(Mob mob, DeltaState deltaState) {
		// TODO Auto-generated method stub
		
	}

	public abstract  void desplazarBrosArrastrado(Bros bros, Mob mob, DeltaState deltaState );

	public abstract void moverMob(EstadoAgresividad e, DeltaState deltaState);

	public abstract void setearImagenSalto(Mob mob);

	public abstract void setearImagen(Mob mob);

	public abstract void recorreFuego(DisparoFuego disparoFuego);

	public abstract void spriteDisparar(TrollVerde tv);

	public abstract void spritefuego(DisparoFuego disparoFuego);

	public abstract  void reacomodarBrosSiEsNecesario(Bros bros, Mob mob);

	public abstract void spritePorCornear(TrollRojoEnojado tr);

	public abstract void cornear(TrollRojoEnojado trollRojoEnojado, DeltaState deltaState);

	public abstract boolean terminoRecorrido(Mob mob);

	public abstract void moverBoss(Boss boss);
}
