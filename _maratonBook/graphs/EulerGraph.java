package maratonBook.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;

/* // Graph in Figure 4.1/4.2
 9
 1 1
 3 0 2 4
 2 1 3
 3 1 2 4
 1 3
 0
 2 7 8
 1 6
 1 6
 R/ true

 //Graph in Figure 4.33 KONIGSBERG(ids-1) 
 4
 3 1 2 2
 5 0 0 2 3 3
 3 0 1 3
 3 1 1 2
 R/ EULER PATH UNDIRECTED GRAPH CHECK 
 false
 R/ PRINT EULER CYCLE END
 El tour de euler no existe

 //Graph in Figure 4.33 Uva 291 casita(ids-1)
 5
 2 1 2
 4 0 2 4 3
 4 0 1 3 4
 3 4 2 1
 3 3 2 1
 R/ EULER PATH UNDIRECTED GRAPH CHECK 
 true
 R/ PRINT EULER CYCLE END
 El tour de euler no existe

 //Graph in Figure 4.33 Uva 291 casita(ids-1)
 4
 2 1 2
 4 0 1 1 3
 2 0 3
 2 1 2

 */
public class EulerGraph {

	private static void dbg(Object... objects) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		for (int i = 0; i < AdjList.length; i++) {
			AdjList[i] = new ArrayList<Edge>(V);// assign blank vectors to
												// AdjList
			outdeg[i] = indeg[i] = 0;
		}
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				// weight = 1;
				AdjList[i].add(new Edge(id, 1));
				indeg[id]++;
				outdeg[i]++;
			}
		}
		// LECTURA END

		boolean es = checkEulerPathGraph();
		System.out.println(es);

		// dbg(AdjList);
		initPrintEulerCycle();
	}

	// EULER PATH UNDIRECTED GRAPH CHECK

	static int MAX_V = 1000000;// DEFINIR BIEN
	static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];
	static int V, total_neighbors, id, weight;
	static int[] indeg = new int[MAX_V], outdeg = new int[MAX_V];

	static int impares;

	private static boolean checkEulerPathGraph() {
		impares = 0;
		for (int i = 0; i < V; i++) {
			if (outdeg[i] % 2 == 1) {
				impares++;
				if (impares > 2) {
					return false;
				}
			}
		}
		return true;
	}

	// EULER PATH UNDIRECTED GRAPH CHECK END

	// EULER UNDIRECTED GRAPH CHECK

	// static int MAX_V = 1000000;// DEFINIR BIEN
	// static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];
	// static int V, total_neighbors, id, weight;
	// static int[] indeg = new int[MAX_V], outdeg = new int[MAX_V];

	private static boolean checkEulerCicleGraph() {// es cycle o es tour
		for (int i = 0; i < V; i++) {
			if (outdeg[i] % 2 == 1) {
				return false;
			}
		}
		return true;
	}

	// EULER UNDIRECTED GRAPH CHECK END

	// PRINT EULER CYCLE

	// static int MAX_V = 20;// DEFINIR BIEN
	// static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];
	// static int V, total_neighbors, id, weight;
	static int[] degree = new int[MAX_V];
	static boolean EulerTourExist;
	static LinkedList<Integer> cyc = new LinkedList<Integer>();

	private static void initPrintEulerCycle() {
		EulerTourExist = true;
		for (int i = 0; i < V; i++) {
			if (degree[i] % 2 == 1) { // odd-degree vertex => Euler tour does
										// not exist
				EulerTourExist = false;
			}
		}

		if (EulerTourExist) {
			cyc.clear();
			int A = 0;
			EulerTour(cyc.listIterator(), A);// cyc contains an Euler tour
												// starting at A como es
			// un tour/ciclo no importa donde empieze
			int prev = cyc.getLast();
			for (Integer e : cyc) {
				System.out.printf("%d %d\n", prev, e); // the Euler tour
				prev = e;
			}
		} else
			System.out.printf("El tour de euler no existe\n");

	}

	static void EulerTour(ListIterator<Integer> i, int u) {
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

	// EULER DIRECTED GRAPH CHECK

	// static int MAX_V = 1000000;// DEFINIR BIEN
	// static ArrayList<Edge>[] AdjList = new ArrayList[MAX_V];
	// static int V, total_neighbors, id, weight;
	// static int[] indeg = new int[MAX_V], outdeg = new int[MAX_V];
	static boolean hasIni, hasFin, hasRest;

	private static boolean checkEulerDirectedGraph() {
		hasRest = true;// por ahora
		hasIni = false;
		hasFin = false;
		for (int i = 0; i < V; i++) {
			if (indeg[i] != outdeg[i]) {
				if (indeg[i] - outdeg[i] == 1) {
					if (hasFin) {
						hasRest = false;
						return false;
					} else {
						hasFin = true;
					}
				} else if (outdeg[i] - indeg[i] == 1) {
					if (hasIni) {
						hasRest = false;
						return false;
					} else {
						hasIni = true;
					}
				} else {
					hasRest = false;
				}
			}
		}
		return hasIni && hasFin && hasRest;
	}

	// EULER DIRECTED GRAPH CHECK END

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
