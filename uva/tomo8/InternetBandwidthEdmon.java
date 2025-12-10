package uva.tomo8;

//Uva 820.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class InternetBandwidthEdmon {
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

		for (int i = 0; i < MAX_V; i++) {
			AdjList[i]=new ArrayList<Edge>();
		}
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
				// deg[i] = 0;
				AdjList[i].clear();
				for (int j = 0; j < V + 7; j++) {
					res[i][j] = 0;
				}
			}

			for (int i = 0; i < c; i++) {
				readln();
				from = nextInt() - 1;
				to = nextInt() - 1;
				w = nextInt();
				// cap[from][to] += w;
				// cap[to][from] += w;
				res[from][to] += w;
				res[to][from] += w;
				AdjList[from].add(new Edge(to, w));
				AdjList[to].add(new Edge(from, w));
				// Adj[from][deg[from]++] = to;
				// Adj[to][deg[to]++] = from;
			}

			// dinic();
			flow=edmonKarpF();
			output.printf("Network %d\n", idCases++);
			output.printf("The bandwidth is %d.\n\n", flow);
		}

		output.close();
	}

	// DINIC MAX FLOW

	// the maximum number of vertices
	static int MX_V = 100 + 7;

	// // adjacency matrix (fill this up)
	// // If you fill adj[][] yourself, make sure to include both u->v and v->u.
	// static int[][] cap = new int[MX_V][MX_V], Adj = new int[MX_V][MX_V];

	// static int[] deg = new int[MX_V];
	//
	// // BFS stuff
	// static int[] q = new int[MX_V * MX_V], prev = new int[MX_V];// q es el
	// // queue
	static int V, s, t, flow, c, from, to, w;

	static int k, vertex, weight;
	static int MAX_V = 100 + 7; // enough for sample graph in Figure
	// 4.22/4.23
	static int INF = 2000000000;

	// we need these global variables
	private static int[][] res = new int[MAX_V][MAX_V]; // define MAX_V
														// appropriately
	private static int mf, f;
	private static int[] p = new int[MAX_V];
	static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];// define MAX_V
	// appropriately
	static BitSet dist = new BitSet(MAX_V);

	static int edmonKarpF() {
		mf = 0;
		while (true) { // run O(VE^2) Edmonds Karp to solve the Max Flow problem
			f = 0;

			// run BFS, please examine parts of the BFS code that is different
			// than in Section 4.3
			Queue<Integer> q = new LinkedList<Integer>();
			dist.clear();
			q.offer(s);
			dist.set(s, true);
			Arrays.fill(p, -1);// (we have to record the BFS
								// spanning tree)
			while (!q.isEmpty()) { // (we need the shortest path from s to t!)
				int u = q.poll();
				if (u == t)
					break; // immediately stop BFS if we already reach sink t
				for (Edge e : AdjList[u])
					// note: enumerating neighbors with AdjMatrix is `slow'
					// res[u][v] > 0 &&
					if (res[u][e.to] > 0 && !dist.get(e.to)) { // res[u][v] can
																// change!
						dist.set(e.to);
						q.offer(e.to);
						p[e.to] = u; // parent of vertex v is vertex u
					}
			}

			augment(t, INF); // find the min edge weight `f' along this path, if
								// any
			if (f == 0)
				break; // if we cannot send any more flow (`f' = 0), terminate
						// the loop
			mf += f; // we can still send a flow, increase the max flow!
		}

		// System.out.printf("%d\n", mf); // this is the max flow value of this
		// flow graph
		return mf;

	}

	private static void augment(int v, int minEdge) { // traverse the BFS
		// spanning tree as in
		// print_path (section
		// 4.3)
		if (v == s) {
			f = minEdge;
			return;
		} // reach the source, record minEdge in a global variable `f'
		else if (p[v] != -1) {
			augment(p[v], Math.min(minEdge, res[p[v]][v])); // recursive
			// call
			res[p[v]][v] -= f;
			res[v][p[v]] += f;
		} // alter residual capacities
	}

	// CLASES: EDMON KARP
	static class Edge {
		Integer to, value;

		public Edge(Integer toIn, Integer valueIn) {
			to = toIn;
			value = valueIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", _second=" + value + "]";
		}
	}

	// CLASES: EDMON KARP END
	//
	// static int dinic() {
	// flow = 0;
	//
	// while (true) {
	// // find an augmenting path
	// Arrays.fill(prev, -1);
	// int qf = 0, qb = 0;// queue front y back
	// prev[q[qb++] = s] = -2;
	// while (qb > qf && prev[t] == -1) {// q not empty, y no hemos llegado
	// // a t
	// for (int u = q[qf++], i = 0, v; i < deg[u]; i++) {// saco en
	// if (prev[v = Adj[u][i]] == -1 && cap[u][v] != 0) { // v=act,
	// prev[q[qb++] = v] = u;
	// }
	// }
	// }
	//
	// // see if we're done
	// if (prev[t] == -1)
	// break;
	//
	// // try finding more paths
	// for (int z = 0; z < V; z++) {
	// if (cap[z][t] != 0 && prev[z] != -1) {
	// int bot = cap[z][t];
	// for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {// busco
	// bot = Math.min(cap[u][v], bot);// el maximo que se
	// // puede mandar=f
	// }
	//
	// if (bot == 0) {
	// continue;
	// }
	//
	// cap[z][t] -= bot;
	// cap[t][z] += bot;
	// for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {
	// cap[u][v] -= bot;// y lo mando.
	// cap[v][u] += bot;
	// }
	// flow += bot;
	// }
	// }
	// }
	// return flow;
	// }

	// DINIC MAX FLOW END

}
