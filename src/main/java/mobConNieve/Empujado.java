package mobConNieve;

import java.util.List;

import others.Direccion;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;
import componentes.Mob;

import com.uqbar.vainilla.GameComponent;

public class Empujado extends EstadoNieve{

	private int rebotes;
	public Direccion dir;
	
	public Empujado(Mob mob, double duracionNieve, Direccion dir) {
		super(mob, duracionNieve);
		this.rebotes = 3;
		this.dir = dir;
		mob.setVelocity(mob.getVelocity() * 5);
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

	public int getRebotes() {
		return rebotes;
	}

	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
	
	public void update(DeltaState deltaState) 
	{
		this.dir.rodar(this.getMob(), deltaState); 
		//if(this.getMob().getScene().colisionaEstaEsferaConBros(this.getMob()))
			this.getMob().getScene().arrastrarBros(this.getMob());
			
	}
	
	public void arrastrarBros(Bros bros)
	{
		bros.setInvencible(true);
		bros.setX(this.getMob().getX() + this.getMob().getAppearance().getWidth()/2);
	}
	

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}
	
	public boolean puedeRebotar(){
		return true;
	}
	
		
	
	
}