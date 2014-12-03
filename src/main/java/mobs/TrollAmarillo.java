package mobs;

import java.awt.Dimension;

import com.uqbar.vainilla.appearances.Sprite;

import apariencias.AparienciaMob;
import componentes.Mob;

public class TrollAmarillo extends Mob{
	Sprite moverDrc;
	Sprite moverIzq;
	
	public TrollAmarillo(Dimension dim, boolean playState, double velocity) {
		super(dim, playState, velocity * 2, new AparienciaMob("mob3Drc.png", "mob3Izq.png", "mob3SaltoDrc.png", "mob3SaltoIzq.png", "mob3Baja.png", "mob3Muerto.png"));
		moverDrc = Sprite.fromImage("mob3MueveDrc.png");
		moverIzq = Sprite.fromImage("mob3MueveIzq.png");
		
		this.setApariencia(apariencia);
	}
}
