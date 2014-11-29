package mobs;

import com.uqbar.vainilla.DeltaState;

import componentes.Mob;
import agresividad.EstadoAgresividad;
import agresividad.Pasivo;

public class PasivoTrollVerde extends Pasivo{

	public PasivoTrollVerde(TrollVerde mob) {
		super(mob);
		// TODO Auto-generated constructor stub
		//this.setearNumeroDeMovimiento();
	}

	
	public void update(DeltaState deltaState){
		TrollVerde tv = (TrollVerde) this.getMob();
		tv.descansandoDeDisparo = tv.descansandoDeDisparo - 1;
		if(tv.brosCercanoATroll())
			if(tv.descansandoDeDisparo <= 0 && tv.getScene().brosEstaALaAlturaDelMob(tv))
			{
			tv.volverseAgresivo();
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
	

}
