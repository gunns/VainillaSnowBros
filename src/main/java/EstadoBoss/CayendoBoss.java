package EstadoBoss;

import boss.Boss;

import com.uqbar.vainilla.DeltaState;

public class CayendoBoss extends EstadoBoss {

	public CayendoBoss(double yInicial, Boss unBoss) {
		super(yInicial, unBoss);
	}

	@Override
	public void cambiarMovimiento(Boss boss) {
		this.getBoss().setEstado(
				new SubiendoBoss(this.getBoss().getY(), this.getBoss()));

	}

	public void update(DeltaState deltaState) {
		if (!this.getBoss().getScene().tocoFondo(this.getBoss())) {
			double incrementoVelocidad = this.getBoss().getScene()
					.getVelocity() / 4;
			double velocidadIncremento = this.getBoss().getScene()
					.getVelocity()
					+ incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento
					* deltaState.getDelta();
			double incrementoY = this.getBoss().getY()
					+ velocidadIncrementadaPorDelta;
			this.getBoss().setY(incrementoY);
		} else {
			if (!this.getBoss().isAcabaDeTocarElPiso()) {
				this.getBoss().golpeoElPiso();
				this.setyInicial(this.getBoss().getY());
			}
		}
	}

}