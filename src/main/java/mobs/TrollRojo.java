package mobs;

import java.awt.Dimension;

import apariencias.AparienciaMob;
import componentes.Mob;

public class TrollRojo extends Mob
{

	public TrollRojo(Dimension dim, boolean playState, double velocity) {
		super(dim, playState, velocity, new AparienciaMob("MobDrc.png", "MobIzq.png", "MobSaltaDrc.png", "MobSaltaIzq.png", "MobCae.png", "MobMuerto.png"));
	}

}
