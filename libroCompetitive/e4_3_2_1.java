package libroCompetitive;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/* 
 5 7
 0 1 4
 0 2 4
 0 3 6
 0 4 6
 1 2 2
 2 3 8
 3 4 9
 */
public class e4_3_2_1 {

	public static void main(String[] args) {
		// Lectura
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		// format: weight, (two vertices of the edge)
		EdgeList = new LinkedList<EdgeL>();
		for (int i = 0; i < E; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt(); // read the triple: (a, b, w)
			EdgeList.addLast(new EdgeL(u, v, w));
		}
		// Lectura END
		
		kruskal();
	}

	// KRUSKALL
	static int V, E, u, v, w;
	static LinkedList<EdgeL> EdgeList;
	static boolean[] taken; // global boolean flag to avoid cycle
	static PriorityQueue<Edge> pq; // priority queue to help
									// choose shorter edges
	static int mst_cost = 0;

	private static void kruskal() {
		Collections.sort(EdgeList); // sort by edge weight in O(E log E)

		mst_cost = 0;
		initSet(V); // all V are disjoint sets initially
		for (int i = 0; i < E; i++) { // for each edge, O(E)
			EdgeL front = EdgeList.get(i);
			if (!isSameSet(front.from, front.to)) { // if no cycle
				mst_cost += front.cost; // add the weight of e to MST
				unionSet(front.from, front.to); // link endpoints
			}
			if (sizeOfSet(front.from) == V) {
				break;
			}
		} // note: the runtime cost of UFDS is very light

		// note: the number of disjoint sets must eventually be one for a valid
		// MST
		System.out.printf("MST cost = %d (Kruskal's)\n", mst_cost);

	}

	// Union-Find Disjoint Sets Library
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

	// KRUSKALL END
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
