package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;

public class NieveFaseF extends EstadoNieve{
	
	public NieveFaseF(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		this.getMob().setAppearance(new Circle(Color.CYAN, ((int) this.getMob().getAlto() + 6)));
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);
		}
		else {this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve() {		
		//TODO modificar al agregar los demas estados
		this.setDuracionNieve((double)1200);		
	}

	public boolean PuedoEmpujar() { return true;}

	@Override
	public void arrolla(Mob mob) {
		if(mob.getScene().colisionaEsferaConEsfera(this.getMob(),mob ) )
			{
			this.getMob().setEstadoNieve(new Empujado(this.getMob(), 0, this.getMob().getDir().direccionContraria()));
			mob.getEstadoNieve().setDir(mob.getDir().direccionContraria());
			}
	}
}
