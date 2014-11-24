package componentes;

import java.awt.Dimension;

import others.Derecha;
import others.Direccion;
import others.Izquierda;
import tesoros.Tesoro;
import estadoBros.CayendoBros;
import estadoBros.EstadoBros;
import estadoBros.SiendoArrastrado;
import estadosCapsula.EstadoCapsula;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;


public class Bros extends GameComponent<SnowBrosScene>{

	//MOVIMIENTO
	public boolean invencible = true;
	public int tiempoInvencible = 1000;
	public EstadoBros estado;
	public boolean realizandoSalto= false;
	public Dimension gameDimension;
	public boolean playState = true;
	private int ancho = 10;
	private int alto = 25;
	private double velocity;
	public boolean derecha = true;
	
	public boolean muriendo;
	public Integer tiempoMuriendo;
	public Integer muriendoYSubiendo; 	
	//DISPARO
	public Direccion dir;
	
	
	//ESTADOS LUEGO DE TOMAR LAS CAPSULAS
	EstadoCapsula estadoCapsula;
	
	//Puntos
	Puntaje puntaje;
	//Vidas
	Vidas vidas;
	
	//Sonidos
	Sound sonidoSalto;
	
	
	public Bros(Dimension dim, boolean playState, double velocity){
		Sprite sprite = Sprite.fromImage("BrosDrc.png");
		this.setAppearance(sprite);
		this.dir =  new Derecha();
		this.gameDimension= dim;
		this.playState = playState;
		this.setEstado(new CayendoBros(this.getY(),this));
		this.setX(this.gameDimension.getWidth()/2-this.getAppearance().getWidth()/2);
		this.setY((this.gameDimension.getHeight()-(this.getAppearance().getHeight() ) - 60)) ;
		this.setZ(1);
		this.velocity = velocity;
		this.estadoCapsula  = new EstadoCapsula();
		this.muriendo=false;
		this.tiempoMuriendo=500;
		this.muriendoYSubiendo=500;
		
		this.sonidoSalto= new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump.wav"));
	}

	protected boolean getPlayState() {return this.playState;}
	
	protected void setPlayState(boolean playState) {this.playState = playState;}
	
	public EstadoBros getEstado() {return estado;}

	public void setEstado(EstadoBros estado) {this.estado = estado;}

	public int getAncho(){return ancho;}
	
	public int getAlto(){return alto;}
	
	
	
