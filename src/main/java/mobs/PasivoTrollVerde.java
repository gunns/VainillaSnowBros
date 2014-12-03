package mobs;

import agresividad.Pasivo;

import com.uqbar.vainilla.DeltaState;

public class PasivoTrollVerde extends Pasivo {

	public PasivoTrollVerde(TrollVerde mob) {
		super(mob);
	}

	public void update(DeltaState deltaState) {
		TrollVerde tv = (TrollVerde) this.getMob();
		tv.descansandoDeDisparo = tv.descansandoDeDisparo - 1;
		if (tv.brosCercanoATroll() && tv.getScene().hayColisionConUnPiso(tv))
			if (tv.descansandoDeDisparo <= 0
					&& tv.getScene().brosEstaALaAlturaDelMob(tv)) {
				tv.volverseAgresivo();
			} else if (this.getMob().getTiempoDeReaccionActual() > 1) {
				this.getMob().setTiempoDeReaccionActual(
						this.getMob().getTiempoDeReaccionActual() - 1);
			} else {
				this.mover(deltaState);
			}
	}

}
