package uva.tomo2;

//Uva 259.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class SoftwareAllocation {
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
		input = new BufferedReader(new FileReader("SoftwareAllocation"));

		map.clear();
		invMap.clear();
		for (char i = 'A'; i <= 'Z'; i++) {
			map(i);
		}

		l1 = "";
		V = MX_V;
		while (l1 != null) {
			for (int i = 0; i < MX_V; i++) {
				deg[i] = 0;
				for (int j = 0; j < MX_V; j++) {
					cap[i][j] = 0;
					// Adj[i][j] = -1;// como porque si.
				}
			}

			for (int i = 27; i < t; i++) {
				Adj[i][deg[i]++] = t;
				Adj[t][deg[t]++] = i;
				cap[i][t] = 1;

			}

			totUsers = 0;
			while (true) {
				l1 = readln();
				// dbg(l1);
				if (l1 == null) {
					break;
				}
				if (l1.equals("")) {
					break;
				}

				apU = next();
				app = map(apU.charAt(0));
				totUsers += users = Integer.parseInt(apU.charAt(1) + "");
				cap[s][app] = users;
				Adj[s][deg[s]++] = app;
				Adj[app][deg[app]++] = s;
				comps = next();
				for (int i = 0; i < comps.length() - 1; i++) {
					pc = Integer.parseInt(comps.charAt(i) + "") + 27;
					cap[app][pc] = 1;
					Adj[app][deg[app]++] = pc;
					Adj[pc][deg[pc]++] = app;
				}
			}
			dinic();
			// dbg(flow);
			// for (int i = 0; i < Adj.length; i++) {
			// dbg(Adj[i]);
			// }
			// dbg("cap");
			// for (int i = 0; i < cap.length; i++) {
			// dbg(cap[i]);
			// }

			if (flow == totUsers) {
				for (int p = 27; p < 37; p++) {
					tiene = false;
					for (int a = 1; a < 27; a++) {
						if (cap[p][a] == 1) {
							output.printf("%c", invMap.get(a));// a?
							tiene = true;
						}
					}
					if (!tiene) {
						output.printf("_");
					}
				}
				output.println();
			} else {
				output.println("!");
			}

		}
		output.close();

	}

	static boolean tiene;

	private static int map(char i) {
		//
		Integer idx = map.get(i);
		if (idx == null) {
			idx = map.size() + 1;
			map.put(i, idx);
			invMap.put(idx, i);
		}
		return idx;
	}

	// DINIC MAX FLOW

	// the maximum number of vertices
	static int MX_V = 2 + ('Z' - 'A') + 7 + 10;// s t, aps, luckyxD, pcs

	// adjacency matrix (fill this up)
	// If you fill adj[][] yourself, make sure to include both u->v and v->u.
	static int[][] cap = new int[MX_V][MX_V], Adj = new int[MX_V][MX_V];
	static int[] deg = new int[MX_V];

	// BFS stuff
	static int[] q = new int[MX_V * MX_V], prev = new int[MX_V];// q es el
																// queue
	static int V, s = 0, t = 37, flow, app, pc, users, totUsers;
	static String apU, comps, l1;
	static HashMap<Character, Integer> map = new HashMap<Character, Integer>(
			MX_V);
	static HashMap<Integer, Character> invMap = new HashMap<Integer, Character>(
			MX_V);

	static int dinic() {
		flow = 0;

		while (true) {
			// find an augmenting path
			Arrays.fill(prev, -1);
			int qf = 0, qb = 0;// queue front y back
			prev[q[qb++] = s] = -2;
			while (qb > qf) {// q not empty, y no hemos llegado
								// a t
				for (int u = q[qf++], i = 0, v; i < deg[u]; i++) {
					if (u == t) {
						continue;
					}
					if (prev[v = Adj[u][i]] == -1 && cap[u][v] != 0) {
						prev[q[qb++] = v] = u;
					}
				}
			}
			// dbg("papa t");
			// dbg(prev[t]);
			// see if we're done
			if (prev[t] == -1)
				break;

			// try finding more paths
			for (int z = 0; z < V; z++) {
				if (cap[z][t] != 0 && prev[z] != -1) {
					int bot = cap[z][t];
					for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {// busco
						bot = Math.min(cap[u][v], bot);// el maximo que se
														// puede mandar=f
					}

					if (bot == 0) {
						continue;
					}

					cap[z][t] -= bot;
					cap[t][z] += bot;
					for (int v = z, u = prev[v]; u >= 0; v = u, u = prev[v]) {
						cap[u][v] -= bot;// y lo mando.
						cap[v][u] += bot;
					}
					flow += bot;
				}
			}
		}
		return flow;
	}
}
