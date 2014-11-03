package others;

import java.awt.Dimension;

import componentes.Snow;

public class Derecha extends Direccion{

	@Override
	public void avanzaDisparo(Snow snow) {
		// TODO Auto-generated method stub
		
		/*   DISPARO HACIA LA DERECHA
		public void avanzaDisparoDerecha(){
		if(this.distance > 0){
			this.setX(this.getX() + 1);
			this.distance = distance - 1;
							 }
		else
			{
			if(this.falling > 0){
			this.setX(this.getX() + 1);
			this.setY(this.getY() + 1);
			}
			else
				{
				this.setY(this.getY() + 1);
				}
			}
		}
		
		*/
		
		if(snow.getDistance() > 1)
		{
			if(snow.getFalling() > 0)
			{
				if(snow.getInitialX()+10>snow.getX()){
					snow.setY(snow.getY() - 0.10);
				}
				else if(snow.getInitialX()+30<snow.getX()&&snow.getInitialX()+50>snow.getX()){
					snow.setY(snow.getY() + 0.20);
				}
				else if(snow.getInitialX()+50<snow.getX())
					snow.setY(snow.getY()+0.50);
				
				snow.setFalling(snow.getFalling() - 0.30);
				snow.setX(snow.getX() + 0.80);
				
				}
			
			
			
				
				else
					snow.destroy();
				
				//snow.setFalling(snow.getFalling() - 0.20);
				//snow.setEnd(snow.getEnd() - 0.20);
			}
			//else
				//if(snow.getEnd() < 0.0){
					//snow.setY(snow.getY() + 0.20);
					//snow.setEnd(snow.getEnd() - 0.1); 
					//}
					//else
					//snow.destroy();
		}

	@Override
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		// TODO Auto-generated method stub
		//return ((this.getX() < this.gameDimension.getWidth()) && (this.gameDimension.getHeight() > this.getY()))
		return ((  (snow.getX() + (snow.getAppearance().getWidth())) < dim.getWidth()) && dim.getHeight() > (snow.getY() + (snow.getAppearance().getHeight())) );
		//return false;
		
	}

}
