package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;
import componentes.Snow;

public class NieveFase3 extends EstadoNieve{

	public NieveFase3(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.white, ((int) this.getMob().getAlto())));
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase2(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);	
		}
		else { this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve(Snow snow) {
		double tiempoActual = this.getDuracionNieve();
		
		//TODO modificar al agregar los demas estados
		this.getMob().setEstadoNieve(new NieveFaseF(this.getMob(), ((double)800 + tiempoActual) ));
		
		snow.getBros().sumarPuntaje(5);
	}
}
