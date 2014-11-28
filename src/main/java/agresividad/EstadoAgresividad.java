package agresividad;

import java.util.Random;

import others.Derecha;
import others.Izquierda;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Mob;

public class EstadoAgresividad {
	private Mob mob;
	public boolean realizandoMovimientoRandom;
	public Integer numeroDeMovimiento;
	public boolean acabaDeTocarElLimite;
	public boolean saltara;
	public boolean bajara;

	public Mob getMob() {
		return mob;
	}

	public void setMob(Mob mob) {
		this.mob = mob;
	}
	
	public EstadoAgresividad(Mob mob)
	{
		this.mob = mob;
		this.acabaDeTocarElLimite = false;
		bajara = false;
		saltara = false;
	}
	
	
	public void update(DeltaState deltaState){
		
	}
	
	
	public void moverALaDerecha(DeltaState deltaState) {
		//setear imagen
				this.getMob().setAppearance(this.getMob().getApariencia().getMobDerecha());
				if (!this.getMob().noLlegoAlFinal())
				{
					this.getMob().setDir(new Izquierda());
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					if(!this.bajara || !this.saltara)
						{
						this.setearNumeroDeMovimiento();
						}
						
					//this.getMob().setDir(this.getMob().getDir().direccionContraria());
					//this.realizandoMovimientoRandom = false;
					}
				else
				{
					if(this.getMob().getScene().terminaElPiso((this.getMob().getX()+1+this.getMob().getAppearance().getWidth()), this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))// && this.acabaDeTocarElLimite)
						{
						this.getMob().setDir(new Izquierda());
						this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
						this.acabaDeTocarElLimite = true;
						//this.acabaDeTocarElLimite = false;
						}
					else
						{
						//if(!this.acabaDeTocarElLimite)
							//{
						this.getMob().setX(this.getMob().getX()+(this.getMob().getScene().getVelocity()+ (this.getMob().getVelocity()/4 - 4))* deltaState.getDelta());
						this.acabaDeTocarElLimite = false;
							}
						
						}
					}
					
	
	
	
	
	
	
	public void moverALaIzquierda(DeltaState deltaState) {
		//setear imagen
		this.getMob().setAppearance(this.getMob().getApariencia().getMobIzquierda());
		//if(!this.getMob().getScene().getSystemPause()){
			//this.getMob().dir = new Izquierda();
			//if(!this.getMob().playState && !this.getMob().getScene().getPlayState()){
				if (!this.getMob().noLlegoAlComienzo())
					{
					this.getMob().setDir(new Derecha());
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					if(!this.bajara || !this.saltara)
					{
					this.setearNumeroDeMovimiento();
					}
					}
				else
				{
					if(this.getMob().getScene().terminaElPiso(this.getMob().getX()-1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))
						{
						this.getMob().setDir(new Derecha());
						this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
						this.acabaDeTocarElLimite = true;
						}
					else
						{
						this.getMob().setX(this.getMob().getX()-(this.getMob().getVelocity() + (this.getMob().getVelocity()/4 - 4))* deltaState.getDelta());
						this.acabaDeTocarElLimite = false;
						}
				}
	}
	
	public void saltar(DeltaState deltaState)
	{
		this.getMob().getEstado().saltar();
	}
	
	public void bajar(DeltaState deltaState)
	{
		//setear imagen
		this.getMob().setAppearance(this.getMob().getApariencia().getMobBaja());
		this.getMob().setY(this.getMob().getY() + 5);
	}
	
	
	public void actualizarTiempoReaccion()
	{
		this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
		//this.setAcabaDeCambiar(false);
	}
	
	
	public boolean isrealizandoMovimientoRandom() {
		return realizandoMovimientoRandom;
	}

	public void setrealizandoMovimientoRandom(boolean realizandoMovimientoRandom) {
		this.realizandoMovimientoRandom = realizandoMovimientoRandom;
	}

	public Integer getNumeroDeMovimiento() {
		return numeroDeMovimiento;
	}

	public void setNumeroDeMovimiento(Integer numeroDeMovimiento) {
		this.numeroDeMovimiento = numeroDeMovimiento;
	}

	public boolean isAcabaDeTocarElLimite() {
		return acabaDeTocarElLimite;
	}

	public void setAcabaDeTocarElLimite(boolean acabaDeTocarElLimite) {
		this.acabaDeTocarElLimite = acabaDeTocarElLimite;
	}
	
	
	public boolean mobEstaEnElFondo()
	{
		return ((this.getMob().getY() - this.getMob().getAppearance().getWidth() + 50)  > (this.getMob().getScene().getGameDimension().getWidth())); 
	}

	public boolean isSaltara() {
		return saltara;
	}

	public void setSaltara(boolean saltara) {
		this.saltara = saltara;
	}

	public boolean isBajara() {
		return bajara;
	}

	public void setBajara(boolean bajara) {
		this.bajara = bajara;
	}
	
	public void setearNumeroDeMovimiento()
	{
		Random rand = new Random();//creamos una instancia de Random 
		int posAleatoria = rand.nextInt(17);//obtenemos una posicion entre 0 y el tamano del arreglo 
		this.numeroDeMovimiento = posAleatoria;
	}
	
}
