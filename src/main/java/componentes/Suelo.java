package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class Suelo {
	private int anchura = 50;
	private int altura = 50;
	private int proporcionAlto;
	private int proporcionAncho;
	private Dimension gameDimension;
	private int density[][] = new int[anchura][altura];
	ArrayList<Piso> suelos;
	
	
	//Pasar por parametro los tamaños de la grilla (anchura y altura)
	public Suelo(Dimension dim){
			
		this.suelos = new ArrayList<Piso>();
		this.gameDimension= dim;
		this.proporcionAlto = (int)(dim.height/this.altura);
		this.proporcionAncho = (int)(dim.width/this.anchura);
		Fill();
		Pintar();
	}
	
	private void Pintar() {
		int i,j;
		  for (i=0;i<anchura;i++){
			  for (j=0;j<altura;j++){
				  if(density[i][j]==1){
					  Piso piso = new Piso(gameDimension.getHeight()-j*proporcionAlto,
						   i*proporcionAncho, Color.black, proporcionAncho, proporcionAlto);
					  this.suelos.add(piso);
				  }
			  }
		  }
	}


	private void unaLinea(int nSuelos, int k){
		int j; 
		Random r = new Random();
		int inicio =  r.nextInt(4);
		j = inicio;
		for (int val=nSuelos;val>0;val--){
			for (j=inicio;j<inicio+r.nextInt(4)+4;j++){
				if (j<anchura){
					density[j][k] = 1;
				}
				else{break;}
			}
			inicio = r.nextInt(5)+j;
		}
	}

	private void Fill(){
		Ground();
		int j;
		Random r = new Random();
		int valorDado = r.nextInt(7)+4;
		for (j=7;j<=28;j+=7){
			unaLinea(valorDado+1,j);
			valorDado = r.nextInt(7)+4;
		}
	}

//	private void Fill(){
//		Ground();
//		int j;
//		for (j=0;j<7;j++){
//			  density[j][7] = 1;
//			}
//		for (j=13;j<20;j++){
//			  density[j][7] = 1;
//			}
//		for (j=7;j<13;j++){
//			  density[j][14] = 1;
//			}
//		for (j=5;j<15;j++){
//			  density[j][21] = 1;
//			}
//		for (j=0;j<4;j++){
//			  density[j][28] = 1;
//			}
//		for (j=9;j<14;j++){
//			  density[j][28] = 1;
//			}
//		for (j=17;j<20;j++){
//			  density[j][28] = 1;
//			}
//	}

	private void Ground(){
		int j;
		for (j=0;j<anchura;j++){
		  density[j][1] = 1;
		}
	}
	
	public ArrayList<Piso> getSuelos() {
		return suelos;
	}
}
