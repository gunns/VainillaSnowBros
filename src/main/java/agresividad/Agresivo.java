package agresividad;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Mob;

public class Agresivo extends EstadoAgresividad{

	public Agresivo(Mob mob) {
		this.setMob(mob);
	}
	
	public void mover(DeltaState deltaState){
		if(this.getMob().getScene().brosEstaAbajoDeMob(this.getMob())&& this.getMob().esPeligroso()){
		     if(this.getMob().derecha&&this.getMob().getY()<this.getMob().gameDimension.getHeight()-40&&this.getMob().getScene().hayColisionConUnPiso(this.getMob())){
			      //Sprite sprite = Sprite.fromImage("MobSaltaDrc.png");
			      //this.getMob().setAppearance(sprite.crop(this.getMob().getAncho()+7, this.getMob().getAlto()));
			      this.getMob().setY(this.getMob().getY()+5);
			    //TODO actualiza tiempo de reaccion
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					//TODO adios sonido salto
			      //sonido salto abajo
			     // Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
			      //sonidoSalto.play();
			     }
			     if(!this.getMob().derecha&&this.getMob().getY()<this.getMob().gameDimension.getHeight()-40&&this.getMob().getScene().hayColisionConUnPiso(this.getMob())){
			      //Sprite sprite = Sprite.fromImage("MobSaltaIzq.png");
			      //this.getMob().setAppearance(sprite.crop(this.getMob().getAncho()+7, this.getMob().getAlto()));
			      this.getMob().setY(this.getMob().getY()+5);
			    //TODO actualiza tiempo de reaccion
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					//TODO adios sonido salto
			      //Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
			      //sonidoSalto.play();
			     }
		}
		if(this.getMob().esPeligroso()&&this.getMob().getScene().brosEstaArribaDeMob(this.getMob())&& this.getMob().getScene().hayColisionConUnPiso(this.getMob())&&this.getMob().esPeligroso()&&this.getMob().getScene().tieneUnPisoJustoArriba(this.getMob().getX(),this.getMob().getY()-100 , 100, this.getMob().getAncho())){
			this.getMob().getEstado().saltar();
			//TODO actualiza tiempo de reaccion
			this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
		}
		if(this.getMob().getScene().brosEstaALaDerechaDeMob(this.getMob())&&this.getMob().esPeligroso()&&this.getMob().noLlegoAlFinal() &&!this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
			this.moverALaDerecha(deltaState);
		}else
		if(this.getMob().esPeligroso()&&this.getMob().getScene().brosEstaALaIzquierdaDeMob(this.getMob())&&this.getMob().noLlegoAlComienzo()&&!this.getMob().getScene().terminaElPiso((this.getMob().getX()-1), this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
			this.moverALaIzquierda(deltaState);
		}else{
			
		}
	}


	public void update(DeltaState deltaState){
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