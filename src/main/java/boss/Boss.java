package boss;

import java.awt.Dimension;
import others.Derecha;
import others.Direccion;
import others.Izquierda;
import EstadoBoss.CaidaBrusca;
import EstadoBoss.EstadoBoss;
import EstadoBoss.SubiendoBoss;
import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import componentes.Explosion;
import componentes.Mob;

public class Boss extends GameComponent<SnowBrosScene> {

	Sound voice;

	/*
	 * primer (y seguramente unico) boss del juego el boss permanecerá en un
	 * solo lugar (en X) y saltará. este permanecera a la derecha A veces este
	 * saltará y se irá hacia abajo. cada cierto tiempo el boss escupirá una
	 * orda de mobs hacia la izquierda, los cuales no tendrán inteligencia y se
	 * moveran de izquierda a derecha sin dudar(SubClase de mob) y luego de un
	 * cierto tiempo estos desaparecerán.
	 */

	public Integer intervaloDeAccion;
	public boolean apareciendo;
	public Integer cantidadDeVidas;
	public Integer tiempoInactividad;
	public boolean saltando;
	public Dimension dim;
	public EstadoBoss estado;
	public Direccion direccion;
	boolean acabaDeTocarElPiso;
	private Sound golpe;

	public Boss(Dimension gameDimension) {
		golpe = new SoundBuilder().buildSound(this.getClass().getClassLoader()
				.getResourceAsStream("crash.wav"));
		apareciendo = true;
		saltando = false;
		dim = gameDimension;
		Sprite sprite = Sprite.fromImage("snowMan.png");
		this.setAppearance(sprite);
		this.setY(0);
		this.direccion = new Izquierda();

		this.setX(gameDimension.getWidth() / 2 - 1);
		tiempoInactividad = 700;
		intervaloDeAccion = 300;
		cantidadDeVidas = 12;
		this.setEstado(new CaidaBrusca(this));

		voice = new SoundBuilder().buildSound(this.getClass().getClassLoader()
				.getResourceAsStream("startBoss.wav"));

	}

	public void update(DeltaState deltaState) {
		if (!this.getScene().getSystemPause() && this.getScene().getPlayState()) {
			if (this.cantidadDeVidas <= 0) {
				this.morir();
			} else {

				if (this.cantidadDeVidas < 7) {
					intervaloDeAccion = 80;
				}
				this.getScene().bossMataBrosEnElCamino(this);

				if (this.getScene().bossGolpeadoPorEsferaRodante(this)) {
					this.cantidadDeVidas = cantidadDeVidas - 1;
					Mob mob = this.getScene().esferaColisionadaConBoss(this);

					mob.getScene().esferaExploto(mob);

					// sonido explosion
					Sound sonidoExplosion = new SoundBuilder().buildSound(this
							.getClass().getClassLoader()
							.getResourceAsStream("snowBallExplode.wav"));
					sonidoExplosion.play();
					Explosion explosion = new Explosion(mob.getX() - 15,
							mob.getY() - 20);
					mob.getScene().addComponent(explosion);

					mob.destroy();
				}

				if (apareciendo) {
					this.estado.update(deltaState);
					if (this.getScene().tocoFondo(this)) {

						this.setY(this.getDim().getHeight()
								- this.getAppearance().getHeight());
						this.getEstado().cambiarMovimiento(this);
						apareciendo = false;
					}
				} else if (saltando) {
					this.estado.update(deltaState);
					this.direccion.moverBoss(this);
				} else if (tiempoInactividad > 0) {
					tiempoInactividad--;
				} else {
					this.comenzarAMoverse();
					this.saltando = true;
				}

			}
		}
	}

	private void morir() {
		this.destroy();
		this.getScene().bossDerrotado();

	}

	private void comenzarAMoverse() {
		if (this.getScene().brosEstaALaDerecha(this)) {
			this.direccion = new Derecha();
			this.estado.cambiarMovimiento(this);
		} else {
			this.direccion = new Izquierda();
			this.setEstado(new SubiendoBoss(this.getY(), this));
		}

	}

	public void moverIzquierda() {
		this.setX(this.getX() - 0.6);

	}

	public void moverDerecha() {
		this.setX(this.getX() + 0.6);

	}

	public void invocarEnemigos() {
		this.getScene().invocarEnemigos();
	}

	public EstadoBoss getEstado() {
		return estado;
	}

	public void setEstado(EstadoBoss estado) {
		this.estado = estado;
	}

	public boolean isAcabaDeTocarElPiso() {
		return acabaDeTocarElPiso;
	}

	public void setAcabaDeTocarElPiso(boolean acabaDeTocarElPiso) {
		this.acabaDeTocarElPiso = acabaDeTocarElPiso;
	}

	public Integer getCantidadDeVidas() {
		return cantidadDeVidas;
	}

	public void setCantidadDeVidas(Integer cantidadDeVidas) {
		this.cantidadDeVidas = cantidadDeVidas;
	}

	public Integer getTiempoInactividad() {
		return tiempoInactividad;
	}

	public void setTiempoInactividad(Integer tiempoInactividad) {
		this.tiempoInactividad = tiempoInactividad;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void golpeoElPiso() {
		golpe.play();
		this.saltando = false;
		this.tiempoInactividad = intervaloDeAccion;
		if (!this.getScene().hayEnemigos()) {
			this.invocarEnemigos();
		}
	}

	public Sound getVoice() {
		return voice;
	}

	public void setVoice(Sound voice) {
		this.voice = voice;
	}
}
