package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

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
	public void agregandoNieve() {		
		//TODO modificar al agregar los demas estados
		this.getMob().setEstadoNieve(new NieveFase2(this.getMob(), ((double)500) ));		
	}
}
