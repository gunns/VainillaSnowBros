package estadoBros;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public abstract class EstadoBros {
	
	private double yInicial;
	private Bros bros;

	public double getyInicial() {return yInicial;}

	public void setyInicial(double yInicial) {this.yInicial = yInicial;}
	
	public boolean realizandoSalto(){return false;}
	
	public void saltar() {}

	public Bros getBros() {return bros;}

	public void setBros(Bros bros) {this.bros = bros;}
	
	public void update(DeltaState deltaState){}

	public abstract void cambiarMovimiento(Bros bros);

	public boolean siendoArrastrado() {
		// TODO Auto-generated method stub
		return false;
	}
}
