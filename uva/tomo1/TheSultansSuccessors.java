package uva.tomo1;

/*Uva 167*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TheSultansSuccessors { /* 8 Queens Chess Problem */
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
//		input = new BufferedReader(new FileReader("TheSultansSuccessors"));
		readln();
		TC = nextInt();
		while (TC-- > 0) {
			
			for (int i = 1; i <=8 ; i++) {
				readln();
				for (int j = 1; j <= 8; j++) {
					tab[i][j]=nextInt();
				}
			}
			
			for (int i = 0; i < 9; i++)
				row[i] = 0;
			
			max=0;
//			dbg(tab);
			backtrack(1); // generate all possible 8! candidate solutions
			
			System.out.printf("%5d\n",max);
		}
	}

	static int[][] tab=new int[9][9];
	private static int[] row = new int[9];
	private static int TC,  max, tmp; // it is ok to use global
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
		for (int tryrow = 1; tryrow <= 8; tryrow++) {
			// try all possible row
			if (place(col, tryrow)) { // if can place a queen at this col and
										// row...
				row[col] = tryrow; // put this queen in this col and row
				if (col == 8) {
					tmp=0;
					for (int i = 1; i <= 8; i++) {
						tmp+=tab[row[i]][i];
					}
					if (tmp>max) {
						max=tmp;
//						dbg(row);
					}
				} else
					backtrack(col + 1); // recursively try next column
			}
		}
	}

}
