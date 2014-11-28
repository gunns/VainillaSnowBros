package apariencias;

import com.uqbar.vainilla.appearances.Sprite;

public class AparienciaMob {
	public Sprite mobDerecha;
	public Sprite mobIzquierda;
	public Sprite mobSaltaDerecha;
	public Sprite mobSaltaIzquierda;
	public Sprite mobBaja;
	public Sprite mobMuere;
	
	public AparienciaMob(String mobDerecha, String mobIzquierda, String mobSaltaDerecha, String mobSaltaIzquierda, String mobBaja, String mobMuere )
	{
		this.mobDerecha = Sprite.fromImage(mobDerecha);
		this.mobIzquierda = Sprite.fromImage(mobIzquierda);
		this.mobSaltaDerecha = Sprite.fromImage(mobSaltaDerecha);
		this.mobSaltaIzquierda = Sprite.fromImage(mobSaltaIzquierda);
		this.mobBaja = Sprite.fromImage(mobBaja);
		this.mobMuere = Sprite.fromImage(mobMuere);
	}

	public Sprite getMobDerecha() {
		return mobDerecha;
	}

	public void setMobDerecha(Sprite mobDerecha) {
		this.mobDerecha = mobDerecha;
	}

	public Sprite getMobIzquierda() {
		return mobIzquierda;
	}

	public void setMobIzquierda(Sprite mobIzquierda) {
		this.mobIzquierda = mobIzquierda;
	}

	public Sprite getMobSaltaDerecha() {
		return mobSaltaDerecha;
	}

	public void setMobSaltaDerecha(Sprite mobSaltaDerecha) {
		this.mobSaltaDerecha = mobSaltaDerecha;
	}

	public Sprite getMobSaltaIzquierda() {
		return mobSaltaIzquierda;
	}

	public void setMobSaltaIzquierda(Sprite mobSaltaIzquierda) {
		this.mobSaltaIzquierda = mobSaltaIzquierda;
	}

	public Sprite getMobBaja() {
		return mobBaja;
	}

	public void setMobBaja(Sprite mobBaja) {
		this.mobBaja = mobBaja;
	}

	public Sprite getMobMuere() {
		return mobMuere;
	}

	public void setMobMuere(Sprite mobMuere) {
		this.mobMuere = mobMuere;
	}

	
}
