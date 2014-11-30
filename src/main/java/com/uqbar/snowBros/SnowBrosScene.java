package com.uqbar.snowBros;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
import java.util.List;

//import javax.swing.Timer;








import others.Derecha;
import others.Direccion;
import others.Izquierda;
import sonidoContinuo.Musica;
import suelos.Piso;
import suelos.Suelo;
//import sonidoContinuo.MusicaFondo;
import tesoros.Tesoro;
import mobConNieve.Empujado;
import mobs.DisparoFuego;
import mobs.TrollAmarillo;
import mobs.TrollRojo;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;
import Cartel.CartelLevelComplete;
import capsulas.Capsula;
import capsulas.CapsulaPotencia;
import capsulas.CapsulaPrisa;
import capsulas.CapsulaRango;
//import componentes.AnimacionRegeneracion;
import componentes.Bros;
import componentes.CartelInicioNivel;
import componentes.CartelSiguienteNivel;
import componentes.Enemigos;
import componentes.Fondo;
import componentes.Fondos;
import componentes.Mob;
import componentes.Cartel;
import componentes.Puntaje;
import componentes.Reanimacion;
import componentes.Reposicionar;
import componentes.Snow;
import componentes.Vidas;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
//import com.uqbar.vainilla.sound.SoundPlay;
//import com.uqbar.vainilla.sound.SoundPlayer;








