package agresividad;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class Pasivo extends EstadoAgresividad {
	boolean derecha;
	
	public Pasivo(Mob mob){
		super(mob);
		this.setearNumeroDeMovimiento();
	}
	
	
	public void mobSeMueve(DeltaState deltaState)
	{
		if(this.numeroDeMovimiento <=5)
			{
			this.saltara = true;
			this.moverParaSaltar(deltaState);
			}
		if(this.numeroDeMovimiento >5 && this.numeroDeMovimiento <=10)
			{
			if(!this.mobEstaEnElFondo())
				{
				this.bajara = true;
				this.moverParaBajar(deltaState);
				}
			else
				{
				this.setearNumeroDeMovimiento();
				this.mobSeMueve(deltaState);
				}
			}
		else
			{
			this.moverSoloEnX(deltaState);
			}
	}
	
	public void moverSoloEnX(DeltaState deltaState)
	{
		this.saltara = false;
		this.bajara = false;	
	this.getMob().getDir().moverMob(this, deltaState);
	}
	
	public void moverParaSaltar(DeltaState deltaState)
	{
		if(this.getMob().getScene().hayColisionTotalConUnPiso(this.getMob()) && this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho()) )
				{
			    this.saltar(deltaState);
			    this.setearNumeroDeMovimiento();
			    this.saltara = false;
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
			this.mobSeMueve(deltaState);
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
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccionActual() - 1);
			}
		else
		{
		this.mover(deltaState);
		}
	}


	public void moverEnX(DeltaState deltaState ){
		this.saltara = false;
		this.bajara = false;	
		this.getMob().getDir().moverMob(this, deltaState);
	}
}