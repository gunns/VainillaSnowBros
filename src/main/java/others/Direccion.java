package others;

import java.awt.Dimension;

import componentes.Snow;

public abstract class Direccion {
	
	public abstract void avanzaDisparo(Snow snow);
	
	public abstract boolean puedeRecorrer(Dimension dim, Snow snow);
}
