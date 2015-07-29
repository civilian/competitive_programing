package maratonBook.paradigmas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BusquedaCompleta_EightQueens {
	/*
	 * Ejemplo de busqueda completa=recursive backtraking se prueba todo el
	 * espacio de busqueda mirando si es una solucion plausible aqui se hace con
	 * el problema de las 8-queens con recursion probando las permutaciones de las filas
	 */

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 9; i++)
			row[i] = 0;
		backtrack(1);
		// dbg(valids);
	}

	//row[i]=>(row[i],i);
	private static int[] row = new int[9];
	private static ArrayList<Conf> valids = new ArrayList<Conf>(56);

	static Conf Prim = new Conf(row);

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
		for (int tryrow = 1; tryrow <= 8; tryrow++) {
			// try all possible row
			if (place(col, tryrow)) { // if can place a queen at this col and
										// row...
				row[col] = tryrow; // put this queen in this col and row
				if (col == 8) {
					Conf tmp = new Conf(row);
					valids.add(tmp);
				} else
					backtrack(col + 1); // recursively try next column
			}
		}
	}

}

class Conf {
	@Override
	public String toString() {
		return "(" + Arrays.toString(rows) + ")\n";
	}

	int[] rows;

	public Conf(int[] rows) {
		this.rows = rows.clone();
	}

	public void set(int[] rows) {
		this.rows = rows.clone();
	}
}
