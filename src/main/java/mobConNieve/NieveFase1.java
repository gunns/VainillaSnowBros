package mobConNieve;

import agresividad.Pasivo;

import com.uqbar.vainilla.appearances.Sprite;
import componentes.Mob;
import componentes.Snow;

import estadoMob.CayendoMob;

public class NieveFase1 extends EstadoNieve {

	public NieveFase1(Mob mob, double duracion) {
		super(mob, duracion);
		Sprite sprite = Sprite.fromImage("MobSnow1.png");
		this.getMob().setAppearance(sprite);
		mob.setEstado(new CayendoMob(mob.getY(), mob));
		mob.setEstadoAgresividad(new Pasivo(mob));
	}

	@Override
	public void derritiendoNieve() {
		if (this.getDuracionNieve() < 1) {
			this.getMob().setY(this.getMob().getY() - 10);
			this.getMob().setEstadoNieve(new SinNieve(this.getMob(), 0));
		} else {
			this.setDuracionNieve(this.getDuracionNieve() - 1);
		}
	}

	@Override
	public void agregandoNieve(Snow snow) {
		if (snow.isPotencia())
			this.getMob().setEstadoNieve(
					new NieveFase3(this.getMob(), ((double) 500)));
		else
			this.getMob().setEstadoNieve(
					new NieveFase2(this.getMob(), ((double) 500)));

		snow.getBros().sumarPuntaje(5);
	}

}
