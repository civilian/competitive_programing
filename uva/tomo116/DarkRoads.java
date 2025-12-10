package uva.tomo116;

//Uva 11631.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class DarkRoads {
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
			EdgeList.clear();
			costoTot = 0;
			for (int i = 0; i < E; i++) {
				readln();
				u = nextInt();
				v = nextInt();
				w = nextInt();
				EdgeList.add(new EdgeL(u, v, w));

				costoTot += w;
			}
			kruskal();
		}

	}

	// KRUSKALL
	static int V, E, u, v, w, costoTot;
	static int MAX_VERTEX=200001;
	static ArrayList<EdgeL> EdgeList = new ArrayList<EdgeL>(MAX_VERTEX);
	static int mst_cost = 0;

	private static void kruskal() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (EdgeL front : EdgeList) { // for each edge, O(E)
			if (!isSameSet(front.from, front.to)) { // if no cycle
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
//			if (sizeOfSet(front.from) == V) {// ya meti a todos
//				break;
//			}
		} // note: the runtime cost of UFDS is very light
		System.out.printf("%d\n", costoTot - mst_cost);

	}

	// Union-Find
	static int[] pset, setSize, rank;

	static void initSet(int N) {
		rank = new int[N];
		setSize = new int[N];
		Arrays.fill(setSize, 1);
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
		// TODO: hacerle pruebas
		int xroot = findSet(i), yroot = findSet(j);
		if (xroot == yroot)
			return;
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
	int from, to, cost;

	public int compareTo(EdgeL e) {
		return cost - e.cost;
	}

	public EdgeL(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "(from=" + from + ", to=" + to + ", cost=" + cost + ")";
	}
}
