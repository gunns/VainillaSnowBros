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
			      //sonido salto abajo
			      Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
			      sonidoSalto.play();
			     }
			     if(!this.getMob().derecha&&this.getMob().getY()<this.getMob().gameDimension.getHeight()-40&&this.getMob().getScene().hayColisionConUnPiso(this.getMob())){
			      //Sprite sprite = Sprite.fromImage("MobSaltaIzq.png");
			      //this.getMob().setAppearance(sprite.crop(this.getMob().getAncho()+7, this.getMob().getAlto()));
			      this.getMob().setY(this.getMob().getY()+5);
			      Sound sonidoSalto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("jump_11.wav"));
			      sonidoSalto.play();
			     }
		}
		if(this.getMob().getScene().brosEstaArribaDeMob(this.getMob())&& this.getMob().getScene().hayColisionConUnPiso(this.getMob())&&this.getMob().esPeligroso()){
			this.getMob().getEstado().saltar();
		}
	}
	public void update(DeltaState deltaState){
		this.mover(deltaState);
	}

}