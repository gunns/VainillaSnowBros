package mobConNieve;

//import java.awt.Color;

//import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Mob;
import componentes.Snow;

public class SinNieve extends EstadoNieve {
	
	public SinNieve( Mob mob, double duracion ){
		super(mob, duracion);
		Sprite sprite = Sprite.fromImage("MobIzq.png");
		this.getMob().setAppearance(sprite);
	}
	
	@Override
	public void derritiendoNieve() {
		//No hace nada, ya que no hay nieve
	}
	
	@Override
	public void agregandoNieve(Snow snow) {
		double tiempoActual = this.getDuracionNieve();
		if(snow.isPotencia())
			this.getMob().setEstadoNieve(new NieveFase2(this.getMob(), (((double)500) + tiempoActual ) ));
		else
		//TODO modificar al agregar los demas estados
		this.getMob().setEstadoNieve(new NieveFase1(this.getMob(), (((double)500) + tiempoActual ) ));
		
		snow.getBros().sumarPuntaje(5);
	}
	
	public boolean esPeligroso() { return true;}
	
	public  void arrolla(Mob mob)
	{
	if(mob.getScene().colisionaEsferaConMob(mob, this.getMob()))
	this.morir(mob.getEstadoNieve().getBros());
	}

}
