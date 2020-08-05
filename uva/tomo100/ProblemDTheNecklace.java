package uva.tomo100;

//Uva 10054.
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
import java.util.ListIterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class ProblemDTheNecklace {
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
		input = new BufferedReader(new FileReader("ProblemDTheNecklace"));
		readln();
		int tc = nextInt() + 1, N;
		V = 51;
		for (int i = 0; i < AdjList.length; i++) {
			AdjList[i] = new ArrayList<Edge>();
		}

		for (int idCases = 1; idCases < tc; idCases++) {
			readln();
			N = nextInt();
			for (int i = 0; i < MAX_V; i++) {
				degree[i] = 0;
				AdjList[i].clear();
			}
			for (int i = 0; i < N; i++) {
				readln();
				A = nextInt();
				B = nextInt();

				AdjList[A].add(new Edge(B, 1));
				AdjList[B].add(new Edge(A, 1));
				degree[A]++;
				degree[B]++;
			}
			if (idCases != 1) {
				output.println();
			}
			output.printf("Case #%d\n", idCases);
			initPrintEulerCycle();
		}
		output.close();
	}

	// PRINT EULER CYCLE

	static int MAX_V = 50 + 7;// DEFINIR BIEN
	static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];
	static int V, total_neighbors, id, weightj, A, B;
	static int[] degree = new int[MAX_V];
	static boolean EulerTourExist;
	static LinkedList<Integer> cyc = new LinkedList<Integer>();

	private static void initPrintEulerCycle() {
		EulerTourExist = true;
		for (int i = 1; i < V; i++) {
			if (degree[i] % 2 == 1) { // odd-degree vertex => Euler tour does
										// not exist
				EulerTourExist = false;
			}
		}

		if (EulerTourExist) {
			cyc.clear();
			EulerTour(cyc.listIterator(), A);// cyc contains an Euler tour starting at A como es
							// un tour/ciclo no importa donde empieze
			int prev = cyc.getLast();
//			int prev = A;
			// dbg(A);
			// dbg(cyc);
			for (Integer e : cyc) {
				output.printf("%d %d\n", prev, e); // the Euler tour
				prev = e;
			}
		} else
			output.printf("some beads may be lost\n");

	}

	

	static void EulerTour(ListIterator<Integer> i, int u)  {
		for (Edge v : AdjList[u]) {
			// dbg(v);
			if (v.cost != 0) {// if this edge can still be used/not removed
				v.cost = 0; // must use -> to change the actual value
				for (Edge uu : AdjList[v.to]) {
					if (uu.to == u && uu.cost != 0) {
						uu.cost = 0;
						break;// solo 1 bidireccional el que estoy usando
					}
				}
				i.add(u);
				EulerTour(i, v.to);
				i.previous();
			}
		}
	}

	// PRINT EULER CYCLE END

	// ClASES

	static class Edge {
		Integer to, cost;

		public Edge(Integer toIn, Integer costIn) {
			to = toIn;
			cost = costIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", cost=" + cost + "]";
		}
	}

	// ClASES END
}
