package maratonBook.graphs;

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
import java.util.Scanner;
import java.util.StringTokenizer;

/*//Ejemplo Uva 825.//el formato indica para que fila cuales columnas son estados 
 //a los que no se puede/debe llegar
 4 5
 1
 2 2
 3 3 5
 4 
 */
public class DAG_CountingPaths2D {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		F = sc.nextInt();
		C = sc.nextInt();
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
		for (int i = 0;; i++) {

			break;
		}
		indegree[0][0] = 0;
		String read;
		for (int i = 0; i < F; i++) {
			fil = sc.nextInt() - 1;
			while (true) {
				read = sc.findInLine("\\d");// los que estan en la misma linea
											// son las columnas por las que no
											// se puede/quiere pasara
				if (read == null) {
					break;
				}
				col = Integer.parseInt(read) - 1;
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
		System.out.println(paths[F - 1][C - 1]);
	}

	private static void dbg(Object... objects) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.deepToString(objects));
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
		return (r > -1 && r < F && c > -1 && c < C && paths[r][c] != -1);// esta
																			// //
																			// dentro
	}
}
