package uva.tomo7;

//Uva 796.
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class CriticalLinks {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("CriticalLinks"));
		while (true) {
			if (readln() == null)
				break;

			V = nextInt();
			AdjList = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				AdjList[i] = new LinkedList<Edge>();
			}

			for (int i = 0; i < V; i++) {
				readln();

				desde = nextInt();
				qTmp = next();
				qTmp = qTmp.substring(1);
				qTmp = qTmp.substring(0, qTmp.indexOf(')'));
				neighbors = Integer.parseInt(qTmp);
				for (int j = 0; j < neighbors; j++) {
					AdjList[desde].add(new Edge(nextInt(), 0));
				}
			}

			bridges.clear();
			initArticulationPointAndBridge(V);

			System.out.println(bridges.size() + " critical links");
			Comparator<Point> comp = new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if (o1.x == o2.x) {
						return o1.y - o2.y;
					} else {
						return o1.x - o2.x;
					}
				}
			};

			Collections.sort(bridges, comp);

			for (Point p : bridges) {
				System.out.printf("%d - %d\n", p.x, p.y);
			}
			System.out.println();
			readln();
		}

	}

	static LinkedList<Point> bridges = new LinkedList<Point>();// puede ser mas

	static String qTmp;
	static int V, neighbors, desde, hasta;
	static LinkedList<Edge>[] AdjList;
	static final int DFS_WHITE = -1; // normal DFS
	static final int DFS_BLACK = 1;
	static int[] dfs_num;
	static int[] dfs_parent; // to differentiate real back edge versus
	static int[] dfs_low; // additional information for articulation
							// points/bridges/SCCs
	static int dfsNumberCounter;

	static void initArticulationPointAndBridge(int V) {
		dfs_num = new int[V];
		dfs_parent = new int[V];
		dfs_low = new int[V];
		dfsNumberCounter = 0;
		Arrays.fill(dfs_num, DFS_WHITE);
		Arrays.fill(dfs_low, 0);
		Arrays.fill(dfs_parent, -1);
		for (int i = 1; i < V; i++) {
			if (dfs_num[i] == DFS_WHITE) {
				articulationPointAndBridge(i);
			}
		}
	}

	static void articulationPointAndBridge(int u) {
		// dfs_low[u] <= dfs_num[u]
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
		for (int j = 0; j < (int) AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);
			if (dfs_num[v.to] == DFS_WHITE) {// a tree edge
				dfs_parent[v.to] = u;
				articulationPointAndBridge(v.to);

				if (dfs_low[v.to] > dfs_num[u])// for bridge
					addBridge(u, v.to);
				// System.out.printf(" Edge (%d, %d) is a bridge\n", u, v.to);
				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v.to]);
			}
			// a back edge and not direct cycle
			else if (v.to != dfs_parent[u])
				// update dfs_low[u]
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v.to]);
		}
	}

	static void addBridge(int u, Integer to) {
		if (u > to) {
			bridges.add(new Point(to, u));
		} else {
			bridges.add(new Point(u, to));
		}

	}
}

class Edge implements Comparable {
	Integer to, value;

	public Edge(Integer toIn, Integer valueIn) {
		to = toIn;
		value = valueIn;
	}

	@Override
	public String toString() {
		return "pair [to=" + to + ", _second=" + value + "]";
	}

	@Override
	public int compareTo(Object o) {
		if (this.first() != ((Edge) o).first())
			return this.first() - ((Edge) o).first();
		else
			return this.second() - ((Edge) o).second();
	}

	Integer first() {
		return to;
	}

	Integer second() {
		return value;
	}
}
