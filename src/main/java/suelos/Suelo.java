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

	public Suelo(Dimension dim) {

		this.suelos = new ArrayList<Piso>();
		this.gameDimension = dim;
		this.proporcionAlto = (int) (dim.height / this.altura);
		this.proporcionAncho = (int) (dim.width / this.anchura);
		this.floorGen = new FloorGen(anchura, altura);
		Fill();
		Pintar();
	}

	private void Pintar() {
		int i, j;
		for (i = 0; i < anchura; i++) {
			for (j = 0; j < altura; j++) {
				if (density[i][j] == 1) {
					Piso piso = new Piso(gameDimension.getHeight() - j
							* proporcionAlto, i * proporcionAncho, Color.black,
							proporcionAncho, proporcionAlto);
					this.suelos.add(piso);
				}
			}
		}
	}

	private void Fill() {
		this.density = floorGen.lvl1_1();
	}

	public ArrayList<Piso> getSuelos() {
		return suelos;
	}
}