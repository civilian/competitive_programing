package maratonBook.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/*
 4 1 4 5
 1 4 1 10
 1 3 3 10
 3 4 4 10
 1 2 2 10
 2 4 5 10
 20 10*/
public class MaxFlow_MinCost {

	private static void dbg(Object...objects) {
		System.out.println(Arrays.deepToString(objects));
	}
	
	public static void main(String[] args) {
		// read a graph into cap[][]
		int from, to, c, costo;
		for (int i = 0; i < MAX_V; i++) {
			deg[i] = 0;
			for (int j = 0; j < MAX_V; j++) {
				cap[i][j] = 0;
			}
		}
		Scanner sc = new Scanner(System.in);
		int E;
		V = sc.nextInt();
		s = sc.nextInt();
		t = sc.nextInt();
		E = sc.nextInt();
		for (int i = 0; i < E; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			costo = sc.nextInt();
			c = sc.nextInt();
			cap[from][to] = c;
			cost[from][to] = costo;

			cap[to][from] = c;
			cost[to][from] = costo;

			Adj[from][deg[from]++] = to;
			Adj[to][deg[to]++] = from;// para mantener la correctitud siempre
										// conectar asi, la direcion la da cap
		}

		edmonKarp();
	}

	static int MAX_V = 1024; // define MAX_V appropriately
	static int mf, flow, s, t, V, E;
	static int INF = 1000000000;

	// we need these global variables
	static int[][] cap = new int[MAX_V][MAX_V], Adj = new int[MAX_V][MAX_V],
			cost = new int[MAX_V][MAX_V];

	static int[] p = new int[MAX_V], deg = new int[MAX_V],
			dist = new int[MAX_V];

	static PriorityQueue<State> pq = new PriorityQueue<State>(MAX_V);

	static int edmonKarp() {
		mf = 0;
		while (true) { // run O(VE^2) Edmonds Karp to solve the Max Flow problem
			flow = 0;

			pq.clear();
			pq.add(new State(s, 0));

			Arrays.fill(dist, INF);
			Arrays.fill(p, -1);

			dist[s] = 0;
			p[s] = -1;

			while (!pq.isEmpty()) {
				State cur = pq.poll();
				if (cur.node == t)
					break;// cuando solo quiero saber
				// el minimo costo de llegar aqui sin
				// los demas, PERO SOLO SIRVE PARA COSTOS POSITIVOS

				if (dist[cur.node] == cur.cost) {
					for (int i = 0, e; i < deg[cur.node]; i++) {
						e = Adj[cur.node][i];
						if (cap[cur.node][e] != 0
								&& dist[cur.node] + cost[cur.node][e] < dist[e]) {
							dist[e] = dist[cur.node] + cost[cur.node][e];
							p[e] = cur.node;
							pq.add(new State(e, cur.cost + cost[cur.node][e]));
						}
					}
				}
			}

			augment(t, INF); // find the min edge weight `f' along this path, if
								// any
			if (flow == 0)
				break; // if we cannot send any more flow (`f' = 0), terminate
						// the loop
			mf += flow; // we can still send a flow, increase the max flow!
		}

		System.out.printf("%d\n", mf); // this is the max flow value of this
										// flow graph
		return mf;

	}

	private static void augment(int v, int minEdge) { // traverse the BFS
		// spanning tree as in
		// print_path (section
		// 4.3)
		if (v == s) {
			flow = minEdge;
			return;
		} // reach the source, record minEdge in a global variable `f'
		else if (p[v] != -1) {
			augment(p[v], Math.min(minEdge, cap[p[v]][v])); // recursive
			// call
			cap[p[v]][v] -= flow;
			cap[v][p[v]] += flow;
		} // alter residual capacities
	}

	// CLASES: EDMON KARP
	static class Edge {
		Integer to;

		public Edge(Integer to) {
			this.to = to;
		}

	}

	static class State implements Comparable<State> {
		int node;
		int cost;

		public State(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		public int compareTo(State o) {
			return cost - o.cost;
		}

		@Override
		public String toString() {
			return "State [node=" + node + ", cost=" + cost + "]";
		}

	}

	// CLASES: EDMON KARP END

}
