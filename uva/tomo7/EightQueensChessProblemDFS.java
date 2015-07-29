package uva.tomo7;

/*Uva 750*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class EightQueensChessProblemDFS { /* 8 Queens Chess Problem */
	// NO FUNCIONA, la forma en la que explora el espacio no va con el codigo,
	// se queda con la una columna llenada y trata con todas las filas, pero eso
	// deberia ser diferente, primero el coge una columna y una fila, y de ahi
	// hace lo mismo en profundidad revisando no dañar los estados anteriores

	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("EightQueensChessProblem"));
		readln();
		TC = nextInt();
		while (TC-- > 0) {
			readln();
			readln();
			a = nextInt();
			b = nextInt();
			for (int i = 0; i < 9; i++)
				row[i] = 0;
			lineCounter = 0;
			System.out.printf("SOLN       COLUMN\n");
			System.out.printf(" #      1 2 3 4 5 6 7 8\n\n");
			backtrack(1); // generate all possible 8! candidate solutions
			if (TC > 0)
				System.out.printf("\n");
		}
	}

	private static int[] row = new int[9];
	private static int TC, a, b, lineCounter; // it is ok to use global
												// variables in competitive
												// programming

	private static boolean place(int col, int tryrow) {
		for (int prev = 1; prev < col; prev++)
			// check previously placed queens
			if (row[prev] == tryrow
					|| (Math.abs(row[prev] - tryrow) == Math.abs(prev - col)))
				return false; // an infeasible solution if share same row or
								// same diagonal
		return true;
	}

	private static void backtrack(int col) {

		LinkedList<Integer> busq = new LinkedList<Integer>();
		busq.add(col);
		// dbg(AdjList);
		while (!busq.isEmpty()) {
			col = busq.removeFirst();
			// dbg(busq);
			for (int tryrow = 1; tryrow <= 4; tryrow++) {
				dbg("a", a, "b", b, "place", place(col, tryrow), "row", tryrow,
						"col", col, "row", row);
				// if (col == b && tryrow != a)
				// continue;
				// try all possible row
				if (place(col, tryrow)) { // if can place a queen at this col
											// and
											// row...
					row[col] = tryrow; // put this queen in this col and row
					if (col == 8) { // a candidate solution & (a, b)
									// has 1 queen
						System.out.printf("%2d      %d", ++lineCounter, row[1]);
						for (int j = 2; j <= 8; j++)
							System.out.printf(" %d", row[j]);
						System.out.printf("\n");

					} else {
						busq.addFirst(col + 1);
						dbg(busq);
					}
				}
			}
		}

	}

}
