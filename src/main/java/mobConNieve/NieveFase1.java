package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;
import componentes.Snow;

public class NieveFase1 extends EstadoNieve {
	
	public NieveFase1( Mob mob, double duracion ){
		super(mob, duracion);
		this.getMob().setAppearance(new Circle(Color.white, this.getMob().getAlto() - 6));
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setY(this.getMob().getY()-10);
			this.getMob().setEstadoNieve(new SinNieve(this.getMob(), 0));
		}
		else { this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve(Snow snow) {	
		if(snow.isPotencia())
			this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), ((double)500) ));	
		//TODO modificar al agregar los demas estados
		else
		this.getMob().setEstadoNieve(new NieveFase2(this.getMob(), ((double)500) ));		
	}


}