import dulces.Caramelo;
import dulces.Paleta;
import dulces.Pastel;
//import estadoBros.CayendoBros;


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
	
	//sonido
	private Sound gameSound;
	//musica fondo
	
	
	private Musica musica;
	
	
	//private MusicaFondo music;
	
	public SnowBrosScene(Dimension dim, double velocity) throws Exception{
		super();
		
		this.numeroNivel = 1;
		this.nivelCompleto = false;
		
		CartelInicioNivel cartelInicio = new CartelInicioNivel(dim, numeroNivel, velocity);
		this.velocity=velocity;
		this.gameDimension= dim;
		this.buildBackground(Color.blue);
		
		this.addComponent(cartelInicio);
		
		
		/*
		
		
		
		this.bros=new Bros(dim,this.playState, velocity);
		this.addComponent(this.bros);
		this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity());
		this.addComponents(this.enemigos.getEnemigos());
		this.suelo= new Suelo(this.gameDimension);
		this.addComponents(suelo.getSuelos());
		
		CapsulaPrisa c1 = new CapsulaPrisa(200);
		c1.setX(100);
		c1.setY(300);
		this.addComponent(c1);
		
		Puntaje puntaje = new Puntaje(bros, 20, 20);
		bros.setPuntaje(puntaje);
		this.addComponent(puntaje);
		
		Vidas vida1 = new Vidas(bros);
		bros.setVidas(vida1);
		this.addComponent(vida1);
		
		*/
		this.initSound();
		
		musica = new Musica();
		this.addComponent(musica);
		musica.reproducir();
		
		this.suelo= new Suelo(this.gameDimension);
		this.addComponents(suelo.getSuelos());
		//this.playContinue();
		
		
		//for(int i = 1; i > 0; i ++  )
		//this.gameSound.play();
		
		
		
		
		//musica = new SoundPlay();
		
		
	}
	
	

	protected void initSound() {
		this.gameSound= new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("stage1.wav"));
	}
	
	private void buildBackground(Color color) {
		if (backGround != null) {this.removeComponent(this.backGround);}
		Fondos fondo = new Fondos();
//		Fondo name = fondo.getFondos().get(4);
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
		//Piso piso;
		//for(int n = 0; n < this.suelo.getSuelos().size(); n++){
		for(GameComponent<?> each : this.getComponents())
			//piso = this.suelo.getSuelos().get(n);
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
		//Piso piso;
		//for(int n = 0; n < this.suelo.getSuelos().size(); n++){
		for(GameComponent<?> each : this.getComponents())
			//piso = this.suelo.getSuelos().get(n);
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
		this.buildBackground(Color.black);
		
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
	//Se asegura que haya un Mob que colisiona con el bros
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
					//bros.empujar(each, deltaState);
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
	
	/*
	public boolean colisionInternaBolaNieveFConBros(Bros bros){
		boolean hayColision = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(esUnMob(each))
				{
				Mob mob = (Mob) each;
				if(mob.getEstadoNieve().PuedoEmpujar())
					{
					if(CollisionDetector.INSTANCE.collidesCircleAgainstRect(mob.getX(), mob.getY(),
							mob.getAppearance().getWidth()/2,bros.getX(), bros.getY(),
							bros.getAppearance().getWidth(), bros.getAppearance().getHeight()))
								{
						
								if(
								  (mob.getX()< (bros.getX()+bros.getAppearance().getWidth()) && (mob.getX()+ mob.getAppearance().getWidth()) > )
								  
								hayColision = true;
								}
					}
				}
			}
		return hayColision;
	}
	*/
	
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
		//for(int n = 0; n < this.suelo.getSuelos().size(); n++){
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
				//mob.getEstadoNieve().arrastrarBros(bros);
				}
			}
		}
	
	return colisiona;
	}
	
	public Mob esferaQueColisionaConBros(Bros bros)
	{//Este metodo retorna mob con estado Empujado si o si
		Mob esfera = null;
		for(GameComponent<?> each : this.getComponents())
		{
		if(esUnMob(each))
			{
			Mob mob = (Mob) each;
			
			if(this.colisionBolaRodanteConBros(each, bros) && mob.getEstadoNieve().puedeRebotar())
				{
				esfera = mob; 
				//mob.getEstadoNieve().arrastrarBros(bros);
				}
			}
		}
		return esfera;
	} 
	
	public Mob esferaFQueColisionaConBros(Bros bros)
	{//Este metodo retorna mob con estado Empujado si o si
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
				//mob.getEstadoNieve().arrastrarBros(bros);
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
				//bros.saltar();
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
	

	
	/*private void playContinue() {
		this.gameSound.play();
		
	}*/


	public void comenzarNivel(Dimension dim, double velocity) {
		if(this.esPrimerNivel())
		{
		
		this.bros= new Bros(dim,this.playState, velocity);
		Reanimacion r = new Reanimacion(bros, this, 17);
		this.addComponent(r);
		this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity());
		this.addComponents(this.enemigos.getEnemigos());
		
		//CapsulaPrisa c1 = new CapsulaPrisa(200);
		//c1.setX(100);
		//c1.setY(300);
		//this.addComponent(c1);
		
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
//	    SoundPlayer.INSTANCE.parar(this.music.getGameSound());
		
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
		//this.musica.parar();
		Sound sonidoNivelCompleto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("nextLevel.wav"));
		//																												"levelComplete2.wav"
	      sonidoNivelCompleto.play();
	      this.numeroNivel = numeroNivel +1;
	      this.musica.actualizar(this.numeroNivel);
	      
		//this.stop();
		
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
		//nivel+1
		
				//TODO mucho que hacer
				//Reposicionar el bros al inicio de la pantalla
					
				
					this.reposicionar();
					
					//agregar Enemigos
					//por inv.rep. no debería haber ninguno
					this.enemigos=new Enemigos(this.gameDimension,this.playState, this.getVelocity());
					this.addComponents(this.enemigos.getEnemigos());
					//agrega pisos nuevos y  quita los anteriores
					this.nuevosPisos();
					
					
					
					//cartel
					
					CartelSiguienteNivel cartel = new CartelSiguienteNivel(gameDimension, this.numeroNivel);
					this.addComponent(cartel);
								
				
				
			 	 //unBros.invencible = true;
			 	 //unBros.tiempoInvencible = 300;
			 	 //correccion de error de reposicionamiento del bros al morir
			 	 //unBros.setEstado(new CayendoBros((this.gameDimension.getHeight()-(unBros.getAppearance().getHeight())-25),unBros));
				//this.reanimarBros(unBros);
				}



	private void nuevosPisos() {
		// TODO Auto-generated method stub
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Piso.class)
				{
				Piso unPiso = (Piso) each;
				unPiso.setSiguienteNivel(true);
				//this.removeComponent(each);
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
				//estaAbajo = (bros.getY() - bros.getAppearance().getHeight()) -(mob.getY()- mob.getAppearance().getHeight()) <= 70;
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
				//estaArriba = (mob.getY()- mob.getAppearance().getHeight()) - (bros.getY() - bros.getAppearance().getHeight()) <= 70 ;
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
				//estaArriba = (mob.getY()- mob.getAppearance().getHeight()) - (bros.getY() - bros.getAppearance().getHeight()) <= 70 ;
				}
			}
		return estaArriba;
	}
	
	

	private void reposicionar() {
		
		
	
//			}
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros unBros = (Bros) each;
				//unBros.setNivelCompleto(false);
				//this.removeComponent(unBros);
				//bros.nuevoNivel(this);
				//this.reanimarBros(unBros);
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
		//TODO modificar para 2 jugadores
		return this.getBros().getX()>mob.getX();
	}



	public boolean brosEstaALaIzquierdaDeMob(Mob mob) {
		//TODO modificar para 2 jugadores
		return mob.getX()>this.getBros().getX();
	}



	public boolean hayUnBrosCerca(Mob mob) {
		// tanto en X como en Y
		boolean estaCerca = false;
		//boolean encontre = false;
		//TODO modificar para 2 jugadores 
		
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
	
	//cercano = (distanciaX <= (this.gameDimension.getWidth()/5)) && (distanciaY <= (this.gameDimension.getHeight()/3));
	cercano = (distanciaX <= 100) && (distanciaY <= 300);
	return cercano;
	}
	
	
	
	
	public boolean brosCercanoEstaVivo(Mob mob){
		


		//TODO modificar para 2 jugadores
		boolean cercanoYVivo = false;
		for(GameComponent<?> each : this.getComponents())
			{
			if(each.getClass() == Bros.class)
				{
				Bros bros = (Bros)each;
				if(!bros.isMuriendo() && brosCercanoAMob(bros, mob))
					{
					//encontre
					cercanoYVivo = true;
					}
				}
				
			}
		return cercanoYVivo;
		
	}



	public Direccion ubicacionDelBros(Mob mob) {
		//boolean encontre = false;
		//TODO modificar para 2 jugadores 
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
			
	
	//cercano = (distanciaX <= (this.gameDimension.getWidth()/5)) && (distanciaY <= (this.gameDimension.getHeight()/3));
	
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



	
	
	
}