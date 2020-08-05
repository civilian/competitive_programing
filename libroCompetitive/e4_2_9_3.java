package libroCompetitive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

/* Example of directed cyclic graph in Figure 4.8 (for graph check)
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
public class e4_2_9_3 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new LinkedList<Edge>();// assign blank vectors to
												// AdjList
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				AdjList[i].addLast(new Edge(id, weight));
			}
		}
		// LECTURA END
		initTarjanSCC(V);
	}

	static int V, total_neighbors, id, weight;
	static LinkedList<Edge>[] AdjList;
	static LinkedList<Edge>[] AdjListComp;
	static final int DFS_WHITE = -1; // normal DFS
	static int[] dfs_num; // global es para recursion
	static int numComp;
	static int[] dfs_low;
	static Stack<Integer> S; // additional global variables
	static boolean[] visited;
	static int dfsNumberCounter;

	static HashMap<Integer, Integer> nodosComprimidos = new HashMap<Integer, Integer>(
			100);

	static void initTarjanSCC(int V) {
		dfs_num = new int[V];
		dfs_low = new int[V];
		visited = new boolean[V];
		S = new Stack<Integer>();
		Arrays.fill(dfs_num, DFS_WHITE);
		Arrays.fill(dfs_low, 0);
		Arrays.fill(visited, false);
		dfsNumberCounter = numComp = 0;
		nodosComprimidos.clear();

		for (int i = 0; i < V; i++)
			if (dfs_num[i] == DFS_WHITE)
				tarjanSCC(i);

		AdjListComp = new LinkedList[numComp];
		for (int i = 0; i < numComp; i++)
			AdjListComp[i] = new LinkedList<Edge>();// assign blank vectors to
		// AdjList
		int de, hasta;
		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				de = nodosComprimidos.get(i);
				hasta = nodosComprimidos.get(e.to);
				if (de != hasta) {
					AdjListComp[de].add(new Edge(hasta, e.value));// pss porque
																	// es DAG
																	// Aciclico
				}

			}
		}

		dbg(nodosComprimidos);

		for (int i = 0; i < AdjListComp.length; i++) {
			dbg(AdjListComp[i]);
		}

	}

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
		// TODO Auto-generated method stub
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

				// nodosComprimidos.put(v, u);
				nodosComprimidos.put(v, numComp - 1);

				if (u == v)
					break;
			}
			System.out.printf("\n");
		}
	}
}
