package maratonBook.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/*IMPORTANTE: Correr uno solo a la vez ya que se utiliza una matriz de cap global

 // Graph in Figure 4.22.C
 5 1 0 6
 1 2 100 
 1 3 50
 2 4 5
 2 0 5
 3 4 100
 4 0 125
 R/MAX FLOW 60
 R/ MIN CUT
 60
 2 4
 2 5
 1 3
 R/ MULTI-SOURCE MULTI-SINK MAX FLOW(sources=1,2,3)
 110
 // Graph from the problem uva10480. sabotage 
 5 0 1 8
 0 3 30
 0 2 70
 4 2 20
 3 2 5
 3 4 15
 4 1 10
 2 1 25
 1 3 50
 R/ MIN CUT
 35
 2 1
 4 1
 3 1
 //Grap Figure 4.25.B MAX INDEPEND PATHS
 7 0 6 11
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
 R/3
 */
public class MaxFlow_Dinnic_YVariaciones {

	private static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) {
		// read a graph into cap[][]
		for (int i = 0; i < MAX_V; i++) {
			deg[i] = 0;
			for (int j = 0; j < MAX_V; j++) {
				cap[i][j] = 0;
			}
		}
		Scanner sc = new Scanner(System.in);
		int E;
		V = sc.nextInt();
		s = sc.nextInt();
		t = sc.nextInt();
		E = sc.nextInt();
		for (int i = 0; i < E; i++) {
			int from, to, c;
			from = sc.nextInt();
			to = sc.nextInt();
			c = sc.nextInt();
			cap[from][to] = c;
			Adj[from][deg[from]++] = to;
			Adj[to][deg[to]++] = from;// para mantener la correctitud siempre
										// conectar asi, la direcion la da cap
		}

		// MAX FLOW

		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// POR EL FOR QUE SE UTILIZA PARA ENCONTRAR Y PUSH EL FLOW
		// System.out.printf("%d\n", dinic());

		// MAX FLOW END

		// MAX FLOW MIN CUT

		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// POR EL FOR QUE SE UTILIZA PARA ENCONTRAR Y PUSH EL FLOW
		// System.out.printf("%d\n", dinic());
		// minCut.clear();
		// Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
		// dfs(s);
		// for (Edge e : minCut) {
		// if (dfs_num[e.to] == DFS_WHITE) {// son u,v donde u e S y v e T,
		// // osea cap[u][v]=usada y v
		// // no e a S
		// System.out.printf("%d %d\n", e.from, e.to);
		// }
		// }

		// MAX FLOW MIN CUT END

		// MAX FLOW MULTI SOURCE MULTI SINK

		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// POR EL FOR QUE SE UTILIZA PARA ENCONTRAR Y PUSH EL FLOW
		// ArrayList<Integer> sources = new ArrayList<Integer>(MAX_V);
		// ArrayList<Integer> sinks = new ArrayList<Integer>(MAX_V);
		// sources.add(s);// DEPENDE DEL PROBLEMA LOS SOURCE Y LOS SINK
		// sources.add(2);
		// sources.add(3);
		// sinks.add(t);
		// // IMPORTANTE TENER EN CUENTA TENER UN MAX_V MAS GRANDE LO QUE SE
		// // NECESITA(7 como siempre) PARA PODER PORNER EL sS Y EL sT EN LOS
		// // ULTIMO
		// int sS = s = V + 1;
		// int sT = t = V + 2;
		// V = V + 7;
		// for (Integer i : sources) {
		// cap[s][i] = INF;
		// Adj[s][deg[s]++] = i;
		// Adj[i][deg[i]++] = s;
		// }
		// for (Integer i : sinks) {
		// cap[i][t] = INF;
		// Adj[t][deg[t]++] = i;
		// Adj[i][deg[i]++] = t;
		// }
		// System.out.printf("%d\n", dinic());

		// MAX FLOW MULTI SOURCE MULTI SINK END

		// MAX EDGE DISJOINT PATHS

		// Solo todas las capacidades son 1.
		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// POR EL FOR QUE SE UTILIZA PARA ENCONTRAR Y PUSH EL FLOW
		// System.out.printf("%d\n", dinic());

		// MAX EDGE DISJOINT PATHS END

		// MIN COST MAX FLOW
		// Esta en su propio archivo MaxFlow_MinCost.java
		// MIN COST MAX FLOW END
	}

	// DINIC MAX FLOW

	// the maximum number of vertices
	static int MAX_V = 1024;

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V];
	static int[] deg = new int[MAX_V];

	// BFS stuff
	static int[] q = new int[MAX_V * MAX_V + 7], prev = new int[MAX_V];// q es
																		// el
																		// queue
	static int V, s, t, flow;

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

	// DINIC MAX FLOW MIN CUT

	// DINIC COPIARLO DE ARRIBA TAL CUAL
	static int INF = 1000000000;
	static ArrayList<Edge> minCut = new ArrayList<Edge>(MAX_V);

	static class Edge {
		int from, to, cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}

	static final int DFS_WHITE = -1;
	static final int DFS_BLACK = 1;
	static int[] dfs_num = new int[MAX_V]; // this variable has to be global, we
											// cannot put it in

	/* A call of dfs(u) visits only vertices connected to u */
	static void dfs(int u) { // DFS for normal usage: as graph traversal
								// algorithm
		dfs_num[u] = DFS_BLACK; // important step: we mark this vertex as
								// visited
		for (int j = 0; j < deg[u]; j++) {
			int v = Adj[u][j]; // v is a (neighbor,
								// weight) pair
			if (dfs_num[v] == DFS_WHITE) { // important check to avoid
											// cycle
				if (cap[u][v] <= 0) {
					minCut.add(new Edge(u, v, cap[u][v]));
				} else {
					dfs(v); // recursively visits unvisited neighbors v of
					// vertex u
				}
			}
		}
	}

	// DINIC MAX FLOW MIN CUT END

}
