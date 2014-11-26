package componentes;
import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

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
	//tiempoReanimacion = 400;
	
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
			
	
	
	
	sprites = new Sprite[19];
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
	sprites[16] = sprite8;
	sprites[17] = sprite9;
	sprites[18] = sprite10;
	
	 //sonido aparicion
    Sound sonidoAparicion = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("appear.wav"));
    sonidoAparicion.play();
	
}

public void update(DeltaState deltaState)
	{
	if(this.getScene().getPlayState())
	{
	
	if(this.current > 18)
	{
		Sprite spriteVive = Sprite.fromImage("BrosDrc.png");
		bros.setAppearance(spriteVive);
		bros.setX(bros.getAparicionEnX());
		bros.setY(bros.getAparicionEnY());
		bros.setZ(1);
		bros.setInvencible(true);
		bros.setTiempoInvencible(200);
		this.destroy();
		//
		//bros.nivelCompleto = false;
		sns.addComponent(bros);
	
	}
else
	{
this.setAppearance(sprites[current]);
//this.setY(this.getY() - (sprites[current]));
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
	
	/*
	if(this.tiempoReanimacion < 1)
		{
		//reposicionarBros
		Sprite spriteVive = Sprite.fromImage("BrosDrc.png");
		bros.setAppearance(spriteVive);
		bros.setX(sns.getGameDimension().getWidth()/2-bros.getAppearance().getWidth()/2);
		bros.setY(sns.getGameDimension().getHeight()-(bros.getAppearance().getHeight())-25);
		bros.setZ(1);
		sns.addComponent(bros);
		this.destroy();
		}
		else
			{
			tiempoReanimacion = tiempoReanimacion -1;
			}
			
			*/
	}
}

public void reposicionar(Integer current) {
	if(current < 18)
		{
		if(sprites[current -1].getHeight() < sprites[current].getHeight())
			{
			this.setY(this.getY() - (sprites[current].getHeight() - sprites[current - 1].getHeight()));
			}
		else
			//if(sprites[current].getHeight() > sprites[current - 1].getHeight())
			//{
			if(sprites[current -1].getHeight() > sprites[current].getHeight())
			this.setY(this.getY() + (sprites[current - 1].getHeight() - sprites[current].getHeight()));
			//}
		}
		
	
}


}
