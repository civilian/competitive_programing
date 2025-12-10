package uva.tomo104;

//Uva 10496
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class CollectingBeepers { /* Collecting Beepers */
	// DP TSP
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
//		input = new BufferedReader(new FileReader("TravelingSalesmanProblem"));

		readln();
		TC = nextInt();
		while (TC-- > 0) {
			readln();
			xsize = nextInt();
			ysize = nextInt(); // these two values are not used
			readln();
			x[0] = nextInt();
			y[0] = nextInt();
			readln();
			n = nextInt();
			for (i = 1; i <= n; i++) { // karel's position is at index 0
				readln();
				x[i] = nextInt();
				y[i] = nextInt();
			}

			// El tsp es el clasico aunque como solo se dan los x y los y's y no
			// se puede mover diagonal por eso se calculan las distancias
			// manhatan, pero eso no tiene nada que ver
			for (i = 0; i <= n; i++)
				// build distance table
				for (j = 0; j <= n; j++)
					dist[i][j] = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]); // Manhattan
																				// distance

			for (i = 0; i < 11; i++)
				for (j = 0; j < (1 << 11); j++)
					memo[i][j] = -1;

			System.out.printf("The shortest path has length %d\n", tsp(0, 1)); // DP-TSP
		}
	}

	private static int i, j, TC, xsize, ysize, n;
	private static int[] x = new int[11], y = new int[11];
	private static int[][] dist = new int[11][11], memo = new int[11][1 << 11]; // Karel
																				// +
																				// max
																				// 10
																				// beepers

	private static int tsp(int pos, int bitmask) { // bitmask stores the visited
													// coordinates
		if (bitmask == (1 << (n + 1)) - 1)// bitmask== (2^(N+1))-1 el n+1 es
											// porque tiene en cuenta que tiene
											// que volver a el inicio
			return dist[pos][0]; // return trip to close the loop
		if (memo[pos][bitmask] != -1)
			return memo[pos][bitmask];

		int ans = 2000000000;// 2*10^9
		for (int nxt = 0; nxt <= n; nxt++)
			// O(n) here
			if (nxt != pos && (bitmask & (1 << nxt)) == 0) // if coordinate nxt
															// is not visited
															// yet
				ans = Math.min(ans,
						dist[pos][nxt] + tsp(nxt, bitmask | (1 << nxt)));
		return memo[pos][bitmask] = ans;
	}
}
