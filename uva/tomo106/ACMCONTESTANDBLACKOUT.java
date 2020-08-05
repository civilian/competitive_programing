package uva.tomo106;
//Uva 10600.
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
import java.util.Locale;
import java.util.StringTokenizer;

public class ACMCONTESTANDBLACKOUT {
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

//	static void dbg(Object... o) {
//		output.println(Arrays.deepToString(o));
//	}
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("ACMCONTESTANDBLACKOUT"));
		readln();
		int testCases=nextInt();
		for (int idCases = 0; idCases < testCases; idCases++) {
			readln();
			V=nextInt();
			E=nextInt();
			EdgeList.clear();
			for (int i = 0; i < E; i++) {
				readln();
				EdgeList.add(new EdgeL(nextInt()-1, nextInt()-1, nextInt()));
			}
			kruskalSecondBestInit();
		}
		output.close();
	}

	// KRUSKALL SECOND BEST

	static int V, E, u, v, w;
	static int MAX_VERTEX = 100;// depende del problema
	static ArrayList<EdgeL> EdgeList=new ArrayList<EdgeL>(MAX_VERTEX*MAX_VERTEX +2);
	static int mst_cost = 0;
	static ArrayList<EdgeL> mst = new ArrayList<EdgeL>(MAX_VERTEX);
	static EdgeL noUsar;
	static int secondMin;

	private static void kruskalSecondBestInit() {
		mst.clear();
		Collections.sort(EdgeList); // ordeno 1 vez
		noUsar = null;
		kruskalSecondBest();
		output.printf("%d", mst_cost);
		secondMin=1000000000;
		for (EdgeL e : mst) {
			noUsar = e;
			kruskalSecondBest();
			if (_numDisjointSets <= 1) {
				secondMin = Math.min(secondMin, mst_cost);
			}
		}
		output.printf(" %d\n", secondMin);
	}

	private static void kruskalSecondBest() {
		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < EdgeList.size(); i++) { // tambien se puede
													// conecciones < vertex-1
			// y meter conecciones++ en el if
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to) && !front.equals(noUsar)) {
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
				if (noUsar == null) {
					mst.add(front);
				}
			}
			// En casos especiales se puede terminar la ejecucion cuando se
			// tengan todos los vertices
			if (_numDisjointSets <= 1) {
				break;// Mirar si los componentes deseados ya estan o hay menos
			}
		} // note: the runtime cost of UFDS is very light
	}

	// Union-Find
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

	// KRUSKALL SECOND BEST END

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
