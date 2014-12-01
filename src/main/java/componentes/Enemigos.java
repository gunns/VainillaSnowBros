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
				this.añadirTrollsRojos(1, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=3){
				this.añadirTrollsRojos(1, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=6){
				this.mobs.removeAll(this.getEnemigos());
				this.añadirTrollsRojos(2, dim, playState, velocity);
				this.añadirTrollsVerdes(1, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=10){
				this.mobs.removeAll(this.getEnemigos());
				this.añadirTrollsRojos(1, dim, playState, velocity);
				this.añadirTrollsVerdes(1, dim, playState, velocity);
				this.añadirTrollsAmarillos(1, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=15){
				this.añadirTrollsAmarillos(1, dim, playState, velocity);
				this.añadirTrollsVerdes(1, dim, playState, velocity);
				this.añadirTrollsRojosEnojados(1, dim, playState, velocity);
			}else
			if(this.sbs.getNumeroNivel()<=19){
				this.añadirTrollsAmarillos(1, dim, playState, velocity);
				this.añadirTrollsRojosEnojados(1, dim, playState, velocity);
				this.añadirTrollsVerdes(1, dim, playState, velocity);
			}
		}
	public void añadirTrollsAmarillos(int num, Dimension dim, boolean playState, double velocity){
		int check=num;
		while(check>0){
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
	
	
	public Enemigos enemigosParaBoss(Dimension dim,boolean playState, double velocity) {
		// TODO Auto-generated method stub
		
		
		TrollRojo  tr1= new TrollRojo(dim, playState, ((velocity*2) /3 ) ) ;
		tr1.setY(0);
		tr1.setX(5);
		this.mobs.add(tr1);
		//this.mobs.add(new TrollRojo(dim, playState, (velocity*2)/3));
		
		TrollRojo  tr2= new TrollRojo(dim, playState, ((velocity*2) /3 ) ) ;
		tr2.setY(0);
		tr2.setX(dim.getWidth()/2 - 100);
		this.mobs.add(tr2);
		//this.mobs.add(new TrollAmarillo(dim, playState, (velocity*2)/3));
		
		TrollRojo  tr3= new TrollRojo(dim, playState, ((velocity*2) /3 ) ) ;
		tr3.setY(0);
		tr3.setX(dim.getWidth()/2  + 100);
		this.mobs.add(tr3);
		//this.mobs.add(new TrollAmarillo(dim, playState, (velocity*2)/3));
		
		TrollRojo  tr4= new TrollRojo(dim, playState, ((velocity*2) /3 ) ) ;
		tr4.setY(0);
		tr4.setX(dim.getWidth() - 50);
		this.mobs.add(tr4);
		
		return this;
		
	}

}
