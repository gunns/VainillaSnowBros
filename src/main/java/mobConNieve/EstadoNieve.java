package mobConNieve;

//import java.awt.Color;

import others.Direccion;

import com.uqbar.vainilla.DeltaState;
//import com.uqbar.vainilla.appearances.Circle;

import componentes.Bros;
import componentes.Mob;
import componentes.Snow;

public abstract class EstadoNieve {
	
	private Mob mob;
	private double duracionNieve;
	public abstract void derritiendoNieve();
	public abstract void agregandoNieve(Snow snow);
	
	public EstadoNieve(Mob mob, double duracionNieve){
		this.mob = mob;
		this.duracionNieve = duracionNieve;
	}

	public Mob getMob() {return mob;}

	public void setMob(Mob mob) {this.mob = mob;}

	public double getDuracionNieve() {return duracionNieve;}

	public void setDuracionNieve(double duracionNieve) {this.duracionNieve = duracionNieve;}
	
	public boolean PuedoEmpujar() { return false;}
	
	public boolean esPeligroso() { return false;}
	
	public void update(DeltaState deltaState) 
{
		if(this.getMob().colisionConNieve())
			{ 
			this.getMob().colisionConNieve();
			}
			else {
				this.derritiendoNieve();
				 }
}
	public int getRebotes() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setRebotes(int i) {
		// TODO Auto-generated method stub
		
	}
	public Direccion getDir() {
		return null;
	}

	public void setDir(Direccion dir) {
	}

public boolean puedeRebotar(){
	return false;
}
public void arrastrarBros(Bros bros) {
	// TODO Auto-generated method stub
	
}
public  void arrolla(Mob mob)
	{
	if(mob.getScene().colisionaEsferaConEsfera(mob, this.getMob()))
	this.morir(mob.getEstadoNieve().getBros());
	
	
	}

public void morir(Bros bros) {
	// TODO Auto-generated method stub
	//this.getMob().setAppearance(new Circle(Color.MAGENTA, 15));
	this.getMob().setEstadoNieve(new Muerto(this.getMob(), bros, 500));
	
}

public Bros getBros(){
	return null;
}
}
