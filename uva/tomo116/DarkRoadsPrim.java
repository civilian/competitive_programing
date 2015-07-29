package uva.tomo116;

//Uva 11631.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DarkRoadsPrim {
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
		 input = new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("DarkRoads"));
		while (true) {
			readln();

			V = nextInt();
			E = nextInt();
			if (V == 0 && E == 0) {
				break;
			}
			AdjList = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				Adj[i] = new ArrayList<Edge>(V);
			}
			costoTot = 0;
			for (int i = 0; i < E; i++) {
				readln();
				to = nextInt();
				from = nextInt();
				E = nextInt();
				Adj[from].add(new Edge(to, E));
				Adj[to].add(new Edge(from, E));

				costoTot += E;
			}
			prim();
		}

	}

	// PRIM
	static int V, E, u, v, w, costoTot;
	static int MAX_EDGES = 200001;// depende del problema
	static int mst_cost = 0;
	static ArrayList<Edge>[] AdjList;
	static PriorityQueue<Edge> pq=new PriorityQueue<Edge>(MAX_EDGES); // priority queue to help
	static boolean[] taken; // global boolean flag to avoid cycleF

	static void process(int vtx) {
		taken[vtx] = true;
		for (int j = 0; j < AdjList[vtx].size(); j++) {
			Edge v = Adj[vtx].get(j); // weight //id
			if (!taken[v.to])
				pq.add(new Edge(v.to, v.cost));
		}
	}

	private static void prim() {
		int conecciones=0;
		pq.clear();
		taken=new boolean[V];
		// take vertex 0 and process all edges incident to vertex 0
		process(0);
		mst_cost = 0;
		while (!pq.isEmpty()) {
			// repeat until V vertices (E = V-1 edges) are taken
			Edge front = pq.poll();
			u = front.to;
			w = front.cost;
			if (!taken[u]) {// we have not connect this vertex yet
				// take u and process all edges incident to u
				mst_cost += w;
				conecciones++;
				if(conecciones==V-1)
					break;
				process(u);
			}
		}// each edge is in pq only once!
		System.out.printf("%d\n", costoTot - mst_cost);
	}

}

//class Edge implements Comparable<Edge>{
//	int to;
//	int cost;
//
//	public Edge(int to, int cost) {
//		this.to = to;
//		this.cost = cost;
//	}
//
//	@Override
//	public String toString() {
//		return "(" + to + ", cost=" + cost + ")";
//	}
//
//	@Override
//	public int compareTo(Edge o2) {
//		if (cost == o2.cost) {
//			return to - o2.to;
//		} else {
//			return cost - o2.cost;
//		}
//	}
//
//}
