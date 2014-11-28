package mobConNieve;

import java.awt.Color;

import tesoros.Tesoro;
import tesoros.Tesoros;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import componentes.Bros;
import componentes.Mob;
import componentes.Snow;
import dulces.Caramelo;
import dulces.Dulce;

public class Muerto extends EstadoNieve{

	
	Bros bros;
	public Muerto(Mob mob, Bros bros, double duracionMuerto) {
		super(mob, duracionMuerto);
		//Sprite sprite = Sprite.fromImage("MobMuerto.png");
		this.getMob().setAppearance(this.getMob().getApariencia().getMobMuere());
		this.bros = bros;
		//sonido muerto
		Sound sonidoMuerto = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("mobDie.wav"));
		sonidoMuerto.play();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derritiendoNieve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregandoNieve(Snow snow) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(DeltaState deltaState)
	{
		if(this.getDuracionNieve() < 1)
			{
			//TODO ejemplo de dulce, se necesita de un algoritmo para generar el objeto
			Tesoros tesoros = new Tesoros(); 
			Tesoro premio = (Tesoro)tesoros.dropear();
			premio.setX(this.getMob().getX());
			premio.setY(this.getMob().getY() + (this.getMob().getAppearance().getHeight()/2));
			this.getMob().getScene().addComponent(premio);
			
			this.getBros().sumarPuntaje(300);
			this.getMob().destroy();
			}
		else
			{
			this.setDuracionNieve(this.getDuracionNieve() - 1);
			}
	}

	public Bros getBros() {
		return bros;
	}

	public void setBros(Bros bros) {
		this.bros = bros;
	}

	public  void arrolla(Mob mob)
	{
	
	}
	
	
}
