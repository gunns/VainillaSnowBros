package EstadoBoss;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

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
		this.getBoss().getVoice().play();
		boss.setEstado(new CayendoBoss(boss.getY(), boss));
		
	}
	
	public void update(DeltaState deltaState)
		{
			this.getBoss().setY(this.getBoss().getY() + 4);
			
		}

}
