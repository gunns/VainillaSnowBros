package mobConNieve;

import java.awt.Color;

import tesoros.Tesoro;
import tesoros.Tesoros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;
import dulces.Caramelo;
import dulces.Dulce;

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
			//TODO ejemplo de dulce, se necesita de un algoritmo para generar el objeto
			Tesoros tesoros = new Tesoros(); 
			Tesoro premio = (Tesoro)tesoros.dropear();
			premio.setX(this.getMob().getX());
			premio.setY(this.getMob().getY() - this.getMob().getAppearance().getHeight());
			this.getMob().getScene().addComponent(premio);
			this.getMob().destroy();
			}
		else
			{
			this.setDuracionNieve(this.getDuracionNieve() - 1);
			}
	}

}
