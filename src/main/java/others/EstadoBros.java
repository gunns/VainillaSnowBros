package others;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;

public class EstadoBros {
	
	private double yInicial;
	private Bros bros;

	public double getyInicial() {
		return yInicial;
	}

	public void setyInicial(double yInicial) {
		this.yInicial = yInicial;
	}
	
	public boolean realizandoSalto(){
		return false;
	}
	public void saltar(){
	}

	public Bros getBros() {
		return bros;
	}

	public void setBros(Bros bros) {
		this.bros = bros;
	}
	
	public void update(DeltaState deltaState){
		
	}
	
}
