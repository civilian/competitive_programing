package uva.tomo4;

//Uva 452.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProjectScheduling {
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
	// ouput.println(Arrays.deepToString(o));
	// }

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("ProjectScheduling"));

		for (int i = 0; i < MAX_V; i++) {
			AdjList[i] = new ArrayList<Edge>();
		}
		readln();
		int testCases = nextInt();
		readln();
		for (int idCases = 0; idCases < testCases; idCases++) {
			for (int i = 0; i < MAX_V; i++) {
				AdjList[i].clear();
			}
			map.clear();
			Arrays.fill(indegree, 0);
			Arrays.fill(times, 0);
			while (true) {
				l = readln();
				if (l == null || l.equals("")) {
					break;
				}
				if (!_stk.hasMoreTokens()) {
					break;
				}
				to = map(next().charAt(0));
				cost = nextInt() * -1;
				times[to] = cost;
				while (_stk.hasMoreTokens()) {
					l = next();
					for (Character c : l.toCharArray()) {
						from = map(c);
						AdjList[from].add(new Edge(to));
						indegree[to]++;
					}

				}
			}
			V = map.size();
			initTopologicalSort(V);
			// dbg(topoSort);
			// dbg(map);
			// dbg(AdjList);
			s = topoSort.get(0);
			SSShortestPathOnDag();
			// dbg(dist);
			max = INF;
			for (int i = 0; i < topoSort.size(); i++) {
				max = Math.min(max, dist[i]);
			}
			if (idCases != 0) {
				output.println();
			}
			output.println(-max);
		}
		output.close();
	}

	static int max;

	private static int map(char charAt) {
		Integer idx = map.get(charAt);
		if (idx == null) {
			idx = map.size();
			map.put(charAt, idx);
		}
		return idx;
	}

	// SINGLE SOURCE SHORTEST/LONGES PATH ON DAG

	static int MAX_V = 27 + 7;// DEPENDE DEL PROBLEMA

	static String l;
	static HashMap<Character, Integer> map = new HashMap<Character, Integer>(
			MAX_V);
	static int V, total_neighbors, id, weight, to, from, cost;
	static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];

	// TopoSort Stuff
	static LinkedList<Integer> topoSort = new LinkedList<Integer>(); // global
																		// vector
																		// to
																		// store
																		// the
	static int[] indegree = new int[MAX_V], times = new int[MAX_V];
	// static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// (MAX_VERTICES); //para imprimir el toposort
	// // respetando el orden de la entrada
	static Queue<Integer> pDeg = new LinkedList<Integer>();

	// Sssp
	static int[] dist = new int[MAX_V];
	static int INF = 1000000000;
	static int s;

	private static void SSShortestPathOnDag() {
		Arrays.fill(dist, 0, V + 7, INF);
		dist[s] = times[s];
		for (Integer i : topoSort) {
			// dbg();
			// dbg(i);
			for (Edge e : AdjList[i]) {
				// dbg("e", e);
				if (dist[e.to] > dist[i] + times[e.to]) {
					dist[e.to] = dist[i] + times[e.to];
				}
			}
		}

	}

	private static void initTopologicalSort(int V) {

		// creo el grafo
		// AdjList = new ArrayList[V];
		// for (int i = 0; i < AdjList.length; i++)
		// AdjList[i] = new ArrayList<Edge>(V);

		pDeg.clear();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				pDeg.add(i);
			}
		}

		topologicalSort();
		// Mostrar el toposort
		// for (int i = 0; i < topoSort.size(); i++)
		// ouput.printf(" %d", topoSort.get(i));
		// ouput.printf("\n");
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
		Integer to;

		public Edge(Integer to) {
			this.to = to;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + "]";
		}
	}

	// ClASES END

}
