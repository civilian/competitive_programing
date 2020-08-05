package uva.tomo110;
/*Uva 11085.*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class BackToThe8Queens {
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

//	static void dbg(Object... o) {
//		System.out.println(Arrays.deepToString(o));
//	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("BackToThe8Queens"));

		for (int i = 0; i < 9; i++)
			row[i] = 0;
		backtrack(1);

//		dbg(valids);
		for (int casesId = 1;; casesId++) {
			if(readln()==null)
				break;
			
			for (int i = 1; i <= 8; i++) {
				ori[i] = nextInt();
			}
			Prim.set(ori);
			min = Integer.MAX_VALUE;

			for (int i = 0; i < valids.size(); i++) {
				tmp = compare(Prim, valids.get(i));
				if (tmp < min) {
					min = tmp;
//					dbg("tmp<min valid",valids.get(i));
				}
			}
			
			System.out.printf("Case %d: %d\n",casesId,min);
		}

	}

	private static int compare(Conf prim2, Conf conf) {
		difss = 0;
		for (int i = 1; i <= 8; i++) {
			if (prim2.rows[i] != conf.rows[i]) {
				difss++;
			}
		}
		return difss;
	}

	private static int[] row = new int[9];
	private static int[] ori = new int[9];
	private static ArrayList<Conf> valids = new ArrayList<Conf>(56);
	private static int min = 0, tmp = -1, difss = 0; // it is ok to use global
														// variables in
														// competitive
														// programming

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
					Conf tmp=new Conf(row);
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
