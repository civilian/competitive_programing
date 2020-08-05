package maratonBook.graphs;

//TODO:lo estoy organizando
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 // Use the following input:
 // Graph in Figure 4.1/4.2
 9
 1 1 0
 3 0 0 2 0 4 0
 2 1 0 3 0
 3 1 0 2 0 4 0
 1 3 0
 0
 2 7 0 8 0
 1 6 0
 1 6 0

 R/Completar en todos los archivos
 ==================================
 Standard DFS Demo (the input graph must be UNDIRECTED)
 ==================================
 Component 1: 0 1 2 3 4
 Component 2: 5
 Component 3: 6 7 8
 There are 3 connected components

 R/==================================
 Topological Sort (el grafo no puede ser multi)
 ==================================
 5//Esta bien es el unico que no tiene ciclos

 R/==================================
 Graph Edges Property Check
 ==================================
 Component 1:
 Bidirectional (1, 0) - (0, 1)
 Bidirectional (2, 1) - (1, 2)
 Back Edge (3, 1) (Cycle)
 Bidirectional (3, 2) - (2, 3)
 Bidirectional (4, 3) - (3, 4)
 Forward/Cross Edge (1, 4)
 Component 2:
 Component 3:
 Bidirectional (7, 6) - (6, 7)
 Bidirectional (8, 6) - (6, 8)

 // Left graph in Figure 4.3/4.4/4.5
 6
 1 1 0
 3 0 0 2 0 4 0
 1 1 0
 1 4 0
 3 1 0 3 0 5 0
 1 4 0

 // Right graph in Figure 4.3/4.4/4.5
 6
 1 1 0
 5 0 0 2 0 3 0 4 0 5 0
 1 1 0
 1 1 0
 2 1 0 5 0
 2 1 0 4 0

 // Directed graph in Figure 4.6
 8
 1 1 0
 1 3 0
 1 1 0
 2 2 0 4 0
 1 5 0
 1 7 0
 1 4 0
 1 6 0

 // Example of directed graph with back edges
 3
 1 1 0
 2 0 0 2 0
 1 1 0

 // Example of directed acyclic graph in Figure 4.7 (for toposort)
 8
 2 1 0 2 0
 2 2 0 3 0
 2 3 0 5 0
 1 4 0
 0
 0
 0
 1 6 0

 R/
 ==================================
 Topological Sort (the input graph must be DAG)
 ==================================
 7 6 0 1 2 5 3 4
 ==================================
 Topological Sort (el grafo no puede ser multi)// topologicalSort(V)
 ==================================
 0 7 1 6 2 3 5 4

 // Example of directed cyclic graph in Figure 4.8 (for graph check)
 8
 1 1 0
 1 3 0
 1 1 0
 2 2 0 4 0
 1 5 0
 1 7 0
 1 4 0
 1 6 0
 R/==================================
 Graph Edges Property Check
 ==================================
 Component 1:
 Back Edge (2, 1) (Cycle)
 Back Edge (6, 4) (Cycle)
 R/==================================
 Strongly Connected Components (the input graph must be DIRECTED)
 ==================================
 SCC 1: 6 7 5 4
 SCC 2: 2 3 1
 SCC 3: 0
 */
public class DFS {

	private static void dbg(Object... os) {
		System.out.println(Arrays.deepToString(os));
	}

	static void printThis(String message) {
		System.out.printf("==================================\n");
		System.out.printf("%s\n", message);
		System.out.printf("==================================\n");
	}

