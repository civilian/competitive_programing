package uva.tomo115;

/*Uva 11553*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import static java.lang.Math.*;

public class GridGame {
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
//		input = new BufferedReader(new FileReader("GridGame"));

		readln();

		int numCases = nextInt();// 29
		int n;
		int[][] M;
		ArrayList<Integer> res = new ArrayList<Integer>(8);
		int min;
		int tmp;
		for (int id = 0; id < numCases; id++) {
			readln();
			n = nextInt();
			M = new int[n][n];
			for (int i = 0; i < n; i++) {
				readln();
				for (int j = 0; j < n; j++) {
					M[i][j] = nextInt();
				}
			}

			res.clear();
			for (int i = 0; i < n; i++)
				res.add(i);
			min = Integer.MAX_VALUE;

			do {
				tmp = 0;
				for (int i = 0; i < n; i++)
					tmp += M[i][res.get(i)];
				min = min(min, tmp);
			} while (next_permutation(res));

			System.out.println(min);
		}
	}

	static boolean next_permutation(ArrayList<Integer> a) {
		int limit;
		for (int i = a.size() - 2, j; i >= 0; i--) {
			if (a.get(i + 1) > a.get(i)) {
				for (j = a.size() - 1; a.get(j) <= a.get(i); j--)
					;// solo ajusta los indices por eso tiene el ;
				swap(a, i, j);

				limit = (a.size() - i - 1);// el -1 para no intercambiar un elo
											// con el mismo
				for (j = 1; j <= limit / 2; j++)
					swap(a, i + j, a.size() - j);
				return true;
			}
		}
		return false;
	}

	static void swap(ArrayList<Integer> a, int i, int j) {
		int temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
	}
}