	public void update(DeltaState deltaState) {
		  
		  
		  if(this.getScene().getPlayState())
		  {
		   
		    this.getScene().tomarTesoro(this);
		    
		    
		    //si está muriendo...
		    if(this.muriendo)
		     {
		      this.brosMuere();
		     }
		    else
		    {
		   //Colision con enemigos (rectangulos)
		   //restar el tiempo de invencibilidad
		  
		   if(this.invencible && !this.getEstado().siendoArrastrado()){
		    this.tiempoInvencible--;
		    if(this.tiempoInvencible < 1)
		     {
		     this.invencible = false;
		     }
		   }
		   
		  if (!this.getScene().enemigoColisionaConBros(this) || (this.getScene().enemigoColisionaConBros(this) && this.invencible)){
		   //TODO Agarrar tesoro
		   
		   if((this.getScene().esferaRodanteColisionaConBros(this)))
		    {
		    this.setEstado(new SiendoArrastrado());
		    this.getEstado().setBros(this);
		    this.getEstado().update(deltaState);
		    }
		   else{
		     
		    //COMANDO SALTAR
		    if(deltaState.isKeyPressed(Key.A)) {
		     if(this.derecha){
		      Sprite sprite = Sprite.fromImage("BrosSaltaDrc.png");
		      this.setAppearance(sprite.crop(ancho+7,alto));
		      this.saltar();
		     }else {
		      Sprite sprite = Sprite.fromImage("BrosSaltaIzq.png");
		      this.setAppearance(sprite.crop(ancho+7,alto));
		      this.saltar();
		      }
		     }
		    
		    if(deltaState.isKeyBeingHold(Key.DOWN)&&deltaState.isKeyPressed(Key.A)){
		     if(this.derecha&&this.getY()<this.gameDimension.getHeight()-40){
		      Sprite sprite = Sprite.fromImage("BrosSaltaDrc.png");
		      this.setAppearance(sprite.crop(ancho+7, alto));
		      this.setY(this.getY()+5);
		      //sonido salto abajo
		      Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
		      sonidoSalto.play();
		     }
		     if(!this.derecha&&this.getY()<this.gameDimension.getHeight()-40){
		      Sprite sprite = Sprite.fromImage("BrosSaltaIzq.png");
		      this.setAppearance(sprite.crop(ancho+7, alto));
		      this.setY(this.getY()+5);
		      Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
		      sonidoSalto.play();
		     }
		    }
		    this.getEstado().update(deltaState);
		    
		    //COMANDO MOVER
		    if (deltaState.isKeyBeingHold(Key.RIGHT)) {
		     this.derecha = true;
		     Sprite sprite = Sprite.fromImage("BrosDrc.png");
		     this.setAppearance(sprite);
		     this.moverALaDerecha(deltaState);
		     
		     }
		    else
		     if(deltaState.isKeyBeingHold(Key.LEFT)) 
		      {
		      this.derecha = false;
		      Sprite sprite = Sprite.fromImage("BrosIzq.png");
		      this.setAppearance(sprite);
		      this.moverALaIzquierda(deltaState);
		      }
		    
		    //COMANDO:DISPARAR   
		    if(deltaState.isKeyPressed(Key.S))
		       {
		       this.CongelaOEmpuja(deltaState);
		       }
		    
		    //RENDER SPRITE AL DEJAR DE DISPARAR
		    if(deltaState.isKeyReleased(Key.S)){
		     if(this.derecha){
		      Sprite sprite = Sprite.fromImage("BrosDrc.png");
		      this.setAppearance(sprite);
		     }else {
		      Sprite sprite = Sprite.fromImage("BrosIzq.png");
		      this.setAppearance(sprite);
		      }
		    }
		   }
		  } else{
		   //TODO MORIR
			  this.muriendo = true;
			  Sound sonidoMuerte = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("Player Death.wav"));
		      sonidoMuerte.play();
			  
		   //this.perderVida();
		   }
		  }
		 }
	}
	
		
	 private void brosMuere() {
		// TODO Auto-generated method stub
				if(this.muriendoYSubiendo > 1)
				{
				this.muriendoYSubiendo = this.muriendoYSubiendo - 1;
				this.setY(this.getY() - 0.20);
				}
				else
					if(this.tiempoMuriendo > 1)
						{
						this.tiempoMuriendo = this.tiempoMuriendo -1;
						}
					else
						{
						this.muriendo = false;
						this.tiempoMuriendo = 500;
						this.muriendoYSubiendo = 500;
						 if(this.vidas.getCantidadVidas() == 0)
							{
							this.getScene().cartelLose();
							this.getScene().gameOverMusic();
							}
						 else
							 this.vidas.setCantidadVidas(this.vidas.getCantidadVidas() - 1);
						}
	}
	
	
	public void perderVida()
	{
	if(this.vidas.getCantidadVidas() < 1)
		{
		this.getScene().cartelLose();
		}
		else
			{
			this.morir();
			}
	}
	
	
	private void morir() {
		  // TODO Hacer que muera y reaparezca en el origen del nivel;
		 
		   this.vidas.setCantidadVidas(this.vidas.getCantidadVidas() - 1);
		   
		   this.muriendo =true;
		   this.tiempoMuriendo = 500;
		 }

	//COMPROBAR CASO SI EMPUJA BOLA DE NIEVE O SOLO DISPARA NIEVE
	public void CongelaOEmpuja(DeltaState deltaState){
		if(this.getScene().puedeEmpujarBolaDeNieve(this,deltaState))
		{
			if(this.derecha){
//				Sprite sprite = Sprite.fromImage("BrosEmpujaDrc.png");
//				this.setAppearance(sprite.crop(ancho+7,alto));
				this.getScene().empujar(this, deltaState);
			}else {
				this.saltar();
//				Sprite sprite = Sprite.fromImage("BrosEmpujaIzq.png");
//				this.setAppearance(sprite.crop(ancho+7,alto));
				this.getScene().empujar(this, deltaState);
				}
		}
		else{
			if(this.derecha){
				Sprite sprite = Sprite.fromImage("BrosDisparaDrc.png");
				this.setAppearance(sprite);
				this.disparar();
				//sonido disparo
				Sound sonidoDisparo = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("snowShot.wav"));
				sonidoDisparo.play();
				
			}else {
				this.saltar();
				Sprite sprite = Sprite.fromImage("BrosDisparaIzq.png");
				this.setAppearance(sprite);
				this.disparar();
				Sound sonidoDisparo = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("snowShot.wav"));
				sonidoDisparo.play();
				}
			
		}
	}
	
	
	//DISPARAR NIEVE
	private void disparar() {
		Double alturaDeDisparo = this.getY() - ( (this.getAppearance().getHeight())/10);
		Snow snow = new Snow(gameDimension, (this.getX()), alturaDeDisparo, this, this.getEstadoCapsula().isPotencia(), this.getEstadoCapsula().isRango());
		this.getScene().addComponent(snow);
	}
	
	
	

	public void saltar() {this.getEstado().saltar();}
	
	private void moverALaDerecha(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Derecha();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlFinal()){
						if(this.getEstadoCapsula().isPrisa())
					this.setX(this.getX()+(this.getScene().getVelocity()*1.5+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
					else
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlFinal()){
					if(this.getEstadoCapsula().isPrisa())
					this.setX(this.getX()+(this.getScene().getVelocity()*1.5+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
					else
					this.setX(this.getX()+(this.getScene().getVelocity()+ (this.getScene().getVelocity()/4))* deltaState.getDelta());
					this.getScene().moverEsfera(this, deltaState);
				}
			}
		}
	}
	
	private void moverALaIzquierda(DeltaState deltaState) {
		if(!this.getScene().getSystemPause()){
			this.dir = new Izquierda();
			if(!playState && !this.getScene().getPlayState()){
				if (this.noLlegoAlComienzo()){
					if(this.getEstadoCapsula().isPrisa())
					this.setX(this.getX()- (this.getScene().getVelocity()*1.5 + (this.getScene().getVelocity()/4))* deltaState.getDelta());
					else
					this.setX(this.getX()- (this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
				}
			}else{
				if (this.noLlegoAlComienzo()){
					if(this.getEstadoCapsula().isPrisa())
					this.setX(this.getX()-(this.getScene().getVelocity()*1.5 + (this.getScene().getVelocity()/4))* deltaState.getDelta());
					else
					this.setX(this.getX()-(this.getScene().getVelocity() + (this.getScene().getVelocity()/4))* deltaState.getDelta());
					this.getScene().moverEsfera(this, deltaState);
				}
			}
		}
	}
	
	private boolean noLlegoAlFinal() {return this.getX()+this.getAppearance().getWidth()<= gameDimension.getWidth();}
	
	private boolean noLlegoAlComienzo() {return this.getX()>=0;}
	
	
	//MOVIMIENTO NIEVE
	public boolean puedeRecorrer(Dimension dim, Snow snow) {return this.dir.puedeRecorrer(dim, snow);}

	public Direccion getDir() {return dir;}

	public void setDir(Direccion dir) {this.dir = dir;}

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
		//TODO Modificar
		this.getPuntaje().sumarPuntaje(puntos);
		
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
}
