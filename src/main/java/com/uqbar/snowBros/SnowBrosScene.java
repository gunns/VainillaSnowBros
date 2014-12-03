package com.uqbar.snowBros;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import sonidoContinuo.Musica;
import suelos.Piso;
import suelos.Suelo;
import tesoros.Tesoro;
import mobConNieve.Empujado;
import mobs.DisparoFuego;
import mobs.TrollAmarillo;
import mobs.TrollRojo;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;
import Cartel.CartelLevelComplete;
import Cartel.CartelWin;
import boss.Boss;
import boss.NivelBoss;
import capsulas.Capsula;
import capsulas.CapsulaPotencia;
import capsulas.CapsulaPrisa;
import capsulas.CapsulaRango;
import componentes.Bros;
import componentes.CartelInicioNivel;
import componentes.CartelSiguienteNivel;
import componentes.Enemigos;
import componentes.Fondo;
import componentes.Fondos;
import componentes.InicioJuego;
import componentes.Mob;
import componentes.Cartel;
import componentes.Puntaje;
import componentes.Reanimacion;
import componentes.Reposicionar;
import componentes.Snow;
import componentes.Vidas;
import dulces.Caramelo;
import dulces.Paleta;
import dulces.Pastel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;


public class SnowBrosScene extends GameScene{
	
	private Integer numeroNivel;
	
	private boolean nivelCompleto;
	private Dimension gameDimension;
	private double velocity;
	private GameComponent<GameScene> backGround;
	private boolean playState = true;
	private boolean systemPause = false;
	
	private Bros bros;
	private Enemigos enemigos;
	private Suelo suelo;
	
	private Sound gameSound;

	
	private Musica musica;
	public boolean nivelBoss; 
	
	public SnowBrosScene(Dimension dim, double velocity) throws Exception{
		super();
		nivelBoss = false;
		this.velocity=velocity;
		this.gameDimension= dim;
		InicioJuego inicio = new InicioJuego(gameDimension);
		this.addComponent(inicio);
		inicio.reproducir();
		
		Sprite sprite = Sprite.fromImage("inicioSnowBros.png");

		if (backGround != null) {this.removeComponent(this.backGround);}
		
		this.backGround = new GameComponent<GameScene>(sprite.scale(0.46, 0.57), 0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
		
		
		musica = new Musica();
		this.addComponent(musica);
		
			
	}
	
	public void comenzarJuego()
	{

		this.numeroNivel = 1;
		this.nivelCompleto = false;
		
		CartelInicioNivel cartelInicio = new CartelInicioNivel(gameDimension, numeroNivel, velocity);
		
		
		this.buildBackground(Color.blue);
		
		this.addComponent(cartelInicio);
		
		musica = new Musica();
		this.addComponent(musica);
		musica.reproducir();
		
		this.suelo= new Suelo(this.gameDimension);
		this.addComponents(suelo.getSuelos());
	}
	
	private void buildBackground(Color color) {
		if (backGround != null) {this.removeComponent(this.backGround);}
		Fondos fondo = new Fondos();
		Fondo name = fondo.randomFondo();
		Sprite sprite = Sprite.fromImage(name.getFile());;
		this.backGround = new GameComponent<GameScene>(sprite.scale(name.getEscala1(),name.getEscala2()),0, 0);
		this.backGround.setZ(-1);
		this.addComponent(this.backGround);
	}
	
	public boolean getPlayState() {return this.playState;}
	
	public void setPlayState(boolean playState) {this.playState = playState;}
	
	public boolean getSystemPause() {return this.systemPause;}
	
	public void setSystemPause(boolean SysPause) {this.systemPause = SysPause;}
	
	public double getVelocity() {return this.velocity;}

	public Bros getBros() {return this.bros;}
	
	
	public boolean hayColisionConUnPiso(GameComponent<SnowBrosScene> componenteRectangular){
		boolean hayColision = false;
		for(GameComponent<?> each : this.getComponents())
			if(each.getClass() == Piso.class)
			{
				Piso unPiso  = (Piso) each;
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(componenteRectangular.getX(),
					(componenteRectangular.getY() + componenteRectangular.getAppearance().getHeight()-1),
					(int) (componenteRectangular.getAppearance().getWidth()), (int) (1),
					unPiso.getX(), unPiso.getY(),(int) (unPiso.getAppearance().getWidth()),(int) (2))){
				hayColision = true;
				}
			}
		return hayColision;
		}
	
