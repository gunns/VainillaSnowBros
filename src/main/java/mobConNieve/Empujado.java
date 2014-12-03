package mobConNieve;

import others.Direccion;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import componentes.Bros;
import componentes.Mob;
import componentes.Snow;

import estadoBros.SiendoArrastrado;

public class Empujado extends EstadoNieve {

	private int rebotes;
	public Direccion dir;
	Bros bros;

	public Empujado(Mob mob, double duracionNieve, Direccion dir, Bros bros) {
		super(mob, duracionNieve);
		this.rebotes = 3;
		this.dir = dir;
		mob.setVelocity(mob.getScene().getVelocity() * 2);
		this.bros = bros;
		Sound sonidoEmpuje = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("pullSnowBall.wav"));
		sonidoEmpuje.play();

		bros.sumarPuntaje(500);
	}

	@Override
	public void derritiendoNieve() {
	}

	@Override
	public void agregandoNieve(Snow snow) {

	}

	public int getRebotes() {
		return rebotes;
	}

	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}

	public void update(DeltaState deltaState) {
		this.dir.primerMovimiento(this.getMob(), deltaState);
		this.dir.rodar(this.getMob(), deltaState);
		this.getMob().getScene().matarMobsEnElCamino(this.getMob());

	}

	public void arrastrarBros(Bros bros) {
		bros.setInvencible(true);
		bros.setTiempoInvencible(500);
		bros.setEstado(new SiendoArrastrado(this.getMob(), bros));

		bros.setX(this.getMob().getX()
				+ this.getMob().getAppearance().getWidth() / 2);
	}

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	public boolean puedeRebotar() {
		return true;
	}

	@Override
	public void arrolla(Mob mob) {

	}

	public Bros getBros() {
		return this.bros;
	}

}
