package EstadoBoss;

import com.uqbar.vainilla.DeltaState;

import boss.Boss;

public class CaidaBrusca extends EstadoBoss {

	public CaidaBrusca(Boss unBoss) {
		super(unBoss.getY(), unBoss);
	}

	@Override
	public void cambiarMovimiento(Boss boss) {
		this.getBoss().getVoice().play();
		boss.setEstado(new CayendoBoss(boss.getY(), boss));

	}

	public void update(DeltaState deltaState) {
		this.getBoss().setY(this.getBoss().getY() + 4);

	}

}
