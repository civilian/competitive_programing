package uva.tomo104;

//Uva 10480.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Sabotage {
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
		input = new BufferedReader(new FileReader("Sabotage"));
		t = 2;
		s = 1;
		while (true) {
			readln();
			V = nextInt();
			m = nextInt();
			if (m == 0 && V == 0) {
				break;
			}
			for (int i = 0; i < V + 7; i++) {
				deg[i] = 0;
				for (int j = 0; j < V + 7; j++) {
					cap[i][j] = 0;
				}
			}
			for (int i = 0; i < m; i++) {
				readln();
				from = nextInt();
				to = nextInt();
				c = nextInt();
				cap[from][to] = c;
				cap[to][from] = c;
				Adj[from][deg[from]++] = to;
				Adj[to][deg[to]++] = from;
			}

			dinicMinCut();
			// dbg(flow);
			minCut.clear();
			Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
			dfs(s);
			// dbg(minCut);
			for (Edge e : minCut) {
				if (dfs_num[e.to] == DFS_WHITE) {// son u,v donde u e S y v e T,
													// osea cap[u][v]=usada y v
													// no e a S
					output.printf("%d %d\n", e.from, e.to);
				}
			}
			output.println();
		}
		output.close();
	}

	// DINIC MAX FLOW MIN CUT

	// the maximum number of vertices
	static int MAX_V = 50 + 7;

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V];
	static int[] deg = new int[MAX_V];
	static int[] dist = new int[MAX_V];

	// BFS stuff
	static int[] q = new int[MAX_V * MAX_V], prev = new int[MAX_V];// q es queue
	static int V, s, t, m, from, to, c, distMax;
	static long flow;

	static class Edge {
		int from, to, cost;

		void set(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}

	static ArrayList<Edge> minCut = new ArrayList<Edge>(MAX_V);

	static long dinicMinCut() {
		flow = 0;

		while (true) {
			// find an augmenting path
			Arrays.fill(prev, -1);
			Arrays.fill(dist, 1000000000);
			int qf = 0, qb = 0;// queue front y back
			prev[q[qb++] = s] = -2;
			dist[s] = 0;
			distMax = 1000000000;
			while (qb > qf) {// q not empty, y no hemos llegado a t
				int u = q[qf++];
				if (u == t) {
					continue;
				}
				for (int i = 0, v; i < deg[u]; i++) {
					v = Adj[u][i];
					if (prev[v] == -1 && cap[u][v] != 0) { // v=act,
						prev[q[qb++] = v] = u;
					}
				}
			}

			// dbg("prev t", prev[t]);
			// printPath(2);
			// System.out.println();
			// printCap(2);
			// see if we're done
			if (prev[t] == -1)
				break;

			// try finding more paths
			for (int z = 0; z <= V; z++) {
				// dbg("cap", cap[z][t]);
				// dbg("prev", prev[z]);
				// dbg(z);
				if (cap[z][t] != 0 && prev[z] != -1) {
					int bot = cap[z][t];
					for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {// busco
						// bot=Math.min(bot, cap[u][v]);
						if (bot > cap[u][v]) {// el maximo que se puede mandar=f
							bot = cap[u][v];
						}
					}

					// dbg("bot", bot);
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
