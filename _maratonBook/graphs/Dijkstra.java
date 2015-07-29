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

 // Graph in Figure 4.18, negative cycle exists
 3 3
 0 1 1000
 1 2 15
 2 1 -42
 */

public class Dijkstra {

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

		// DIJKSTRA
		System.out.println("DIJKSTRA");
		dikstra(0, 3);
		// DIJKSTRA END

	}

	// DIJKSTRA HEAP

	static int V, E, from, to, s, w;
	static ArrayList<Edge>[] AdjList;
	static int INF = 1000000000;
	static int dist[];
	static int p[];

	/* Se demora mucho(TLE seguro) para ciclos negativos */
	static int dikstra(int inicio, int fin) {
		s = inicio;
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(s, 0));

		dist = new int[V];
		Arrays.fill(dist, INF);

		p = new int[V];
		dist[s] = 0;
		p[s] = -1;

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			// if(cur.node==fin) return cur.cost;//cuando solo quiero saber
			// el minimo costo de llegar aqui sin
			// los demas, PERO SOLO SIRVE PARA COSTOS POSITIVOS

			if (dist[cur.node] == cur.cost) {
				for (Edge e : AdjList[cur.node]) {
					if (dist[cur.node] + e.cost < dist[e.to]) {
						dist[e.to] = dist[cur.node] + e.cost;
						p[e.to] = cur.node;
						pq.add(new State(e.to, cur.cost + e.cost));
					}
				}
			}
		}

		for (int i = 0; i < V; i++) {// SOLO PARA IMPRIMRIR
			System.out.printf("SSSP(%d, %d) = %d\n", s, i, dist[i]);
		}
		return -1;
	}

	static void ruta(int fin) {
		int cur = fin;
		while (cur != -1) {
			System.out.printf(" %d", cur);
			cur = p[cur];
		}
		return;
	}

	// DIJKSTRA HEAP END

	// DISKSTRA, SHORTEST FASTER ALGORITM.

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

	// END

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
