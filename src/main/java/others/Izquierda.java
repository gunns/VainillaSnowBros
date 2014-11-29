package others;

import java.awt.Color;
import java.awt.Dimension;

import mobConNieve.Empujado;
import mobs.DisparoFuego;
import mobs.TrollVerde;
import agresividad.EstadoAgresividad;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;







import componentes.Bros;
import componentes.Explosion;
import componentes.Mob;
import componentes.Snow;

public class Izquierda extends Direccion {

	@Override
	public void avanzaDisparo(Snow snow) {
	
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
					snow.setX(snow.getX() - (0.80 + this.disparoExtendido(snow)));
			}		
			else{
				snow.destroy();
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
			mob.moverEsferaALaIzquierda(deltaState);
		}
		else
			{
			mob.getEstadoNieve().setRebotes(mob.getEstadoNieve().getRebotes() - 1);
			mob.getEstadoNieve().setDir(new Derecha());
			//sonido rebote
			Sound sonidoRebote = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("rebote.wav"));
			sonidoRebote.play();
			}
		
		}
	else
		{
		mob.getScene().esferaExploto(mob);
		//AnimacionBreak animacion = new AnimacionBreak(mob.getX(), mob.getY(), mob);
		//mob.getScene().addComponent(animacion);
		
		//sonido explosion
		Sound sonidoExplosion = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("snowBallExplode.wav"));
		
		
		sonidoExplosion.play();
		Explosion explosion = new Explosion(mob.getX() - 15, mob.getY() - 20);
		mob.getScene().addComponent(explosion);
		//mob.getScene().soltarBrosAdherido(mob);
		mob.destroy();
		}
	}
	

	@Override
	public Direccion direccionContraria() {
		
		return new Derecha();
	}

	@Override
	public void moverEsfera(Mob mob, DeltaState deltaState) {
		mob.moverEsferaALaIzquierda(deltaState);
		
	}

	@Override
	public void desplazarBrosArrastrado(Bros bros, Mob mob,
			DeltaState deltaState) {
		// TODO Auto-generated method stub
		//bros.moverALaIzquierda(mob.getVelocity(), deltaState);
		bros.setX(mob.getX() + mob.getAppearance().getWidth()/2);
		bros.setY(mob.getY());
		
	}

	@Override
	public void moverMob(EstadoAgresividad e, DeltaState deltaState) {
		e.moverALaIzquierda(deltaState);
		
	}

	@Override
	public void setearImagenSalto(Mob mob) {
		// TODO Auto-generated method stub
		//setear imagen
				mob.setAppearance(mob.getApariencia().getMobSaltaIzquierda());
	}

	@Override
	public void setearImagen(Mob mob) {
		if(mob.esPeligroso())
		mob.setAppearance(mob.getApariencia().getMobIzquierda());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recorreFuego(DisparoFuego disparoFuego) {
		// TODO Auto-generated method stub
		disparoFuego.setX(disparoFuego.getX() - 2);	
	}

	@Override
	public void spriteDisparar(TrollVerde tv) {
		// TODO Auto-generated method stub
		tv.setAppearance(tv.disparoIzquierda);
		
	}

	@Override
	public void spritefuego(DisparoFuego disparoFuego) {
		// TODO Auto-generated method stub
		disparoFuego.setAppearance(disparoFuego.fuegoIzquierda);
		
	}
}


