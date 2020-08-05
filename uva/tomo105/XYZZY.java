package uva.tomo105;

//Uva 10557.
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

public class XYZZY {
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

	private static int nextIntS() throws IOException {
		int resultado;
		try {
			resultado = nextInt();
		} catch (Exception e) {
			if (readln() == null)
				System.exit(0);

			resultado = nextIntS();
		}
		return resultado;
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("XYZZY"));

		AdjList = new ArrayList[100 + 7];
		AdjListDFS = new ArrayList[100 + 7];
		for (int i = 0; i < 100 + 7; i++) {
			AdjList[i] = new ArrayList<Edge>();
			AdjListDFS[i] = new ArrayList<Edge>();
		}
		while (true) {
			V = nextIntS();
			if (V == -1) {
				break;
			}
			// dbg(V);
			for (int i = 0; i < V + 7; i++) {
				AdjList[i].clear();
				AdjListDFS[i].clear();
			}
			for (int i = 0; i < V; i++) {
				w = nextIntS();
				vecinos = nextIntS();
				for (int j = 0; j < vecinos; j++) {
					v = nextIntS() - 1;
					AdjList[i].add(new Edge(v, w));
					AdjListDFS[v].add(new Edge(i, w));
				}
			}

			s = 0;

			if (V > 0) {
				dist = new int[V];
				Arrays.fill(dist, -INF);
				dist[s] = 100;
				times = 1;// V-1
				secondT = false;
				bellmanFord();
			} else {
				output.println("hopeless");
				continue;
			}

			for (int i = 0; i < V; i++) {
				if (dist[i] < 0) {
					AdjList[i].clear();//shit out of luk.
				}
			}
			times = V - 2;
			secondT = true;
			positiveCycle.clear();
			bellmanFord();

			if (dist[V - 1] > 0) {
				// dbg(dist[V - 1]);
				output.println("winnable");
				continue;
			} else if (hasPositiveCycle) {
				can = false;
				initDFS(V);
				dfs(V - 1);
				for (int i = 0; i < positiveCycle.size(); i++) {
					if (dfs_num[positiveCycle.get(i)] == DFS_BLACK) {
						can = true;
						break;
					}
				}
				if (can) {
					output.println("winnable");
				} else {
					output.println("hopeless");
				}
				continue;
			} else { // si no llego normal y no hay un ciclo negativo
				output.println("hopeless");
				continue;
			}
		}
		output.close();
	}

	// BELLMAN FORD

	static int V, v, s, w, vecinos, times;
	static ArrayList<Edge>[] AdjList;
	static int INF = 1000000000;
	static int dist[];
	static int p[];
	static boolean hasPositiveCycle = false, can, toD[], secondT;
	static ArrayList<Integer> positiveCycle = new ArrayList<Integer>(100 + 7);

	private static void bellmanFord() {
		for (int i = 0; i < times; i++)
			// relax all E edges V-1 times, overall O(VE)
			for (int u = 0; u < V; u++)
				// these two loops = O(E)
				for (int j = 0; j < (int) AdjList[u].size(); j++) {
					Edge v = AdjList[u].get(j); // we can record SP spanning
												// here if needed
					dist[v.to] = Math.max(dist[v.to], dist[u] + v.cost); // relax
				}

		hasPositiveCycle = false;

		if (secondT) {
			for (int u = 0; u < V; u++)
				// one more pass to check
				for (int j = 0; j < (int) AdjList[u].size(); j++) {
					Edge v = AdjList[u].get(j);
					if (dist[v.to] < dist[u] + v.cost) { // should be false
						hasPositiveCycle = true; // but if true, then negative
													// cycle
						positiveCycle.add(v.to);
						// exists!
						// (but this shortest paths problem cannot be solved)
					}
				}
		}
		// System.out.printf("Negative Cycle Exist? %s\n",
		// hasNegativeCycle ? "Yes" : "No");// SOLO PARA IMPRIMRIR

		// if (!hasNegativeCycle)// SOLO PARA IMPRIMRI
		// for (int i = 0; i < V; i++) {
		// System.out.printf("SSSP(%d, %d) = %d\n", s, i, dist[i]);
		// }

	}

	// BELLMAN FORD

	static class Edge {
		int to, cost;

		public Edge(int toIn, int valueIn) {
			to = toIn;
			cost = valueIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", cost=" + cost + "]";
		}
	}

	// DFS STANDAR
	// static int V, total_neighbors, id, weight;
	static final int DFS_WHITE = -1;
	static final int DFS_BLACK = 1;
	static ArrayList<Edge>[] AdjListDFS;
	static int[] dfs_num; // this variable has to be global, we cannot put it in

	// // recursion

	private static void initDFS(int V) { // used in normal DFS
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
	}

	/* A call of dfs(u) visits only vertices connected to u */
	static void dfs(int u) { // DFS for normal usage: as graph traversal
								// algorithm
		dfs_num[u] = DFS_BLACK; // important step: we mark this vertex as
								// visited
		for (int j = 0; j < AdjListDFS[u].size(); j++) {
			Edge v = AdjListDFS[u].get(j); // v is a (neighbor,
											// weight) pair
			if (dfs_num[v.to] == DFS_WHITE) // important check to avoid
											// cycle
				dfs(v.to); // recursively visits unvisited neighbors v of
							// vertex u
		}
	}

	// END DFS STANDAR

}