	public static void main(String[] args) throws IOException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		AdjList = new ArrayList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new ArrayList<Edge>(V);// assign blank vectors to
												// AdjList
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				AdjList[i].add(new Edge(id, weight));
			}
		}
		// LECTURA END

		// DFS STANDAR
		initDFS(V);
		dfsComponents();
		// END DFS STANDAR

		System.out.println();

		// DFS stack//es posible que solo utilize mas memoria
		initDFS(V);
		dfsStack(0);
		System.out.println();
		// END DFS stack

		// FLOOD FILL DEMO
		floodfill();
		// FLOOD FILL DEMO END

		// TOPOLOGICAL SORT DFS
		initTopologicalSortDFS(V);// dentro esta el toplogical
		// TOPOLOGICAL SORT DFS END

		// TOPOLOGICAL SORT DFS
		initTopologicalSort(V);// dentro esta el toplogical TODO: HAY QUE HACER
								// LA LECTURA
		// TOPOLOGICAL SORT DFS END

		// GRAPH EDGE PROPERTY
		initGraphCheck(V);
		// GRAPH EDGE PROPERTY END

		// ARTICULATION POINTS
		initArticulationPointAndBridge(V);
		// ARTICULATION POINTS END

		// STRONGLY CONNECTED
		initTarjanSCC(V);
		// STRONGLY CONNECTED END

	}

	// DFS STANDAR
	static int V, total_neighbors, id, weight;
	static final int DFS_WHITE = -1;
	static final int DFS_BLACK = 1;
	static ArrayList<Edge>[] AdjList;
	static int[] dfs_num; // this variable has to be global, we cannot put it in
	// // recursion
	static int numComp;

	private static void initDFS(int V) { // used in normal DFS
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
		numComp = 0;
	}

	// con este DFS estoy contando los componentes, el que hace el dfs normal es
	// // el DFS(int u)
	private static void dfsComponents() {
		printThis("Standard DFS Demo (the input graph must be UNDIRECTED)");
		for (int i = 0; i < V; i++) { // for each vertex i in [0..V-1]
			if (dfs_num[i] == DFS_WHITE) { // if that vertex is not visited yet
				System.out.printf("Component %d:", ++numComp);
				dfs(i);
				System.out.printf("\n");
			}
		}
		System.out.printf("There are %d connected components\n", numComp);
	}

	/* A call of dfs(u) visits only vertices connected to u */
	static void dfs(int u) { // DFS for normal usage: as graph traversal
								// algorithm
		System.out.printf(" %d", u); // this vertex is visited
		dfs_num[u] = DFS_BLACK; // important step: we mark this vertex as
								// visited
		for (int j = 0; j < AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j); // v is a (neighbor,
										// weight) pair
			if (dfs_num[v.to] == DFS_WHITE) // important check to avoid
											// cycle
				dfs(v.to); // recursively visits unvisited neighbors v of
							// vertex u
		}
	}

	// END DFS STANDAR

	// DFS STACK

	// static int V, total_neighbors, id, weight;
	// static final int DFS_WHITE = -1;
	// static final int DFS_BLACK = 1;
	// static ArrayList<Edge>[] AdjList;
	// static int[] dfs_num; // this variable has to be global, we cannot put it
	// //in recursion
	// al parecer es mas rapido el recursivo porque de todas formas el tamaño de
	// la pila es constante
	/*
	 * Imprime diferente porque incluso antes de haberlo visitado incluye varias
	 * veces en la pila a un vertice
	 */
	static void dfsStack(int u) { // DFS for normal usage
		printThis("DFS STACK (the input graph must be UNDIRECTED)");
		LinkedList<Edge> busqueda = new LinkedList<Edge>();
		busqueda.add(new Edge(u, 0));
		// dbg(AdjList);
		while (!busqueda.isEmpty()) {
			Edge cur = busqueda.removeFirst();
			// if(cur.node==fin) return cur.cost;// miro la alguna condicion de
			// que la busqueda haya acabado
			System.out.printf(" %d", cur.to); // this vertex is visited
			dfs_num[cur.to] = DFS_BLACK; // mark as visited
			for (Edge e : AdjList[cur.to]) {// try all
											// neighbors
											// v of
											// vertex u
				if (dfs_num[e.to] == DFS_WHITE) { // avoid cycle
					Edge tmp = new Edge(e.to, e.cost++);
					busqueda.addFirst(tmp);// v is a (neighbor, weight) pair
				}
			}
		}
	}

	// END DFS STACK

	// FLODFILL

	// static int V, total_neighbors, id, weight;
	// static final int DFS_WHITE = -1;
	// static final int DFS_BLACK = 1;
	// static ArrayList<Edge>[] AdjList;
	// static int[] dfs_num; // this variable has to be global, we cannot put it
	// //in recursion
	// static int numComp;
	// note: this is not the version on implicit graph

	static void floodfill() {
		printThis("Flood Fill Demo (the input graph must be UNDIRECTED)");
		numComp = 0;
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);
		for (int i = 0; i < V; i++)
			if (dfs_num[i] == DFS_WHITE)
				floodfill(i, ++numComp);
		for (int i = 0; i < V; i++)
			System.out.printf("Vertex %d has color %d\n", i, dfs_num[i]);
	}

	static void floodfill(int u, int color) {
		dfs_num[u] = color; // not just a generic DFS_BLACK
		for (int j = 0; j < AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE)
				floodfill(v.to, color);
		}
	}

	// FLODFILL END

	// FLODFILL 2D IMPLICITO

	// Implicit graph Version(2D grid)//pero este floodfill no es bueno en
	// recursion lo ultimo es la suma
	static int dr[] = { 1, 1, 0, -1, -1, -1, 0, 1 }; // S,SE,E,NE,N,NW,W,SW
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 }; // neighbors
	static int[][] grid;
	static int R, C;// R es la fila mas grande C es la columna

	static int floodfill(int r, int c, char c1, char c2) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return 0; // outside
		if (grid[r][c] != c1)
			return 0; // we want only c1
		grid[r][c] = c2; // important step to avoid cycling!
		int ans = 1; // coloring c1 -> c2, add 1 to answer
		for (int d = 0; d < 8; d++)
			// recurse to neighbors
			ans += floodfill(r + dr[d], c + dc[d], c1, c2);
		return ans;
	}

	// FLODFILL 2D IMPLICITO END

	// TOPOLOGICAL SORT DFS (OF DAG)

	// static int V, total_neighbors, id, weight;
	// static ArrayList<Edge>[] AdjList;
	// static final int DFS_WHITE= -1; // normal DFS, do not change this with
	// // other values (other than 0), because we usually use memset with
	// // conjunction with DFS_WHITE
	// static final int DFS_BLACK= 1;
	// static int[] dfs_num; // this variable has to be global, we cannot put it
	// // in recursion
	static ArrayList<Integer> topoSort; // global vector to store the toposort

	private static void initTopologicalSortDFS(int V) {
		// // make sure that the given graph is DAG
		printThis("Topological Sort (the input graph must be DAG)");
		topoSort = new ArrayList<Integer>();
		topoSort.clear();
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);

		for (int i = 0; i < V; i++)
			// this part is the same as finding CCs
			if (dfs_num[i] == DFS_WHITE)
				// asi se hace todo el topological
				topologicalSortDFS(i);
		Collections.reverse(topoSort);
		// reverse topoSort
		for (int i = 0; i < topoSort.size(); i++)
			// or you can simply read
			System.out.printf(" %d", topoSort.get(i));
		// the content of `topoSort' backwards
		System.out.printf("\n");
	}// in reverse order

	static void topologicalSortDFS(int u) { // change function name to
											// differentiate with
		// original dfs
		dfs_num[u] = DFS_BLACK;
		for (int j = 0; j < AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE)
				topologicalSortDFS(v.to);
			// para ver si el grafo no es valido para tener una toposort solo
			// hay que ver que tenga
			// ciclos o back edges
		}
		topoSort.add(u);
	} // that is, this is the only change

	// TOPOLOGICAL SORT DFS (OF DAG) END

	// TOPOLOGICAL SORT(No multigrafos por el degre)

	// static int V, total_neighbors, id, weight;
	// static ArrayList<Edge>[] AdjList;
	// static ArrayList<Integer> topoSort; // global vector to store the
	// // toposort
	static int MAX_V = 101;// DEPENDE DEL PROBLEMA
	static int[] indegree = new int[MAX_V];
	// static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// (MAX_V); //para imprimir el toposort
	// // respetando el orden de la entrada
	static Queue<Integer> pDeg = new LinkedList<Integer>();

	private static void initTopologicalSort(int V) {
		// // make sure that the given graph is DAG
		printThis("Topological Sort (el grafo no puede ser multi)");

		// creo el grafo
		// AdjList = new ArrayList[V];
		// for (int i = 0; i < AdjList.length; i++)
		// AdjList[i] = new ArrayList<Edge>(V);

		Arrays.fill(indegree, 0);
		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				indegree[e.to]++;// TODO: en la lectura como 11060
									// BeveragesTopoSort
			}
		}

		pDeg.clear();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				pDeg.add(i);
			}
		}

		topologicalSort();
		for (int i = 0; i < topoSort.size(); i++)
			// TODO: El tamaño para cuando falla
			System.out.printf(" %d", topoSort.get(i));
		System.out.printf("\n");
	}

	static void topologicalSort() {
		topoSort.clear();
		int to;
		int act;
		while (!pDeg.isEmpty()) {
			act = pDeg.poll();
			topoSort.add(act);
			for (int j = 0; j < AdjList[act].size(); j++) {
				to = AdjList[act].get(j).to;
				indegree[to]--;
				if (indegree[to] == 0) {
					pDeg.add(to);
				}
			}
		}
	}

	// TOPOLOGICAL SORT(No multigrafos por el degre) END

	// GRAPH CHECK
	// static int V, total_neighbors, id, weight;
	// static ArrayList<Edge>[] AdjList;

	// static final int DFS_WHITE= -1; // normal DFS
	// static final int DFS_BLACK= 1;
	// static int[] dfs_num; // this variable has to be global, we cannot put it
	// in recursion
	static int[] dfs_parent; // papas
	static final int DFS_GRAY = 2; // one more color for graph edges property
									// check

	private static void initGraphCheck(int V) {
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
		numComp = 0;
		dfs_parent = new int[V];
		Arrays.fill(dfs_parent, -1);

		printThis("Graph Edges Property Check");
		for (int i = 0; i < V; i++) {
			if (dfs_num[i] == DFS_WHITE) {
				System.out.printf("Component %d:\n", ++numComp);
				graphCheck(i);
			}
		}
	}

	static void graphCheck(int u) { // DFS for checking graph edge properties
		dfs_num[u] = DFS_GRAY; // color this as DFS_GRAY (temp)
		for (int j = 0; j < (int) AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE) { // Tree Edge, DFS_GRAY to
												// DFS_WHITE
				dfs_parent[v.to] = u; // parent of this children is me
				graphCheck(v.to);
			} else if (dfs_num[v.to] == DFS_GRAY) { // DFS_GRAY to DFS_GRAY
				if (v.to == dfs_parent[u]) // to differentiate these two
											// cases
					System.out.printf(" Bidirectional (%d, %d) - (%d, %d)\n",
							u, v.to, v.to, u);
				else
					// la mas usada pillar si tiene un ciclo
					System.out.printf(" Back Edge (%d, %d) (Cycle)\n", u, v.to);
			} else if (dfs_num[v.to] == DFS_BLACK) // DFS_GRAY to DFS_BLACK
				System.out.printf(" Forward/Cross Edge (%d, %d)\n", u, v.to);
		}
		dfs_num[u] = DFS_BLACK; // despues de la recursion DFS_BLACK (DONE)
	}

	// GRAPH CHECK END

	// ARTICULATION POINT AND BRIDGE

	// static int V, total_neighbors, id, weight;
	// static ArrayList<Edge>[] AdjList;
	// static final int DFS_WHITE= -1; // normal DFS
	// static final int DFS_BLACK= 1;
	// static int[] dfs_num;
	// static int[] dfs_parent; // to differentiate real back edge versus
	// // bidirectional edge
	static int[] dfs_low; // additional information for articulation
							// points/bridges/SCCs
	static boolean[] articulation_vertex;
	static int dfsNumberCounter;

	static int dfsRoot;

	static int rootChildren;

	static void initArticulationPointAndBridge(int V) {
		printThis("Articulation Points & Bridges (the input graph must be UNDIRECTED)");
		dfs_num = new int[V];
		dfs_parent = new int[V];
		dfs_low = new int[V];
		articulation_vertex = new boolean[V];
		dfsNumberCounter = 0;
		Arrays.fill(dfs_num, DFS_WHITE);
		Arrays.fill(dfs_low, 0);
		Arrays.fill(dfs_parent, -1);
		Arrays.fill(articulation_vertex, false);
		System.out.printf("Bridges:\n");
		for (int i = 1; i < V; i++) {
			if (dfs_num[i] == DFS_WHITE) {
				dfsRoot = i;
				rootChildren = 0;
				articulationPointAndBridge(i);
				articulation_vertex[dfsRoot] = (rootChildren > 1); // special
																	// case
			}
		}
		System.out.printf("Articulation Points:\n");
		for (int i = 0; i < V; i++)
			if (articulation_vertex[i])
				System.out.printf(" Vertex %d\n", i);
	}

	static void articulationPointAndBridge(int u) {
		// dfs_low[u] <= dfs_num[u]
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
		for (int j = 0; j < (int) AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE) {// a tree edge
				dfs_parent[v.to] = u;
				// special case, count children of root
				if (u == dfsRoot)
					rootChildren++;
				articulationPointAndBridge(v.to);
				// for articulation point
				if (dfs_low[v.to] >= dfs_num[u])
					// stoare this information first
					articulation_vertex[u] = true;

				if (dfs_low[v.to] > dfs_num[u])// for bridge
					System.out.printf(" Edge (%d, %d) is a bridge\n", u, v.to);
				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.to]);
			}
			// a back edge and not direct cycle
			else if (v.to != dfs_parent[u])
				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v.to]);
		}
	}

	// ARTICULATION POINT AND BRIDGE END

	// FINDING STRONGLY CONECTED COMPONENTS
	// static int V, total_neighbors, id, weight;
	// static ArrayList<Edge>[] AdjList;
	// static final int DFS_WHITE= -1; // normal DFS
	// static int[] dfs_num; // global es para recursion
	// static int[] dfs_low;
	// static int numComp;
	// static int dfsNumberCounter;
	static Stack<Integer> S; // additional global variables
	static boolean[] visited;

	static void initTarjanSCC(int V) {
		printThis("Strongly Connected Components (the input graph must be DIRECTED)");
		dfs_num = new int[V];
		dfs_low = new int[V];
		visited = new boolean[V];
		S = new Stack<Integer>();
		Arrays.fill(dfs_num, DFS_WHITE);
		Arrays.fill(dfs_low, 0);
		Arrays.fill(visited, false);
		dfsNumberCounter = numComp = 0;
		for (int i = 0; i < V; i++)
			if (dfs_num[i] == DFS_WHITE)
				tarjanSCC(i);
	}

	static void tarjanSCC(int u) {
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <=
														// dfs_num[u]
		S.push(u);// stores u in a vector based on order of visitation
		visited[u] = true;
		for (int j = 0; j < (int) AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE)
				tarjanSCC(v.to);
			if (visited[v.to]) // condition for update
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.to]);
		}

		if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
			System.out.printf("SCC %d:", ++numComp); // this part is done after
														// recursion
			while (true) {
				int v = S.pop();
				visited[v] = false;
				System.out.printf(" %d", v);
				if (u == v)
					break;
			}
			System.out.printf("\n");
		}
	}

	// FINDING STRONGLY CONECTED COMPONENTS
	
	//ClASES
	
	static class Edge {
		Integer to, cost;

		public Edge(Integer toIn, Integer costIn) {
			to = toIn;
			cost = costIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", _second=" + cost + "]";
		}
	}
	
	//ClASES END

}

