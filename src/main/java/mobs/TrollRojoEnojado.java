package mobs;

import java.awt.Dimension;

import others.Direccion;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

import apariencias.AparienciaMob;
import componentes.Mob;

public class TrollRojoEnojado extends Mob{
	public Integer tiempoPreparandoCorneada;
	public Integer descansandoDeCorneada;
	
	public Sprite corneadaDerecha;
	public Sprite corneadaIzquierda;
	
	public Sprite preparandoCorneadaDerecha;
	public Sprite preparandoCorneadaIzquierda;

	public TrollRojoEnojado(Dimension dim, boolean playState, double velocity) {
		super(dim, playState, velocity, new AparienciaMob("mob4MueveDrc.png", "mob4MueveIzq.png", "mob4SaltoDrc.png", "mob4SaltoIzq.png", "mob4Baja.png", "mob4Muere.png"));
		// TODO Auto-generated constructor stub
		this.tiempoPreparandoCorneada = 120;
		
		
		
		corneadaDerecha = Sprite.fromImage("mob4CorneadaDrc.png");
		corneadaIzquierda = Sprite.fromImage("mob4CorneadaIzq.png");
		
		preparandoCorneadaDerecha = Sprite.fromImage("mob4PreparaCorneadaDrc.png");
		preparandoCorneadaIzquierda = Sprite.fromImage("mob4PreparaCorneadaIzq.png");
		this.volversePasivo();
	}
	
	
	public void volverseAgresivo()
	{
		this.setEstadoAgresividad(new AgresivoTrollRojoEnojado(this));
	}

	
	public void volversePasivo()
	{
	this.setEstadoAgresividad(new PasivoTrollRojoEnojado(this));
	}

	public Integer getTiempoPreparandoCorneada() {
		return tiempoPreparandoCorneada;
	}


	public void setTiempoPreparandoCorneada(Integer tiempoPreparandoCorneada) {
		this.tiempoPreparandoCorneada = tiempoPreparandoCorneada;
	}


	public Integer getDescansandoDeCorneada() {
		return descansandoDeCorneada;
	}


	public void setDescansandoDeCorneada(Integer descansandoDeCorneada) {
		this.descansandoDeCorneada = descansandoDeCorneada;
	}


	public Sprite getCorneadaDerecha() {
		return corneadaDerecha;
	}


	public void setCorneadaDerecha(Sprite corneadaDerecha) {
		this.corneadaDerecha = corneadaDerecha;
	}


	public Sprite getCorneadaIzquierda() {
		return corneadaIzquierda;
	}


	public void setCorneadaIzquierda(Sprite corneadaIzquierda) {
		this.corneadaIzquierda = corneadaIzquierda;
	}


	public Sprite getPreparandoCorneadaDerecha() {
		return preparandoCorneadaDerecha;
	}


	public void setPreparandoCorneadaDerecha(Sprite preparandoCorneadaDerecha) {
		this.preparandoCorneadaDerecha = preparandoCorneadaDerecha;
	}


	public Sprite getPreparandoCorneadaIzquierda() {
		return preparandoCorneadaIzquierda;
	}


	public void setPreparandoCorneadaIzquierda(Sprite preparandoCorneadaIzquierda) {
		this.preparandoCorneadaIzquierda = preparandoCorneadaIzquierda;
	}


	public void cornear(Direccion direccionDeCorneada, DeltaState deltaState) {
		direccionDeCorneada.cornear(this, deltaState);
		
	}
	
	public void update(DeltaState deltaState)
	{
		super.update(deltaState);
	}
	
	

}
