package mobs;

import java.awt.Dimension;

import others.Direccion;
import apariencias.AparienciaMob;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;
import componentes.Mob;

public class TrollVerde extends Mob {
	public Integer tiempoPreparandoDisparo;
	public Integer descansandoDeDisparo;
	public Sprite disparoDerecha;
	public Sprite disparoIzquierda;

	public TrollVerde(Dimension dim, boolean playState, double velocity) {
		super(dim, playState, velocity, new AparienciaMob("mob2Drc.png",
				"mob2Izq.png", "mob2SaltoDrc.png", "mob2SaltoIzq.png",
				"mob2Baja.png", "mob2Muere.png"));

		this.tiempoPreparandoDisparo = 120;
		this.descansandoDeDisparo = 0;

		this.disparoIzquierda = Sprite.fromImage("mob2DisparaIzq.png");
		this.disparoDerecha = Sprite.fromImage("mob2DisparaDrc.png");
	}

	public void update(DeltaState deltaState) {
		super.update(deltaState);
	}

	public Integer getTiempoPreparandoDisparo() {
		return tiempoPreparandoDisparo;
	}

	public void setTiempoPreparandoDisparo(Integer tiempoPreparandoDisparo) {
		this.tiempoPreparandoDisparo = tiempoPreparandoDisparo;
	}

	public Integer getDescansandoDeDisparo() {
		return descansandoDeDisparo;
	}

	public void setDescansandoDeDisparo(Integer descansandoDeDisparo) {
		this.descansandoDeDisparo = descansandoDeDisparo;
	}

	public void disparar(Direccion direccionDeDisparo) {
		DisparoFuego disparo = new DisparoFuego(direccionDeDisparo, this);
		this.getScene().addComponent(disparo);

	}

	public boolean brosCercanoATroll() {
		return this.getScene().brosCercanoATroll(this);
	}

	public void volversePasivo() {
		this.setEstadoAgresividad(new PasivoTrollVerde(this));
	}

	public void volverseAgresivo() {

		this.setEstadoAgresividad(new AgresivoTrollVerde(this));
	}
}
