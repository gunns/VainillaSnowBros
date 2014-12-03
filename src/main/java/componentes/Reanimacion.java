package componentes;
import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

import estadosCapsula.EstadoCapsula;

public class Reanimacion extends GameComponent<SnowBrosScene>{
	
Bros bros;
Integer tiempoReanimacion;
SnowBrosScene sns;

Integer tiempoAnimacion;
Integer tiempoAnimacionAux;
Integer current;

Sprite sprite1;
Sprite sprite2;
Sprite sprite3;
Sprite sprite4;
Sprite sprite5;
Sprite sprite6;
Sprite sprite7;
Sprite sprite8;
Sprite sprite9;
Sprite sprite10;

Sprite[] sprites;


public Reanimacion(Bros bros, SnowBrosScene sns, Integer tiempo){
	this.bros = bros;
	this.sns = sns;
	
	this.setX(bros.getAparicionEnX() - 17);
	this.setY(sns.getGameDimension().getHeight() - 25);
	this.current = 0;
	this.tiempoAnimacion = tiempo;
	this.tiempoAnimacionAux = tiempo;
	
	sprite1 = Sprite.fromImage("appear1.png");
	sprite2 = Sprite.fromImage("appear2.png");
	sprite3 = Sprite.fromImage("appear3.png");
	sprite4 = Sprite.fromImage("appear4.png");
	sprite5 = Sprite.fromImage("appear5.png");
	sprite6 = Sprite.fromImage("appear6.png");
	sprite7 = Sprite.fromImage("appear7.png");
	sprite8 = Sprite.fromImage("appear8.png");
	sprite9 = Sprite.fromImage("appear9.png");
	sprite10 = Sprite.fromImage("appear10.png");		
			
	
	
	
	sprites = new Sprite[16];
	sprites[0] = sprite1;
	sprites[1] = sprite2;
	sprites[2] = sprite3;
	sprites[3] = sprite4;
	sprites[4] = sprite5;
	sprites[5] = sprite6;
	sprites[6] = sprite7;
	sprites[7] = sprite8;
	sprites[8] = sprite9;
	sprites[9] = sprite10;
	sprites[10] = sprite8;
	sprites[11] = sprite9;
	sprites[12] = sprite10;
	sprites[13] = sprite8;
	sprites[14] = sprite9;
	sprites[15] = sprite10;
	
    Sound sonidoAparicion = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("appear.wav"));
    sonidoAparicion.play();
	
}

public void update(DeltaState deltaState)
	{
	if(this.getScene().getPlayState())
	{
	
	if(this.current > 15)
	{
		Sprite spriteVive = Sprite.fromImage("BrosDrc.png");
		bros.setAppearance(spriteVive);
		bros.setX(bros.getAparicionEnX());
		bros.setY(bros.getAparicionEnY());
		bros.setZ(1);
		bros.setInvencible(true);
		bros.setTiempoInvencible(500);
		bros.setEstadoCapsula(new EstadoCapsula());
		bros.setVelocity(this.getScene().getVelocity());
		this.destroy();
		sns.addComponent(bros);
	
	}
else
	{
this.setAppearance(sprites[current]);
if(this.tiempoAnimacionAux < 1)
		{
		current = current + 1;
		this.reposicionar(current);
		tiempoAnimacionAux = tiempoAnimacion;
		}
	else
		{
		tiempoAnimacionAux = tiempoAnimacionAux - 1;	
		}	
	}
	
	}
}

public void reposicionar(Integer current) {
	if(current < 15)
		{
		if(sprites[current -1].getHeight() < sprites[current].getHeight())
			{
			this.setY(this.getY() - (sprites[current].getHeight() - sprites[current - 1].getHeight()));
			}
		else
			if(sprites[current -1].getHeight() > sprites[current].getHeight())
			this.setY(this.getY() + (sprites[current - 1].getHeight() - sprites[current].getHeight()));
		}
		
	
}


}
