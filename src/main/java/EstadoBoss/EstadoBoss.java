package EstadoBoss;

import boss.Boss;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public abstract class EstadoBoss {
	
	
	
	

	private double yInicial;
	private Boss boss;

	public EstadoBoss(double yInicial, Boss unBoss)
	{
	boss = unBoss;
	this.yInicial = yInicial;
	}
	
	
	
	public double getyInicial() {return yInicial;}

	public void setyInicial(double yInicial) {this.yInicial = yInicial;}
	
	public boolean realizandoSalto(){return false;}
	
	public void saltar() {}

	public Boss getBoss() {return boss;}

	public void setBoss(Boss boss) {this.boss = boss;}
	
	public void update(DeltaState deltaState){}

	public abstract void cambiarMovimiento(Boss boss);
}
