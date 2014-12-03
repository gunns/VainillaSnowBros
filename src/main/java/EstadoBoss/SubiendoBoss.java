package EstadoBoss;

import boss.Boss;

import com.uqbar.vainilla.DeltaState;

public class SubiendoBoss extends EstadoBoss {

	public SubiendoBoss(double yInicial, Boss unBoss) {
		super(yInicial, unBoss);
	}

	public void update(DeltaState deltaState) {
		if (this.getBoss().getY() >= this.getyInicial() - 200) {
			double incrementoVelocidad = this.getBoss().getScene()
					.getVelocity() * 2;
			double velocidadIncremento = this.getBoss().getScene()
					.getVelocity()
					+ incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento
					* deltaState.getDelta();
			double incrementoY = this.getBoss().getY()
					- velocidadIncrementadaPorDelta;
			this.getBoss().setY(incrementoY);
		} else if (this.getBoss().getY() <= this.getyInicial() - 50) {
			this.getBoss().getEstado().cambiarMovimiento(this.getBoss());
		}
	}

	public boolean realizandoSalto() {
		return true;
	}

	@Override
	public void cambiarMovimiento(Boss boss) {
		boss.setEstado(new CayendoBoss(this.getyInicial(), boss));

	}
}
