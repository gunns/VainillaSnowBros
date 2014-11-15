package others;

import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;

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

	public abstract void moverEsfera(Mob mob, DeltaState deltaState);



	public abstract Direccion direccionContraria();
}
