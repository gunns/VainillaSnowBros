package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

import componentes.Mob;

public class NieveFase2 extends EstadoNieve{

	
	
	
	public NieveFase2(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.white, ((int) this.getMob().getAlto() - 3)));
		//this.getMob().setAppearance(new Circle(Color.white, ((int) this.getMob().getAppearance().getHeight() * 2)));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derritiendoNieve() {
		// TODO Auto-generated method stub
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase1(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);
			
		}
		else
		{
			this.setDuracionNieve(this.getDuracionNieve() - 1);
		}
			
	}

	@Override
	public void agregandoNieve() {
		// TODO Auto-generated method stub
				double tiempoActual = this.getDuracionNieve();
				
				//TODO modificar al agregar los demas estados
				this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), ((double)500 +  tiempoActual) ));
		
	}

}