	public boolean hayColisionTotalConUnPiso(GameComponent<SnowBrosScene> componenteRectangular){
		boolean hayColision = false;
		for(GameComponent<?> each : this.getComponents())
			if(each.getClass() == Piso.class)
			{
				Piso unPiso  = (Piso) each;
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(componenteRectangular.getX(),
					componenteRectangular.getY(),
					(int) (componenteRectangular.getAppearance().getWidth()), (int) (componenteRectangular.getAppearance().getHeight()),
					unPiso.getX(), unPiso.getY(),(int) (unPiso.getAppearance().getWidth()),(int) (unPiso.getAppearance().getHeight()))){
				hayColision = true;
				}
			}
		return hayColision;
		}
	public boolean enemigoColisionaConBros(GameComponent<SnowBrosScene> componenteRectangular){
		boolean hayColision = false;
		Mob mob;
		for(int n = 0; n < this.enemigos.getEnemigos().size(); n++){
			mob= this.enemigos.getEnemigos().get(n);
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(componenteRectangular.getX(),
					componenteRectangular.getY(),(int) componenteRectangular.getAppearance().getWidth(),
					(int) componenteRectangular.getAppearance().getHeight(),
					mob.getX(), mob.getY(),(int) mob.getAppearance().getWidth(), (int) mob.getAppearance().getHeight())){
				
				if(mob.getEstadoNieve().esPeligroso()){
				hayColision = true;
			}
		}
		}
		return hayColision;
	}
	
	public void stop() {
		this.velocity= 0d;
		this.playState=false;
	}
	
	public void cartelLose(){
		
		this.musica.parar();
		this.addComponent(new Cartel(this.gameDimension,0));
		this.setPlayState(false);
		this.stop();
	}
	
	public void colisionConNieve(Mob mob) {
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(each.getClass().equals(Snow.class)){
				Snow snow = (Snow) each;
				if(this.colisionNieveConMob(each, mob)){
					mob.getEstadoNieve().agregandoNieve(snow);
					}
				}
			}
	}	

	public Mob EsferaFQueColisionaConBros(Bros bros)
	{
		Mob mob = null;
		for(GameComponent<?> each : this.getComponents())
			{
			if(esUnMob(each))
				{
				Mob esfera = (Mob) each;
				if(esfera.getEstadoNieve().PuedoEmpujar())
					{
					if(CollisionDetector.INSTANCE.collidesRectAgainstRect(esfera.getX(),
							esfera.getY(),(int) esfera.getAppearance().getWidth(),
							(int) esfera.getAppearance().getHeight(),
							bros.getX(), bros.getY(),(int) bros.getAppearance().getWidth(), (int) bros.getAppearance().getHeight()))
						{
						mob = esfera;
						}
					}
				}
			}
		return mob;
	}
	
	private boolean colisionNieveConMob(GameComponent<?> each, Mob mob2) {
		boolean hayColision = false;
		GameComponent<?> nieve = each;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(nieve.getX(), nieve.getY(),
				nieve.getAppearance().getWidth()/2,mob2.getX(), mob2.getY(),
				mob2.getAppearance().getWidth(), mob2.getAppearance().getHeight())){
			hayColision = true;
			each.destroy();
		}
		return hayColision;
	}	
	
	public boolean puedeEmpujarBolaDeNieve(Bros bros, DeltaState deltaState){
		
		
		boolean puedeEmpujar = false;
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(esUnMob(each)){
				Mob mob = (Mob) each;
				if(this.colisionBolaNieveConBros(each, bros) && mob.getEstadoNieve().PuedoEmpujar())
						{
					puedeEmpujar = true;
					}
				}
			}
		return puedeEmpujar;
	}
	
	public void empujar(Bros bros, DeltaState deltaState)
	{
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
			{
			if(esUnMob(each)){
				Mob mob = (Mob) each;
				if(this.colisionBolaNieveConBros(each, bros) && mob.getEstadoNieve().PuedoEmpujar())
						{
					    mob.setEstadoNieve(new Empujado(mob, 0, bros.getDir(), bros));
					}
				}
			}	
	}
	
	
	public boolean colisionBolaNieveConBros(GameComponent<?> each, Bros bros){
		boolean hayColision = false;
		GameComponent<?> mob = each;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(mob.getX(), mob.getY(),
				mob.getAppearance().getWidth()/2,bros.getX(), bros.getY(),
				bros.getAppearance().getWidth(), bros.getAppearance().getHeight())){
			hayColision = true;
			
			
			
		}
		return hayColision;
	}
	
	public boolean colisionBolaNieveFConBros(Bros bros, Mob mob){
		return(CollisionDetector.INSTANCE.collidesRectAgainstRect(bros.getX(),
				bros.getY(),
				(int) (bros.getAppearance().getWidth()), (int) (bros.getAppearance().getHeight()),
				mob.getX(), mob.getY(),(int) (mob.getAppearance().getWidth()),(int) (mob.getAppearance().getHeight())));
		}
	
	
	public boolean colisionBolaRodanteConBros(GameComponent<?> each, Bros bros){
		boolean hayColision = false;
		GameComponent<?> mob = each;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(mob.getX(), mob.getY(),
				mob.getAppearance().getWidth()/3,bros.getX(), bros.getY(),
				bros.getAppearance().getWidth(), bros.getAppearance().getHeight())){
			hayColision = true;
			
			
			
		}
		return hayColision;
	}

	public void moverEsfera(Bros bros, DeltaState deltaState) {
		List <GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c)
		{
		if(esUnMob(each)){
			Mob mob = (Mob) each;
			if(this.colisionBolaNieveConBros(mob, bros) && mob.getEstadoNieve().PuedoEmpujar() && this.hayColisionConUnPiso(bros))
					{
				    bros.getDir().moverEsfera(bros,mob, deltaState);
					}
			}
		}
	}
		
	
		

	public boolean hayColisionConUnaEsfera(Bros bros){
		boolean hayColision = false;
		Mob mob;
		List<GameComponent<?>> c = this.getComponents();
		for(GameComponent<?> each : c ){
			if(esUnMob(each))
			{
				mob = (Mob) each;
			if(this.colisionBolaNieveConBros(mob, bros) && mob.getEstadoNieve().PuedoEmpujar())
			{
				hayColision = true;
			}
		}
		
		}
		return hayColision;
	}
	
	public boolean esferaRodanteColisionaConBros(Bros bros)
	{
	boolean colisiona= false;
	List<GameComponent<?>> esferas = this.getComponents();
	for(GameComponent<?> each : esferas)
		{
		if(esUnMob(each))
			{
			Mob mob = (Mob) each;
			
			if(this.colisionBolaRodanteConBros(each, bros) && mob.getEstadoNieve().puedeRebotar())
				{
				colisiona = true;
				}
			}
		}
	
	return colisiona;
	}
	
	public Mob esferaQueColisionaConBros(Bros bros){
		Mob esfera = null;
		for(GameComponent<?> each : this.getComponents())
		{
		if(esUnMob(each))
			{
			Mob mob = (Mob) each;
			
			if(this.colisionBolaRodanteConBros(each, bros) && mob.getEstadoNieve().puedeRebotar())
				{
				esfera = mob; 
				}
			}
		}
		return esfera;
	} 
	
	public Mob esferaFQueColisionaConBros(Bros bros){
		Mob mob = null;
		for(GameComponent<?> each : this.getComponents())
		{
		if(esUnMob(each))
			{
			Mob esfera = (Mob) each;
			if (esfera.getEstadoNieve().PuedoEmpujar())
				{
				if(CollisionDetector.INSTANCE.collidesRectAgainstRect(esfera.getX(),
						esfera.getY(),(int) esfera.getAppearance().getWidth(),
						(int) esfera.getAppearance().getHeight(),
						bros.getX(), bros.getY(),(int) bros.getAppearance().getWidth(), (int) bros.getAppearance().getHeight()))	
					{
					mob = esfera; 
					}
				}
			}
		
		}
		return mob;
	}

	public void esferaExploto(Mob mob) {
		
		for(GameComponent<?> c : this.getComponents())
			{
			if(c.getClass() == Bros.class)
				{
				Bros bros = (Bros) c;
				if(bros.getEstado().siendoArrastrado())
				bros.getEstado().cambiarMovimiento(bros);
				bros.setTiempoInvencible(500);
				}
			}
	}

	

	public boolean colisionaEstaEsferaConBros(Mob mob) {
		boolean hayColision = false;
		for(GameComponent<?> c : this.getComponents())
		{
		if(c.getClass() == Bros.class)
			{
			Bros bros = (Bros) c;
			if(this.colisionBolaNieveConBros(mob, bros))
				{
				hayColision = true;
				}
			}
		}
		return hayColision;
	}

	public void arrastrarBros(Mob mob) {
		for(GameComponent<?> each : this.getComponents())
		{
		if(each.getClass() == Bros.class)
			{
			Bros unBros = (Bros) each;
			if(esferaRodanteColisionaConBros(unBros))
				{
				mob.getEstadoNieve().arrastrarBros(unBros);
				}
			}
		}
	}

	public void matarMobsEnElCamino(Mob mob) 
	{
		for(GameComponent<?> each : this.getComponents())
			{
			if(esUnMob(each))
				{
				Mob mobColisionado = (Mob) each;
				mobColisionado.arrolla(mob);
				}
			}
	}

	public boolean colisionaEsferaConMob(Mob mob, Mob mobColisionado) 
	{
		boolean hayColision = false;
		if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(mob.getX(), mob.getY(),
				mob.getAppearance().getWidth()/2,mobColisionado.getX(), mobColisionado.getY(),
				mobColisionado.getAppearance().getWidth(), mobColisionado.getAppearance().getHeight())){
			hayColision = true;
			
			
			
		}
		return hayColision;
	}

	public boolean colisionaEsferaConEsfera(Mob mob, Mob mob2) {
		if(CollisionDetector.INSTANCE.collidesCircleAgainstCircle(mob.getX(), mob.getY(), (int)mob.getAppearance().getWidth()/2,
				mob2.getX(), mob2.getY(), (int)mob2.getAppearance().getWidth()/2)){
			return true;
		}
		else
		return false;
	}
	
	public void tomarTesoro(Bros bros) {
		for(GameComponent<?> each : this.getComponents())
			{
			if(this.esUnTesoro(each))
				{
				Tesoro drop = (Tesoro) each;
				if(drop.colisionaConBros(bros))
					{
					drop.sumarPuntaje(bros);
					}
				}
			}
	}

	private boolean esUnTesoro(GameComponent<?> each) {
		
	return(each.getClass() == Caramelo.class
			|| each.getClass() == Paleta.class
			|| each.getClass() == Pastel.class
			|| each.getClass() == CapsulaPrisa.class
			|| each.getClass() == CapsulaPotencia.class
			|| each.getClass() == CapsulaRango.class
			|| each.getClass() == Caramelo.class
			|| each.getClass() == Capsula.class);
	}
	
	public double distanciaABros(int x, int y){
		double ret=0;
		if(x>this.getBros().getX()){
			ret=ret +(x-this.getBros().getX());
		}
		else {
			ret= ret + (this.getBros().getX()-x);
		}
		if(y>this.getBros().getX()){
			ret=ret + (y-this.getBros().getX());
		}
		else{
			ret=ret + (this.getBros().getX());
		}
		return ret;
	}
	
	public boolean tieneUnPisoJustoArriba(double x,double y, int height,int width){
		GameComponent<SnowBrosScene> rectanguloPrueba = new GameComponent<SnowBrosScene>();
		rectanguloPrueba.setX(x);
		rectanguloPrueba.setY(y);
		rectanguloPrueba.setAppearance(new Rectangle(Color.blue, width, height));
		boolean ret= this.hayColisionTotalConUnPiso(rectanguloPrueba);
		rectanguloPrueba.destroy();
		return ret;
	}
	public boolean terminaElPiso(double x,double y){
		
		GameComponent<SnowBrosScene> rectanguloPrueba=new GameComponent<SnowBrosScene>();
		rectanguloPrueba.setX(x);
		rectanguloPrueba.setY(y);
		rectanguloPrueba.setAppearance(new Rectangle(Color.blue, 1, 1));
		boolean ret = this.hayColisionConUnPiso(rectanguloPrueba);
		rectanguloPrueba.destroy();
		return !ret;
		}

	public void reanimarBros(Bros bros) 
	{
		Reanimacion reanimacion = new Reanimacion(bros, this, 17);
		this.addComponent(reanimacion);
		}
	
	public boolean estaCercaBrosdeMob(Mob mob){
		boolean ret=false;
		if(this.getBros().getY()>mob.getY()){
			ret= this.getBros().getY()-mob.getY()<70;
		}
		else{
			ret= mob.getY()-this.getBros().getY()<70;
		}
		return ret;
	}

	public Sound getGameSound() {
		return gameSound;
	}

	public void setGameSound(Sound gameSound) {
		this.gameSound = gameSound;
	}


	public void comenzarNivel(Dimension dim, double velocity) {
		if(this.esPrimerNivel())
		{
		
		this.bros= new Bros(dim,this.playState, velocity);
		Reanimacion r = new Reanimacion(bros, this, 17);
		this.addComponent(r);
		this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity(), this);
		this.addComponents(this.enemigos.getEnemigos());
		
		Puntaje puntaje = new Puntaje(bros, 20, 20);
		bros.setPuntaje(puntaje);
		this.addComponent(puntaje);
		
		Vidas vida1 = new Vidas(bros);
		bros.setVidas(vida1);
		this.addComponent(vida1);
		}
		
		
		
	}



	private boolean esPrimerNivel() {
		
		return (this.numeroNivel == 1);
	}



	public void gameOverMusic() {
		
		this.musica.parar();
		this.removeComponent(musica);
		Sound sonidoGameOver = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("gameOver.wav"));
	    sonidoGameOver.play();
		
	}



	public Dimension getGameDimension() {
		return gameDimension;
	}



	public void setGameDimension(Dimension gameDimension) {
		this.gameDimension = gameDimension;
	}
	
	
	public boolean enemigosExterminados()
	{
		boolean nivelTerminado = true;
		for(GameComponent<?> each : this.getComponents())
			{
			if(nivelBoss)
				{
				nivelTerminado = false;
				}
			else
				if(esUnMob(each) || esUnTesoro(each))
					{
					nivelTerminado = false;
					}
			}
		return nivelTerminado;
	}



	public void nivelCompleto() {
		this.nivelCompleto = true;
		CartelLevelComplete cartel = new CartelLevelComplete(gameDimension, this.numeroNivel);
		this.addComponent(cartel);
		Sound sonidoNivelCompleto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("nextLevel.wav"));
	      sonidoNivelCompleto.play();
	      this.numeroNivel = numeroNivel +1;
	      this.musica.actualizar(this.numeroNivel);
	}
	
	public void reposicionarBros(Bros bros) 
	{
		Reposicionar reposicion = new Reposicionar(bros, this, 17);
		this.addComponent(reposicion);
		}



	public void soltarBrosAdherido(Mob mob) {
		for(GameComponent<?> each: this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				if(esferaRodanteColisionaConBros(bros))
					{
					bros.esferaSueltaBros();
					}
				}
			}
		
	}



	public boolean isNivelCompleto() {
		return nivelCompleto;
	}



	public void setNivelCompleto(boolean nivelCompleto) {
		this.nivelCompleto = nivelCompleto;
	}



	public void siguienteNivel() {
		if(this.numeroNivel == 15)
		{
		
			this.nivelBoss = true;
			
			this.nivelCompleto = false;
			NivelBoss nivelBoss = new NivelBoss();
			this.addComponent(nivelBoss);
			this.removeComponent(backGround);
			this.backGround = new GameComponent<GameScene>(nivelBoss.getImagenFondo().scale(0.9, 1.2), 0, 0);
			this.addComponent(backGround);
			this.musica.actualizar(this.numeroNivel);
		}
		else
		{
			this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity(), this);
			this.addComponents(this.enemigos.getEnemigos());
		}
				
			this.reposicionar();
			
			this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity(),this);
			this.addComponents(this.enemigos.getEnemigos());
			this.nuevosPisos();
			
			CartelSiguienteNivel cartel = new CartelSiguienteNivel(gameDimension, this.numeroNivel);
			this.addComponent(cartel);

			musica.actualizar(numeroNivel);
	}


	private void nuevosPisos() {
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Piso.class)
				{
				Piso unPiso = (Piso) each;
				unPiso.setSiguienteNivel(true);
				}
			}
		
		this.suelo= new Suelo(this.gameDimension);
		for(Piso piso : suelo.getSuelos())
			{
			piso.asignarPiso(this);
			}
		this.addComponents(suelo.getSuelos());
		
		
		
	}


	public boolean brosEstaAbajoDeMob(Mob mob){
		boolean estaAbajo = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				estaAbajo= (mob.getY()- mob.getAppearance().getHeight()) < (bros.getY() - bros.getAppearance().getHeight());
				}
			}
		return estaAbajo;
	}
	
 public boolean brosEstaALaAlturaDelMob(Mob mob)
 {
	 boolean estaIgual = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				estaIgual = (
						 (bros.getY()+20) > (mob.getY())
						 &&
						 (bros.getY()-20) < mob.getY()
						 );
				}
			}
		return estaIgual;
 }
	

	public boolean brosEstaArribaDeMob(Mob mob) {
		boolean estaArriba = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				estaArriba = (mob.getY()- mob.getAppearance().getHeight()) > (bros.getY() - bros.getAppearance().getHeight());
				}
			}
		return estaArriba;
	}
	
	

	private void reposicionar() {
		

		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros unBros = (Bros) each;
				unBros.setReposicionando(true);
				}
			}

			}



	public Integer getNumeroNivel() {
		return numeroNivel;
	}



	public void setNumeroNivel(Integer numeroNivel) {
		this.numeroNivel = numeroNivel;
	}



	public boolean brosEstaALaDerechaDeMob(Mob mob) {
		return this.getBros().getX()>mob.getX();
	}



	public boolean brosEstaALaIzquierdaDeMob(Mob mob) {
		return mob.getX()>this.getBros().getX();
	}



	public boolean hayUnBrosCerca(Mob mob) {
		boolean estaCerca = false;
		
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				estaCerca = this.brosCercanoAMob(bros, mob);
				}
			}
		return estaCerca;
		
	}
	
	public boolean esUnMob(GameComponent<?> element)
	{
		return(element.getClass() == Mob.class ||
				element.getClass() == TrollVerde.class
				|| element.getClass() == TrollAmarillo.class
				|| element.getClass() == TrollRojo.class
				|| element.getClass() == TrollRojoEnojado.class);
	}
	
	
	public boolean brosCercanoAMob(Bros bros, Mob mob)
	{
		double distanciaX;
		double distanciaY;
		boolean cercano = false;
		if(bros.getX() < mob.getX())
		{
		distanciaX = mob.getX() - bros.getX();
		}
	else
		{
		distanciaX =bros.getX() -  mob.getX();
		}
	if(bros.getY() < mob.getY())
		{
		distanciaY = mob.getY() - bros.getY();
		}
	else
		{
		distanciaY = bros.getY() - mob.getY();
		}
	
	cercano = (distanciaX <= 100) && (distanciaY <= 300);
	return cercano;
	}
	
	
	
	
	public boolean brosCercanoEstaVivo(Mob mob){
		boolean cercanoYVivo = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros)each;
				if(!bros.isMuriendo() && brosCercanoAMob(bros, mob))
					{
					cercanoYVivo = true;
					}
				}
				
			}
		return cercanoYVivo;
		
	}



	public Direccion ubicacionDelBros(Mob mob) {
		Direccion ubicacionBros =  new Izquierda();
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
					Bros bros = (Bros) each;
					if(bros.getX() > mob.getX())
					{
						ubicacionBros = new Derecha();
					}
				}
			}
		return ubicacionBros;
	}



	public boolean brosCercanoATroll(TrollVerde trollVerde) {
		double distanciaX = 0;
		double distanciaY = 0;
		boolean cercano = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
		if(bros.getX() < trollVerde.getX())
		{
		distanciaX = trollVerde.getX() - bros.getX();
		}
	else
		{
		distanciaX =bros.getX() - trollVerde.getX();
		}
	if(bros.getY() < trollVerde.getY())
		{
		distanciaY = trollVerde.getY() - bros.getY();
		}
	else
		{
		distanciaY = bros.getY() - trollVerde.getY();
		}

	}
	
	}
		cercano = (distanciaX <= 300) && (distanciaY <= 300);
		return cercano;
}



	public void matarBrosEnElCamino(DisparoFuego disparo) {
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				if(!bros.muriendo)
					{
					if(CollisionDetector.INSTANCE.collidesRectAgainstRect(bros.getX(),
							bros.getY(),
							(int) (bros.getAppearance().getWidth()), (int) (bros.getAppearance().getHeight()),
							disparo.getX(), disparo.getY(),(int) (disparo.getAppearance().getWidth()),(int) (disparo.getAppearance().getHeight()))
							&& !bros.invencible)
							{
							bros.matarBros();
							}
					}
				
				}
			}
		
		
	}

	
	
	
	public boolean brosEstaALaDerecha(Boss boss) {
		boolean derecha = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros) each;
				derecha = bros.getX() > boss.getX();
				}
			}
		return derecha;
	}

	public boolean tocoFondo(Boss boss) {
		return ((boss.getY() + boss.getAppearance().getHeight())  > (this.getGameDimension().getHeight())-10);
	}

	public boolean hayEnemigos() {
			boolean hay = false;
			for(GameComponent<?> each : this.getComponents())
				{
					if(esUnMob(each))
						{
						hay = true;
						}
				}
			return hay;
		}
	

	public void invocarEnemigos()
	{
		this.enemigos= this.enemigos.enemigosParaBoss(gameDimension, playState, velocity);
		this.addComponents(this.enemigos.getEnemigos());
	}

	public boolean bossGolpeadoPorEsferaRodante(Boss boss) {
		
		boolean colisiona= false;
		List<GameComponent<?>> esferas = this.getComponents();
		for(GameComponent<?> each : esferas)
			{
			if(esUnMob(each))
				{
				Mob mob = (Mob) each;
				
				if(mob.getEstadoNieve().puedeRebotar())
					{
					if(CollisionDetector.INSTANCE.collidesRectAgainstRect(boss.getX(),
							boss.getY(),
							(int) (boss.getAppearance().getWidth()), (int) (boss.getAppearance().getHeight()),
							mob.getX(), mob.getY(),(int) (mob.getAppearance().getWidth()),(int) (2)))
						{
						colisiona = true;
						}
					}
				}
			}
		
		return colisiona;
		}

	
	public Mob esferaColisionadaConBoss(Boss boss)
	{
		Mob unMob = null;
		List<GameComponent<?>> esferas = this.getComponents();
		for(GameComponent<?> each : esferas)
			{
			if(esUnMob(each))
				{
				Mob mob = (Mob) each;
				
				if(mob.getEstadoNieve().puedeRebotar())
					{
					if(CollisionDetector.INSTANCE.collidesRectAgainstRect(boss.getX(),
							boss.getY(),
							(int) (boss.getAppearance().getWidth()), (int) (boss.getAppearance().getHeight()),
							mob.getX(), mob.getY(),(int) (mob.getAppearance().getWidth()),(int) (2)))
						{
						
						unMob = mob;
							
						}
					}
				}
			}
		return unMob;
		
	}

	public void bossDerrotado() {
		CartelWin cartel = new CartelWin(gameDimension);
		this.addComponent(cartel);
		this.musica.parar();
		this.stop();
		
		
	}

	public void bossMataBrosEnElCamino(Boss boss) {
		for(GameComponent<?> each : this.getComponents())
		{
		if(each.getClass() == Bros.class)
			{
			Bros bros = (Bros) each;
			if(!bros.muriendo)
				{
				if(CollisionDetector.INSTANCE.collidesRectAgainstRect(bros.getX(),
						bros.getY(),
						(int) (bros.getAppearance().getWidth()), (int) (bros.getAppearance().getHeight()),
						boss.getX(), boss.getY(),(int) (boss.getAppearance().getWidth()),(int) (boss.getAppearance().getHeight()))
						&& !bros.invencible)
						{
						bros.matarBros();
						}
				}
			
			}
		}
		
	}
	
}