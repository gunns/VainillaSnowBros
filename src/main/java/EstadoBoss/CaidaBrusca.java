package EstadoBoss;

import com.uqbar.vainilla.DeltaState;

import estadoBros.CayendoBros;
import boss.Boss;

public class CaidaBrusca extends EstadoBoss{

	public CaidaBrusca(Boss unBoss) {
		super(unBoss.getY(), unBoss);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void cambiarMovimiento(Boss boss) {
		// TODO Auto-generated method stub
		boss.setEstado(new CayendoBoss(boss.getY(), boss));
		
	}
	
	public void update(DeltaState deltaState)
		{
			this.getBoss().setY(this.getBoss().getY() + 4);
			
		}

}
