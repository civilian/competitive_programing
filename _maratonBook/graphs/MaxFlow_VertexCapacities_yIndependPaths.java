package maratonBook.graphs;

//Uva 11506.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

/*//Grap Problema Angry Programer, EDGE CAPACITI
 4 4
 3 5
 2 2
 1 2 3
 1 3 3
 2 4 1
 3 4 3
 R/4
 //Grap Problema Angry Programer, EDGE CAPACITI
 4 4
 3 2
 2 2
 1 2 3
 1 3 3
 2 4 1
 3 4 3
 R/3
 //Grap Problema Angry Programer, EDGE CAPACITI
 4 6
 3 0
 2 0
 1 2 100000
 1 3 100000
 1 4 4
 2 4 100000
 2 3 100000
 3 4 100000
 R/4
 //Grap Figure 4.25.B MAX INDEPEND PATHS
 7 0 6 11
 0 1
 1 1
 2 1
 3 1
 4 1
 5 1
 6 1
 0 1 1
 0 2 1
 0 3 1
 1 3 1
 1 4 1
 2 3 1
 3 4 1
 3 6 1
 3 5 1
 4 6 1
 5 6 1
 */
public class MaxFlow_VertexCapacities_yIndependPaths {

	public static void main(String[] args) throws IOException {
		// Scanner sc=new Scanner(new File("AngryProgrammer"));
		Scanner sc = new Scanner(System.in);

		// MAX FLOW VERTEX CAPACITIES
		for (int i = 0; i < MAX_V; i++) {
			map("i" + i);
			map("o" + i);
		}
		// while(true)//lo de arriba solo 1 vez
		V = sc.nextInt();
		s = sc.nextInt();
		t = sc.nextInt();
		E = sc.nextInt();
		for (int i = 0; i < 2 * V + 7; i++) {
			deg[i] = 0;
			for (int j = 0; j < 2 * V + 7; j++) {
				cap[i][j] = 0;
			}
		}

		for (int i = 0; i < V; i++) {
			from = sc.nextInt();
			fromI = map("i" + from);
			fromO = map("o" + from);
			c = sc.nextInt();
			cap[fromI][fromO] = c;
			cap[fromO][fromI] = c;

			Adj[fromI][deg[fromI]++] = fromO;
			Adj[fromO][deg[fromO]++] = fromI;
		}

		for (int i = 0; i < E; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			c = sc.nextInt();

			fromO = map("o" + from);
			toI = map("i" + to);

			fromI = map("i" + from);
			toO = map("o" + to);

			cap[fromO][toI] = c;
			cap[toO][fromI] = c;// esto es por edges bidireccionales

			Adj[fromO][deg[fromO]++] = toI;
			Adj[toI][deg[toI]++] = fromO;

			Adj[toO][deg[toO]++] = fromI;
			Adj[fromI][deg[fromI]++] = toO;
		}
		s = map("o" + s);
		t = map("i" + t);
		V = 2 * V + 7;
		dinic();
		System.out.println(flow);

		// MAX FLOW VERTEX CAPACITIES END

		// MAX FLOW VERTEX MAXIMUN INDEPENDENT PATHS
		// Aunque se podria recibir un grafo normal sin peso la idea es que
		// pongan peso 1 a las aristas y a los edges y correr max flow

		// MAX FLOW VERTEX MAXIMUN INDEPENDENT PATHS END
	}

	private static int map(String string) {
		Integer idx = map.get(string);
		if (idx == null) {
			idx = map.size();
			map.put(string, idx);
		}
		return idx;
	}

	// DINIC MAX FLOW

	// the maximum number of vertices
	static int MAX_V = (2 * 50) + 7;

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V];
	static int[] deg = new int[MAX_V];

	// BFS stuff
	static int[] q = new int[MAX_V * MAX_V + 7], prev = new int[MAX_V];// q es
																		// el
																		// queue
	static int V, s, t, flow, E, fromI, fromO, toI, toO, c, from, to;
	static HashMap<String, Integer> map = new HashMap<String, Integer>(MAX_V);

	static int dinic() {
		flow = 0;

		while (true) {
			// find an augmenting path
			Arrays.fill(prev, -1);
			int qf = 0, qb = 0;// queue front y back
			prev[q[qb++] = s] = -2;
			while (qb > qf) {// q not empty, y no hemos llegado
								// a t
				int u = q[qf++];
				if (u == t) {
					continue;
				}
				for (int i = 0, v; i < deg[u]; i++) {
					if (prev[v = Adj[u][i]] == -1 && cap[u][v] != 0) { // v=act,
						prev[q[qb++] = v] = u;
					}
				}
			}

			// see if we're done
			if (prev[t] == -1)
				break;

			// try finding more paths
			for (int z = 0; z < V; z++) {
				if (cap[z][t] != 0 && prev[z] != -1) {
					int bot = cap[z][t];
					for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {// busco
						bot = Math.min(cap[u][v], bot);// el maximo que se
														// puede mandar=f
					}

					if (bot == 0) {
						continue;
					}

					cap[z][t] -= bot;
					cap[t][z] += bot;
					for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {
						cap[u][v] -= bot;// y lo mando.
						cap[v][u] += bot;
					}
					flow += bot;
				}
			}
		}
		return flow;
	}

	// DINIC MAX FLOW END

}
