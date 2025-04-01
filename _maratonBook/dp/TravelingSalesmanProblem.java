package maratonBook.dp;

//Uva 10496
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class TravelingSalesmanProblem { /* Collecting Beepers */
	// DP TSP

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new File("TravelingSalesmanProblem"));

		TC = sc.nextInt();//no se utiliza solo para mantener el mismo archivo de pruebas

		xsize = sc.nextInt();
		ysize = sc.nextInt(); // these two values are not used
		x[0] = sc.nextInt();
		y[0] = sc.nextInt();
		n = sc.nextInt();
		for (i = 1; i <= n; i++) { // karel's position is at index 0
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
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
