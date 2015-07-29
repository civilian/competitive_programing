package maratonBook.graphs;

import java.util.LinkedList;
import java.util.Scanner;

public class TopologicalSort {
	public static void main(String[] args) {
		Scanner sc = new Scanner("grafo.in");
		V = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new LinkedList<Edge>();
		// assign blank vectors of pair<int, int>s to AdjList
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				AdjList[i].addLast(new Edge(id, weight));
			}
		}
		// LECTURA END
	}

	static LinkedList<Edge>[] AdjList;
	static int V, total_neighbors, id, weight;// TODO: tener en cuenta que no se
												// repitan variables

	// TODO: toposort usando el grado de entrada 0,

	static class Edge {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
