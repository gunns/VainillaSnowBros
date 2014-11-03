package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

public class NieveFaseF extends EstadoNieve{

	
	
	public NieveFaseF(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.CYAN, ((int) this.getMob().getAlto() + 6)));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derritiendoNieve() {
		// TODO Auto-generated method stub
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), 500));
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
				this.setDuracionNieve((double)1200);
				
		
	}
	
	
	public boolean PuedoEmpujar(){
		return true;
	}
	

}
