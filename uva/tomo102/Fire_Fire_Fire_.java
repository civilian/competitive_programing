package uva.tomo102;

//Uva 10243
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class Fire_Fire_Fire_ {
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

	static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Fire_Fire_Fire_"));// 152//10
																		// m

		for (int i = 0; i < MAX_V; i++) {
			Childrend[i] = new ArrayList<Edge>();
		}
		while (true) {
			readln();
			V = nextInt();
			if (V == 0) {
				break;
			}
			vecinosDeMin.clear();
			for (int i = 0; i < V; i++) {
				leaf[i] = true;
				root[i] = true;
				Childrend[i].clear();
				for (int j = 0; j < 2; j++) {
					memo[i][j] = -1;
				}
			}
			for (int i = 0; i < V; i++) {
				readln();
				vecinos = nextInt();
				if (vecinos != 0 && vecinosDeMin.size() == 0) {
					vecinosDeMin.add(i);
					iRoot=i;
				}
				for (int j = 0; j < vecinos; j++) {
					tmp = nextInt() - 1;
					// from = Math.min(tmp, i);
					// to = Math.max(tmp, i);
					from = i;
					to = tmp;
					leaf[from] = false;// le apunta a alguien
					root[to] = false;// le apunta a alguien

					Childrend[from].add(new Edge(to, 0));
				}
			}
			iRoot = MAX_V + 1;
			for (int i = 0; i < V; i++) {
				dbg(Childrend[i]);
			}

			dbg("leaf");
			dbg(leaf);
			dbg(root);
			int i = 0;
			ans = 0;
			while (i < V) {
				for (; i < V; i++) {
					if (root[i]) {
						iRoot = i;
						break;
					}
				}
				if (i == V) {
					break;
				}
				// dbg(i);
				i++;
				dbg("root", iRoot);
				ans += Math.min(MVC(iRoot, 0), MVC(iRoot, 1));
			}

			System.out.println(ans);
		}
	}
	//TODO: CAMBIAR PARA 1000 Y PICO VERTICES
	static int MAX_V = 10 + 7, V, vecinos, ans, to, tmp, from, iRoot;
	static boolean[] leaf = new boolean[MAX_V], root = new boolean[MAX_V];
	static int[][] memo = new int[MAX_V][2];
	static ArrayList<Edge>[] Childrend = new ArrayList[MAX_V];
	static HashSet<Integer> vecinosDeMin = new HashSet<Integer>(MAX_V);//solo llendo desde los vecinos del minimo pero tiene problema con arboles  independientes.

	private static int MVC(int v, int flag) {
		int ans = 0;
		if (memo[v][flag] != -1) {
			return memo[v][flag];
		} else if (leaf[v]) {
			ans = flag;
		} else if (flag == 0) {
			ans = 0;
			for (int i = 0; i < Childrend[v].size(); i++) {
				ans += MVC(Childrend[v].get(i).to, 1);// el papa no se cogio hay
														// que coger los hijos
			}
		} else if (flag == 1) {
			ans = 1;
			for (int i = 0; i < Childrend[v].size(); i++) {
				ans += Math.min(MVC(Childrend[v].get(i).to, 1),
						MVC(Childrend[v].get(i).to, 0));
			}
		}
		return memo[v][flag] = ans;
	}

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + "]";
		}

	}
}
