package componentes;

import java.awt.Dimension;
import java.util.ArrayList;


public class Enemigos{
	
	public ArrayList<Mob> mobs;
	private Dimension dim; 
	
	public Enemigos(Dimension dim,boolean playState){
			this.mobs = new ArrayList<Mob>();
			this.dim=dim;
			Mob mob1=new Mob(dim,playState);
			mobs.add(mob1);
			Mob mob2=new Mob(this.dim,playState);
			mob2.setY(40);
			mob2.setX(240);
			mobs.add(mob2);
	}
	public ArrayList<Mob> getEnemigos(){
		return mobs;
	}

}
