package componentes;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;

public class AnimacionRegeneracion extends GameComponent<SnowBrosScene>{
	
	
	
	//OLVIDALO: MEJOR SIN ANIMACION
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
	Bros bros;
	
	public AnimacionRegeneracion(Integer tiempo, Bros bros)
	{
		this.bros = bros;
		
		this.setX(bros.getAparicionEnX()- 20);
		this.setY(bros.getScene().getGameDimension().getHeight() - 25);
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
		
		
	//asignar sprites
		
	//crear array
		
	//asignar sprites al array
		
	//crear animacion	tiempo 25 y el array
		//
		
	
	}
	
	public void update(DeltaState deltaState)
	{
		
		if(this.current > 18)
			{
			this.destroy();
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
	}

	private void reposicionar(Integer current) {
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
