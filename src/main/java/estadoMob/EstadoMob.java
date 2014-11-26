package estadoMob;

import com.uqbar.vainilla.DeltaState;

import componentes.Mob;

public class EstadoMob {
	
	private double yInicial;
	private Mob mob;
	
	public boolean realizandoSalto() {return false;}

	public double getyInicial() {return yInicial;}

	public void setyInicial(double yInicial) {this.yInicial = yInicial;}

	public Mob getMob() {return mob;}

	public void setMob(Mob mob) {this.mob = mob;}
	
	public void update(DeltaState deltaState){}
}
