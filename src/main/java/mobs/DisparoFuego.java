package mobs;

import java.awt.Color;

import others.Direccion;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.appearances.Sprite;

public class DisparoFuego extends GameComponent<SnowBrosScene>{
	public Direccion direccion;
	public Integer distancia;
	
	public Sprite fuegoIzquierda;
	public Sprite fuegoDerecha;
	
	
	public DisparoFuego(Direccion dir, TrollVerde troll)
		{
		this.setAppearance(new Rectangle(Color.red, 20, 20));
		direccion = dir;
		distancia = 100;
		this.fuegoDerecha = Sprite.fromImage("DisparoDrc.png");
		this.fuegoIzquierda = Sprite.fromImage("DisparoIzq.png");
		
		
		this.setX(troll.getX() + (troll.getAppearance().getWidth()/2));
		this.setY(troll.getY());// + (troll.getAppearance().getHeight()/2));
		}
	
	public void update(DeltaState deltaState)
		{
		
		direccion.spritefuego(this);
		if(this.distancia >= 0)
			{
			direccion.recorreFuego(this);
			this.distancia = this.distancia -1;
			
			this.getScene().matarBrosEnElCamino(this);
			
			}
		else
			{
			this.destroy();
			}
		}
		

}
