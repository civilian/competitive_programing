package uva.tomo110;

//Uva 11060.
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
import java.util.PriorityQueue;

import java.util.StringTokenizer;

public class BeveragesTopoSortLibro {
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

	static void dbg(Object... o) {
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("Beverages"));

		int caseId = 0;
		while (true) {
			if (caseId != 0)
				output.println();
			if (readln() == null)
				break;

			V = nextInt();

			// creo el grafo
			AdjList = new ArrayList[V];
			for (int i = 0; i < AdjList.length; i++)
				AdjList[i] = new ArrayList<Edge1>();

			map.clear();
			inverseMap.clear();
			for (int i = 0; i < V; i++) {
				readln();
				String bebida = next();
				mapearConInverso(bebida);
				indegree[mapearConInverso(bebida)] = 0;// al mismo tiempo que
														// leo reinicio
			}

			readln();
			edges = nextInt();
			int iB1, iB2;
			String B1, B2;
			for (int i = 0; i < edges; i++) {
				readln();
				B1 = next();
				B2 = next();

				iB1 = map.get(B1);
				iB2 = map.get(B2);

				AdjList[iB1].add(new Edge1(iB2, 0));
				indegree[iB2]++;
			}

			pDeg.clear();
			for (int i = 0; i < V; i++) {
				if (indegree[i] == 0) {
					pDeg.add(i);
				}
			}
			topologicalSort();

			StringBuilder bd = new StringBuilder("");
			if (topoSort.isEmpty()) {
				bd.append(" ");
			}
			while (!topoSort.isEmpty()) {
				bd.append(inverseMap.get(topoSort.poll()) + " ");
			}
			output.printf(
					"Case #%d: Dilbert should drink beverages in this order: %s.\n",
					++caseId, bd.substring(0, bd.length() - 1));

			readln();
		}

		output.close();
		// primero la lectura de el grafo.
	}

	static int V, total_neighbors, id, weight;
	static ArrayList<Edge1>[] AdjList;

	static int edges;// solo lo dan en este problema
	static int[] indegree = new int[101];
	static PriorityQueue<Integer> pDeg = new PriorityQueue<Integer>(101);
	static LinkedList<Integer> topoSort = new LinkedList<Integer>(); // global
																		// vector
																		// to
																		// store
																		// the
																		// toposort

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

	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static HashMap<Integer, String> inverseMap = new HashMap<Integer, String>();

	private static int mapearConInverso(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			inverseMap.put(idx, next);
		}
		return idx;
	}
}

class Edge1 {
	Integer to, value;

	public Edge1(Integer toIn, Integer valueIn) {
		to = toIn;
		value = valueIn;
	}

	@Override
	public String toString() {
		return "pair [_first=" + to + ", _second=" + value + "]";
	}

}
