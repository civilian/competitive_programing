package maratonBook.graphs;

/*KRUSKAL ES MAS RAPIDO, PERO ES MAS DIFICIL IMPLEMENTAR*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 // Graph in Figure 4.9 left/4.10, format: list of weighted edges
 El 0 al final es porque no tiene aristas iniciales
 5 7
 0 1 4
 0 2 4
 0 3 6
 0 4 6
 1 2 2
 2 3 8
 3 4 9
 0
 R/MST cost = 27 (Kruskal's Max)
 MST cost = 27 (Prim's Max)
 MST cost = 18 (Kruskal's Minimo Parcial)
 MST cost = 18 (Prim's Minimo Parcial)
 MST cost = 12 (Kruskal's Spaning Forest)

 // Graph in Figure 4.9 left/4.10, format: list of weighted edges
 con 1 vertice de mas
 5 6
 0 1 4
 0 3 6
 0 4 6
 1 2 2
 2 3 8
 3 4 9
 1
 2 3
 R/MST cost = 27 (Kruskal's Max)
 MST cost = 27 (Prim's Max)
 MST cost = 24 (Kruskal's Minimo Parcial)
 MST cost = 24 (Prim's Minimo Parcial)
 R/MST cost = 18 (Kruskal second normal)
 ..MST cost = 20 (Kruskal second best)
 */

