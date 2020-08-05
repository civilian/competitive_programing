package uva.tomo105;

//Uva 10594.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DataFlow {
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

	// (:25
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("DataFlow"));

		while (readln() != null) {
			V = nextInt();
			E = nextInt();
			for (int i = 0; i < V + 7; i++) {
				deg[i] = 0;
				for (int j = 0; j < V + 7; j++) {
					cap[i][j] = 0;
					// cost[i][j]=0;
				}
			}
			for (int i = 0; i < E; i++) {
				readln();
				from = nextInt() - 1;
				to = nextInt() - 1;
				w = nextInt();
				cost[from][to] = w;
				cap[from][to] = 1;

				cost[to][from] = -w;
				cap[to][from] = 1;

				Adj[from][deg[from]++] = to;
				Adj[to][deg[to]++] = from;
			}
			readln();
			D = nextInt();
			K = nextInt();
			s = 0;
			t = V - 1;
			time = 0;
			edmonKarp();
			// dbg(mf);
			// output.println();
			// output.println();
			if (mf == D) {
				output.println(time);
			} else {
				output.println("Impossible.");
			}
		}
		output.close();
	}

	static int MAX_V = 100 + 7; // define MAX_V appropriately
	static int s, t, V, E, from, to, w;
	static int INF = 1000000000;
	static long INFl = 1000000000000000000l, mf, flow, time, D, K;

	// we need these global variables
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V],
			cost = new int[MAX_V][MAX_V];

	static int[] p = new int[MAX_V], deg = new int[MAX_V],
			dist = new int[MAX_V], cont = new int[MAX_V];

	static PriorityQueue<State> pq = new PriorityQueue<State>(MAX_V);
	static BitSet visited = new BitSet(MAX_V);

	static long edmonKarp() {
		mf = 0;
		while (true) { // run O(VE^2) Edmonds Karp to solve the Max Flow problem
			flow = 0;

			pq.clear();
			// pq.add(new State(s, 0));

			// Arrays.fill(dist, 0, V + 7, INF);
			Arrays.fill(p, 0, V + 7, -1);
			// visited.clear();
			//
			// dist[s] = 0;
			// p[s] = -1;
			//
			// while (!pq.isEmpty()) {
			// State cur = pq.poll();
			// if (cur.node == t)
			// break;// cuando solo quiero saber
			// // el minimo costo de llegar aqui sin
			// // los demas, PERO SOLO SIRVE PARA COSTOS POSITIVOS
			// if (visited.get(cur.node)) {
			// continue;
			// }
			// visited.set(cur.node);
			//
			// if (dist[cur.node] == cur.cost) {
			// for (int i = 0, e; i < deg[cur.node]; i++) {
			// e = Adj[cur.node][i];
			// if (cap[cur.node][e] != 0
			// && dist[cur.node] + cost[cur.node][e] < dist[e]) {
			// dist[e] = dist[cur.node] + cost[cur.node][e];
			// p[e] = cur.node;
			// pq.add(new State(e, cur.cost + cost[cur.node][e]));
			// }
			// }
			// }
			// }

			dist = new int[V];
			Arrays.fill(dist, INF);
			dist[s] = 0;
			for (int i = 0; i < V - 1; i++) {
				// relax all E edges V-1 times, overall O(VE)
				for (int u = 0; u < V; u++) {
					// these two loops = O(E)
					for (int j = 0; j < deg[u]; j++) {
						int v = Adj[u][j]; // we can record SP spanning
											// here if needed // relax)
						if (dist[v] > dist[u] + cost[u][v]) {
							p[v] = u;
							dist[v] = dist[u] + cost[u][v]; // relax
						}

					}
				}
			}

			costCamino = 0;
			dbg(p);
//			printPath(t);
//			augment(t, INF); // find the min edge weight `f' along this path, if
								// any
			if (flow == 0)
				break; // if we cannot send any more flow (`f' = 0), terminate
						// the loop

			// dbg("flow");
			// dbg(flow);
			// dbg("dist");
			// dbg(dist[t]);
			// printPath(t);
			// output.println();
			if (mf + (flow * K) >= D) {
				res = D - mf;
				mf += res;
				// time += (res * costCamino);
				time += (res * dist[t]);
				break;
			} else {
				time += (flow * K) * dist[t];
				// time += (flow * K) * costCamino;
				mf += flow * K;
			}
		}

		return mf;

	}

	static int costCamino;

	static void printPath(int u) {
		if (u == s) {
			output.printf("%d", u);
			return;
		}
		printPath(p[u]);// recursive call: to make the output format: s -> ...
						// -> t
		output.printf(" %d", u);
	}

	static long res;

	private static void augment(int v, int minEdge) { // traverse the BFS
		// spanning tree as in
		// print_path (section
		// 4.3)
		if (v == s) {
			flow = minEdge;
			return;
		} // reach the source, record minEdge in a global variable `f'
		else if (p[v] != -1) {
			augment(p[v], Math.min(minEdge, cap[p[v]][v])); // recursive
			// call
			// costCamino += cost[p[v]][v];
			cap[p[v]][v] -= flow;
			cap[v][p[v]] += flow;
		} // alter residual capacities
	}

	// CLASES: EDMON KARP
	static class Edge {
		Integer to;

		public Edge(Integer to) {
			this.to = to;
		}

	}

	static class State implements Comparable<State> {
		int node;
		int cost;

		public State(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		public int compareTo(State o) {
			return cost - o.cost;
		}

		@Override
		public String toString() {
			return "State [node=" + node + ", cost=" + cost + "]";
		}

	}

	// CLASES: EDMON KARP END
}
