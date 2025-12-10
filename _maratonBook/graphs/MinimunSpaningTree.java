package maratonBook.graphs;

/*KRUSKAL ES MAS RAPIDO, PERO SEGUN ES MAS DIFICIL IMPLEMENTAR*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 // Graph in Figure 4.9 left/4.10, format: list of weighted edges
 5 7
 0 1 4
 0 2 4
 0 3 6
 0 4 6
 1 2 2
 2 3 8
 3 4 9
 R/MST cost = 18 (Kruskal's)
 MST cost = 18 (Prim's)
 */

public class MinimunSpaningTree {

	public static void main(String[] args) {

		// Lectura
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		AdjList = new ArrayList[V];
		for (int i = 0; i < V; i++)
			AdjList[i] = new ArrayList<Edge>(V);
		// format: weight, (two vertices of the edge)
		EdgeList = new ArrayList<EdgeL>(MAX_VERTEX);
		for (int i = 0; i < E; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt(); // read the triple: (a, b, w)
			// KRUSKALL
			EdgeList.add(new EdgeL(u, v, w));
			// PRIM
			AdjList[u].add(new Edge(v, w));
			AdjList[v].add(new Edge(u, w));// es no dirigido
		}
		// Lectura END

		// Kruskal's

		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// (POR UNION SET)
		kruskal();
		// Kruskal's END

		// PRIM´s

		// MUY IMPORTANTE:
		// -LOS INDICES QUE MANEJA EL ALGORITMO PARA LOS VERTICES SON DESDE 0
		// (POR UNION SET)
		prim();
		// PRIM´s END

	}

	// KRUSKALL
	static int V, E, u, v, w;
	static int MAX_VERTEX = 200001;// depende del problema
	static ArrayList<EdgeL> EdgeList;
	static int mst_cost = 0;

	static void kruskal() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			// y meter conecciones++ en el if
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to)) { // if no cycle
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
			// if (_numDisjointSets <= 1) {
			// break;// Mirar si los componentes deseados ya estan o hay menos
			// }
		} // note: the runtime cost of UFDS is very light
		System.out.printf("MST cost = %d (Kruskal's)\n", mst_cost);

	}

	// Union-Find
	static int[] pset, setSize, rank;
	static int _numDisjointSets;

	static void initSet(int N) {
		rank = new int[N];
		setSize = new int[N];
		Arrays.fill(setSize, 1);
		_numDisjointSets = N;
		pset = new int[N];
		for (int i = 0; i < N; i++) {
			pset[i] = i;
		}
	}

	static int findSet(int i) {
		return (pset[i] == i) ? i : (pset[i] = findSet(pset[i]));
	}

	static boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	static void unionSet(int i, int j) {
		int xroot = findSet(i), yroot = findSet(j);
		if (xroot == yroot)
			return;
		_numDisjointSets--;
		if (rank[xroot] < rank[yroot]) {
			pset[xroot] = yroot;
			setSize[yroot] += setSize[xroot];
		} else if (rank[xroot] > rank[yroot]) {
			pset[yroot] = xroot;
			setSize[xroot] += setSize[yroot];
		} else {
			pset[yroot] = xroot;
			rank[xroot]++;
			setSize[xroot] += setSize[yroot];
		}
	}

	// KRUSKALL END

	// PRIM
	// static int V, E, u, v, w;
	static int MAX_EDGES = 200001;// depende del problema
	// static int mst_cost = 0;
	static ArrayList<Edge>[] AdjList;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(MAX_EDGES);
	static boolean[] taken; // global boolean flag to avoid cycleF

	static void process(int vtx) {
		taken[vtx] = true;
		for (int j = 0; j < AdjList[vtx].size(); j++) {
			Edge v = AdjList[vtx].get(j); // weight //id
			if (!taken[v.to])
				pq.add(new Edge(v.to, v.cost));
		}
	} // sort by (inc) weight then by (inc) id by using -ve sign to reverse
		// order

	static void prim() {
		pq.clear();
		taken = new boolean[V];
		// take vertex 0 and process all edges incident to vertex 0
		process(0);
		mst_cost = 0;
		while (!pq.isEmpty()) {
			// repeat until V vertices (E = V-1 edges) are taken
			Edge front = pq.poll();
			u = front.to;
			w = front.cost;
			if (!taken[u]) {// we have not connect this vertex yet
				// take u and process all edges incident to u
				mst_cost += w;
				process(u);
			}
		}// each edge is in pq only once!
		System.out.printf("MST cost = %d (Prim's)\n", mst_cost);
	}

	// CLASES
	static class EdgeL implements Comparable<EdgeL> {
		int from, to, cost;

		public int compareTo(EdgeL e) {
			return cost - e.cost;
		}

		public EdgeL(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "(from=" + from + ", to=" + to + ", cost=" + cost + ")";
		}
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "(" + to + ", cost=" + cost + ")";
		}

		@Override
		public int compareTo(Edge o2) {
			if (cost == o2.cost) {
				return to - o2.to;
			} else {
				return cost - o2.cost;
			}
		}
	}
	// CLASES END
}
