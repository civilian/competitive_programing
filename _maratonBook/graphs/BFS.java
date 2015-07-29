package maratonBook.graphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 // Graph in Figure 4.2, format: list of unweighted edges
 // This example shows another form of reading graph input
 13 16
 10 15   15 20   20 25   10 30   30 47   47 50   25 45   45 65
 15 35   35 55   20 40   50 55   35 40   55 60   40 60   60 65

 R/
 Layer 0:, visit 35
 Layer 1:, visit 15, visit 55, visit 40
 Layer 2:, visit 10, visit 20, visit 50, visit 60
 Layer 3:, visit 30, visit 25, visit 47, visit 65
 Layer 4:, visit 45
 Shortest path: 35 15 10 30
 isBipartite? false

 // Subset of Figure 4.2 where the graph is bipartite (remove edge 30-47)
 13 15
 10 15   15 20   20 25   10 30           47 50   25 45   45 65
 15 35   35 55   20 40   50 55   35 40   55 60   40 60   60 65
 R/...
 isBipartite? true
 */
public class BFS {

	static void printThis(String message) {
		System.out.printf("==================================\n");
		System.out.printf("%s\n", message);
		System.out.printf("==================================\n");
	}

	public static void main(String[] args) throws IOException {
		// LECTURA
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		AdjList = new ArrayList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new ArrayList<Edge>(V);
		// assign blank vectors to AdjList

		counter = 0;

		mapper = new HashMap<Integer, Integer>();
		reverseMapper = new HashMap<Integer, Integer>();

		for (int i = 0; i < E; i++) {
			// readln();
			a = sc.nextInt();
			b = sc.nextInt();
			if (mapper.get(a) == null) {
				mapper.put(a, counter++);
				reverseMapper.put(mapper.get(a), a);
			}
			if (mapper.get(b) == null) {
				mapper.put(b, counter++);
				reverseMapper.put(mapper.get(b), b);
			}
			AdjList[mapper.get(a)].add(new Edge(mapper.get(b), 0));
			AdjList[mapper.get(b)].add(new Edge(mapper.get(a), 0));
		}
		// END LECTURA

		// BFS BIPARTITE Y DISTANCIA
		printThis("BFS BIPARTITE Y DISTANCIA");
		BFS(35, 30);
		// BFS BIPARTITE Y DISTANCIA END

		// BFS SIMPLE
		printThis("BFS SIMPLE");
		BFSSimple(mapper.get(35), mapper.get(30));
		// BFS SIMPLE END

		// BFS BIPARTITE SIMPLE
		printThis("BFS BIPARTITE SIMPLE");
		BFSBipSimple(35, 30);
		// BFS BIPARTITE SIMPLE

		// BFS MULTI SOURCE SHORTEST PATH
		printThis("BFS MULTI SOURCE SHORTEST PATH");
		sources.add(mapper.get(35));
		BFSMultiSourceShortesPath(mapper.get(35), mapper.get(30));
		// BFS MULTI SOURCE SHORTEST PATH
	}

	// BFS BIPARTITE Y DISTANCIA

	// Para saber si un grafo es Bipartite== se puede pintar con dos colores lo
	// que se hace es tratar de utilizar estos dos colores pintando el grafo y
	// si se presenta un conflicto se puede decir que no es bipartido por la
	// forma en como se realiza
	static int V, E, s;
	static ArrayList<Edge>[] AdjList;
	static int[] dist;
	static Integer[] p;// addition: the predecessor/parent vector

	// Esto es solo por la lectura
	static HashMap<Integer, Integer> mapper;
	static HashMap<Integer, Integer> reverseMapper;
	static int a, b, counter;

