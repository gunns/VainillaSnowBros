package others;

import java.awt.Color;
import java.awt.Dimension;

import mobConNieve.Empujado;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;

import componentes.Mob;
import componentes.Snow;

public class Izquierda extends Direccion {

	@Override
	public void avanzaDisparo(Snow snow) {
		
		// booleano que indica si el bros tomó la capsula de rango o no
				//caso normal, sin capsula:
				if(!snow.isRango())
					
				{
				if(snow.getDistance() > 0) {
				if(snow.getFalling() > 0) {
					if(snow.getInitialX()- 10<snow.getX()){ //-10
						snow.setY(snow.getY() - 0.10);
					}
					else if(snow.getInitialX()-30>snow.getX()&&snow.getInitialX()-50<snow.getX()){ //-30
						snow.setY(snow.getY() + 0.20);
					}
					else if(snow.getInitialX()-50>snow.getX())//-50
						snow.setY(snow.getY()+0.50);				
						snow.setFalling(snow.getFalling() - 0.30);
						snow.setX(snow.getX() - 0.80);
				}		
				else
				snow.destroy();
			}
			else
				//TODO Caso en que sí tomo la capsula
			{
			
			}
				}
	}

	@Override
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		return ((snow.getX() > 0) && (dim.getHeight() > (snow.getY() + (snow.getAppearance().getHeight()))));
	}

	
	public void rodar(Mob mob, DeltaState deltaState)
	{
		Empujado estado = (Empujado) mob.getEstadoNieve();
	if(estado.getRebotes() > 0)
	{
		if(mob.noLlegoAlComienzo())
		{
			mob.moverALaIzquierda(deltaState);
		}
		else
			{
			mob.getEstadoNieve().setRebotes(mob.getEstadoNieve().getRebotes() - 1);
			mob.getEstadoNieve().setDir(new Derecha());
			}
		
		}
	else
		{
		mob.getScene().esferaExploto(mob);
		mob.destroy();
		}
	}

	@Override
	public void moverEsfera(Mob mob, DeltaState deltaState) {
		mob.moverALaIzquierda(deltaState);
		
	}

	@Override
	public Direccion direccionContraria() {
		
		return new Derecha();
	}
}


