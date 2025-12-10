package maratonBook.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 // Example of directed acyclic graph in Figure 4. (for toposort)//no se sabe
 8
 2 1 0 2 0
 2 2 0 3 0
 2 3 0 5 0
 1 4 0
 0
 0
 0
 1 6 0
 R/ COUNTING PATHS
 3

 // Example of directed acyclic graph in Figure 4.29 (for counting paths)
 9
 1 1 0
 2 2 0 4 0
 2 3 0 4 0
 1 4 0
 2 5 0 6 0
 1 7 0
 1 8 0
 1 8 0
 0
 R/ COOUNTING PATHS
 [[1, 1, 1, 1, 3, 3, 3, 3, 6, 0, ..
 6
 */

public class DAGAlgoritmos {

	private static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) {
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

		// SINGLE SOURCE SHORTEST/LONGES PATH ON DAG

		// // Para longest se ponen los costos negativos y se corre el mismo
		// // algoritmo y para la salida se vuelve a pasar de signo
		// initTopologicalSort(V);
		// s = topoSort.get(0);// a menos que el problema diga otra cosa
		// SSShortestPathOnDag();

		// SINGLE SOURCE SHORTEST/LONGES PATH ON DAG END

		// COUNTING PATHS ON DAG

		initTopologicalSort(V);
		s = topoSort.get(0);// a menos que el problema diga otra cosa
		countingPathsOnDag();
		dbg(num_paths);
		System.out.println(num_paths[topoSort.get(topoSort.size() - 1)]);

		// COUNTING PATHS ON DAG END
	}

	// SINGLE SOURCE SHORTEST/LONGES PATH ON DAG

	static int MAX_V = 101;// definirlo bn

	static int V, total_neighbors, id, weight;
	static ArrayList<Edge>[] AdjList;

	// TopoSort Stuff
	static ArrayList<Integer> topoSort = new ArrayList<Integer>(MAX_V);
	static int[] indegree = new int[MAX_V];
	// static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// (MAX_VERTICES); //para imprimir el toposort respetando el orden de la
	// //entrada
	static Queue<Integer> pDeg = new LinkedList<Integer>();

	// Sssp
	static int[] dist = new int[MAX_V];
	static int INF = 1000000000;
	static int s;

	private static void SSShortestPathOnDag() {
		Arrays.fill(dist, 0, V + 7, INF);
		dist[s] = 0;
		for (Integer v : topoSort) {
			for (Edge e : AdjList[v]) {
				if (dist[e.to] > dist[v] + e.cost) {
					dist[e.to] = dist[v] + e.cost;
				}
			}
		}

	}

	// TOPOSORT
	private static void initTopologicalSort(int V) {
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
		// Mostrar el toposort
		for (int i = 0; i < topoSort.size(); i++)
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

	// COUNTING PATHS ON DAG

	// static int MAX_V = 101;// definirlo bn
	//
	// static int V, total_neighbors, id, weight, s;
	// static ArrayList<Edge>[] AdjList;
	//
	// // TopoSort Stuff
	// static ArrayList<Integer> topoSort = new ArrayList<Integer>(MAX_V);
	// static int[] indegree = new int[MAX_V];
	// // static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// // (MAX_VERTICES); //para imprimir el toposort respetando el orden de la
	// // //entrada
	// static Queue<Integer> pDeg = new LinkedList<Integer>();

	static int[] num_paths = new int[MAX_V];

	// Topological sort Copiarlo de arriba completo

	private static void countingPathsOnDag() {
		Arrays.fill(num_paths, 0, V + 7, 0);
		num_paths[s] = 1;
		for (int i = 0, cur; i < topoSort.size(); i++) {
			cur = topoSort.get(i);
			for (Edge e : AdjList[cur]) {
				num_paths[e.to] += num_paths[cur];
			}
		}
	}

	// COUNTING PATHS ON DAG END

	// ClASES

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

	// ClASES END

}
