package uva.tomo100;
//uva 10004.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class BicoloringDFS {
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("Bicoloring"));

		while (true) {
			readln();
			V = nextInt();
			if (V == 0) {
				break;
			}
			AdjList = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				AdjList[i] = new LinkedList<Edge>();
			}

			readln();
			edges = nextInt();
			for (int i = 0; i < edges; i++) {
				readln();
				a = nextInt();
				b = nextInt();
				AdjList[a].add(new Edge(b, 0));
				AdjList[b].add(new Edge(a, 0));
			}

			initDFS(V);
			DFS(0);

			if (isBipartite) {
				System.out.println("BICOLORABLE.");
			} else {	
				System.out.println("NOT BICOLORABLE.");
			}

		}
	}

	static int V, E, a, b, edges;

	static LinkedList<Edge>[] AdjList;
	static boolean isBipartite;
	static int total_neighbors,  weight;
	static final int DFS_WHITE = -1;
	static final int DFS_BLACK = 1;
	static int[] dfs_num;
	static int[] dist;
	static int numComp;

	private static void initDFS(int V) { // used in normal DFS
		isBipartite=true;
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
		dist = new int[V];
		numComp = 0;
	}

	public static void DFS(int u) {
		dfs_num[u] = DFS_BLACK;

		for (int j = 0; j < AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);

			if (dfs_num[v.to] == DFS_WHITE) {
				dist[v.to] = 1 - dist[u];
				DFS(v.to);
			} else if (dist[v.to] == dist[u]) {
				isBipartite = false;
			}
		}
	}
}

class Edge {
	Integer to, value;

	public Edge(Integer f, Integer s) {
		to = f;
		value = s;
	}
}
