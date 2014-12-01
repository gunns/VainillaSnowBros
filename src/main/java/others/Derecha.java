package others;

import java.awt.Color;
import java.awt.Dimension;

import mobConNieve.Empujado;
import mobs.DisparoFuego;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;
import agresividad.EstadoAgresividad;
import boss.Boss;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;










import componentes.Bros;
import componentes.Explosion;
import componentes.Mob;
import componentes.Snow;

public class Derecha extends Direccion{

	@Override
	public void avanzaDisparo(Snow snow) {
		if(snow.getDistance() > 0) 
			{
			if(snow.getFalling() > 0) {
				if(snow.getInitialX()+10>snow.getX()){
					snow.setY(snow.getY() - 0.10);
				}
				else if(snow.getInitialX()+30<snow.getX() &&snow.getInitialX()+50>snow.getX()){
					snow.setY(snow.getY() + 0.20);
				}
				else if(snow.getInitialX()+50<snow.getX())
					snow.setY(snow.getY()+0.50);
					snow.setFalling(snow.getFalling() - 0.30);
					snow.setX(snow.getX() + (0.80+this.disparoExtendido(snow)));
				}
			else
				snow.destroy();
		}				
	}

	@Override
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		return ((  (snow.getX() + (snow.getAppearance().getWidth())) < dim.getWidth()) && dim.getHeight() > (snow.getY() + (snow.getAppearance().getHeight())) );
	}

	
	
	
	public void rodar(Mob mob, DeltaState deltaState)
	{
		Empujado estado = (Empujado) mob.getEstadoNieve();
	if(estado.getRebotes() > 0)
	{
		if(mob.noLlegoAlFinal())
		{
			//mob.moverEsferaALaDerecha(deltaState);
			mob.setX(mob.getX() + (mob.getVelocity() + (mob.getVelocity()/4))* deltaState.getDelta());
		}
		else
			{
			mob.getEstadoNieve().setRebotes(mob.getEstadoNieve().getRebotes() - 1);
			mob.getEstadoNieve().setDir(new Izquierda());
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
	Explosion explosion = new Explosion(mob.getX() -15, mob.getY() - 20);
	mob.getScene().addComponent(explosion);
	
	//mob.getScene().soltarBrosAdherido(mob);
	
	mob.destroy();
	}
	}

	
	@Override
	public Direccion direccionContraria() {
		
		return new Izquierda();
	}

	@Override
	public void moverEsfera(Bros bros, Mob mob, DeltaState deltaState) {
		// TODO Auto-generated method stub
		mob.moverEsferaALaDerecha(bros, deltaState);
	}

	@Override
	public void desplazarBrosArrastrado(Bros bros, Mob mob, DeltaState deltaState) {
		// TODO Auto-generated method stub
		//bros.moverALaDerecha(mob.getVelocity(), deltaState);
		bros.setX(mob.getX());// - mob.getAppearance().getWidth()/2);
		bros.setY(mob.getY());
		
	}

	@Override
	public void moverMob(EstadoAgresividad e, DeltaState deltaState) {
		e.moverALaDerecha(deltaState);
		if(e.acabaDeTocarElLimite || !e.getMob().noLlegoAlFinal())
		{
		e.setearNumeroDeMovimiento();
		//actualizar tiempo
		e.getMob().setTiempoDeReaccionActual(e.getMob().getTiempoDeReaccion());
		}
		
	}

	@Override
	public void setearImagenSalto(Mob mob) {
		//setear imagen
		mob.setAppearance(mob.getApariencia().getMobSaltaDerecha());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setearImagen(Mob mob) {
		if(mob.esPeligroso())
		mob.setAppearance(mob.getApariencia().getMobDerecha());
		
	}

	@Override
	public void recorreFuego(DisparoFuego disparoFuego) {
		disparoFuego.setX(disparoFuego.getX() + 2);
		
	}

	@Override
	public void spriteDisparar(TrollVerde tv) {
		// TODO Auto-generated method stub
		tv.setAppearance(tv.disparoDerecha);
		
	}

	@Override
	public void spritefuego(DisparoFuego disparoFuego) {
		disparoFuego.setAppearance(disparoFuego.fuegoDerecha);
		
	}

	@Override
	public void reacomodarBrosSiEsNecesario(Bros bros, Mob mob) {
		//mon es una esfera f, bros puede estar adentro y hay que reacomodar, se asume que el bros puede estar mirando a la derecha
		if(bros.getScene().colisionBolaNieveFConBros(bros, mob))
			{
			bros.setX(mob.getX() - bros.getAppearance().getWidth());
			}
	}

	@Override
	public void spritePorCornear(TrollRojoEnojado tr) {
		//tr.setY(tr.getY()-3);
		tr.setAppearance(tr.getPreparandoCorneadaDerecha());
		//this.setAppearance(sprite.crop(ancho+7, alto));
		
	}

	@Override
	public void cornear(TrollRojoEnojado tr, DeltaState deltaState) {
		tr.setAppearance(tr.getCorneadaDerecha());
		tr.setX(tr.getX() + (tr.getVelocity()*4 + (tr.getVelocity()))* deltaState.getDelta());
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean terminoRecorrido(Mob mob) {
		return !mob.noLlegoAlFinal();
	
		
	}

	@Override
	public void moverBoss(Boss boss) {
		if((boss.getX() + boss.getAppearance().getWidth()) >= boss.getDim().getWidth())
			{
			boss.setDireccion(new Izquierda());
			}
		else
			{
			boss.moverDerecha();
			}
		
	}
	
}
