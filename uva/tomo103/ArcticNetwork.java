package uva.tomo103;
//Uva 10369.
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

public class ArcticNetwork {
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
		input = new BufferedReader(new FileReader("ArcticNetwork"));
		readln();
		int testCases = nextInt();
		for (int i = 0; i < testCases; i++) {
			readln();
			S = nextInt();
			V = nextInt();
			outpost.clear();
			map.clear();
			EdgeList.clear();
			for (int j = 0; j < V; j++) {
				readln();
				outpost.add(new Point(nextInt(), nextInt()));
			}

			annadirVertices();
			kruskal();
		}
		output.close();
	}

	static double dist;

	private static void annadirVertices() {
		for (int i = 0; i < V; i++) {
			for (int j = i + 1; j < V; j++) {
				u = mappear(outpost.get(i));
				v = mappear(outpost.get(j));
				dist = outpost.get(i).distance(outpost.get(j));
				EdgeList.add(new EdgeL(u, v, dist));
			}
		}
	}

	private static int mappear(Point point) {
		Integer idx = map.get(point);
		if (idx == null) {
			idx = map.size();
			map.put(point, idx);
		}
		return idx;
	}

	static ArrayList<Point> outpost = new ArrayList<Point>(501);
	static HashMap<Point, Integer> map = new HashMap<Point, Integer>(501);
	// KRUSKALL
	static int V, E, u, v, w;
	static int S;
	static int MAX_VERTEX = 501;// depende del problema
	static ArrayList<EdgeL> EdgeList = new ArrayList<EdgeL>(500 * 500);
	static int mst_cost = 0;

	private static void kruskal() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		double max = 0.0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			if (_numDisjointSets <= S) {
				break;
			}
			// y meter conecciones++ en el if
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to)) { // if no cycle
				max = Math.max(max, front.cost);
				unionSet(front.from, front.to); // link endpoints
			}
		} // note: the runtime cost of UFDS is very light
		output.printf("%.2f\n", max);

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