package uva.tomo9;

/*Uva 988. */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class Manypathsonedestination {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	// static void dbg(Object... o) {
	// output.println(Arrays.deepToString(o));
	// }

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Manypathsonedestination"));

		// LECTURA
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new ArrayList<Edge>(V);// assign blank vectors to
												// AdjList
		String l;
		int idCases = 0;
		while (true) {

			readln();
			V = nextInt();
			for (int i = 0; i < V + 7; i++) {
				AdjList[i].clear();
			}

			for (int i = 0; i < V; i++) {
				readln();
				total_neighbors = nextInt();
				for (int j = 0; j < total_neighbors; j++) {
					id = nextInt();
					AdjList[i].add(new Edge(id, weight));
					indegree[id]++;
				}
			}

			l = readln();

			// COUNTING PATHS ON DAG

			initTopologicalSort(V);
			// for (int i = 0; i < V; i++) {
			// dbg(AdjList[i]);
			// }
			// dbg(topoSort);
			s = 0;// a menos que el problema diga otra cosa
			countingPathsOnDag();
			if (idCases != 0) {
				output.println();
			}
			idCases++;
			// dbg(num_paths);
			ways = 0;
			for (int i = 0; i < V; i++) {
				if (AdjList[i].size() == 0) {
					ways += num_paths[i];
				}
			}
			output.println(ways);
			if (l == null) {
				break;
			}
			// COUNTING PATHS ON DAG END
		}
		output.close();
	}

	// COUNTING PATHS ON DAG

	static int MAX_V = 1000000;// definirlo bn

	static int V, total_neighbors, id, weight, s, ways;
	static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];

	// TopoSort Stuff
	static ArrayList<Integer> topoSort = new ArrayList<Integer>(MAX_V);
	static int[] indegree = new int[MAX_V];
	// static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// (MAX_VERTICES); //para imprimir el toposort respetando el orden de la
	// //entrada
	static Queue<Integer> pDeg = new LinkedList<Integer>();

	static int[] num_paths = new int[MAX_V];

	// Topological sort Copiarlo de arriba completo

	private static void countingPathsOnDag() {
		Arrays.fill(num_paths, 0, V + 7, 0);
		num_paths[s] = 1;
		for (Integer cur: topoSort) {
			for (Edge e : AdjList[cur]) {
				num_paths[e.to] += num_paths[cur];
			}
		}
	}

	// COUNTING PATHS ON DAG END

	private static void initTopologicalSort(int V) {
		Arrays.fill(indegree, 0, V + 7, 0);

		pDeg.clear();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				pDeg.add(i);
			}
		}

		topologicalSort();
		// Mostrar el toposort
		// for (int i = 0; i < topoSort.size(); i++)
		// output.printf(" %d", topoSort.get(i));
		// output.printf("\n");
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

	// ClASES

	static class Edge {
		Integer to, cost;

		public Edge(Integer toIn, Integer costIn) {
			to = toIn;
			cost = costIn;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", cost=" + cost + "]";
		}

	}

	// ClASES END

}
