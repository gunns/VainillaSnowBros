package componentes;

import java.awt.Dimension;

import others.Derecha;
import others.Direccion;
import others.Izquierda;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import estadoBros.CayendoBros;
import estadoBros.EstadoBros;
import estadoBros.SiendoArrastrado;
import estadoBros.SubiendoBros;
import estadosCapsula.EstadoCapsula;

public class Bros extends GameComponent<SnowBrosScene> {

	// MOVIMIENTO
	private Integer puntajeParaVida;
	public boolean invencible = true;
	public int tiempoInvencible = 500;
	public EstadoBros estado;
	public boolean realizandoSalto = false;
	public Dimension gameDimension;
	public boolean playState = true;
	private int ancho = 10;
	private int alto = 25;
	private double velocity;
	public boolean derecha = true;

	public boolean muriendo;
	public Integer tiempoMuriendo;
	public Integer muriendoYSubiendo;
	public boolean invisible;
	public Integer tiempoInvisible;
	// DISPARO
	public Direccion dir;

	public double aparicionEnX;
	public double aparicionEnY;

	EstadoCapsula estadoCapsula;
	Puntaje puntaje;
	Vidas vidas;

	Sound sonidoSalto;

	boolean aparicion;
	boolean nivelCompleto;

	boolean reposicionando;

