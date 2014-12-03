package estadosCapsula;

public class EstadoCapsula {

	boolean prisa;
	boolean potencia;
	boolean rango;

	public EstadoCapsula() {
		prisa = false;
		potencia = false;
		rango = false;
	}

	public boolean isPrisa() {
		return prisa;
	}

	public void setPrisa(boolean prisa) {
		this.prisa = prisa;
	}

	public boolean isPotencia() {
		return potencia;
	}

	public void setPotencia(boolean potencia) {
		this.potencia = potencia;
	}

	public boolean isRango() {
		return rango;
	}

	public void setRango(boolean rango) {
		this.rango = rango;
	}

}
