package uva.tomo100;
//uva 10004.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;;

public class Bicoloring {
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Bicoloring"));

		while (true) {
			readln();
			V = nextInt();
			if (V == 0) {
				break;
			}
			AdjList = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				AdjList[i] = new LinkedList<Edge1>();
			}

			readln();
			edges = nextInt();
			for (int i = 0; i < edges; i++) {
				readln();
				a = nextInt();
				b = nextInt();
				AdjList[a].add(new Edge1(b, 0));
				AdjList[b].add(new Edge1(a, 0));
			}

			BFSBipSimple(0);

			if (isBipartite) {
				System.out.println("BICOLORABLE.");
			} else {	
				System.out.println("NOT BICOLORABLE.");
			}

		}
	}

	static int V, E, a, b, edges;

	static LinkedList<Edge1>[] AdjList;
	static boolean isBipartite;

	public static void BFSBipSimple(int s) {

		int[] dist = new int[V];
		Arrays.fill(dist, -1); // to store parent information
		dist[s] = 0; // distance to source is 0 (default)
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.offer(s);// start from source
		isBipartite = true; // un boolean para ver bipartite

		while (!q.isEmpty()) {
			int u = q.poll();// queue: layer by layer!
			// if(u==fin) return respuesta;//para cuando se esta buscando una
			// solucion
			// reverseMapper maps index to actual vertex label
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge1 v = AdjList[u].get(j); // for each neighbors of u
				if (dist[v.to] == -1) {
					dist[v.to] = 1 - dist[u];// solo le pongo el color
					q.offer(v.to);
				} else if (dist[v.to] == dist[u])
					isBipartite = false;
			}
		}
	}
}

class Edge1 {
	Integer to, value;

	public Edge1(Integer f, Integer s) {
		to = f;
		value = s;
	}
}
