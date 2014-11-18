package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;

import componentes.Mob;
import componentes.Snow;

public class NieveFase2 extends EstadoNieve{
	
	public NieveFase2(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		Sprite sprite = Sprite.fromImage("MobSnow2.png");
		this.getMob().setAppearance(sprite);
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase1(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);
		}
		else { this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve(Snow snow) {
				
					
				double tiempoActual = this.getDuracionNieve();
				if(snow.isPotencia())
					this.getMob().setEstadoNieve(new NieveFaseF(this.getMob(), ((double)800 + tiempoActual ) ));	
				//TODO modificar al agregar los demas estados
				else
				this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), ((double)500 +  tiempoActual) ));
				
				snow.getBros().sumarPuntaje(5);
	}
}
