package uva.tomo101;

//Uva 10147.
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HighwaysPrim {
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
		input = new BufferedReader(new FileReader("Highways"));
		int M;
		readln();
		int tesCases = nextInt();
		for (int idCases = 0; idCases < tesCases; idCases++) {

			if (idCases > 0) {
				output.println();
			}
			cities.clear();

			readln();
			readln();
			V = nextInt();
			for (int i = 0; i < V; i++) {
				readln();
				cities.add(new Point(nextInt(), nextInt()));
			}
			
			annadirVertices();
			readln();
			M = nextInt();
			mst_cost = 0;
			taken = new boolean[V];
			parcialyTaken = new boolean[V];
			for (int i = 0; i < M; i++) {
				readln();
				u = nextInt();
				v = nextInt();
				quienTome(u-1, v-1);
			}
			primMinimoParcial();
		}
		output.close();
	}

	private static void annadirVertices() {
		AdjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			AdjList[i] = new ArrayList<Edge>(V);
		}
		map.clear();
		for (int i = 0; i < V; i++) {
			for (int j = i; j < V; j++) {

				int from = mapear(cities.get(i)), to = mapear(cities.get(j));

				double costo = cities.get(i).distance(cities.get(j));
				AdjList[from].add(new Edge(to, costo));
				AdjList[to].add(new Edge(from, costo));
			}
		}
	}

	static HashMap<Point, Integer> map = new HashMap<Point, Integer>(751);

	private static int mapear(Point point) {
		Integer idx = map.get(point);
		if (idx == null) {
			idx = map.size();
			map.put(point, idx);
		}
		return idx;
	}

	// PRIM MAX
	static ArrayList<Point> cities = new ArrayList<Point>(751);
	static int V, E, u, v;
	static double w;
	static int MAX_EDGES = 200001;// depende del problema
	static int mst_cost = 0;
	static ArrayList<Edge>[] AdjList;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(MAX_EDGES);
	static boolean[] taken; // global boolean flag to avoid cycleF
	static boolean[] parcialyTaken; // global boolean flag to avoid cycleF

	private static void quienTome(int u, int v) {
		if (parcialyTaken[v] == false && parcialyTaken[v] == false) {
			parcialyTaken[u] = true;
			taken[u] = true;
			parcialyTaken[v] = true;
			process(u, true);
			process(v, false);
			return;
		}
		if (parcialyTaken[v]) {
			taken[v] = true;
			parcialyTaken[v] = true;
			process(v, true);
		}
		if (parcialyTaken[u]) {
			taken[u] = true;
			parcialyTaken[u] = true;

			process(u, true);
		}
	}

	static void process(int vtx, boolean take) {
		taken[vtx] = take;
		for (int j = 0; j < AdjList[vtx].size(); j++) {
			Edge v = AdjList[vtx].get(j); // weight //id
			if (!taken[v.to])
				pq.add(new Edge(v.to, v.cost));
		}
	}

	private static void primMinimoParcial() {
		pq = new PriorityQueue<Edge>(E + 1);
		// taken = new boolean[V];
		// mst_cost = 0;//se inicializan y cambian antes
		// take vertex 0 and process all edges incident to vertex 0
		process(0, true);
		boolean build = false;
		while (!pq.isEmpty()) {
			// repeat until V vertices (E = V-1 edges) are taken
			Edge front = pq.poll();
			u = front.to;
			w = front.cost;
			if (!taken[u]) {// we have not connect this vertex yet
				// take u and process all edges incident to u
				mst_cost += w;
				process(u, true);
				output.printf("%d\n", front.to + 1);
				build = true;
			}
		}// each edge is in pq only once!
//		System.out.printf("MST cost = %d (Prim's Minimo Parcial)\n", mst_cost);
		if (!build) {
			output.println("No new highways need");
		}
	}


}

class Edge implements Comparable<Edge> {
	int to;
	Double cost;

	public Edge(int to, Double cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "(" + to + ", cost=" + cost + ")";
	}

	@Override
	public int compareTo(Edge o2) {
		if (cost == o2.cost) {
			return to - o2.to;
		} else {
			return cost.compareTo(o2.cost);
		}
	}
}
