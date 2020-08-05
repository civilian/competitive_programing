package uva.tomo115;

/*Uva 11581*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class GridSuccessors {
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("GridSuccessors"));
		readln();

		int numCases = nextInt();

		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();
			for (int i = 0; i < 3; i++) {
				readln();
				char[] l = next().toCharArray();
				for (int j = 0; j < 3; j++) {
					g[i][j] = Integer.parseInt(l[j] + "");
				}
			}
			System.out.println(gI());
		}

	}

	private static int gI() {
		int[][] copy = f();
		int out = -1;
		for (int i = 0;; i++) {
//			dbg(i);
//			dbg("g",g);
//			dbg("copy ",copy);
			
			if (Arrays.deepEquals(g, copy)) {
				return out;
			} else {
				g = clone(copy);
				copy = f();
				out = i;
			}
		}
		// TODO Auto-generated method stub
	}

	private static int[][] clone(int[][] copy) {
		int [][] out=new int [copy.length][copy[0].length];
		for (int i = 0; i < out.length; i++) {
			for (int j = 0; j < out[0].length; j++) {
				out[i][j]=copy[i][j];
			}
		}
		return out;
	}

	static int[] di = new int[] { -1, 0, 1, 0 };
	static int[] dj = new int[] { 0, -1, 0, 1 };
	static int[][] out = new int[3][3];

	private static int[][] f() {
		int ii, ij, v;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				v = 0;
				for (int k = 0; k < 4; k++) {
					ii = i + di[k];
					ij = j + dj[k];
					if (valido(ii, ij)) {
//						dbg(ii,ij,v);
						v += g[ii][ij];
					}
				}
				out[i][j] = v % 2;
			}
		}
//		dbg("f",out);
		return out;
	}

	private static boolean valido(int ii, int ij) {
		return (-1 < ii) && (ii < 3) && (-1 < ij) && (ij < 3);
	}

	static int[][] g = new int[3][3];
}
