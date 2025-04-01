package uva.tomo114;
//Uva 11492.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Babel {
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("Babel"));

		AdjList = new ArrayList[4000 + 7];
		for (int i = 0; i < AdjList.length; i++) {
			AdjList[i] = new ArrayList<Edge>();
		}

		while (true) {
			map.clear();
			readln();
			E = nextInt();// 1027, 1048 en pruebas 21 m
			if (E == 0) {
				break;
			}
			readln();
			s = mapear(next());
			destini = mapear(next());
			for (int i = 0; i < (2 * E) + 7; i++) {
				AdjList[i].clear();
			}

			for (int i = 0; i < E; i++) {
				readln();
				u = mapear(next());
				v = mapear(next());
				word = next();
				w = word.length();
				letter = word.charAt(0);

				AdjList[u].add(new Edge(v, w, letter));
				AdjList[v].add(new Edge(u, w, letter));
			}
			V = map.size();
			minLenght = INF;
			dikstra(s, destini);
			if (minLenght < INF) {
				System.out.println(minLenght);
			} else {
				System.out.println("impossivel");
			}
		}
	}

	// DIJKSTRA HEAP

	static int V, E, u, v, s, w, destini, minLenght;
	static char letter;
	static ArrayList<Edge>[] AdjList;
	static final int INF = 1000000000;
	static int dist[][];
	static int p[];
	static HashMap<String, Integer> map = new HashMap<String, Integer>(4000 + 7);
	static HashMap<Integer, String> reverseMapper = new HashMap<Integer, String>(
			4000 + 7);
	static String word;
	static PriorityQueue<State> pq = new PriorityQueue<State>(6000);

	static int dikstra(int inicio, int fin) {
		s = inicio;
		pq.clear();
		pq.add(new State(s, 0, (char) ('z' +1)));

		dist = new int[V][('z' + 2) - 'a'];//tiene que entrar el 'z'+1
		for (int i = 0; i < V; i++) {
			Arrays.fill(dist[i], INF);
		}
		

		p = new int[V];
		dist[s][('z'+1) - 'a'] = 0;
		p[s] = -1;

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			// if(cur.node==fin) return cur.cost;//cuando solo quiero saber
			// el minimo costo de llegar aqui sin
			// los demas, PERO SOLO SIRVE PARA COSTOS POSITIVOS
			if (cur.node == destini) {
				minLenght = Math.min(cur.cost, minLenght);
			}
			if (dist[cur.node][cur.letter - 'a'] == cur.cost) {
				for (Edge e : AdjList[cur.node]) {
					if (cur.letter != e.letter
							&& dist[cur.node][cur.letter - 'a'] + e.cost < dist[e.to][e.letter - 'a']) {
						
						dist[e.to][e.letter - 'a'] = dist[cur.node][cur.letter - 'a']
								+ e.cost;
						p[e.to] = cur.node;
						pq.add(new State(e.to, cur.cost + e.cost, e.letter));
					}
				}
			}
		}
		return -1;
	}

	static void printPath(int u) {
		if (u==-1) {
			return;
		}
		if (u == s) {
			System.out.printf("%s", reverseMapper.get(u));
			return;
		}
		printPath(p[u]);// recursive call: to make the output format: s -> ...
						// -> t
		System.out.printf(" %s", reverseMapper.get(u));
	}

	static int mapear(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			reverseMapper.put(idx, next);
		}
		return idx;
	}

	// DIJKSTRA HEAP END
	static class State implements Comparable<State> {
		int node;
		int cost;
		char letter;

		public State(int node, int cost, char letter) {
			this.node = node;
			this.cost = cost;
			this.letter = letter;
		}

		public int compareTo(State o) {
			return cost - o.cost;
		}

		@Override
		public String toString() {
			return "State [node=" + node + ", cost=" + cost + ", letter="
					+ letter + "]";
		}

	}

	static class Edge {
		int to, cost;
		char letter;

		public Edge(int to, int cost, char letter) {
			this.to = to;
			this.cost = cost;
			this.letter = letter;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", value=" + cost + ", letter=" + letter
					+ "]";
		}

	}
}
