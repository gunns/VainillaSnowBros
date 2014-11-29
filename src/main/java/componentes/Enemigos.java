package componentes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import mobs.TrollAmarillo;
import mobs.TrollRojo;
import mobs.TrollVerde;

public class Enemigos{
	
	public ArrayList<Mob> mobs;
	private Dimension dim; 
	
	public Enemigos(Dimension dim,boolean playState, double velocity){
			this.mobs = new ArrayList<Mob>();
			
			
			/*
			this.dim=dim;
			Random r = new Random();
			int nEnemigos=0;
			for (nEnemigos=0; nEnemigos <= r.nextInt(2)+4; nEnemigos++){
				//Mob aMob=new Mob(this.dim,playState, velocity);
				
				
				
				Mob aMob= new TrollRojo(this.dim,playState, velocity);
				for (Mob c :mobs){
					if(c.getX()==aMob.getX() && c.getY()==aMob.getY()){
						aMob.setX(r.nextInt(dim.width-(int)aMob.getAppearance().getWidth()));
					}
				}
				mobs.add(aMob);
			}
			
			*/
				
			this.mobs.add(new TrollRojo(dim, playState, (velocity*2)/3));
			this.mobs.add(new TrollRojo(dim, playState, (velocity*2)/3));
			this.mobs.add(new TrollRojo(dim, playState, (velocity*2)/3));
			
			this.mobs.add(new TrollVerde(dim, playState, (velocity*2)/3));
			this.mobs.add(new TrollVerde(dim, playState, (velocity*2)/3));
			
			
			this.mobs.add(new TrollAmarillo(dim, playState, (velocity*2)/3));
			this.mobs.add(new TrollAmarillo(dim, playState, (velocity*2)/3));
			this.mobs.add(new TrollAmarillo(dim, playState, (velocity*2)/3));
			}
	
	
	public ArrayList<Mob> getEnemigos(){return mobs;}
}
