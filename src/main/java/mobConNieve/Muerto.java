package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

public class Muerto extends EstadoNieve{
	
	public Muerto(Mob mob, double duracionMuerto) {
		super(mob, duracionMuerto);
		mob.setAppearance(new Circle(Color.MAGENTA, 10));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derritiendoNieve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregandoNieve() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(DeltaState deltaState)
	{
		if(this.getDuracionNieve() < 1)
			{
			this.getMob().destroy();
			}
		else
			{
			this.setDuracionNieve(this.getDuracionNieve() - 1);
			}
	}

}
