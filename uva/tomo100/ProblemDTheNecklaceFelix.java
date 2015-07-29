package uva.tomo100;

//Uva 10054.
import java.awt.Point;
import java.io.*;
import java.util.*;

public class ProblemDTheNecklaceFelix {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		// Scanner sc = new Scanner(new File("ProblemDTheNecklace"));
		int tc = sc.nextInt() + 1, N;
		V = 51;
		for (int i = 0; i < AdjList.length; i++) {
			AdjList[i] = new ArrayList<Point>();
		}

		for (int idCases = 1; idCases < tc; idCases++) {
			N = sc.nextInt();
			for (int i = 0; i < MAX_V; i++) {
				degree[i] = 0;
				AdjList[i].clear();
			}
			for (int i = 0; i < N; i++) {
				A = sc.nextInt();
				B = sc.nextInt();

				AdjList[A].add(new Point(B, 1));
				AdjList[B].add(new Point(A, 1));
				degree[A]++;
				degree[B]++;
			}
			if (idCases != 1) {
				System.out.println();
			}
			System.out.printf("Case #%d\n", idCases);

			EulerTourExist = true;
			for (int i = 1; i <= 50; i++) {
				if (degree[i] % 2 == 1) { // odd-degree vertex => Euler tour
											// does not exist
					EulerTourExist = false;
				}
			}

			if (EulerTourExist) {
				cyc.clear();
				EulerTour(cyc.listIterator(), A);// cyc contains an Euler tour
													// starting at A como es
				int prev = cyc.getLast();
				for (Integer e : cyc) {
					System.out.printf("%d %d\n", prev, e); // the Euler tour
					prev = e;
				}
			} else
				System.out.printf("some beads may be lost\n");
		}
	}

	static int MAX_V = 50 + 7;// DEFINIR BIEN
	static ArrayList<Point>[] AdjList = new ArrayList[MAX_V];
	static int V, A, B;
	static int[] degree = new int[MAX_V];
	static boolean EulerTourExist;
	static LinkedList<Integer> cyc = new LinkedList<Integer>();

	static void EulerTour(ListIterator<Integer> i, int u) {
		for (Point v : AdjList[u]) {
			// dbg(v);
			if (v.y != 0) {// if this edge can still be used/not removed
				v.y = 0; // must use -> to change the actual value
				for (Point uu : AdjList[v.x]) {
					if (uu.x == u && uu.y != 0) {
						uu.y = 0;
						break;
					}
				}
				i.add(u);
				EulerTour(i, v.x);
				i.previous();
			}
		}
	}

}
