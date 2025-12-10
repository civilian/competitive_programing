package uva.tomo3;

/*uva 315*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class Network {
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
		// input=new BufferedReader(new FileReader("Network"));

		for (;;) {
			int critical = 0;

			readln();
			int V = nextInt();
			if (V == 0)
				break;
			V++;

			AdjList = new LinkedList[V];
			for (int i = 0; i < AdjList.length; i++)
				AdjList[i] = new LinkedList<pair<Integer, Integer>>();

			papa: while (true) {
				readln();
				int in = nextInt();
				if (in == 0)
					break papa;

				while (_stk.hasMoreTokens()) {
					int to = nextInt();
					AdjList[in].add(new pair<Integer, Integer>(to, 1));
					AdjList[to].add(new pair<Integer, Integer>(in, 1));
				}
			}

			// ARTICULATION POINTS
			// printThis("Articulation Points & Bridges (the input graph must be UNDIRECTED)");
			dfs_num = new int[V];
			dfs_parent = new int[V];
			dfs_low = new int[V];
			articulation_vertex = new boolean[V];
			dfsNumberCounter = 0;
			Arrays.fill(dfs_num, DFS_WHITE);
			Arrays.fill(dfs_low, 0);
			Arrays.fill(dfs_parent, -1);
			Arrays.fill(articulation_vertex, false);
			for (int i = 1; i < V; i++)
				if (dfs_num[i] == DFS_WHITE) {
					dfsRoot = i;
					rootChildren = 0;
					articulationPointAndBridge(i);
					// special case
					articulation_vertex[dfsRoot] = (rootChildren > 1);
				}

			for (int i = 0; i < V; i++)
				if (articulation_vertex[i])
					critical++;
			// ARTICULATION POINTS END

			System.out.println(critical);
		}

	}

	static LinkedList<pair<Integer, Integer>>[] AdjList;

	static final int DFS_WHITE = -1; // normal DFS, do not change this with
										// other values (other than 0), because
										// we usually use memset with
										// conjunction with DFS_WHITE
	static final int DFS_BLACK = 1;
	static int[] dfs_num; // this variable has to be global, we cannot put it in
							// recursion
	static int numCC;
	static int[] dfs_parent; // to differentiate real back edge versus
								// bidirectional edge
	static int[] dfs_low; // additional information for articulation
							// points/bridges/SCCs
	static boolean[] articulation_vertex;
	static int dfsNumberCounter;

	static int dfsRoot;

	static int rootChildren;

	static void articulationPointAndBridge(int u) {
		// dfs_low[u] <= dfs_num[u]
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
		for (int j = 0; j < (int) AdjList[u].size(); j++) {
			pair<Integer, Integer> v = AdjList[u].get(j);
			if (dfs_num[v._first] == DFS_WHITE) {// a tree edge
				dfs_parent[v._first] = u;
				// special case, count children of root
				if (u == dfsRoot)
					rootChildren++;
				articulationPointAndBridge(v._first);
				// for articulation point
				if (dfs_low[v._first] >= dfs_num[u])
					// store this information first
					articulation_vertex[u] = true;

				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v._first]);
			}
			// a back edge and not direct cycle
			else if (v._first != dfs_parent[u])
				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v._first]);
		}
	}

}

// ES NECESARIO
class pair<X, Y> { // utilizing Java "Generics"
	X _first;
	Y _second;

	@Override
	public String toString() {
		return "pair [_first=" + _first + ", _second=" + _second + "]";
	}

	public pair(X f, Y s) {
		_first = f;
		_second = s;
	}

	X first() {
		return _first;
	}

	Y second() {
		return _second;
	}

	void setFirst(X f) {
		_first = f;
	}

	void setSecond(Y s) {
		_second = s;
	}
}
