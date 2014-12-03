package estadoBros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;
import componentes.Bros;
import componentes.Mob;

public class SiendoArrastrado extends EstadoBros {

	public Mob esfera;
	public Bros bros;

	public SiendoArrastrado(Mob mob, Bros bros) {
		this.esfera = mob;
		this.bros = bros;
	}

	public void update(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.A)) {
			this.saltar();
		}

		esfera.getDir().desplazarBrosArrastrado(bros, esfera, deltaState);
		bros.setInvencible(true);
	}

	@Override
	public void cambiarMovimiento(Bros bros) {
		bros.setY(bros.getY() - 60);
		bros.setEstado(new CayendoBros(this.getyInicial(), bros));
	}

	public boolean siendoArrastrado() {
		return true;
	}

	public void saltar() {
		this.cambiarMovimiento(bros);
		bros.setTiempoInvencible(250);
	}
}
