package uva.tomo5;

//Uva 558.
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

public class Wormholes {
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
		// input = new BufferedReader(new FileReader("Wormholes"));
		readln();
		int testCases = nextInt();

		// AdjList = new ArrayList[1000 + 7];
		// for (int i = 0; i < AdjList.length; i++) {
		// AdjList[i] = new ArrayList<Edge>();
		// }
		for (int idCases = 0; idCases < testCases; idCases++) {
			readln();
			V = nextInt();
			E = nextInt();
			AdjList = new ArrayList[V];
			for (int i = 0; i < AdjList.length; i++)
				AdjList[i] = new ArrayList<Edge>();
			for (int i = 0; i < E; i++) {
				readln();
				u = nextInt();
				v = nextInt();
				w = nextInt();

				AdjList[v].add(new Edge(u, w));
			}
			// dbg(AdjList);
			bellmanFord(0, 1);
		}
		output.close();
	}

	// BELLMAN FORD

	static int V, E, u, v, s, w;
	static ArrayList<Edge>[] AdjList;
	static int INF = 1000000000;
	static int dist[];
	static int p[];

	private static void bellmanFord(int inicio, int fin) {
		s = inicio;
		dist = new int[V];
		Arrays.fill(dist, INF);
		dist[s] = 0;

		for (int i = 0; i < V - 1; i++) {
			// relax all E edges V-1 times, overall O(VE)
			for (int u = 0; u < V; u++) {
				// these two loops = O(E)
				for (int j = 0; j < (int) AdjList[u].size(); j++) {
					Edge v = AdjList[u].get(j); // we can record SP spanning
												// here if needed
					dist[v.to] = Math.min(dist[v.to], dist[u] + v.cost); // relax
				}
			}
		}

		boolean hasNegativeCycle = false;
		for (int u = 0; u < V; u++) {
			// one more pass to check
			for (int j = 0; j < (int) AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j);
				if (dist[v.to] > dist[u] + v.cost) { // should be false
					hasNegativeCycle = true; // but if true, then negative cycle
												// exists!
					// (but this shortest paths problem cannot be solved)
				}
			}
		}
		if (hasNegativeCycle) {
			output.println("possible");
		} else {
			output.println("not possible");
		}

	}

	// BELLMAN FORD, DISKSTRA, SHORTEST FASTER ALGORITM.
	static class Edge {
		int to, cost;

		public Edge(int toIn, int valueIn) {
			to = toIn;
			cost = valueIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", _second=" + cost + "]";
		}
	}
}
