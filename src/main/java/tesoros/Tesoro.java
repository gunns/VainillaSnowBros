package tesoros;

import java.awt.Color;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;

import componentes.Bros;

public class Tesoro extends GameComponent<SnowBrosScene>{
	
	Integer valor;
	int duracion;
	
	public Tesoro(int valor)
	{
		this.valor=valor;
		this.duracion = 5000;
		}
		
		public void update(DeltaState deltaState)
		{
			if(duracion < 1)
				{
				//TODO agregar puntos
				this.destroy();
				}
			else
				{
				duracion--;
				}
		}

		public boolean colisionaConBros(Bros bros) 
		{
			boolean colisiona = false;
			if(CollisionDetector.INSTANCE.collidesRectAgainstRect(bros.getX(),
					bros.getY(),(int) bros.getAppearance().getWidth(),
					(int) bros.getAppearance().getHeight(),
					this.getX(),this.getY(),(int) this.getAppearance().getWidth(), (int) this.getAppearance().getHeight()))
				colisiona = true;
			
			return colisiona;

		}
		
		public boolean equals(Class<?> clase)
			{
			return(clase == Tesoro.class);
			}

		public void sumarPuntaje(Bros bros) {
			bros.sumarPuntaje(this.getValor());
			this.destroy();
			
		}

		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
		}
		
		
}