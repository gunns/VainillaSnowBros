package mobConNieve;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Mob;
import componentes.Snow;

public class NieveFaseF extends EstadoNieve{
	
	public NieveFaseF(Mob mob, double duracionNieve) {
		super(mob, duracionNieve);
		this.getMob().setY(this.getMob().getY()-10);
		Sprite sprite = Sprite.fromImage("MobSnow4.png");
		this.getMob().setAppearance(sprite);
	}

	@Override
	public void derritiendoNieve() {
		if(this.getDuracionNieve() < 1){
			this.getMob().setEstadoNieve(new NieveFase3(this.getMob(), 500));
			this.getMob().setY(this.getMob().getY()-10);
		}
		else {this.setDuracionNieve(this.getDuracionNieve() - 1);}
	}

	@Override
	public void agregandoNieve(Snow snow) {		
		//TODO modificar al agregar los demas estados
		this.setDuracionNieve((double)1200);		
	}

	public boolean PuedoEmpujar() { return true;}

	@Override
	public void arrolla(Mob mob) {
		if(mob.getScene().colisionaEsferaConEsfera(this.getMob(),mob ) )
			{
			this.getMob().setEstadoNieve(new Empujado(this.getMob(), 0, this.getMob().getDir().direccionContraria(), mob.getEstadoNieve().getBros()));
			mob.getEstadoNieve().setDir(mob.getDir().direccionContraria());
			//sonido empuje
			Sound sonidoEmpuje = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("pullSnowBall.wav"));
			sonidoEmpuje.play();
			//this.getMob().getEstadoNieve().getBros().sumarPuntaje(500);
			}
	}
}
