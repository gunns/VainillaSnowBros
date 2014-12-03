package componentes;

public class Fondo {
	private String file;
	private double escala1;
	private double escala2;

	public Fondo(String file, double escala1, double escala2) {
		this.file = file;
		this.escala1 = escala1;
		this.escala2 = escala2;

	}

	public double getEscala1() {
		return this.escala1;
	}

	public double getEscala2() {
		return this.escala2;
	}

	public String getFile() {
		return this.file;
	}
}
