package mobs;

import com.uqbar.vainilla.DeltaState;

import componentes.Mob;
import estadoMob.Volando;
import agresividad.Pasivo;

public class PasivoTrollRojoEnojado extends Pasivo{

	public PasivoTrollRojoEnojado(Mob mob) {
		super(mob);
		// TODO Auto-generated constructor stub
	}

	
	public void update(DeltaState deltaState){
		TrollRojoEnojado tr = (TrollRojoEnojado) this.getMob();
			if(tr.getScene().brosEstaALaAlturaDelMob(tr) && tr.getScene().hayColisionConUnPiso(tr))
			{
			//tr.setEstado(new Volando(tr));
			tr.volverseAgresivo();
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
	
/*public void update(DeltaState deltaState){
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
	}*/
	
}
