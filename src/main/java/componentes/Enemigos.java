package componentes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import com.uqbar.snowBros.SnowBrosScene;

import mobs.TrollAmarillo;
import mobs.TrollRojo;
import mobs.TrollRojoEnojado;
import mobs.TrollVerde;

public class Enemigos{
	
	public ArrayList<Mob> mobs;
	private Dimension dim; 
	public SnowBrosScene sbs;
	
	
	public Enemigos(Dimension dim,boolean playState, double velocity,SnowBrosScene snowi){
			this.mobs = new ArrayList<Mob>();
			this.sbs=snowi;
			if(this.sbs.getNumeroNivel()<=1){
				this.añadirTrollsRojos(2, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=3){
				this.añadirTrollsRojos(4, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=6){
				this.añadirTrollsRojos(3, dim, playState, velocity);
				this.añadirTrollsVerdes(2, dim, playState, velocity);
			}
			else
			if(this.sbs.getNumeroNivel()<=10){
				this.añadirTrollsRojos(2, dim, playState, velocity);
				this.añadirTrollsVerdes(2, dim, playState, velocity);
				this.añadirTrollsAmarillos(2, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=15){
				this.añadirTrollsAmarillos(2, dim, playState, velocity);
				this.añadirTrollsVerdes(2, dim, playState, velocity);
				this.añadirTrollsRojosEnojados(2, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=19){
				this.añadirTrollsAmarillos(2, dim, playState, velocity);
				this.añadirTrollsRojosEnojados(3, dim, playState, velocity);
				this.añadirTrollsVerdes(2, dim, playState, velocity);
				this.añadirTrollsRojos(1, dim, playState, velocity);
			}
		}
	public void añadirTrollsAmarillos(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check>=0){
			this.mobs.add(new TrollAmarillo(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsRojos(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check>0){
			this.mobs.add(new TrollRojo(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsRojosEnojados(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check>0){
			this.mobs.add(new TrollRojoEnojado(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	public void añadirTrollsVerdes(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check>0){
			this.mobs.add(new TrollVerde(dim,playState,(velocity*2)/3));
			check=check-1;
		}
	}
	
	
	public ArrayList<Mob> getEnemigos(){return mobs;}
}
