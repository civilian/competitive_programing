package uva.tomo115;

//Uva 11506.
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

public class AngryProgrammer {
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
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("AngryProgrammer"));

		for (int i = 0; i < MAX_V - 50; i++) {
			map("i" + i);
			map("o" + i);
		}

		while (true) {
			readln();
			V = nextInt();
			w = nextInt();
			if (V == 0 && w == 0) {
				break;
			}
			for (int i = 0; i < 2 * V + 7; i++) {
				deg[i] = 0;
				for (int j = 0; j < 2 * V + 7; j++) {
					cap[i][j] = 0;
				}
			}

			for (int i = 0; i < V - 2; i++) {
				readln();
				from = nextInt();
				fromI = map("i" + from);
				fromO = map("o" + from);
				c = nextInt();
				cap[fromI][fromO] = c;
				cap[fromO][fromI] = c;

				Adj[fromI][deg[fromI]++] = fromO;
				Adj[fromO][deg[fromO]++] = fromI;
			}

			for (int i = 0; i < w; i++) {
				readln();
				from = nextInt();
				to = nextInt();
				c = nextInt();

				fromO = map("o" + from);
				toI = map("i" + to);

				fromI = map("i" + from);
				toO = map("o" + to);

				cap[fromO][toI] = c;
				cap[toO][fromI] = c;

				Adj[fromO][deg[fromO]++] = toI;
				Adj[toI][deg[toI]++] = fromO;

				Adj[toO][deg[toO]++] = fromI;
				Adj[fromI][deg[fromI]++] = toO;
			}
			s = map("o" + 1);
			t = map("i" + V);
			// dbg("i" + V);
			// dbg(t);
			// dbg(deg);
			V = 2 * V + 7;
			dinic();
			output.println(flow);
		}
		output.close();
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
	static int V, s, t, flow, w, fromI, fromO, toI, toO, c, from, to;
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
