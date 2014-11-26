package componentes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class Enemigos{
	
	public ArrayList<Mob> mobs;
	private Dimension dim; 
	
	public Enemigos(Dimension dim,boolean playState, double velocity){
			this.mobs = new ArrayList<Mob>();
			this.dim=dim;
			Random r = new Random();
			int nEnemigos=0;
			for (nEnemigos=0; nEnemigos <= r.nextInt(2)+4; nEnemigos++){
				Mob aMob=new Mob(this.dim,playState, velocity);
				mobs.add(aMob);
			}
	}
	
	public ArrayList<Mob> getEnemigos(){return mobs;}
}
