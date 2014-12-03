package suelos;

import java.util.Random;

public class FloorGen {

	private int anchura;
	private int altura;

	public FloorGen(int anchura, int altura) {
		this.anchura = anchura;
		this.altura = altura;
	}

	public int[] Ground() {
		int line[] = new int[anchura];
		int j;
		for (j = 0; j < anchura; j++) {
			line[j] = 1;
		}
		return line;
	}

	private void fillLine(int[][] density, int row, int[] metodo) {
		int j;
		int[] line = metodo;
		for (j = 0; j < anchura; j++) {
			density[j][row] = line[j];
		}
	}

	public int[][] randomFloor() {
		Random r = new Random();
		int index = r.nextInt(4);
		switch (index) {
		case 0: {
			return lvl1_1();
		}
		case 1: {
			return lvl1_2();
		}
		case 2: {
			return lvl1_3();
		}
		case 3: {
			return lvl1_4();
		}
		case 4: {
			return lvl1_5();
		}
		case 5: {
			return lvl1_6();
		}
		case 6: {
			return lvl1_7();
		}
		default: {
			return lvl1_1();
		}
		}
	}

	public int[][] lvl1_1() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, oneMiddelLine(9));
		fillLine(density, 14, twoFloatingLines(12));
		fillLine(density, 21, threelLines(9));
		fillLine(density, 28, oneMiddelLine(21));

		return density;
	}

	public int[][] lvl1_2() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, twoFloatingLines(10));
		fillLine(density, 14, oneMiddelLine(15));
		fillLine(density, 21, threelLines(12));
		fillLine(density, 28, oneMiddelLine(21));

		return density;
	}

	public int[][] lvl1_3() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, threelLines(12));
		fillLine(density, 14, twoFloatingLines(10));
		fillLine(density, 21, oneMiddelLine(19));
		fillLine(density, 28, oneFloatingRightLine(10));

		return density;
	}

	public int[][] lvl1_4() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, twoFloatingLines(10));
		fillLine(density, 14, threelLines(12));
		fillLine(density, 21, twoFloatingLines(10));
		fillLine(density, 28, oneMiddelLine(10));

		return density;
	}

	public int[][] lvl1_5() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, oneLeftLine(30));
		fillLine(density, 14, oneRightlLine(25));
		fillLine(density, 21, twoFloatingLines(10));
		fillLine(density, 28, oneMiddelLine(30));

		return density;
	}

	public int[][] lvl1_6() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, twoFloatingLines(15));
		fillLine(density, 14, oneRightlLine(25));
		fillLine(density, 21, twoFloatingLines(10));
		fillLine(density, 28, oneMiddelLine(30));

		return density;
	}

	public int[][] lvl1_7() {
		int density[][] = new int[anchura][altura];

		fillLine(density, 1, Ground());
		fillLine(density, 7, threelLines(10));
		fillLine(density, 14, oneFloatingLeftLine(25));
		fillLine(density, 21, oneFloatingRightLine(20));
		fillLine(density, 28, oneMiddelLine(30));

		return density;
	}

	public int[] oneMiddelLine(int size) {
		int line[] = new int[anchura];
		int j;
		int begins = anchura / 2 - size / 2;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] oneLeftLine(int size) {
		int line[] = new int[anchura];
		int j;
		for (j = 0; j < size; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] oneRightlLine(int size) {
		int line[] = new int[anchura];
		int j;
		for (j = anchura - size; j < anchura; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] oneFloatingLeftLine(int size) {
		int line[] = new int[anchura];
		int j;
		int begins = anchura / 3 - size / 3;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] oneFloatingRightLine(int size) {
		int line[] = new int[anchura];
		int j;
		int begins = anchura / 3 * 2 - size / 3;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] twoFloatingLines(int size) {
		int line[] = new int[anchura];
		int j;
		int begins = anchura / 3 - size / 3 * 2;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		begins = (anchura / 3) * 2 - size / 3;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		return line;
	}

	public int[] threelLines(int size) {
		int line[] = new int[anchura];
		int j;
		for (j = 0; j < size; j++) {
			line[j] = 1;
		}
		int begins = anchura / 2 - size / 2;
		for (j = begins; j < begins + size; j++) {
			line[j] = 1;
		}
		for (j = anchura - size; j < anchura; j++) {
			line[j] = 1;
		}
		return line;
	}

}