package libroCompetitive;

import java.io.*;
import java.util.*;

public class e4_4_3_2 {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("grafo.in"));

		readln();
		N = nextInt();
		grafo = new LinkedList[N];
		for (int i = 0; i < N; i++)
			grafo[i] = new LinkedList<Edge>();
		readln();
		int E = nextInt();
		for (int i = 0; i < E; i++) {
			readln();
			int from = nextInt(), to = nextInt(), cost = nextInt();
			grafo[from].add(new Edge(to, cost));
		}

		dijkstra(0);

		System.out.println(minDist[5]);
		System.out.println(Arrays.toString(minDist));
		System.out.println(Arrays.toString(prev));
		// int res = costoUniforme(0, 5);
		// System.out.println(res);
	}

	static final int INF = 1000000000;
	static int minDist[];
	static boolean visited[];
	static int prev[];

	static void dijkstra(int inicio) {
		minDist = new int[N];
		visited = new boolean[N];
		prev = new int[N];

		Arrays.fill(minDist, INF);
		minDist[inicio] = 0;
		prev[inicio] = -1;

		for (int j = 0; j < N; j++) {
			int idx = -1;
			for (int i = 0; i < N; i++)
				if (!visited[i]) {
					if (idx == -1 || minDist[idx] > minDist[i])
						idx = i;// encuentro la mas pequeña distancia de todas
				}

			/*
			 * if(minDist[idx]==INF) { return -1; }
			 */

			visited[idx] = true;
			for (Edge e : grafo[idx]) {
				if (minDist[idx] + e.cost < minDist[e.to]) {
					minDist[e.to] = minDist[idx] + e.cost;
					prev[e.to] = idx;
				}
			}
		}

	}

	static int N;
	static LinkedList<Edge> grafo[];

	static class Edge {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
