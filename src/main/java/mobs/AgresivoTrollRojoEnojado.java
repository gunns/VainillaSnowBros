package mobs;

import others.Derecha;
import others.Direccion;
import others.Izquierda;

import com.uqbar.vainilla.DeltaState;

import estadoMob.CayendoMob;
import estadoMob.Volando;
import agresividad.EstadoAgresividad;

public class AgresivoTrollRojoEnojado extends EstadoAgresividad{
	public boolean encontreBros;
	public Direccion direccionDeCorneada;
	
	public AgresivoTrollRojoEnojado(TrollRojoEnojado mob)
	{
		super(mob);
		encontreBros = false;
		direccionDeCorneada = new Derecha();
		
	}
	
	
	public void update(DeltaState deltaState)
	{
	if(this.getMob().esPeligroso())
	{
	TrollRojoEnojado tr = (TrollRojoEnojado) this.getMob();
		if(!encontreBros)
		{
		if(tr.getScene().brosEstaALaIzquierdaDeMob(tr))
			{
			direccionDeCorneada = new Izquierda();
			this.encontreBros = true;
			tr.setY(tr.getY()-3);
			}
		else
			{
			direccionDeCorneada = new Derecha();
			this.encontreBros = true;
			}
		}
		if(tr.tiempoPreparandoCorneada <= 0)
			{
				if(!direccionDeCorneada.terminoRecorrido(tr))
				{
					tr.setEstado(new Volando(tr));
					tr.cornear(direccionDeCorneada, deltaState);
				}
				else
					{
					direccionDeCorneada.setearImagenSalto(tr);
					tr.volversePasivo();
					tr.setEstado(new CayendoMob(this.getMob().getY(), this.getMob()));
					tr.setTiempoDeReaccionActual(tr.getTiempoDeReaccion());
					tr.setTiempoPreparandoCorneada(120);
					}
			}
			else
				{
				tr.tiempoPreparandoCorneada--;
				direccionDeCorneada.spritePorCornear(tr);
				}
	}
	if(!this.getMob().esPeligroso()){
			this.getMob().setEstado(new CayendoMob(this.getMob().getY(),this.getMob()));
	}
	
}

}
