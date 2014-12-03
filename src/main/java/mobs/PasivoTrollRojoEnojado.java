package mobs;

import agresividad.Pasivo;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class PasivoTrollRojoEnojado extends Pasivo {

	public PasivoTrollRojoEnojado(Mob mob) {
		super(mob);
	}

	public void update(DeltaState deltaState) {
		TrollRojoEnojado tr = (TrollRojoEnojado) this.getMob();
		if (tr.getScene().brosEstaALaAlturaDelMob(tr)
				&& tr.getScene().hayColisionConUnPiso(tr)) {
			tr.volverseAgresivo();
		} else if (this.getMob().getTiempoDeReaccionActual() > 1) {
			this.getMob().setTiempoDeReaccionActual(
					this.getMob().getTiempoDeReaccionActual() - 1);
		} else {
			this.mover(deltaState);
		}
	}

}
