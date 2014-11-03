package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;

import componentes.Mob;

public class SinNieve extends EstadoNieve {
	
public SinNieve( Mob mob, double duracion ){
	super(mob, duracion);
	this.getMob().setAppearance(new Rectangle(Color.red,this.getMob().getAncho(),this.getMob().getAlto()));
}

@Override
public void derritiendoNieve() {
	// TODO Auto-generated method stub
	//No hace nada, ya que no hay nieve
	
}

@Override
public void agregandoNieve() {
	double tiempoActual = this.getDuracionNieve();
	
	//TODO modificar al agregar los demas estados
	this.getMob().setEstadoNieve(new NieveFase1(this.getMob(), (((double)500) + tiempoActual ) ));
//	this.getMob().setAppearance(new Circle(Color.white, this.getMob().getAlto()/2));
	
	
	
}


public boolean esPeligroso(){
	return true;
}


}
