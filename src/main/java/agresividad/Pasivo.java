package agresividad;

import com.uqbar.vainilla.DeltaState;
import componentes.Mob;

public class Pasivo extends EstadoAgresividad {
	boolean derecha;
	
	public Pasivo(Mob mob){
		this.setMob(mob);
		this.derecha=true;
	}

	// NO PUEDO HACER ANDAR EL MOVER, SI ALGUNO ME PUEDE AYUDAR LE AGRADESCO
	public void mover(DeltaState deltaState){
		if(this.getMob().getScene().getPlayState()){
			if(this.getMob().esPeligroso()&&derecha&&this.getMob().noLlegoAlFinal() &&!this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
				this.moverALaDerecha(deltaState);
				if(!this.getMob().noLlegoAlFinal()||this.getMob().getScene().terminaElPiso(this.getMob().getX()+1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1)){
					this.derecha=false;
				}
			}
			if(this.getMob().esPeligroso()&&!derecha&&this.getMob().noLlegoAlComienzo()&&!this.getMob().getScene().terminaElPiso((this.getMob().getX()-1), this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))
				{
				this.moverALaIzquierda(deltaState);
				if(!this.getMob().noLlegoAlComienzo()||this.getMob().getScene().terminaElPiso(this.getMob().getX()-1, this.getMob().getY()+this.getMob().getAppearance().getHeight()+1))
						{
					this.derecha=true;
				}
			}
		}
	}
	public void update(DeltaState deltaState){
		this.mover(deltaState);
	}
}