	public Bros(Dimension dim, boolean playState, double velocity) {
		Sprite sprite = Sprite.fromImage("BrosDrc.png");
		this.setAppearance(sprite);
		this.dir = new Derecha();
		this.gameDimension = dim;
		this.playState = playState;
		this.setEstado(new CayendoBros(this.getY(), this));
		this.aparicionEnX = this.gameDimension.getWidth() / 2
				- this.getAppearance().getWidth() / 2;
		this.aparicionEnY = this.gameDimension.getHeight()
				- (this.getAppearance().getHeight()) - 25;
		this.setX(aparicionEnX);
		this.setY(aparicionEnY);
		this.setZ(1);
		this.setVelocity(velocity);
		this.estadoCapsula = new EstadoCapsula();
		this.muriendo = false;
		this.tiempoMuriendo = 180;
		this.muriendoYSubiendo = 150;
		this.puntajeParaVida = 15000;

		this.sonidoSalto = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("jump.wav"));
		invisible = false;
		tiempoInvisible = 200;
		aparicion = true;
		nivelCompleto = false;
		reposicionando = false;

	}

	protected boolean getPlayState() {
		return this.playState;
	}

	protected void setPlayState(boolean playState) {
		this.playState = playState;
	}

	public EstadoBros getEstado() {
		return estado;
	}

	public void setEstado(EstadoBros estado) {
		this.estado = estado;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void update(DeltaState deltaState) {

		if (!this.getScene().getPlayState()) {
			if (deltaState.isKeyReleased(Key.O)) {
				this.getScene().setPlayState(true);
			}
		}

		if (this.getScene().getPlayState()) {
			if (deltaState.isKeyReleased(Key.U)) {
				this.getScene().setPlayState(false);
			}
			if (this.reposicionando) {

				this.nuevoNivel();
			} else {
				if (this.nivelCompleto) {
					this.saltarAlSiguienteNivel();
					Sprite sprite = Sprite.fromImage("win.png");
					this.setAppearance(sprite);
				} else {

					if (this.getEstado().siendoArrastrado()) {
						this.getEstado().update(deltaState);
					}
					if (this.getScene().enemigosExterminados()) {
						this.getScene().nivelCompleto();
						this.nivelCompleto = true;

					}
					if (this.getY() > this.gameDimension.getHeight()) {
						this.setY(this.gameDimension.getHeight() - 40);
					}
					if (this.getX() > this.gameDimension.getWidth()) {
						this.setX(this.gameDimension.getWidth());
					}
					if (this.getX() < 0) {
						this.setX(0);
					}

					this.getScene().tomarTesoro(this);

					if (this.muriendo) {
						this.brosMuere();
					} else {

						if (this.invencible
								&& !this.getEstado().siendoArrastrado()) {
							this.tiempoInvencible--;
							if (this.tiempoInvencible < 1) {
								this.invencible = false;
							}
						}

						if (!this.getScene().enemigoColisionaConBros(this)
								|| (this.getScene().enemigoColisionaConBros(
										this) && this.invencible)) {

							if ((this.getScene()
									.esferaRodanteColisionaConBros(this))) {

								Mob esfera = this.getScene()
										.esferaQueColisionaConBros(this);
								this.setEstado(new SiendoArrastrado(esfera,
										this));

							} else {

								// COMANDO SALTAR

								if (deltaState.isKeyBeingHold(Key.DOWN)
										&& deltaState.isKeyPressed(Key.A)) {
									if (this.derecha
											&& this.getY() < this.gameDimension
													.getHeight() - 40
											&& this.getScene()
													.hayColisionConUnPiso(this)) {
										Sprite sprite = Sprite
												.fromImage("BrosSaltaDrc.png");
										this.setAppearance(sprite.crop(
												ancho + 7, alto));
										this.setY(this.getY() + 5);
										Sound sonidoSalto = new SoundBuilder()
												.buildSound(this
														.getClass()
														.getClassLoader()
														.getResourceAsStream(
																"jump_11.wav"));
										sonidoSalto.play();
									}
									if (!this.derecha
											&& this.getY() < this.gameDimension
													.getHeight() - 40
											&& this.getScene()
													.hayColisionConUnPiso(this)) {
										Sprite sprite = Sprite
												.fromImage("BrosSaltaIzq.png");
										this.setAppearance(sprite.crop(
												ancho + 7, alto));
										this.setY(this.getY() + 5);
										Sound sonidoSalto = new SoundBuilder()
												.buildSound(this
														.getClass()
														.getClassLoader()
														.getResourceAsStream(
																"jump_11.wav"));
										sonidoSalto.play();
									}
								}
								this.getEstado().update(deltaState);

								// COMANDO MOVER
								if (deltaState.isKeyBeingHold(Key.RIGHT)
										&& !this.getEstado().siendoArrastrado()) {
									this.derecha = true;
									Sprite sprite = Sprite
											.fromImage("BrosDrc.png");
									this.setAppearance(sprite);
									this.moverALaDerecha(deltaState);

								} else if (deltaState.isKeyBeingHold(Key.LEFT)
										&& !this.getEstado().siendoArrastrado()) {
									this.derecha = false;
									Sprite sprite = Sprite
											.fromImage("BrosIzq.png");
									this.setAppearance(sprite);
									this.moverALaIzquierda(deltaState);
								}

								// COMANDO:DISPARAR
								if (deltaState.isKeyPressed(Key.S)
										&& !this.getEstado().siendoArrastrado()) {
									this.CongelaOEmpuja(deltaState);
								}

								// RENDER SPRITE AL DEJAR DE DISPARAR
								if (deltaState.isKeyReleased(Key.S)) {
									if (this.derecha) {
										Sprite sprite = Sprite
												.fromImage("BrosDrc.png");
										this.setAppearance(sprite);
									} else {
										Sprite sprite = Sprite
												.fromImage("BrosIzq.png");
										this.setAppearance(sprite);
									}
								}
							}
							// COMANDO SALTAR
							if (deltaState.isKeyPressed(Key.A)) {
								if (this.derecha) {
									Sprite sprite = Sprite
											.fromImage("BrosSaltaDrc.png");
									this.setAppearance(sprite.crop(ancho + 7,
											alto));
									this.saltar();
								} else {
									Sprite sprite = Sprite
											.fromImage("BrosSaltaIzq.png");
									this.setAppearance(sprite.crop(ancho + 7,
											alto));
									this.saltar();
								}
							}

						} else {
							// MORIR
							this.muriendo = true;
							Sound sonidoMuerte = new SoundBuilder()
									.buildSound(this
											.getClass()
											.getClassLoader()
											.getResourceAsStream(
													"Player Death.wav"));
							sonidoMuerte.play();
							Sprite sprite = Sprite.fromImage("BrosMuere.png");
							this.setAppearance(sprite);
						}
					}
				}
			}
		}
	}

	private void brosMuere() {
		if (this.muriendoYSubiendo > 1) {
			this.muriendoYSubiendo = this.muriendoYSubiendo - 1;
			this.setY(this.getY() - 0.20);
		} else if (this.tiempoMuriendo > 1) {
			this.tiempoMuriendo = this.tiempoMuriendo - 1;
		} else {
			this.muriendo = false;
			this.tiempoMuriendo = 180;
			this.muriendoYSubiendo = 150;
			if (this.vidas.getCantidadVidas() == 0) {
				this.getScene().cartelLose();
				this.getScene().gameOverMusic();
				this.getScene().removeComponent(this);
			} else {
				this.vidas.setCantidadVidas(this.vidas.getCantidadVidas() - 1);
				this.invencible = true;
				this.tiempoInvencible = 300;
				this.setEstado(new CayendoBros((this.gameDimension.getHeight()
						- (this.getAppearance().getHeight()) - 25), this));
				this.getScene().reanimarBros(this);
				this.getScene().removeComponent(this);
			}
		}
	}

	// COMPROBAR CASO SI EMPUJA BOLA DE NIEVE O SOLO DISPARA NIEVE
	public void CongelaOEmpuja(DeltaState deltaState) {
		if (this.getScene().puedeEmpujarBolaDeNieve(this, deltaState)) {
			if (this.derecha) {
				this.getScene().empujar(this, deltaState);
			} else {
				this.saltar();
				this.getScene().empujar(this, deltaState);
			}
		} else {
			if (this.derecha) {
				Sprite sprite = Sprite.fromImage("BrosDisparaDrc.png");
				this.setAppearance(sprite);
				this.disparar();
				Sound sonidoDisparo = new SoundBuilder().buildSound(this
						.getClass().getClassLoader()
						.getResourceAsStream("snowShot.wav"));
				sonidoDisparo.play();

			} else {
				this.saltar();
				Sprite sprite = Sprite.fromImage("BrosDisparaIzq.png");
				this.setAppearance(sprite);
				this.disparar();
				Sound sonidoDisparo = new SoundBuilder().buildSound(this
						.getClass().getClassLoader()
						.getResourceAsStream("snowShot.wav"));
				sonidoDisparo.play();
			}

		}
	}

	// DISPARAR NIEVE
	private void disparar() {
		Double alturaDeDisparo = this.getY()
				- ((this.getAppearance().getHeight()) / 10);
		Snow snow = new Snow(gameDimension, (this.getX()), alturaDeDisparo,
				this, this.getEstadoCapsula().isPotencia(), this
						.getEstadoCapsula().isRango());
		this.getScene().addComponent(snow);
	}

	// SALTA
	public void saltar() {
		this.getEstado().saltar();
	}

	private void moverALaDerecha(DeltaState deltaState) {
		if (!this.getScene().getSystemPause()) {
			this.dir = new Derecha();
			this.getScene().moverEsfera(this, deltaState);
			if (this.noLlegoAlFinal()) {
				if (this.getScene().hayColisionConUnaEsfera(this)) {
					Mob mob = this.getScene().esferaFQueColisionaConBros(this);
					if (mob.getX() < this.getX()
							|| (mob.getY() > this.getY() && !this.getScene()
									.hayColisionConUnPiso(this))) {
						this.setX(this.getX()
								+ (this.getVelocity() + (this.getVelocity() / 4))
								* deltaState.getDelta());
					}
				} else {
					this.setX(this.getX()
							+ (this.getVelocity() + (this.getVelocity() / 4))
							* deltaState.getDelta());
				}

			}
		}
	}

	private void moverALaIzquierda(DeltaState deltaState) {
		if (!this.getScene().getSystemPause()) {
			this.dir = new Izquierda();
			this.getScene().moverEsfera(this, deltaState);
			if (this.noLlegoAlComienzo()) {
				if (this.getScene().hayColisionConUnaEsfera(this)) {
					Mob mob = this.getScene().esferaFQueColisionaConBros(this);
					if (mob.getX() > this.getX()
							|| (mob.getY() > this.getY() && !this.getScene()
									.hayColisionConUnPiso(this))) {
						this.setX(this.getX()
								- (this.getVelocity() + (this.getVelocity() / 4))
								* deltaState.getDelta());
					}
				} else {
					this.setX(this.getX()
							- (this.getVelocity() + (this.getVelocity() / 4))
							* deltaState.getDelta());
				}
			}
		}
	}

	private boolean noLlegoAlFinal() {
		return this.getX() + this.getAppearance().getWidth() <= gameDimension
				.getWidth();
	}

	private boolean noLlegoAlComienzo() {
		return this.getX() >= 0;
	}

	// MOVIMIENTO NIEVE
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		return this.dir.puedeRecorrer(dim, snow);
	}

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	public boolean isInvencible() {
		return invencible;
	}

	public void setInvencible(boolean invencible) {
		this.invencible = invencible;
	}

	public int getTiempoInvencible() {
		return tiempoInvencible;
	}

	public void setTiempoInvencible(int tiempoInvencible) {
		this.tiempoInvencible = tiempoInvencible;
	}

	public EstadoCapsula getEstadoCapsula() {
		return estadoCapsula;
	}

	public void setEstadoCapsula(EstadoCapsula estadoCapsula) {
		this.estadoCapsula = estadoCapsula;
	}

	public void sumarPuntaje(Integer puntos) {

		this.getPuntaje().sumarPuntaje(puntos);
		if (this.getPuntaje().puntos >= this.puntajeParaVida) {
			this.getVidas().setCantidadVidas(
					this.getVidas().getCantidadVidas() + 1);
			Sound vidaExtra = new SoundBuilder().buildSound(this.getClass()
					.getClassLoader().getResourceAsStream("extraLife.wav"));
			vidaExtra.play();
			this.puntajeParaVida = this.puntajeParaVida + 15000;
		}

	}

	public Puntaje getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Puntaje puntaje) {
		this.puntaje = puntaje;
	}

	public Vidas getVidas() {
		return vidas;
	}

	public void setVidas(Vidas vidas) {
		this.vidas = vidas;
	}

	public double getAparicionEnX() {
		return aparicionEnX;
	}

	public void setAparicionEnX(double aparicionEnX) {
		this.aparicionEnX = aparicionEnX;
	}

	public double getAparicionEnY() {
		return aparicionEnY;
	}

	public void setAparicionEnY(double aparicionEnY) {
		this.aparicionEnY = aparicionEnY;
	}

	public void esferaSueltaBros() {
		this.setEstado(new SubiendoBros(this.getY(), this));

	}

	private void saltarAlSiguienteNivel() {
		if (this.getY() > -40 && (this.nivelCompleto)) {
			this.setY(this.getY() - 2.5);
		} else {
			this.setY(-40);
		}
	}

	public boolean isNivelCompleto() {
		return nivelCompleto;
	}

	public void setNivelCompleto(boolean nivelCompleto) {
		this.nivelCompleto = nivelCompleto;
	}

	public void nuevoNivel() {
		this.setEstado(new CayendoBros((this.gameDimension.getHeight()
				- (this.getAppearance().getHeight()) - 25), this));
		Reposicionar reposicion = new Reposicionar(this, this.getScene(), 17);
		this.getScene().addComponent(reposicion);
		this.reposicionando = false;
	}

	public boolean isReposicionando() {
		return reposicionando;
	}

	public void setReposicionando(boolean reposicionando) {
		this.reposicionando = reposicionando;
	}

	public boolean isMuriendo() {
		return muriendo;
	}

	public void setMuriendo(boolean muriendo) {
		this.muriendo = muriendo;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void matarBros() {
		this.muriendo = true;
		Sound sonidoMuerte = new SoundBuilder().buildSound(this.getClass()
				.getClassLoader().getResourceAsStream("Player Death.wav"));
		sonidoMuerte.play();
		Sprite sprite = Sprite.fromImage("BrosMuere.png");
		this.setAppearance(sprite);
	}

}
