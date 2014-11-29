package agresividad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Mob;

public class Agresivo extends EstadoAgresividad{

	public boolean persiguiendo;
	public boolean debeContinuarAccion;
	public Integer numeroDeAccion;
	
	public Agresivo(Mob mob) {
		super(mob);
		this.persiguiendo = true;
		this.debeContinuarAccion = false;
		//this.debeContinuarAccion = false;
		// TODO Auto-generated constructor stub
	}
	
	
	public void perseguir(DeltaState deltaState)
	{
		
		//
		//booleano que indica si mi accion debe cambiar
		if(persiguiendo)
			{
			if(!debeContinuarAccion)
				{
				this.debeContinuarAccion = true;
				//instanciar el movimiento de acuerdo a la posiion del bros
				//estan a la misma altura
				if(this.getMob().getScene().brosEstaALaAlturaDelMob(this.getMob()))
					{
					//esta a la derecha
					if(this.getMob().getScene().brosEstaALaDerechaDeMob(this.getMob()))
						{
						this.setNumeroDeAccion(5);
						}
					else
						//esta a la izquierda
						{
						this.setNumeroDeAccion(6);
						}
					}
				else
					//bros esta arriba
					if(this.getMob().getScene().brosEstaArribaDeMob(this.getMob()))
							{
						//bros esta a la derecha
							if(this.getMob().getScene().brosEstaALaDerechaDeMob(this.getMob()))
								{
								this.setNumeroDeAccion(1);
								}
							else
								{
								//bros esta  a la izquierda
								this.setNumeroDeAccion(2);
								}
							}
					else
						//bros deberia estar abajo
						{
						if(this.getMob().getScene().brosEstaALaDerechaDeMob(this.getMob()))
							{//bros etsa a la derecha
							this.setNumeroDeAccion(3);
							}
						else
							{
							//bros esta a la izquierda
							this.setNumeroDeAccion(4);
							}
						}
								
					}
					else
					{
						this.continuarAccion(deltaState);
					}
							
				}
			
		else
			{
			this.getMob().volversePasivo();
			}
			
	}
	
	public void moverArribaALaIzquierda(DeltaState deltaState)
	{
	this.moverALaIzquierda(deltaState);
	if(this.acabaDeTocarElLimite || !this.getMob().noLlegoAlComienzo())
		{
		this.persiguiendo = false;
		}
	else
		{
			if(this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()))
			{
				this.saltar(deltaState);
				this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
				this.persiguiendo = false;
			}
		}
	}
	
	public void moverArribaALaDerecha(DeltaState deltaState)
	{
	this.moverALaDerecha(deltaState);
	if(this.acabaDeTocarElLimite || !this.getMob().noLlegoAlFinal())
		{
		this.persiguiendo = false;
		}
	else
		{
			if(this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()))
			{
				this.saltar(deltaState);
				this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
				this.persiguiendo = false;
			}
		}
	}
	
	public void moverAbajoALaIzquierda(DeltaState deltaState)
	{
	this.moverALaIzquierda(deltaState);
	if(this.acabaDeTocarElLimite || !this.getMob().noLlegoAlComienzo())
		{
		this.bajar(deltaState);
		this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
		this.persiguiendo = false;
		}

	}
	
	public void moverAbajoALaDerecha(DeltaState deltaState)
	{
	this.moverALaDerecha(deltaState);
	if(this.acabaDeTocarElLimite || !this.getMob().noLlegoAlFinal())
		{
		this.bajar(deltaState);
		this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
		this.persiguiendo = false;
		}

	}
	
	
	public void moverseALaIzquierda(DeltaState deltaState)
	{
		this.moverALaIzquierda(deltaState);
		if(this.acabaDeTocarElLimite)
				{
				if(this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()))
					{
					this.saltar(deltaState);
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					this.persiguiendo = false;
					}
				else
					{
					this.bajar(deltaState);
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					this.persiguiendo = false;
					}
				}
				else
				if(!this.getMob().noLlegoAlComienzo())
			{
			this.persiguiendo = false;
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
			}
	}
	
	public void moverseALaDerecha(DeltaState deltaState)
	{
		this.moverALaDerecha(deltaState);
		if(this.acabaDeTocarElLimite)
		{
		if(this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()))
			{
			this.saltar(deltaState);
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
			this.persiguiendo = false;
			}
		else
			{
			this.bajar(deltaState);
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
			this.persiguiendo = false;
			}
		}
		else
		if(!this.getMob().noLlegoAlFinal())
	{
	this.persiguiendo = false;
	this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
	}
}
	
	
	
	
	
	
	
	
	
	
	public void update(DeltaState deltaState)
	{
		if(this.getMob().esPeligroso())
			{
			if(this.getMob().getTiempoDeReaccionActual() > 0)
				{
				this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccionActual() - 1);
				}
				else
				{
					this.perseguir(deltaState);
				}
			}
		/*
		if(!this.getMob().getScene().brosCercanoEstaVivo(this.getMob()) && this.puedeCalmarse && !this.persiguiendo)
		{
			this.getMob().setEstadoAgresividad(new Pasivo(this.getMob()));
		}
		else
		if(this.getMob().getTiempoDeReaccionActual() > 1)
		{
		//TODO actualiza tiempo de reaccion
		this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccionActual() - 1);
		}
	else
	{
	this.perseguir(deltaState);
	}	
	*/
	
	
		
		
		
	}


	public boolean isPersiguiendo() {
		return persiguiendo;
	}


	public void setPersiguiendo(boolean persiguiendo) {
		this.persiguiendo = persiguiendo;
	}


	public Integer getNumeroDeAccion() {
		return numeroDeAccion;
	}


	public void setNumeroDeAccion(Integer numeroDeAccion) {
		this.numeroDeAccion = numeroDeAccion;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void continuarAccion(DeltaState deltaState)
	{
	/*
	 1: arriba derecha
	 2: arriba izquierda
	 3: abajo derecha
	 4 abajo izquierda
	 5: derecha
	 6 izquierda*/
		
		if(this.numeroDeAccion == 1)
			{
			this.moverArribaALaDerecha(deltaState);
			}
		if(this.numeroDeAccion == 2)
			{
			this.moverArribaALaIzquierda(deltaState);
			}
		if(this.numeroDeAccion == 3)
		{
		this.moverAbajoALaDerecha(deltaState);
		}
		if(this.numeroDeAccion == 4)
		{
			this.moverAbajoALaIzquierda(deltaState);
		}
		if(this.numeroDeAccion == 5)
		{
		this.moverseALaDerecha(deltaState);
		}
		if(this.numeroDeAccion == 6)
		{
		this.moverseALaIzquierda(deltaState);
		}
		
			
		
	}
	
}