package uva.tomo116;

//Uva 11624.
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String l; 
	static String readln() throws IOException {
		l = input.readLine();
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
	// System.out.println(Arrays.deepToString(o));
	// }

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Fire"));
		readln();
		int testCases = nextInt();
		for (int idCases = 0; idCases < testCases; idCases++) {
			fire.clear();

			readln();
			R = nextInt();
			C = nextInt();
			for (int i = 0; i < R; i++) {
				readln();
				for (int j = 0; j < C; j++) {
					maze[i][j] =l.charAt(j); 
					distFire[i][j] = distJoe[i][j] = INF;
					if (maze[i][j] == 'J') {
						joe.setLocation(i, j);
					} else if (maze[i][j] == 'F') {
						fire.add(new Point(i, j));
					}
				}
			}

			BFS(joe);
			BFSMultiSourceShortesPath();

			int minTime = INF;
			// first and last row
			for (int i = 0; i < C; i++) {

				if (distJoe[0][i] < distFire[0][i]) {
					minTime = Math.min(minTime, distJoe[0][i]);
				}
				if (distJoe[R - 1][i] < distFire[R - 1][i]) {
					minTime = Math.min(minTime, distJoe[R - 1][i]);
				}
			}

			// first and last col
			for (int i = 0; i < R; i++) {
				if (distJoe[i][0] < distFire[i][0]) {
					minTime = Math.min(minTime, distJoe[i][0]);
				}
				if (distJoe[i][C - 1] < distFire[i][C - 1]) {
					minTime = Math.min(minTime, distJoe[i][C - 1]);
				}
			}

			if (minTime < INF) {
				output.println(minTime);
			} else {
				output.println("IMPOSSIBLE");
			}
		}
		output.close();
	}

	// BFS SIMPLE
	static char[][] maze = new char[1000 + 7][1000 + 7];
	static int[][] distJoe = new int[1000 + 7][1000 + 7];
	static int[][] distFire = new int[1000 + 7][1000 + 7];;
	static Point joe = new Point();
	static ArrayList<Point> fire = new ArrayList<Point>(1000 * 1000 + 5);
	static int R, C;
	static int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
	static int INF = 1000000000;

	public static void BFS(Point j) {
		// BFS routine
		Queue<State> q = new LinkedList<State>();
		q.offer(new State(j.x, j.y, 1));// start from source
		distJoe[j.x][j.y] = 1;// acordarse
		int rH, cH;
		while (!q.isEmpty()) {
			State u = q.poll();
			for (int i = 0; i < 4; i++) {
				rH = u.x + dx[i];
				cH = u.y + dy[i];
				if (isValid(rH, cH) && maze[rH][cH] == '.'
						&& distJoe[rH][cH] == INF) {
					distJoe[rH][cH] = u.cost + 1;
					q.offer(new State(rH, cH, distJoe[rH][cH]));
				}
			}
		}
	}

	static boolean isValid(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;
	}

	public static void BFSMultiSourceShortesPath() {
		// BFS routine
		Queue<State> q = new LinkedList<State>();
		for (Point p : fire) {
			q.offer(new State(p.x, p.y, 1));// simulo un estado conectado con
											// todos los source
			distFire[p.x][p.y] = 1;// acordarse
		}

		// bueno falta disJoe, etc armar los estados, etc.
		int rH, cH;
		while (!q.isEmpty()) {
			State u = q.poll();
			for (int i = 0; i < 4; i++) {
				rH = u.x + dx[i];
				cH = u.y + dy[i];
				if (isValid(rH, cH) && distFire[rH][cH] == INF
						&& maze[rH][cH] == '.') {
					distFire[rH][cH] = u.cost + 1;
					q.offer(new State(rH, cH, distFire[rH][cH]));
				}
			}
		}
	}
}

class State {

	int x, y, cost;

	public State(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "State [x=" + x + ", y=" + y + ", cost=" + cost + "]";
	}
}
