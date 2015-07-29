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
import java.util.StringTokenizer;

public class Highways {
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
			readln();
			M = nextInt();
			initSet(V);
			for (int i = 0; i < M; i++) {
				readln();
				u = nextInt();
				v = nextInt();
				unionSet(u - 1, v - 1);
			}
			annadirVertices();
			kruskal();
		}
		output.close();
	}

	private static void annadirVertices() {
		EdgeList.clear();
		map.clear();
		for (int i = 0; i < V; i++) {
			for (int j = i + 1; j < V; j++) {

				int f = mapear(cities.get(i)), t = mapear(cities.get(j));

				if (isSameSet(f, t)) {
					continue;
				}

				double costo = cities.get(i).distance(cities.get(j));
				EdgeList.add(new EdgeL(t, f, costo));
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

	// KRUSKALL
	static ArrayList<Point> cities = new ArrayList<Point>(751);
	static int V, E, u, v, w;
	static int MAX_VERTEX = 751;// depende del problema
	static ArrayList<EdgeL> EdgeList = new ArrayList<EdgeL>((750 * 750) + 2);
	static int mst_cost = 0;

	private static void kruskal() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		mst_cost = 0;
		boolean build = false;
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			// y meter conecciones++ en el if
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to)) { // if no cycle
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
				output.printf("%d %d\n", front.to + 1, front.from + 1);
				build = true;
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
			if (_numDisjointSets == 1) {// ya meti a todos
				break;
			}
		} // note: the runtime cost of UFDS is very light
		if (!build) {
			output.println("No new highways need");
		}

	}

	// Union-Find
	static int[] pset, setSize, rank;
	static int _numDisjointSets;

	static void initSet(int N) {
		rank = new int[N];
		setSize = new int[N];
		Arrays.fill(setSize, 1);
		_numDisjointSets = N;
		pset = new int[N];
		for (int i = 0; i < N; i++) {
			pset[i] = i;
		}
	}

	static int findSet(int i) {
		return (pset[i] == i) ? i : (pset[i] = findSet(pset[i]));
	}

	static boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	static void unionSet(int i, int j) {
		int xroot = findSet(i), yroot = findSet(j);
		if (xroot == yroot)
			return;
		_numDisjointSets--;
		if (rank[xroot] < rank[yroot]) {
			pset[xroot] = yroot;
			setSize[yroot] += setSize[xroot];
		} else if (rank[xroot] > rank[yroot]) {
			pset[yroot] = xroot;
			setSize[xroot] += setSize[yroot];
		} else {
			pset[yroot] = xroot;
			rank[xroot]++;
			setSize[xroot] += setSize[yroot];
		}
	}

	static int sizeOfSet(int i) {
		return setSize[findSet(i)];
	}

	// KRUSKALL END

}

class EdgeL implements Comparable<EdgeL> {
	int from, to;
	Double cost;

	public int compareTo(EdgeL e) {
		return cost.compareTo(e.cost);
	}

	public EdgeL(int from, int to, Double cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "(from=" + from + ", to=" + to + ", cost=" + cost + ")";
	}
}
