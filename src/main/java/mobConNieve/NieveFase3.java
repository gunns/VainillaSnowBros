package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

public class NieveFase3 extends EstadoNieve{

	public NieveFase3(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.white, ((int) this.getMob().getAlto())));
		//this.getMob().setAppearance(new Circle(Color.white, ((int) (this.getMob().getAppearance().getHeight() * 2) / 3)));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derritiendoNieve() {
		// TODO Auto-generated method stub
				if(this.getDuracionNieve() < 1){
					this.getMob().setEstadoNieve(new NieveFase2(this.getMob(), 500));
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
		this.getMob().setEstadoNieve(new NieveFaseF(this.getMob(), ((double)800 +  tiempoActual) ));
		
	}

}