public class MinimunSpaningTreeVariantes {
	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

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
			EdgeList.add(new EdgeL(u, v, w));
			AdjList[u].add(new Edge(v, w));
			AdjList[v].add(new Edge(u, w));// es no dirigido
		}
		// Lectura END

		// Kruskal's MAX
		// IMPORTANTE: INDEX DESDE 0
		kruskalMax();
		// Kruskal's MAX END

		// KRUSKAL MINIMO PARCIAL
		int M = sc.nextInt();
		initSet(V);
		mst_cost = 0;
		for (int i = 0; i < M; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			unionSet(u - 1, v - 1);
			int costuUV = 8;// Depende del problema como buscarlo o si buscarlo
			mst_cost += costuUV;
		}
		kruskalMinimoParcial();
		// KRUSKAL MINIMO PARCIAL END

		// KRUSKALL MINIMUN SPANING FOREST
		desiredComponents = 2;// depende del problema
		kruskalSpaningForest();
		// KRUSKALL MINIMUN SPANING FOREST END

		// KRUSKALL SECOND BEST
		kruskalSecondBestInit();
		// KRUSKALL SECOND BEST END

		// KRUSKALL MINIMAX BEST
		// kruskalMinimaxInit();//no se ha terminado
		// KRUSKALL MINIMAX BEST END

		// PRIM´s Max
		// IMPORTANTE: INDEX DESDE 0
		primMax();
		// PRIM´s Max END

		// PRIM MINIMO PARCIAL
		// TODO: PRIM MINIMO PARCIAL NO ESTA COMPLETO EL CASO ESPECIAL CUANDO NO
		// SE NECESITAN MAS VERTICES QUE LOS YA "CONSTRUIDOS" NO FUNCIONA BN
		// ADEMAS LA IMPLEMENTACION ES MUY LENTA
		// IMPORTANTE: INDEX DESDE 0
		// int M = sc.nextInt();
		// mst_cost = 0;
		// taken = new boolean[V];
		// parcialyTaken = new boolean[V];
		// for (int i = 0; i < M; i++) {
		// u = sc.nextInt();
		// v = sc.nextInt();
		// quienTome(u, v);
		// int costuUV = 8;// Depende del problema como buscarlo o si
		// // buscarlo
		// mst_cost += costuUV;
		// }
		// primMinimoParcial();
		// PRIM MINIMO PARCIAL END

	}

	// KRUSKALL MAX
	static int V, E, u, v, w;
	static int MAX_VERTEX = 200001;// depende del problema
	static ArrayList<EdgeL> EdgeList;
	static int mst_cost = 0;

	private static void kruskalMax() {
		Comparator<EdgeL> comp = new Comparator<EdgeL>() {
			@Override
			public int compare(EdgeL o1, EdgeL o2) {
				return o2.cost - o1.cost;
			}
		};
		Collections.sort(EdgeList, comp); // sort by edge weight in O(E log E)

		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < E; i++) { // tambien se puede conecciones < vertex-1
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
		System.out.printf("MST cost = %d (Kruskal's Max)\n", mst_cost);

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

	// KRUSKALL MAX END

	// KRUSKALL MINIMO PARCIAL

	// static int V, E, u, v, w;
	// static int MAX_VERTEX = 200001;// depende del problema
	// static ArrayList<EdgeL> EdgeList;
	// static int mst_cost = 0;

	// Se supone que ya hay algunas aristas que se tienen que tomar.
	private static void kruskalMinimoParcial() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)
		// initSet(V); //en el minimo parcial esto se inicializa y medio llena
		// // antes
		for (int i = 0; i < E; i++) { // tambien se puede conecciones < vertex-1
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
		System.out.printf("MST cost = %d (Kruskal's Minimo Parcial)\n",
				mst_cost);

	}

	// Union-Find
	// copiarlo de mas arriba todo

	// KRUSKALL MINIMO PARCIAL END

	// KRUSKALL SPANING FOREST

	// static int V, E, u, v, w;
	static int desiredComponents;

	// static int MAX_VERTEX = 501;// depende del problema
	// static ArrayList<EdgeL> EdgeList = new ArrayList<EdgeL>(500 * 500);
	// static int mst_cost = 0;

	private static void kruskalSpaningForest() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)
		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			if (_numDisjointSets <= desiredComponents) {
				break;// Mirar si los componentes deseados ya estan o hay menos
			} // al inicio
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to)) { // if no cycle
				mst_cost += front.cost;
				unionSet(front.from, front.to); // link endpoints
			}
		} // note: the runtime cost of UFDS is very light
		System.out.printf("MST cost = %d (Kruskal's Spaning Forest)\n",
				mst_cost);

	}

	// Union-Find
	// copiarlo de mas arriba todo

	// KRUSKALL SPANING FOREST END

	// KRUSKALL SECOND BEST

	// static int V, E, u, v, w;
	// static int MAX_VERTEX = 200001;// depende del problema
	// static ArrayList<EdgeL> EdgeList;
	// static int mst_cost = 0;
	static ArrayList<EdgeL> mst = new ArrayList<EdgeL>(MAX_VERTEX);
	static EdgeL noUsar;
	static int secondMin;

	private static void kruskalSecondBestInit() {
		mst.clear();
		Collections.sort(EdgeList); // ordeno 1 vez
		noUsar = null;
		kruskalSecondBest();
		System.out.printf("MST cost = %d (Kruskal second normal)\n", mst_cost);
		secondMin = 1000000000;
		for (EdgeL e : mst) {
			noUsar = e;
			kruskalSecondBest();
			if (_numDisjointSets <= 1) {// Cualquier otra cosa desconectaria el
				secondMin = Math.min(secondMin, mst_cost); // grafo
			}
		}
		System.out.printf("MST cost = %d (Kruskal second best)\n", secondMin);
	}

	private static void kruskalSecondBest() {
		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			// y meter conecciones++ en el if
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to) && !front.equals(noUsar)) {
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
				if (noUsar == null) {
					mst.add(front);
				}
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
			if (_numDisjointSets <= 1) {
				break;// Mirar si los componentes deseados ya estan o hay menos
			}
		} // note: the runtime cost of UFDS is very light
	}

	// Union-Find
	// Copiarlo de mas arriba todo o de la original

	// KRUSKALL SECOND BEST END

	// PRIM MAX
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
	}

	private static void primMax() {
		Comparator<Edge> comp = new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.cost == o2.cost) {
					return o1.to - o2.to;
				} else {
					return o2.cost - o1.cost;
				}
			}
		};

		pq = new PriorityQueue<Edge>(E + 1, comp);
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
		System.out.printf("MST cost = %d (Prim's Max)\n", mst_cost);
	}

	// PRIM MAX END

	// PRIM MINIMO PARCIAL

	// static int V, E, u, v, w;
	// static int MAX_EDGES = 200001;// depende del problema
	// static int mst_cost = 0;
	// static ArrayList<Edge>[] AdjList;
	// static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(MAX_EDGES);
	// static boolean[] taken; // global boolean flag to avoid cycleF
	static boolean[] parcialyTaken; // global boolean flag to avoid cycleF

	private static void quienTome(int u, int v) {
		if (parcialyTaken[v] == false && parcialyTaken[v] == false) {
			parcialyTaken[u] = true;
			taken[u] = true;
			parcialyTaken[v] = true;
			process(u, true);
			process(v, false);
			return;
		}
		if (parcialyTaken[v]) {
			taken[v] = true;
			parcialyTaken[v] = true;
			process(v, true);
		}
		if (parcialyTaken[u]) {
			taken[u] = true;
			parcialyTaken[u] = true;

			process(u, true);
		}
	}

	static void process(int vtx, boolean take) {
		taken[vtx] = take;
		for (int j = 0; j < AdjList[vtx].size(); j++) {
			Edge v = AdjList[vtx].get(j); // weight //id
			if (!taken[v.to])
				pq.add(new Edge(v.to, v.cost));
		}
	}

	private static void primMinimoParcial() {
		pq = new PriorityQueue<Edge>(E + 1);
		// taken = new boolean[V];
		// mst_cost = 0;//se inicializan y cambian antes
		// take vertex 0 and process all edges incident to vertex 0
		process(0, true);
		while (!pq.isEmpty()) {
			// repeat until V vertices (E = V-1 edges) are taken
			Edge front = pq.poll();
			u = front.to;
			w = front.cost;
			if (!taken[u]) {// we have not connect this vertex yet
				// take u and process all edges incident to u
				mst_cost += w;
				process(u, true);
			}
		}// each edge is in pq only once!
		System.out.printf("MST cost = %d (Prim's Minimo Parcial)\n", mst_cost);
	}

	// PRIM MINIMO PARCIAL END

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
	// CLASES END}
}
