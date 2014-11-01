package componentes;

import java.awt.Dimension;
import java.util.ArrayList;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;




public class Enemigos{
	
	public ArrayList<GameComponent<SnowBrosScene>> mobs;
	private Dimension dim; 
	public Enemigos(Dimension dim,boolean playState){
			this.dim=dim;
			Mob mob1=new Mob(dim,playState);
			mobs.add(mob1);
			Mob mob2=new Mob(this.dim,playState);
			mob2.setY(this.dim.getWidth()-10);
			mobs.add(mob2);
	}
	public ArrayList<GameComponent<SnowBrosScene>> getEnemigos(){
		return mobs;
	}

}
