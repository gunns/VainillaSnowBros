package componentes;

import java.util.ArrayList;
import java.util.Random;

public class Fondos {
	ArrayList<Fondo> fondos;
	Fondo Afondo;

	public Fondos() {
		this.fondos = new ArrayList<Fondo>();
		populate();
	}

	public void populate() {
		this.Afondo = new Fondo("fondo.jpg", 1.4, 1.4);
		fondos.add(Afondo);

		this.Afondo = new Fondo("background2.png", 0.8, 0.9);
		fondos.add(Afondo);

		this.Afondo = new Fondo("background3.png", 1.2, 1.4);
		fondos.add(Afondo);

		this.Afondo = new Fondo("background4.png", 0.7, 0.9);
		fondos.add(Afondo);

		this.Afondo = new Fondo("background6.png", 0.5, 0.7);
		fondos.add(Afondo);
	}

	public ArrayList<Fondo> getFondos() {
		return fondos;
	}

	public Fondo randomFondo() {
		Random r = new Random();
		int index = r.nextInt(this.fondos.size());
		return this.fondos.get(index);
	}

}
