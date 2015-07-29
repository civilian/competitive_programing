package maratonBook.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/*
// Graph in Figure 4.9 left/4.10, format: list of weighted edges
El 0 al final es porque no tiene aristas iniciales
5 7
4 1 4
4 2 4
4 3 6
4 0 6
1 2 2
2 3 8
3 0 9
0
*/
public class KruskallMinimax_SinTerminar {

	private static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) {
		// Lectura
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		// AdjList = new ArrayList[V];
		// for (int i = 0; i < V; i++)
		// AdjList[i] = new ArrayList<Edge>(V);
		// format: weight, (two vertices of the edge)
		EdgeList = new ArrayList<EdgeL>(MAX_VERTEX);
		for (int i = 0; i < E; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt(); // read the triple: (a, b, w)
			EdgeList.add(new EdgeL(u, v, w));
			// AdjList[u].add(new Edge(v, w));
			// AdjList[v].add(new Edge(u, w));// es no dirigido
		}
		// Lectura END
		kruskalMinimaxInit();
	}

	// KRUSKALL MINIMAX

	static int V, E, u, v, w;
	static int MAX_VERTEX = 200001;// depende del problema
	static ArrayList<EdgeL> EdgeList;
	static ArrayList<EdgeL> mst=new ArrayList<EdgeL>();// pa debugear
	static int mst_cost = 0;
	static int[] parents;// tener en cuenta que un vertice puede tener varios
							// hijos.
	static int[] costParents;// el costo de ir i hacia parents[i] es
								// costParents[i]

	static void kruskalMinimaxInit() {
		mst.clear();
		kruskalMinimax();
		dbg(mst);
		dbg(parents);
		dbg(EdgeList);
		int desde = 0, hasta = v - 1;// depende del problema

		desde = Math.min(desde, hasta);
		hasta = Math.max(desde, hasta);
		int actual = parents[desde];
		int miniMax = costParents[desde];
		do {
			if (actual == -1) {
				System.out.println("No path");
				break;
			} else {
				miniMax = Math.max(miniMax, costParents[actual]);
				actual = parents[actual];
			}
		} while (actual != hasta);

		if (actual != -1) {
			System.out.println(miniMax);
		}
	}

	static void kruskalMinimax() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		parents = new int[V];
		costParents = new int[V];
		Arrays.fill(parents, -1);
		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			// y meter conecciones++ en el if
			EdgeL fr = EdgeList.get(i);
			if (!isSameSet(fr.from, fr.to)) { // if no cycle
				mst_cost += fr.cost; // add the weight of e to MST
				unionSet(fr.from, fr.to); // link endpoints
				parents[Math.max(fr.from, fr.to)] = Math.min(fr.from, fr.to);
				costParents[fr.from] = fr.to;
				mst.add(fr);
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
			// if (_numDisjointSets <= 1) {
			// break;// Mirar si los componentes deseados ya estan o hay menos
			// }
		} // note: the runtime cost of UFDS is very light

	}

	// Union-Find
	// Copiarlo de mas arriba todo o de la original
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

	// KRUSKALL MINIMAX END
	
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

	// CLASES END
}
