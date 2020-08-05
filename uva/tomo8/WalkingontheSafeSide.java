package uva.tomo8;

//Uva 825.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class WalkingontheSafeSide {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WalkingontheSafeSide"));

		readln();
		int tc = nextInt();
		for (int idCases = 0; idCases < tc; idCases++) {
			readln();
			readln();
			F = nextInt();
			C = nextInt();
			paths = new int[F][C];
			for (int i = 0; i < F; i++) {
				readln();
				fil = nextInt() - 1;
				while (_stk.hasMoreElements()) {
					col = nextInt() - 1;
					paths[fil][col] = -1;
				}
			}
			// paths[0][0] = 1;
			fin = 0;
			countingPahtsOnDag2D(0, 0);
			// dbg(paths);
			if (idCases != 0) {
				output.println();
			}
			output.println(fin);
			// output.println(paths[F - 1][C - 1]);
		}
		output.close();
	}

	static int nf, nc;
	static int fin;

	private static void countingPahtsOnDag2D(int f, int c) {
		// dbg(f,c);
		for (int i = 0; i < df.length; i++) {
			nf = f + df[i];
			nc = c + dc[i];
			if (isValid(nf, nc) && paths[nf][nc] != -1) {
				// paths[nf][nc] += paths[f][c];
				if (nf == F - 1 && nc == C - 1) {
					fin++;
				}
				countingPahtsOnDag2D(nf, nc);
			}
		}

	}

	static int[][] paths;
	static int[] df = { 0, 1 }, dc = { 1, 0 };
	static int F, C, fil, col;

	static boolean isValid(int r, int c) {
		return (r > -1 && r < F && c > -1 && c < C);// esta dentro
	}
}
