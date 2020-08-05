package uva.tomo111;

//Uva 11138.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class NutsnBolts {
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
		input = new BufferedReader(new FileReader("NutsnBolts"));

		int cases, caseno;
		// for (int i = 0; i < MAX_BOLTS; i++) {
		// map("b" + i);
		// }
		// for (int i = 0; i < MAX_NUTS; i++) {
		// map("n" + i);
		// }
		readln();
		cases = nextInt();
		for (caseno = 1; caseno <= cases; caseno++) {
			map.clear();
			s = map("s");
			t = map("t");
			for (int i = 0; i < MAX_V; i++) {
				deg[i] = 0;
				for (int j = 0; j < MAX_V; j++) {
					cap[i][j] = 0;
				}
			}

			int n, b;
			readln();
			bolts = nextInt();
			nuts = nextInt();
			int bol, nut;

			for (int i = 0; i < bolts; i++) {// tornillos con s
				bol = map("b" + i);
				cap[s][bol] = 1;
				Adj[bol][deg[bol]++] = s;
				Adj[s][deg[s]++] = bol;
			}
			for (int i = 0; i < nuts; i++) {// tuercas con t
				nut = map("n" + i);
				cap[nut][t] = 1;
				Adj[nut][deg[nut]++] = t;
				Adj[t][deg[t]++] = nut;
			}

			for (b = 0; b < bolts; b++) {
				readln();
				for (n = 0; n < nuts; n++) {
					if (nextInt() == 1) {
						bol = map("b" + b);
						nut = map("n" + n);
						cap[bol][nut] = 1;
						Adj[bol][deg[bol]++] = nut;
						Adj[nut][deg[nut]++] = bol;
					}
				}
			}
			V = bolts + nuts + 2 + 7;
			// V = MAX_V;
			dinic();
			output.printf("Case %d: ", caseno);
			output.printf("a maximum of %d nuts and bolts ", flow);
			output.printf("can be fitted together\n");
		}
		output.close();
	}

	/* global copy of the input data */
	static int nuts, bolts;

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
	static int MAX_V = 1010 + 7, MAX_BOLTS = 500 + 7, MAX_NUTS = 500 + 7;

	static HashMap<String, Integer> map = new HashMap<String, Integer>(MAX_V);

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V];
	static int[] deg = new int[MAX_V];

	// BFS stuff
	static int[] q = new int[MAX_V * MAX_V], prev = new int[MAX_V];// q es
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
					if (prev[v = Adj[u][i]] == -1 && cap[u][v] > 0) { // v=act,
						prev[q[qb++] = v] = u;
					}
				}
			}

			// see if we're done
			if (prev[t] == -1)
				break;

			// try finding more paths
			for (int z = 0; z < V; z++) {
				if (cap[z][t] > 0 && prev[z] != -1) {
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
