package maratonBook.graphs;

import java.util.Scanner;

/*
 // IMPORTANTE: Correr uno solo a la vez ya que se utiliza una matriz de min global
 Graph in Figure 4.19
 5 9
 0 1 2
 0 2 1
 0 4 3
 1 3 4
 2 1 1
 2 4 1
 3 0 1
 3 2 3
 3 4 5
 R/ FLOYD WARSHALL
 APSP(0, 0) = 0
 APSP(0, 1) = 2
 APSP(0, 2) = 1
 APSP(0, 3) = 6
 APSP(0, 4) = 2
 APSP(1, 0) = 5
 APSP(1, 1) = 0
 APSP(1, 2) = 6
 APSP(1, 3) = 4
 APSP(1, 4) = 7
 APSP(2, 0) = 6
 APSP(2, 1) = 1
 APSP(2, 2) = 0
 APSP(2, 3) = 5
 APSP(2, 4) = 1
 APSP(3, 0) = 1
 APSP(3, 1) = 3
 APSP(3, 2) = 2
 APSP(3, 3) = 0
 APSP(3, 4) = 3
 APSP(4, 0) = 1000000000
 APSP(4, 1) = 1000000000
 APSP(4, 2) = 1000000000
 APSP(4, 3) = 1000000000
 APSP(4, 4) = 0

 FLOYD WARSHALL IMPRIMIR CAMINOS
 ... lo mismo que FLOYD WARSHALL
 Path 3,4
 3 0 2 4
 Path 1, 4
 1 3 0 2 4

 FLOYD WARSHALL MINIMAX
 Minimax 1, 4 = 4

 FLOYD WARSHALL MAXIMIN
 Minimax 3, 4 = 5
 */
public class AllSourseShortestPath {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// LECTURA
		V = sc.nextInt();
		E = sc.nextInt();

		AdjMatrix = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				AdjMatrix[i][j] = INF;
			}
			AdjMatrix[i][i] = 0;
		}

		initFloydWarshallMaximin();
		for (int i = 0; i < E; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			w = sc.nextInt();
			AdjMatrix[from][to] = w; // directed graph
		}
		// LECTURA END

		// FLOYD WARSHALL
		// floydWarshall();
		// FLOYD WARSHALL END

		// FLOYD WARSHALL IMPRIMIR CAMINO
		// Acordarse de la inicializacion de p hacerla arriba con la AdjMatrix
		// System.out.println("FLOYD WARSHALL IMPRIMIR CAMINO");
		// floydWarshallPInit();// Correrlo Solo si no no sirve
		// System.out.printf("Path %d, %d\n", 3, 4);
		// printPath(3, 4);
		// System.out.printf("\nPath %d, %d\n", 1, V-1);
		// printPath(1, V-1);
		// FLOYD WARSHALL IMPRIMIR CAMINO END

		// FLOYD WARSHALL MINIMAX
		// System.out.println("FLOYD WARSHALL MINIMAX");
		// floydWarshallMinimax();
		// System.out.printf("Minimax %d, %d = %d\n", 1, V - 1,
		// AdjMatrix[1][V - 1]);
		// FLOYD WARSHALL MINIMAX END

		// FLOYD WARSHALL MINIMAX
		System.out.println("FLOYD WARSHALL MAXIMIN");
		floydWarshallMaximin();
		System.out.printf("Minimax %d, %d = %d\n", 3, V - 1,
				AdjMatrix[3][V - 1]);
		// FLOYD WARSHALL MINIMAX END

	}

	// FLOYD WARSHALL
	static int INF = 1000000000;
	static int V, E, from, to, w;// no necesariamente tienen que ser globales
									// porque todo se puede meter en el main
	static int AdjMatrix[][];

	static void floydWarshall() {
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = Math.min(AdjMatrix[i][j], AdjMatrix[i][k]
							+ AdjMatrix[k][j]);
				}
			}
		}

		// Mostrando:
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.printf("APSP(%d, %d) = %d\n", i, j, AdjMatrix[i][j]);
			}
		}
	}

	// FLOYD WARSHALL END

	// FLOYD WARSHALL IMPRIMIR CAMINO
	// Asi se imprime el camino:

	// static int INF = 1000000000;
	// static int V, E, from, to, w;// no necesariamente tienen que ser globales
	// // porque todo se puede meter en el main
	// static int AdjMatrix[][];
	static int p[][];

	static void initFloydWarshallP() {
		// hacerlo antes de la leida luego de V
		p = new int[V][V];// informacion para imprimir caminos
		// AdjMatrix = new int[V][V];//seria descomentar y ponerlo arriba
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				p[i][j] = i;// parents para caminos
				// AdjMatrix[i][j] = INF;
			}
			// AdjMatrix[i][i] = 0;
		}

		floydWarshallP();
	}

	static void floydWarshallP() {
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (AdjMatrix[i][k] + AdjMatrix[k][j] < AdjMatrix[i][j]) {
						AdjMatrix[i][j] = AdjMatrix[i][k] + AdjMatrix[k][j];
						p[i][j] = p[k][j];
					}
				}
			}
		}

		// Mostrando:
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.printf("APSP(%d, %d) = %d\n", i, j, AdjMatrix[i][j]);
			}
		}
	}

	static void printPath(int i, int j) {
		if (i != j) {
			printPath(i, p[i][j]);// [i][j] tiene el papa de j
		}
		System.out.printf(" %d", j);
	}

	// FLOYD WARSHALL IMPRIMIR CAMINO END

	// FLOYD WARSHALL TRANSITIVE CLOSURE
	// static int INF = 1000000000;
	// static int V, E, from, to, w;// no necesariamente tienen que ser globales
	// // porque todo se puede meter en el main
	// static int AdjMatrix[][];

	/* Es para ver si hay un camino el que sea entre x y y */
	static void transitiveClosure() {
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = AdjMatrix[i][j]
							| (AdjMatrix[i][k] & AdjMatrix[k][j]);
				}
			}
		}
	}

	// FLOYD WARSHALL TRANSITIVE CLOSURE END

	// FLOYD WARSHALL MINIMAX
	// static int INF = 1000000000;
	// static int V, E, from, to, w;// no necesariamente tienen que ser globales
	// // porque todo se puede meter en el main
	// static int AdjMatrix[][];
	/*
	 * Es la maxima distancia entre los minimos caminos, como el ejemplo de el
	 * sapo que tenia que llegar a donde su novia con la minima cantidad de
	 * saltos pero teniendo en cuenta su maximo salto
	 */
	static void floydWarshallMinimax() {
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = Math.min(AdjMatrix[i][j],
							Math.max(AdjMatrix[i][k], AdjMatrix[k][j]));
				}
			}
		}
	}

	// FLOYD WARSHALL MINIMAX END

	// FLOYD WARSHALL MAXIMIN
	// static int INF = 1000000000;
	// static int V, E, from, to, w;// no necesariamente tienen que ser globales
	// // porque todo se puede meter en el main
	// static int AdjMatrix[][];

	static void initFloydWarshallMaximin() {
		// hacerlo antes de la leida luego de V
		// AdjMatrix = new int[V][V];//seria descomentar y ponerlo arriba
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				AdjMatrix[i][j] = -INF;
			}
			AdjMatrix[i][i] = 0;
		}
	}

	/* Es minimax alrevez la maxima distancia con los minimos pasos */
	static void floydWarshallMaximin() {
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = Math.max(AdjMatrix[i][j],
							Math.min(AdjMatrix[i][k], AdjMatrix[k][j]));
				}
			}
		}
	}

	// FLOYD WARSHALL MAXIMIN END

}
