class Field {
	int[][] field;
	int size;
	char liveCell;

	public Field(int s, char live, double amtAlive) {
		size = s;
		liveCell = live;
		field = new int[size][size];
		for (int i = 0; i < size; i++){
			for (int n = 0; n < size; n++) {
				field[i][n] = Math.random() < amtAlive ? 1 : 0;
			}
		}
	}

	public void disp() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		for (int[] row : field) {
			for (int entry : row) {
				if (entry == 1) {
					System.out.print(liveCell);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}


	public int checkNeighbors(int i, int n) {
		return field[Math.floorMod(i + 1, size)][n]
				+ field[Math.floorMod(i - 1, size)][n]
				+ field[i][Math.floorMod(n + 1, size)]
				+ field[i][Math.floorMod(n - 1, size)]
				+ field[Math.floorMod(i + 1, size)][Math.floorMod(n + 1, size)]
				+ field[Math.floorMod(i + 1, size)][Math.floorMod(n - 1, size)]
				+ field[Math.floorMod(i - 1, size)][Math.floorMod(n + 1, size)]
				+ field[Math.floorMod(i - 1, size)][Math.floorMod(n - 1, size)];

	}


	public void update() {
		int[][] tempField = field;
		for (int i = 0; i < size; i++) {
			for (int n = 0; n < size; n++) {
				int neighbors = checkNeighbors(i, n);

				if (neighbors < 2) {
					tempField[i][n] = 0;
				} else if (	neighbors > 3) {
					tempField[i][n] = 0;
				} else if(neighbors == 3) {
					tempField[i][n] = 1;
				}
			}
		}
		field = tempField;
	}

}

public class Conway {
	public static void main(String[] args) {
		Field field = new Field(Integer.parseInt(args[0]), '*', Double.parseDouble(args[1]));

		while (true) {
			field.disp();
			try {
				Thread.sleep(Integer.parseInt(args[2]));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			field.update();
		}
		//System.out.println(field.checkNeighbors(1, 1));
	}
}
