package others;

import java.awt.Dimension;

import componentes.Snow;

public class Derecha extends Direccion{

	@Override
	public void avanzaDisparo(Snow snow) {
		if(snow.getDistance() > 1) {
			if(snow.getFalling() > 0) {
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
		}	
	}

	@Override
	public boolean puedeRecorrer(Dimension dim, Snow snow) {
		return ((  (snow.getX() + (snow.getAppearance().getWidth())) < dim.getWidth()) && dim.getHeight() > (snow.getY() + (snow.getAppearance().getHeight())) );
	}
}
