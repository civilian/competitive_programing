package libroCompetitive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
/*
 9
 1 1 0
 3 0 0 2 0 4 0
 2 1 0 3 0
 3 1 0 2 0 4 0
 1 3 0
 0
 2 7 0 8 0
 1 6 0
 1 6 0
 * */
public class e4_2_3_1 {
	// BFS
	public static void main1(String[] args) {

		Locale.setDefault(Locale.US);
		// input = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			Adj[i] = new LinkedList<IntegerPair>();
		// assign blank vectors of pair<int, int>s to AdjList
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				Adj[i].addLast(new IntegerPair(id, weight));
			}
		}
		// LECTURA END

		// DFS STANDAR
		initDFS(V);
		dfsComponents();
		// END DFS STANDAR
	}

	//Disjoint Sets
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		// input = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		// LECTURA
		V = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			Adj[i] = new LinkedList<IntegerPair>();
		// assign blank vectors of pair<int, int>s to AdjList
		
		initSet(V);
		
		for (int i = 0; i < V; i++) {
			total_neighbors = sc.nextInt();
			for (int j = 0; j < total_neighbors; j++) {
				id = sc.nextInt();
				weight = sc.nextInt();
				Adj[i].addLast(new IntegerPair(id, weight));
				
				unionSet(i, id);
			}
		}
		// LECTURA END

		// DFS STANDAR
		System.out.printf("Components: %d",numDisjointSets());
		// END DFS STANDAR
	}

	///////////////////////////////////////////////////////
	// Union-Find Disjoint Sets Library
	static int[] pset, setSize, rank;
	static int _numDisjointSets;

	static void initSet(int N) {
		rank = new int[N];
		setSize = new int[N];
		Arrays.fill(setSize, 1);
		_numDisjointSets = N;
		pset = new int[N];
		for (int i = 0; i < N; i++)
			pset[i] = i;
	}

	static int findSet(int i) {
		return (pset[i] == i) ? i : (pset[i] = findSet(pset[i]));
	}

	static boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	static void unionSet(int i, int j) {
		// TODO: hacerle pruebas
		int xroot = findSet(i), yroot = findSet(j);
		if (xroot == yroot)
			return;
		_numDisjointSets--;
		if (rank[xroot] < rank[yroot]) {
			pset[xroot] = yroot;
			setSize[yroot] += setSize[xroot];
		} else if (rank[xroot] > rank[yroot]) {
			pset[yroot] = xroot;
			setSize[xroot] += setSize[yroot];
		} else {
			pset[yroot] = xroot;
			rank[xroot]++;
			setSize[xroot] += setSize[yroot];
		}
	}// cambia con el union rank

//	private static void unionSet(int i, int j) {// si las cosas son muy grandes
//												// como millones mejor el de
//												// arriba
//		int x = findSet(i), y = findSet(j);
//		pset[x] = y;
//		setSize[x] += setSize[y];
//	}

	static int numDisjointSets() {
		return _numDisjointSets;
	}

	static int sizeOfSet(int i) {
		return setSize[findSet(i)];
	}

	
	
	////////////////////////////////////////////////////////
	//BFS
	static int V, total_neighbors, id, weight;
	static LinkedList<IntegerPair>[] AdjList;
	// recursion
	static int numComp;

	static HashMap<Integer, Integer> dist;

	private static void initDFS(int V) { // used in normal DFS
		dist = new HashMap<Integer, Integer>(V);
		numComp = 0;
	}

	// con este DFS estoy contando los componentes, el que hace el dfs normal es
	// el DFS(int u)
	private static void dfsComponents() {
		for (int i = 0; i < V; i++) { // for each vertex i in [0..V-1]
			if (dist.get(i) == null) { // if that vertex is not visited yet
				System.out.printf("Component %d:", ++numComp);
				BFSSimple(i);
				System.out.printf("\n");
			}
		}
		System.out.printf("There are %d connected components\n", numComp);
	}

	public static void BFSSimple(int s) {
		// BFS routine
		dist.put(s, 0); // distance to source is 0 (default)
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(s);// start from source

		while (!q.isEmpty()) {
			int u = q.peek();
			q.poll();// queue: layer by layer!
			System.out.printf(", visit %d", u);
			// reverseMapper maps index to actual vertex label
			for (int j = 0; j < AdjList[to].size(); j++) {
				IntegerPair v = Adj[to].get(j); // for each neighbors of u
				if (dist.get(v._first) == null) {// dist.find(v.first) ==
													// dist.end() also ok
					dist.put((v._first), dist.get(to) + 1);// v unvisited +
															// reachable
					q.offer(v._first); // enqueue v for next step
				}
			}
		}
	}
}

class Edge implements Comparable {
	Integer to, value;

	public Edge(Integer f, Integer s) {
		to = f;
		value = s;
	}

	@Override
	public String toString() {
		return "pair [to=" + to + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Object o) {
		if (this.first() != ((IntegerPair) o).first())
			return this.first() - ((IntegerPair) o).first();
		else
			return this.second() - ((IntegerPair) o).second();
	}

	Integer first() {
		return to;
	}

	Integer second() {
		return value;
	}
}
