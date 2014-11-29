package agresividad;

import java.util.Random;

import tesoros.Tesoro;

import com.uqbar.vainilla.DeltaState;

import componentes.Bros;
import componentes.Mob;

public class Pasivo extends EstadoAgresividad {
	boolean derecha;
	
	public Pasivo(Mob mob){
		super(mob);
		//this.derecha=true;
		//this.setNumeroDeMovimiento(3);
		this.setearNumeroDeMovimiento();
	}
	

	
	
	
	//Codigo FIN DE PISO : this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)
	
	public void mobSeMueve(DeltaState deltaState)
	{
		//Mover por mover
		//Mover para saltar
		//Mover para bajar
		if(this.numeroDeMovimiento <=5)
			{
			this.saltara = true;
			this.moverParaSaltar(deltaState);
			}
		if(this.numeroDeMovimiento >5 && this.numeroDeMovimiento <=10)
			{
			this.bajara = true;
			this.moverParaBajar(deltaState);
			}
		else
			{
			this.moverSoloEnX(deltaState);
			}
	}
	
	public void moverSoloEnX(DeltaState deltaState)
	{
		
	this.getMob().getDir().moverMob(this, deltaState);
	this.saltara = false;
	this.bajara = false;
	if(this.acabaDeTocarElLimite || !this.getMob().noLlegoAlComienzo() || !this.getMob().noLlegoAlFinal())
		{
		this.setearNumeroDeMovimiento();
		//actualizar tiempo
		this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
		}
	}
	
	public void moverParaSaltar(DeltaState deltaState)
	{
		if(this.getMob().getScene().hayColisionTotalConUnPiso(this.getMob()) && this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()) )
				{
			    this.saltar(deltaState);
			    //preparar siguiente movimiento
			    this.setearNumeroDeMovimiento();
			    this.saltara = false;
			    //actualizarTiempo
			    this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
				}
				else
				{
					this.moverEnX(deltaState);
				}
	}
	public void moverParaBajar(DeltaState deltaState)
	{																												
		if(this.mobEstaEnElFondo()&&!this.mobEstaTocandoUnPiso())
			{
			//setear el movimiento normal
			this.setNumeroDeMovimiento(11);
			this.bajara = false;
			}
		else
			{
			this.moverEnX(deltaState);
			if(this.acabaDeTocarElLimite)
				{
				this.bajar(deltaState);
				this.acabaDeTocarElLimite = false;
				this.setearNumeroDeMovimiento();
				this.bajara = false;
				//actualizar tiempo
				this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
				}
			}
	}
	
	public boolean mobEstaTocandoUnPiso(){
		return this.getMob().getScene().hayColisionConUnPiso(this.getMob());
	}

	
	
	public void mover(DeltaState deltaState){
	if(this.getMob().getScene().getPlayState())
		{
		if(this.getMob().esPeligroso())
			{
			//this.getMob().getDir().moverMob(this, deltaState);
			this.mobSeMueve(deltaState);
			//codigo correcto:saltar
			//if(this.getMob().getScene().hayColisionConUnPiso(this.getMob())&&this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()))
				//	{
					//this.saltar(deltaState);
					//}
			}
		}
	}
	
	public void update(DeltaState deltaState){
		if(this.getMob().brosCercanoEstaVivo())
			{
			this.getMob().volverseAgresivo();
			}
		else
		if(this.getMob().getTiempoDeReaccionActual() > 1)
			{
			//TODO actualiza tiempo de reaccion
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccionActual() - 1);
			}
		else
		{
		this.mover(deltaState);
		}
	}
	
	





	public void moverEnX(DeltaState deltaState ){
		this.getMob().getDir().moverMob(this, deltaState);
	}
	
	
}