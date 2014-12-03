package estadoMob;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class SubiendoMob extends EstadoMob {

	public SubiendoMob(double yInicial, Mob mob) {
		this.setyInicial(yInicial);
		this.setMob(mob);
	}

	@Override
	public void cambiarMovimiento(Mob mob) {
		this.getMob().setEstado(
				new CayendoMob(this.getyInicial(), this.getMob()));
	}

	public void update(DeltaState deltaState) {
		if (this.getMob().getY() >= this.getyInicial() - 100) {
			double incrementoVelocidad = this.getMob().getScene().getVelocity() * 2;
			double velocidadIncremento = this.getMob().getScene().getVelocity()
					+ incrementoVelocidad;
			double velocidadIncrementadaPorDelta = velocidadIncremento
					* deltaState.getDelta();
			double incrementoY = this.getMob().getY()
					- velocidadIncrementadaPorDelta;
			this.getMob().setY(incrementoY);
		} else if (this.getMob().getY() <= this.getyInicial() - 50) {
			this.getMob().getEstado().cambiarMovimiento(this.getMob());
		}
	}

	public boolean realizandoSalto() {
		return true;
	}
}