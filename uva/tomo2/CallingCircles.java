package uva.tomo2;

//Uva 247.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class CallingCircles {
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

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("CallingCircles"));

		int M, a, b, caseId = 0;

		while (true) {
			readln();
			V = nextInt();
			M = nextInt();

			map.clear();
			invMap.clear();
			AdjList = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				AdjList[i] = new LinkedList<Edge>();
			}
			if (V == 0 && M == 0) {
				break;
			}

			// mapConInv("");
			for (int i = 0; i < M; i++) {
				readln();
				a = mapConInv(next());
				b = mapConInv(next());
				AdjList[a].add(new Edge(b, 0));
			}

			if (caseId > 0) {
				output.println();
			}
			output.printf("Calling circles for data set %d:\n", ++caseId);

			initTarjanSCC(V);
		}

		output.close();

	}

	private static int mapConInv(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			invMap.put(idx, next);
		}
		return idx;
	}

	static int V, total_neighbors, id, weight;
	static LinkedList<Edge>[] AdjList;
	static final int DFS_WHITE = -1; // normal DFS
	static int[] dfs_num; // global es para recursion
	static int[] dfs_low;
	static int numComp;
	static int dfsNumberCounter;
	static Stack<Integer> S; // additional global variables
	static boolean[] visited;

	static HashMap<String, Integer> map = new HashMap<String, Integer>(26);
	static HashMap<Integer, String> invMap = new HashMap<Integer, String>(26);

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
		int qu;
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
			qu = 0;
			while (true) {
				int v = S.pop();
				visited[v] = false;
				qu++;
				if (qu > 1) {
					output.printf(", %s", nombre(v));
				} else {
					output.printf("%s", nombre(v));
				}

				if (u == v)
					break;
			}
			output.printf("\n");
		}
	}

	private static String nombre(int v2) {
		String n = invMap.get(v2);
		if (n == null) {
			return "";
		}
		return n;
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
		return "pair [to=" + to + ", _second=" + value + "]";
	}
}
