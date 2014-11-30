package suelos;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class Suelo {
	private int anchura = 50;
	private int altura = 50;
	private int proporcionAlto;
	private int proporcionAncho;
	private Dimension gameDimension;
	private int density[][] = new int[anchura][altura];
	ArrayList<Piso> suelos;
	
	private FloorGen floorGen;
	
	
	
	//Pasar por parametro los tamaños de la grilla (anchura y altura)
	public Suelo(Dimension dim){
			
		this.suelos = new ArrayList<Piso>();
		this.gameDimension= dim;
		this.proporcionAlto = (int)(dim.height/this.altura);
		this.proporcionAncho = (int)(dim.width/this.anchura);
		this.floorGen = new FloorGen(anchura, altura);
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

	private void Fill(){
		
		this.density = floorGen.randomFloor();
//		this.density = floorGen.lvl1_7();
////	imprimirMatrix();
	}
	
	private void imprimirMatrix() {
		System.out.print("[");
		for (int j=0;j<altura;j++){
			System.out.print("[");
			for(int i=0;i<anchura;i++){
				System.out.print(density[i][j]);
				if(i!=anchura-1){
					System.out.print(",");
				}
			}
			if(j!=altura-1){
				System.out.print("],");
				System.out.println("");
			}else{
				System.out.print("]");
			}
			
		}
		System.out.println("]");
	}

	
	public ArrayList<Piso> getSuelos() {
		return suelos;
	}
}