package mobs;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import agresividad.EstadoAgresividad;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class AgresivoTrollVerde extends EstadoAgresividad {
	public boolean encontreBros;
	public Direccion direccionDeDisparo;

	public AgresivoTrollVerde(Mob mob) {
		super(mob);
		encontreBros = false;
		direccionDeDisparo = new Derecha();
	}

	public void update(DeltaState deltaState) {
		if (this.getMob().esPeligroso()) {
			TrollVerde tv = (TrollVerde) this.getMob();

			if (!encontreBros) {
				if (tv.getScene().brosEstaALaIzquierdaDeMob(tv)) {
					direccionDeDisparo = new Izquierda();
					this.encontreBros = true;
				}

			}
			if (tv.tiempoPreparandoDisparo <= 0) {
				tv.disparar(direccionDeDisparo);
				tv.setTiempoPreparandoDisparo(120);
				tv.setDescansandoDeDisparo(200);
				tv.volversePasivo();
				tv.setTiempoDeReaccionActual(tv.getTiempoDeReaccion());
			} else {
				tv.tiempoPreparandoDisparo--;
				direccionDeDisparo.spriteDisparar(tv);
			}
		}
	}

}
