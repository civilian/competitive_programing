package graphs;

/*Ya que dikstra no sirve para costos negativos se utiliza bellman ford que 
 "relaja" los caminos una cantidad de veces, y cuando existe un ciclo negativo
 solo se mira si relajando otra vez los caminos se obtiene un camino mas corto
 esto da un ciclo.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 //Graph in Figure 4.16
 5 7
 1 0 2
 1 2 7
 1 4 6
 0 2 3
 0 3 6
 2 3 5
 4 3 1
 R/DIJKSTRA
 SSSP(0, 0) = 0
 SSSP(0, 1) = 1000000000
 SSSP(0, 2) = 3
 SSSP(0, 3) = 6
 SSSP(0, 4) = 1000000000
 BELLMAN FORD
 Negative Cycle Exist? No
 y los mismos sssp que dijkstra

 // Graph in Figure 4.17, no negative cycle
 5 5
 0 1 1
 0 2 10
 1 3 2
 2 3 -10
 3 4 3
 R/DIJKSTRA
 SSSP(0, 0) = 0
 SSSP(0, 1) = 1
 SSSP(0, 2) = 10
 SSSP(0, 3) = 0
 SSSP(0, 4) = 6
 BELLMAN FORD
 Negative Cycle Exist? No
 y los mismos sssp que dijkstra

 // Graph in Figure 4.18, negative cycle exists
 3 3
 0 1 1000
 1 2 15
 2 1 -42
 */

public class BellmanFord {

	private static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		// Scanner sc = new Scanner(new File("grafo.in"));

		V = sc.nextInt();
		E = sc.nextInt();
		AdjList = new ArrayList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new ArrayList<Edge>();
		for (int i = 0; i < E; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			w = sc.nextInt();
			AdjList[from].add(new Edge(to, w));
		}

		// SSSP ON UNWEIGHTED GRAPH
		// Esta en BFS, con bfs simple y el printpath
		// SSSP ON UNWEIGHTED GRAPH END

		// BELLMAN FORD
		System.out.println("BELLMAN FORD");
		bellmanFord(0, V - 1);
		// BELLMAN FORD END

	}

	// BELLMAN FORD

	 static int V, E, from, to, s, w;
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
		System.out.printf("Negative Cycle Exist? %s\n",
				hasNegativeCycle ? "Yes" : "No");// SOLO PARA IMPRIMRIR

		if (!hasNegativeCycle)// SOLO PARA IMPRIMRI
			for (int i = 0; i < V; i++) {
				System.out.printf("SSSP(%d, %d) = %d\n", s, i, dist[i]);
			}

	}

	// BELLMAN FORD END

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

	// END
}
