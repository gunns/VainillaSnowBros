package agresividad;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Mob;

public class Pasivo extends EstadoAgresividad {
	boolean derecha;
	
	public Pasivo(Mob mob){
		this.setMob(mob);
		this.derecha=true;
	}

	public void volverseAgresivo(){
		this.getMob().setEstadoAgresividad(new Agresivo(this.getMob()));
	}
	
	public void mover(DeltaState deltaState){
		if(this.getMob().getScene().getPlayState()){
			if(this.getMob().esPeligroso()&&derecha&&this.getMob().noLlegoAlFinal() &&!this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
				this.moverALaDerecha(deltaState);
				if(!this.getMob().noLlegoAlFinal()||this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
					//TODO actualiza tiempo de reaccion
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					Sprite sprite = Sprite.fromImage("MobIzq.png");
					this.getMob().setAppearance(sprite);
					this.derecha=false;
				}
			}
			if(this.getMob().esPeligroso()&&!derecha&&this.getMob().noLlegoAlComienzo()&&!this.getMob().getScene().terminaElPiso((this.getMob().getX()-1), this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))
				{
				this.moverALaIzquierda(deltaState);
				if(!this.getMob().noLlegoAlComienzo()||this.getMob().getScene().terminaElPiso(this.getMob().getX()-1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))
						{
					//TODO actualiza tiempo de reaccion
					this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
					Sprite sprite = Sprite.fromImage("MobDrc.png");
					this.getMob().setAppearance(sprite);
					this.derecha=true;
				}
			}
			if(this.getMob().getScene().estaCercaBrosdeMob(this.getMob())){
				//TODO actualiza tiempo de reaccion
				this.getMob().setTiempoDeReaccionActual(this.getMob().getTiempoDeReaccion());
				this.volverseAgresivo();
			}
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
