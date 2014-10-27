package others;

import java.awt.Dimension;

import componentes.Snow;

public class Izquierda extends Direccion {

	@Override
	public void avanzaDisparo(Snow snow) {
		// TODO Auto-generated method stub
		
		
		/*   DISPARO HACIA LA IZQUIERDA
		public void avanzaDisparoIzquierda(){
		if(this.distance > 0){
			this.setX(this.getX() - 1);
			this.distance = distance - 1;
							 }
		else
			{
			if(this.falling > 0){
			this.setX(this.getX() - 1);
			this.setY(this.getY() + 1);
			}
			else
				{
				this.setY(this.getY() + 1);
				}
			}
		}
		
		*/
		
		if(snow.getDistance() > 0)
		{
			snow.setX(snow.getX() - 1);
			snow.setDistance(snow.getDistance() - 1);
		}
		else
		{
			if(snow.getFalling() > 0)
			{
			snow.setX(snow.getX() - 0.20);
			snow.setY(snow.getY() + 0.20);
			snow.setFalling(snow.getFalling() - 0.20);
			snow.setEnd(snow.getEnd() - 0.20);
			}
			else
				if(snow.getEnd() > 0.0){
				snow.setY(snow.getY() + 0.20);
				snow.setEnd(snow.getEnd() - 0.03); 
				}
				else
				snow.destroy();
		}
	}
	
	

	@Override
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		// TODO Auto-generated method stub
		return ((snow.getX() > 0) && (dim.getHeight() > (snow.getY() + (snow.getAppearance().getHeight()))));
		//return false;
		
	}
	

}
