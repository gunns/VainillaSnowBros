package tesoros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import capsulas.CapsulaPotencia;
import capsulas.CapsulaPrisa;
import capsulas.CapsulaRango;
import dulces.Caramelo;
import dulces.Paleta;

public class Tesoros {

	List<Tesoro> tesoros;

	public Tesoros() {
		tesoros = new ArrayList<Tesoro>();
		Tesoro caramelo1 = new Caramelo(50);
		Tesoro caramelo2 = new Caramelo(50);
		Tesoro caramelo3 = new Caramelo(50);
		Tesoro caramelo4 = new Caramelo(50);
		Tesoro caramelo5 = new Caramelo(50);
		Tesoro caramelo6 = new Caramelo(50);
		Tesoro caramelo7 = new Caramelo(50);
		Tesoro caramelo8 = new Caramelo(50);
		Tesoro caramelo9 = new Caramelo(50);
		Tesoro caramelo10 = new Caramelo(50);
		Tesoro caramelo11 = new Caramelo(50);
		Tesoro caramelo12 = new Caramelo(50);
		Tesoro caramelo13 = new Caramelo(50);
		Tesoro caramelo14 = new Caramelo(50);
		Tesoro caramelo15 = new Caramelo(50);
		Tesoro paleta1 = new Paleta(80);
		Tesoro paleta2 = new Paleta(80);
		Tesoro paleta3 = new Paleta(80);
		Tesoro paleta4 = new Paleta(80);
		Tesoro paleta5 = new Paleta(80);
		Tesoro paleta6 = new Paleta(80);
		Tesoro paleta7 = new Paleta(80);
		Tesoro paleta8 = new Paleta(80);
		Tesoro paleta9 = new Paleta(80);
		Tesoro paleta10 = new Paleta(80);

		Tesoro capsulaPrisa1 = new CapsulaPrisa(200);
		Tesoro capsulaPrisa2 = new CapsulaPrisa(200);
		Tesoro capsulaPotencia1 = new CapsulaPotencia(200);
		Tesoro capsulaPotencia2 = new CapsulaPotencia(200);
		Tesoro capsulaRango1 = new CapsulaRango(200);
		Tesoro capsulaRango2 = new CapsulaRango(200);

		tesoros.add(caramelo1);
		tesoros.add(caramelo2);
		tesoros.add(caramelo3);
		tesoros.add(caramelo4);
		tesoros.add(caramelo5);
		tesoros.add(caramelo6);
		tesoros.add(caramelo7);
		tesoros.add(caramelo8);
		tesoros.add(caramelo9);
		tesoros.add(caramelo10);
		tesoros.add(caramelo11);
		tesoros.add(caramelo12);
		tesoros.add(caramelo13);
		tesoros.add(caramelo14);
		tesoros.add(caramelo15);

		tesoros.add(paleta1);
		tesoros.add(paleta2);
		tesoros.add(paleta3);
		tesoros.add(paleta4);
		tesoros.add(paleta5);
		tesoros.add(paleta6);
		tesoros.add(paleta7);
		tesoros.add(paleta8);
		tesoros.add(paleta9);
		tesoros.add(paleta10);

		tesoros.add(capsulaPrisa1);
		tesoros.add(capsulaPrisa2);
		tesoros.add(capsulaPotencia1);
		tesoros.add(capsulaPotencia2);
		tesoros.add(capsulaRango1);
		tesoros.add(capsulaRango2);

	}

	public Tesoro dropear() {
		Random rand = new Random();
		int posAleatoria = rand.nextInt(tesoros.size() - 1);
		Tesoro drop = tesoros.get(posAleatoria);
		return drop;
	}

}
