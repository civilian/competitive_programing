package libroCompetitive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

/* // Example of directed cyclic graph in Figure 4.8 (for graph check)
 8
 1 1 0
 1 3 0
 1 1 0
 2 2 0 4 0
 1 5 0
 1 7 0
 1 4 0
 1 6 0
 R/ FLOYD WARSHALL
 */
public class e4_5_3_1 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		AdjList = new ArrayList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new ArrayList<Edge>(V);// assign blank vectors to
												// AdjList

		AdjMatrix = new int[V][V];
		ConMatrix = new int[V][V];//guardarlo en otra cosa como un AdjList
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				AdjMatrix[i][j] = 0;
				ConMatrix[i][j] = 0;
			}
			AdjMatrix[i][i] = 1;
			ConMatrix[i][i] = 1;
		}

		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				AdjList[i].add(new Edge(id, weight));
				from = i;
				to = id;
				w = weight;
				AdjMatrix[from][to] = 1; // directed graph
			}
		}
		// LECTURA END
		transitiveClosure();
		System.out.println("Tarjan TRANSITIVE CLOSURE");
		initTarjanSCC(V);
	}

	// FLOYD WARSHALL TRANSITIVE CLOSURE
	static int INF = 1000000000;
	static int E, from, to, w;// no necesariamente tienen que ser globales
	// porque todo se puede meter en el main
	static int AdjMatrix[][];

	/* Es para ver si hay un camino el que sea entre x y y */
	static void transitiveClosure() {
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = AdjMatrix[i][j]
							| (AdjMatrix[i][k] & AdjMatrix[k][j]);
				}
			}
		}

		// Mostrando:
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.printf("APSP(%d, %d) = %d\n", i, j, AdjMatrix[i][j]);
			}
		}
	}

	// FLOYD WARSHALL TRANSITIVE CLOSURE END
	// FINDING STRONGLY CONECTED COMPONENTS
	static int V, total_neighbors, id, weight;
	static ArrayList<Edge>[] AdjList;
	static final int DFS_WHITE = -1; // normal DFS
	static int[] dfs_num; // global es para recursion
	static int[] dfs_low;
	static int numComp;
	static int dfsNumberCounter;
	static Stack<Integer> S; // additional global variables
	static boolean[] visited;
	static int ConMatrix[][];

	static void initTarjanSCC(int V) {
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
}
