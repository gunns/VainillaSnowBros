package others;

import componentes.Bros;

public class Quieto extends EstadoBros {

	public Quieto(double yInicial, Bros bros){
		this.setyInicial(yInicial);
		this.setBros(bros);
	}
	
	public void saltar(){
		this.getBros().setEstado(new Subiendo(this.getBros().getY(),this.getBros()));
	}
}
