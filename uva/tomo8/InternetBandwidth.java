package uva.tomo8;

//Uva 820.
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

public class InternetBandwidth {
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

	// static void dbg(Object... o) {
	// output.println(Arrays.deepToString(o));
	// }

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("InternetBandwidth"));
		int idCases = 1;

		while (true) {
			readln();
			V = nextInt();
			if (V == 0) {
				break;
			}
			readln();
			s = nextInt() - 1;
			t = nextInt() - 1;
			c = nextInt();
			for (int i = 0; i < V + 7; i++) {
				deg[i] = 0;
				for (int j = 0; j < V + 7; j++) {
					cap[i][j] = 0;
				}
			}

			for (int i = 0; i < c; i++) {
				readln();
				from = nextInt() - 1;
				to = nextInt() - 1;
				w = nextInt();
				cap[from][to] += w;
				cap[to][from] += w;
				Adj[from][deg[from]++] = to;
				Adj[to][deg[to]++] = from;
			}

			dinic();
			output.printf("Network %d\n", idCases++);
			output.printf("The bandwidth is %d.\n\n", flow);
		}

		output.close();
	}

	// DINIC MAX FLOW

	// the maximum number of vertices
	static int MX_V = 100 + 7;

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MX_V][MX_V], Adj = new int[MX_V][MX_V];
	static int[] deg = new int[MX_V];

	// BFS stuff
	static int[] q = new int[MX_V * MX_V], prev = new int[MX_V];// q es el
																// queue
	static int V, s, t, flow, c, from, to, w;

	static int dinic() {
		flow = 0;

		while (true) {
			// find an augmenting path
			Arrays.fill(prev, -1);
			int qf = 0, qb = 0;// queue front y back
			prev[q[qb++] = s] = -2;
			while (qb > qf /* && prev[t] == -1 */) {// q not empty, y no hemos
														// llegado
														// a t
				int u = q[qf++];
				if (u == t) {
					continue;
				}
				for (int i = 0, v; i < deg[u]; i++) {// saco en
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
				if (cap[z][t] != 0 && prev[z] != -1) {// es lento
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
