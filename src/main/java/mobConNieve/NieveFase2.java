package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

public class NieveFase2 extends EstadoNieve{
	
	public NieveFase2(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.white, ((int) this.getMob().getAlto() - 3)));
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase1(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);
		}
		else { this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve() {
				double tiempoActual = this.getDuracionNieve();
				
				//TODO modificar al agregar los demas estados
				this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), ((double)500 +  tiempoActual) ));
	}
}
