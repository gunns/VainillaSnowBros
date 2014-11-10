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
	
	public Direccion getDir() {
		return null;
	}

	public void setDir(Direccion dir) {
	}
}
