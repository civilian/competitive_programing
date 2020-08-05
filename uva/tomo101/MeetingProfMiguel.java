package uva.tomo101;
//Uva 10171.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class MeetingProfMiguel {
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
		input = new BufferedReader(new FileReader("MeetingProfMiguel"));

		String age, dire;
		while (true) {
			readln();
			N = nextInt();
			if (N == 0) {
				break;
			}
			if (N < 0) {
				output.println("You will never meet.");
				continue;
			}
			for (int i = 0; i < places - 2; i++) {
				for (int j = 0; j < places - 2; j++) {
					AdjMatrixO[i][j] = INF;
					AdjMatrixY[i][j] = INF;
				}
				AdjMatrixO[i][i] = 0;
				AdjMatrixY[i][i] = 0;
			}

			map.clear();
			invMap.clear();
			for (int i = 0; i < N; i++) {
				readln();
				age = next();
				dire = next();
				from = mappear(next().charAt(0));
				to = mappear(next().charAt(0));
				w = nextInt();
				if (age.equals("Y")) {
					if (dire.equals("B")) {
						AdjMatrixY[from][to] = min(w, AdjMatrixY[from][to]);
						AdjMatrixY[to][from] = min(w, AdjMatrixY[to][from]);
					} else {
						AdjMatrixY[from][to] = min(w, AdjMatrixY[from][to]);
					}
				} else {
					if (dire.equals("B")) {
						AdjMatrixO[to][from] = min(w, AdjMatrixO[to][from]);
						AdjMatrixO[from][to] = min(w, AdjMatrixO[from][to]);
					} else {
						AdjMatrixO[from][to] = min(w, AdjMatrixO[from][to]);
					}
				}
			}
			readln();
			me = mappear(next().charAt(0));
			miguel = mappear(next().charAt(0));

			V = map.size();
			floydWarshall();

			min = INF;
			placesMeet.clear();
			for (int i = 0; i < map.size(); i++) {
				if (AdjMatrixO[miguel][i] + AdjMatrixY[me][i] < min) {
					min = AdjMatrixO[miguel][i] + AdjMatrixY[me][i];
					placesMeet.clear();
					placesMeet.add(invMap.get(i));
				} else if (AdjMatrixO[miguel][i] + AdjMatrixY[me][i] == min) {
					min = AdjMatrixO[miguel][i] + AdjMatrixY[me][i];
					placesMeet.add(invMap.get(i));
				}
			}

			if (min < INF) {
				output.printf("%d", min);
				Collections.sort(placesMeet);
				for (Character c : placesMeet) {
					output.printf(" %s", c);
				}
				output.println();
			} else {
				output.println("You will never meet.");
			}

		}
		output.close();
	}

	// FLOYD WARSHALL
	static int places = ('Z' - 'A' + 7);
	static HashMap<Character, Integer> map = new HashMap<Character, Integer>(
			places);
	static HashMap<Integer, Character> invMap = new HashMap<Integer, Character>(
			places);
	static int INF = 1000000000;
	static int V, N, from, to, w, me, miguel, min;// no necesariamente tienen
													// que ser globales
	// porque
	// todo se puede meter en el main
	static int AdjMatrixY[][] = new int[places][places];
	static int AdjMatrixO[][] = new int[places][places];;
	static LinkedList<Character> placesMeet = new LinkedList<Character>();

	static int min(int i, int j) {
		return Math.min(i, j);
	}

	private static int mappear(Character next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			invMap.put(idx, next);
		}
		return idx;
	}

	private static void floydWarshall() {
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrixY[i][j] = min(AdjMatrixY[i][j], AdjMatrixY[i][k]
							+ AdjMatrixY[k][j]);

					AdjMatrixO[i][j] = min(AdjMatrixO[i][j], AdjMatrixO[i][k]
							+ AdjMatrixO[k][j]);
				}
			}
		}

		// // Mostrando:
		// for (int i = 0; i < V; i++) {
		// for (int j = 0; j < V; j++) {
		// System.out.printf("APSP(%d, %d) = %d\n", i, j, AdjMatrixY[i][j]);
		// }
		// }
	}

	// FLOYD WARSHALL END

}
