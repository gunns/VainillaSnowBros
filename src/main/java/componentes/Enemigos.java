package componentes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import mobs.TrollAmarillo;
import mobs.TrollRojo;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;

public class Enemigos{
	
	public ArrayList<Mob> mobs;
	private Dimension dim; 
	
	public Enemigos(Dimension dim,boolean playState, double velocity){
			this.mobs = new ArrayList<Mob>();
			
		}
	public void añadirTrollsAmarillos(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check<=0){
			this.mobs.add(new TrollAmarillo(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsRojos(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check<=0){
			this.mobs.add(new TrollRojo(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsRojosEnojados(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check<=0){
			this.mobs.add(new TrollRojoEnojado(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsVerdes(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check<=0){
			this.mobs.add(new TrollVerde(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	
	
	public ArrayList<Mob> getEnemigos(){return mobs;}
}