	public static void BFS(int source, int destini) {
		s = mapper.get(source);
		dist = new int[V];
		Arrays.fill(dist, -1); // distancia de s a los nodos//Puede que tambien
								// sea 10^9 osea no se puede llegar
		dist[s] = 0; // distance to source is 0 (default)
		// Integer es el estado en este problema se cambia por una clase State
		// con lo que se necesite en los otros problemas
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.offer(s);// start from source
		p = new Integer[V];
		Arrays.fill(p, -1); // to store parent information
		// (p must be a global variable!)
		int layer = -1;// pa imprimir
		boolean isBipartite = true; // es bipartido hasta que se demuestre lo
									// contrario
		while (!q.isEmpty()) {
			int u = q.poll();// queue: layer by layer!
			// Aqui hacer el pruning cortar estados y buscar soluciones
			// if(u==fin) return respuesta;//para cuando se esta buscando una
			// solucion

			if (dist[u] != layer) {// para imprimir
				System.out.printf("\nLayer %d:", dist[u]);
			}
			layer = dist[u];// para imprimi
			System.out.printf(", visit %d", reverseMapper.get(u));// para
																	// imprimir
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j); // for each neighbors of u
				if (dist[v.to] == -1) {
					dist[v.to] = dist[u] + 1;// v unvisited +
												// reachable
					p[v.to] = u; // addition: el papa de v->first es u
					q.offer(v.to);
				} else if ((dist[v.to] % 2) == (dist[u] % 2))// same parity
					isBipartite = false;
			}
		}

		// LO DE MAS ES SOLO IMPRIMIR
		System.out.printf("\nShortest path: ");
		printPath(mapper.get(destini));
		System.out.printf("\n");
		System.out.printf("isBipartite? %b\n", isBipartite);
	}

	static void printPath(int u) {
		if (u == s) {
			System.out.printf("%d", reverseMapper.get(u));
			return;
		}
		printPath(p[u]);// recursive call: to make the output format: s -> ...
						// -> t
		System.out.printf(" %d", reverseMapper.get(u));
	}

	// END BFS BIPARTITE

	// BFS SIMPLE

	// static int V, E, s;
	// static ArrayList<Edge>[] AdjList;
	// static int[] dist;
	// static Integer[] p;// addition: the predecessor/parent vector

	public static void BFSSimple(int s, int destini) {
		// BFS routine
		dist = new int[V];
		Arrays.fill(dist, -1); // distancia de s a los nodos//Puede que tambien
								// sea 10^9 osea no se puede llegar
		dist[s] = 0; // distance to source is 0 (default)
		// Integer es el estado en este problema se cambia por una clase State
		// con lo que se necesite en los otros problemas
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);// start from source

		while (!q.isEmpty()) {
			int u = q.poll();
			// Aqui hacer el pruning cortar estados y buscar soluciones
			System.out.printf(", visit %d", reverseMapper.get(u));
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j); // for each neighbors of u
				if (dist[v.to] == -1) {
					dist[v.to] = dist[u] + 1;// v unvisited + reachable
					q.offer(v.to); // enqueue v for next step
				}
			}
		}
		System.out.println();
	}

	// END BFS SIMPLE

	// BFS Bipartite SIMPLE

	// static int V, E, s;
	// static ArrayList<Edge>[] AdjList;
	// static int[] dist;
	// static Integer[] p;// addition: the predecessor/parent vector

	static boolean isBipartite;

	public static void BFSBipSimple(int source, int destini) {
		s = mapper.get(source);

		dist = new int[V];
		Arrays.fill(dist, -1); // to store parent information
		dist[s] = 0; // distance to source is 0 (default)
		// Integer es el estado en este problema se cambia por una clase State
		// con lo que se necesite en los otros problemas
		Queue<Integer> q = new LinkedList<Integer>();// es un linked cuidado

		q.offer(s);// start from source
		p = new Integer[V];
		Arrays.fill(p, -1); // to store parent information
		// (p must be a global variable!), para saber los padres luego correrlo
		int layer = -1;// pa imprimpir
		isBipartite = true; // un boolean para ver bipartite

		while (!q.isEmpty()) {
			int u = q.poll();// queue: layer by layer!
			// Aqui hacer el pruning cortar estados y buscar soluciones
			// if(u==fin) return respuesta;//para cuando se esta buscando una
			// // solucion

			if (dist[u] != layer) {// solo imprime
				System.out.printf("\nLayer %d:", dist[u]);
			}
			layer = dist[u];// solo imprime
			System.out.printf(", visit %d", reverseMapper.get(u));// solo
																	// imprime
			// reverseMapper maps index to actual vertex label
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j); // for each neighbors of u
				if (dist[v.to] == -1) {
					dist[v.to] = 1 - dist[u];// solo le pongo el color
					p[v.to] = u; // addition: el papa de v->first es u
					q.offer(v.to);
				} else if (dist[v.to] == dist[u])
					isBipartite = false;// tienen el mismo color tonces no se
										// puede es casi lo mismo que el otro
										// solo que se tienen en cuenta que va
										// de par a impar
			}
		}

		// LO DE MAS ES SOLO IMPRIMIR
		System.out.printf("\nShortest path: ");
		printPath(mapper.get(destini));
		System.out.printf("\n");
		System.out.printf("isBipartite? %b\n", isBipartite);
	}

	// BFS Bipartite SIMPLE END

	// BFS SIMPLE

	// static int V, E, s;
	// static ArrayList<Edge>[] AdjList;
	// static int[] dist;
	// static int[] p;// addition: the predecessor/parent vector
	static int MAX_EDGES = 1000;// es como el max estados DEPENDE DEL PROBLEMA
	static ArrayList<Integer> sources = new ArrayList<Integer>(MAX_EDGES);

	public static void BFSMultiSourceShortesPath(int s, int destini) {
		// BFS routine
		dist = new int[V];
		Arrays.fill(dist, -1);
		// Integer es el estado en este problema se cambia por una clase State
		// con
		// lo que se necesite en los otros problemas
		Queue<Integer> q = new LinkedList<Integer>();

		for (Integer sr : sources) {
			q.offer(sr);// start from source
			dist[sr] = 0; // distance to source is 0 (default)
		}

		while (!q.isEmpty()) {
			int u = q.peek();
			// Aqui hacer el pruning cortar estados y buscar soluciones
			q.poll();// queue: layer by layer!
			System.out.printf(", visit %d", reverseMapper.get(u));
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j); // for each neighbors of u
				if (dist[v.to] == -1) {
					dist[v.to] = dist[u] + 1;// v unvisited + reachable
					q.offer(v.to); // enqueue v for next step
				}
			}
		}
		System.out.println();
	}

	// END BFS SIMPLE

	// CLASES BFS
	static class Edge {
		Integer to, value;

		public Edge(Integer toIn, Integer valueIn) {
			to = toIn;
			value = valueIn;
		}

		@Override
		public String toString() {
			return "pair [to=" + to + ", _second=" + value + "]";
		}
	}
	// CLASES BFS
}
