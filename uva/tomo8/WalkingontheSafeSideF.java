package uva.tomo8;

//Uva 825.
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class WalkingontheSafeSideF {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		 input = new BufferedReader(new FileReader("WalkingontheSafeSide"));

		readln();
		int tc = nextInt();
		for (int idCases = 0; idCases < tc; idCases++) {
			readln();
			readln();
			F = nextInt();
			C = nextInt();
			for (int i = 0; i < F; i++) {
				for (int j = 0; j < C; j++) {
					if (i == 0 || j == 0) {
						indegree[i][j] = 1;
					} else {
						indegree[i][j] = 2;
					}
					paths[i][j] = 0;
				}
			}
			indegree[0][0] = 0;

			for (int i = 0; i < F; i++) {
				readln();
				fil = nextInt() - 1;
				while (_stk.hasMoreElements()) {
					col = nextInt() - 1;
					paths[fil][col] = -1;

					indegree[fil + 1][col]--;
					indegree[fil][col + 1]--;
				}
			}

			// fin = 0;
			// s = topoSort.get(0);
			s = new Point(0, 0);
			initTopologicalSort();
			// dbg(topoSort);
			countingPahtsOnDag2D();
			// dbg(paths);
			if (idCases != 0) {
				output.println();
			}
			// output.println(fin);
			if (F < 1 || C < 1) {
				output.println(0);
			} else {
				output.println(paths[F - 1][C - 1]);
			}
		}
		output.close();
	}

	// COUNTING PATHS ON DAG
	static int MAX_F = 1000;// definirlo bn

	// TopoSort Stuff
	static ArrayList<Point> topoSort = new ArrayList<Point>(MAX_F * MAX_F);
	static int[][] indegree = new int[MAX_F][MAX_F];
	// static PriorityQueue<Integer> pDeg = new PriorityQueue <Integer>
	// (MAX_VERTICES); //para imprimir el toposort respetando el orden de la
	// //entrada
	static Queue<Point> pDeg = new LinkedList<Point>();
	static Point s = new Point();
	static int[][] paths = new int[MAX_F][MAX_F];
	static int[] df = { 0, 1 }, dc = { 1, 0 };
	static int F, C, fil, col;
	static int nf, nc;
	static int fin;

	// Topological sort Copiarlo de arriba completo

	private static void countingPahtsOnDag2D() {
		paths[s.x][s.y] = 1;
		Point cur;
		for (int j = 0; j < topoSort.size(); j++) {
			cur = topoSort.get(j);
			// for (Edge e : AdjList[cur]) {
			// num_paths[e.to] += num_paths[cur];
			// }
			for (int i = 0; i < df.length; i++) {
				nf = cur.x + df[i];
				nc = cur.y + dc[i];
				if (isValid(nf, nc)) {
					// paths[nf][nc] += paths[f][c];
					paths[nf][nc] += paths[cur.x][cur.y];
				}
			}
		}

	}

	// TOPOSORT
	private static void initTopologicalSort() {

		pDeg.clear();
		for (int i = 0; i < F; i++) {
			for (int j = 0; j < C; j++) {
				if (indegree[i][j] == 0) {
					pDeg.add(new Point(i, j));
				}
			}
		}

		topologicalSort();
		// Mostrar el toposort
		// for (int i = 0; i < topoSort.size(); i++)
		// System.out.printf(" %d", topoSort.get(i));
		// System.out.printf("\n");
	}

	static void topologicalSort() {
		topoSort.clear();
		Point to;
		Point act;
		while (!pDeg.isEmpty()) {
			act = pDeg.poll();
			if (!isValid(act.x, act.y)) {
				continue;
			}
			topoSort.add(act);
			for (int j = 0; j < df.length; j++) {
				nf = act.x + df[j];
				nc = act.y + dc[j];
				if (isValid(nf, nc)) {
					to = new Point(nf, nc);
					indegree[to.x][to.y]--;
					if (indegree[to.x][to.y] == 0) {
						pDeg.add(to);
					}
				}
			}
		}
	}

	static boolean isValid(int r, int c) {
		return (r > -1 && r < F && c > -1 && c < C && paths[r][c] != -1);// esta																		// dentro
	}
}
