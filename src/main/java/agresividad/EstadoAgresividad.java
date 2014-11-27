package agresividad;

import others.Derecha;
import others.Izquierda;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Mob;

public class EstadoAgresividad {
	private Mob mob;

	public Mob getMob() {
		return mob;
	}

	public void setMob(Mob mob) {
		this.mob = mob;
	}
	public void update(DeltaState deltaState){
		
	}
	public void moverALaDerecha(DeltaState deltaState) {
		if(!this.getMob().getScene().getSystemPause()){
			this.getMob().dir = new Derecha();
			if(!getMob().playState && !this.getMob().getScene().getPlayState()){
				if (this.getMob().noLlegoAlFinal()){
					this.getMob().setX(this.getMob().getX()+(this.getMob().getScene().getVelocity()+ (this.getMob().getVelocity()/4 - 4))* deltaState.getDelta());
					Sprite sprite = Sprite.fromImage("MobDrc.png");
					this.getMob().setAppearance(sprite);
				}
			}else{
				if (this.getMob().noLlegoAlFinal()){
					this.getMob().setX(this.getMob().getX()+(this.getMob().getScene().getVelocity()+ (this.getMob().getVelocity()/4 - 4))* deltaState.getDelta());
					Sprite sprite = Sprite.fromImage("MobDrc.png");
					this.getMob().setAppearance(sprite);
				}
			}
		}
	}
	
	public void moverALaIzquierda(DeltaState deltaState) {
		if(!this.getMob().getScene().getSystemPause()){
			this.getMob().dir = new Izquierda();
			if(!this.getMob().playState && !this.getMob().getScene().getPlayState()){
				if (this.getMob().noLlegoAlComienzo()){
					this.getMob().setX(this.getMob().getX()-(this.getMob().getVelocity()*1.5 + (this.getMob().getVelocity()/4))* deltaState.getDelta());
					Sprite sprite = Sprite.fromImage("MobIzq.png");
					this.getMob().setAppearance(sprite);
					
				}
			}else{
				if (this.getMob().noLlegoAlComienzo()){
					this.getMob().setX(this.getMob().getX()-(this.getMob().getVelocity() + (this.getMob().getVelocity()/4))* deltaState.getDelta());
					Sprite sprite = Sprite.fromImage("MobIzq.png");
					this.getMob().setAppearance(sprite);
				}
			}
		}
	}
}
