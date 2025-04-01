package maratonBook.estructurasLibreriasPropias;

import java.util.*;

class Graph_ComoRepresentarYTeoria {
	/*
	 * a multigraph if it contains a given edge more than once or contain
	 * self-loops.
	 */
	/*
	 * the total number of edges total number possible ((N x (N-l))/2)
	 */
	/*
	 * A directed graph is said to be strongly connected if there is a path
	 * firom every vértex to every other vértex
	 */
	/*
	 * Bipartite Graph 2 sets A conectado con B pero no A con A
	 */
	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) {

		// Try this input for Adjacency Matrix/List/EdgeList
		// Adj Matrix
		// for each line: |V| entries, 0 or the weight
		// Adj List
		// for each line: num neighbors, list of neighbors + weight pairs
		// Edge List
		// for each line: a-b of edge(a,b) and weight
		/*
		 * Adj Matrix 6 0 10 0 0 100 0 10 0 7 0 8 0 0 7 0 9 0 0 0 0 9 0 20 5 100
		 * 8 0 20 0 0 0 0 0 5 0 0
		 */
		adyacenciMatrix();

		/*
		 * Adj List 6 2 2 10 5 100 3 1 10 3 7 5 8 2 2 7 4 9 3 3 9 5 20 6 5 3 1
		 * 100 2 8 4 20 1 4 5
		 */
		adyacenciList();

		/*
		 * Edge List 7 1 2 10 1 5 100 2 3 7 2 5 8 3 4 9 4 5 20 4 6 5
		 */
		edgeList();

		AdjList = amToAl();
		AdjMat = alToAm();
		EdgeList = amToEl();
		AdjMat = elToAm();
		EdgeList = alToEl();
		AdjList = elToAl();

		AdjMat[0][1] = -1;
		transposeAdjMat();

		/*
		 * Adj List 3 2 2 100 3 8 1 1 10 1 2 80
		 */
		adyacenciList();
		AdjList = tranposeAdjList();

	}

	static Scanner sc = new Scanner(System.in);
	static int V, E, total_neighbors, id, weight, a, b;

	static int[][] AdjMat;// we must know this size first!
	// remember that if V is > 100, try NOT to use AdjMat!

	static ArrayList<Edge>[] AdjList;

	static PriorityQueue<EdgeL> EdgeList;

	private static void edgeList() {
		E = sc.nextInt();

		/* EDGELIST DEFINICION Y PRUEBA */
		EdgeList = new PriorityQueue<EdgeL>();

		for (int i = 0; i < E; i++) {
			a = sc.nextInt();
			b = sc.nextInt();

			weight = sc.nextInt();

			EdgeList.offer(new EdgeL(a, b, -weight)); // trick to reverse sort
														// order */
		}

		// edges sorted by weight (smallest->largest)
		for (int i = 0; i < E; i++) {
			EdgeL edge = EdgeList.poll();
			// negate the weight again
			System.out.println("weight: " + (-edge.cost) + " (" + edge.from
					+ "-" + edge.to + ")");
		}
	}

	private static void adyacenciList() {
		/* ADYACENCI LIST */
		V = sc.nextInt();
		AdjList = new ArrayList[V];

		for (int i = 0; i < V; i++) { // for each vertex
			AdjList[i] = new ArrayList<Edge>(); // add this empty neighbor list
												// to Adjacency
			// List
		}

		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				AdjList[i].add(new Edge(id - 1, weight)); // some
															// index
															// adjustment
			}
		}

		System.out.println("Neighbors of vertex 0:");
		Iterator it = AdjList[0].iterator(); // AdjList[0] contains the
												// required information
		while (it.hasNext()) { // O(k), where k is the number of neighbors
			Edge val = (Edge) it.next();
			System.out.println("Edge 0-" + val.to + " (weight = " + val.cost
					+ ")");
		}
	}

	private static void adyacenciMatrix() {
		/* ADYACENCI MATRIX PRUEBA */
		V = sc.nextInt();
		AdjMat = new int[V][];
		for (int i = 0; i < V; i++) {
			AdjMat[i] = new int[V];
			for (int j = 0; j < V; j++)
				AdjMat[i][j] = sc.nextInt();
		}

		System.out.println("Neighbors of vertex 0:");
		for (int j = 0; j < V; j++)
			// O(|V|)
			if (AdjMat[0][j] != 0)
				System.out.println("Edge 0-" + j + " (weight = " + AdjMat[0][j]
						+ ")");
	}

	static ArrayList<Edge>[] amToAl() {
		ArrayList<Edge>[] out = new ArrayList[AdjMat.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < AdjMat.length; i++) {
			for (int j = 0; j < AdjMat[0].length; j++) {
				if (AdjMat[i][j] != 0) {
					out[i].add(new Edge(j, AdjMat[i][j]));
				}
			}
		}
		dbg("amToAl");
		dbg(AdjMat);
		dbg(out);

		return out;
	}

	static PriorityQueue<EdgeL> amToEl() {
		PriorityQueue<EdgeL> out = new PriorityQueue(
				(AdjMat.length * (AdjMat.length - 1)) / 2);

		for (int i = 0; i < AdjMat.length; i++) {
			for (int j = 0; j < AdjMat[0].length; j++) {
				if (AdjMat[i][j] != 0) {
					out.add(new EdgeL(i, j, AdjMat[i][j]));
				}
			}
		}
		dbg("amToEl");
		dbg(AdjMat);
		dbg(out);

		return out;
	}

	private static int[][] alToAm() {
		int[][] out = new int[AdjList.length][AdjList.length];

		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				out[i][e.to] = e.cost;
			}
		}
		dbg("alToAm");
		dbg(AdjList);
		dbg(out);

		return out;
	}

	private static PriorityQueue<EdgeL> alToEl() {
		PriorityQueue<EdgeL> out = new PriorityQueue(
				(AdjMat.length * (AdjMat.length - 1)) / 2);

		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				out.add(new EdgeL(i, e.to, e.cost));
			}
		}
		dbg("alToEl");
		dbg(AdjList);
		dbg(out);

		return out;
	}

	private static int[][] elToAm() {

		HashSet<Integer> diferentes = new HashSet<Integer>();
		/* Contar diferentes */
		for (EdgeL e : EdgeList) {
			diferentes.add(e.from);
			diferentes.add(e.to);
		}
		int V = diferentes.size();

		int[][] out = new int[V][V];

		for (EdgeL e : EdgeList) {
			out[e.from][e.to] = e.cost;
		}
		dbg("elToAm");
		dbg(EdgeList);
		dbg(out);

		return out;
	}

	private static ArrayList<Edge>[] elToAl() {

		HashSet<Integer> diferentes = new HashSet<Integer>();
		/* Contar diferentes */
		for (EdgeL e : EdgeList) {
			diferentes.add(e.from);
			diferentes.add(e.to);
		}
		int V = diferentes.size();

		ArrayList<Edge>[] out = new ArrayList[AdjMat.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = new ArrayList<Edge>();
		}

		for (EdgeL e : EdgeList) {
			out[e.from].add(new Edge(e.to, e.cost));
		}
		dbg("elToAl");
		dbg(EdgeList);
		dbg(out);

		return out;
	}

	private static int[][] transposeAdjMat() {
		int V = AdjMat.length;
		int[][] out = new int[V][V];

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (AdjMat[i][j] != 0) {
					out[j][i] = AdjMat[i][j];
				}
			}
		}
		dbg("transposeAdjMat");
		dbg(AdjMat);
		dbg(out);

		return out;
	}

	private static ArrayList<Edge>[] tranposeAdjList() {
		int V = AdjList.length;
		ArrayList<Edge>[] out = new ArrayList[V];// si es una matriz en vez de
													// una lista de adyacencia
													// seria igual a las
													// columnas de lo original
		for (int i = 0; i < V; i++) {
			out[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				out[e.to].add(new Edge(i, e.cost));
			}
		}
		dbg("transposeAdjList");
		dbg(AdjList);
		dbg(out);

		return out;
	}
}

class Edge {
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

}

class EdgeL implements Comparable<EdgeL> {
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
