package uva.tomo110;

//Uva 11060.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

import java.util.StringTokenizer;

public class Beverages {
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
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("Beverages"));

		int caseId = 0;
		while (true) {
			if(caseId!=0)
				System.out.println();
			if (readln() == null)
				break;
			
			V = nextInt();

			// creo el grafo
			AdjList = new LinkedList[V];
			for (int i = 0; i < AdjList.length; i++)
				AdjList[i] = new LinkedList<Edge>();

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

				AdjList[iB1].add(new Edge(iB2, 0));
				indegree[iB2]++;
			}

			topologicalSort();

			StringBuilder bd = new StringBuilder("");
			if (topoSort.isEmpty()) {
				bd.append(" ");
			}
			while (!topoSort.isEmpty()) {
				bd.append(inverseMap.get(topoSort.poll()) + " ");
			}
			System.out
					.printf("Case #%d: Dilbert should drink beverages in this order: %s.\n",
							++caseId, bd.substring(0, bd.length() - 1));
			
			readln();
		}

		// primero la lectura de el grafo.
	}

	static int V, total_neighbors, id, weight;
	static LinkedList<Edge>[] AdjList;

	static int edges;// solo lo dan en este problema
	static int[] indegree = new int[101];
	static LinkedList<Integer> topoSort; // global vector to store the toposort

	static void topologicalSort() {
		topoSort = new LinkedList<Integer>();
		boolean[] bools = new boolean[V];
		int to;
		for (int i = 0; i < V; i++) {
			if (!bools[i] && indegree[i] == 0) {
				bools[i] = true;
				topoSort.add(i);
				for (int j = 0; j < AdjList[i].size(); j++) {
					to = AdjList[i].get(j).to;
					indegree[to]--;
				}
				i = -1;
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

class Edge {
	Integer to, value;

	public Edge(Integer toIn, Integer valueIn) {
		to = toIn;
		value = valueIn;
	}

	@Override
	public String toString() {
		return "pair [_first=" + to + ", _second=" + value + "]";
	}

}
