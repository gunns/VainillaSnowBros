package mobConNieve;

import com.uqbar.vainilla.DeltaState;

import componentes.Mob;

public abstract class EstadoNieve {
	
	private Mob mob;
	private double duracionNieve;
	
	public EstadoNieve(Mob mob, double duracionNieve){
		this.mob = mob;
		this.duracionNieve = duracionNieve;
	}

	public Mob getMob() {
		return mob;
	}

	public void setMob(Mob mob) {
		this.mob = mob;
	}

	public double getDuracionNieve() {
		return duracionNieve;
	}

	public void setDuracionNieve(double duracionNieve) {
		this.duracionNieve = duracionNieve;
	}

	
	public abstract void derritiendoNieve();
	public abstract void agregandoNieve();

	public void update(DeltaState deltaState) {
		if(this.getMob().colisionConNieve()){
			agregandoNieve();
		}
		else
		{
		this.derritiendoNieve();
		}
	}
	
	
	public boolean PuedoEmpujar(){
		return false;
	}
	
	public boolean esPeligroso(){
		return false;
	}
	
}
