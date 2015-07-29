package libroCompetitive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

/*
 // Graph in Figure 4.2, format: list of unweighted Edge
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
 * */
public class e4_2_6_2 {
	// BFS
	public static void main(String[] args) {
		// LECTURA
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new LinkedList<Edge>();
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
			AdjList[mapper.get(a)].addLast(new Edge(mapper.get(b), 0));
			AdjList[mapper.get(b)].addLast(new Edge(mapper.get(a), 0));
		}
		// END LECTURA

		// DFS STANDAR
		initDFS(V);
		dfsComponents();
		// END DFS STANDAR
	}

	static HashMap<Integer, Integer> mapper;
	static HashMap<Integer, Integer> reverseMapper;
	static int a, b;
	static int V, total_neighbors, id, weight, E, counter;
	static final int DFS_WHITE = -1;
	static final int DFS_BLACK = 1;
	static LinkedList<Edge>[] AdjList;
	static int[] dfs_num;
	static int[] dist;
	static int numComp;
	static boolean isBipartite;

	private static void initDFS(int V) { // used in normal DFS
		dfs_num = new int[V];
		Arrays.fill(dfs_num, DFS_WHITE);// reinicio a no visitado
		dist = new int[V];
		numComp = 0;
	}

	private static void dfsComponents() {
		for (int i = 0; i < V; i++) { // for each vertex i in [0..V-1]
			if (dfs_num[i] == DFS_WHITE) { // if that vertex is not visited yet
				isBipartite = true;
				System.out.printf("Component %d:", ++numComp);
				DFS(i);
				System.out.printf("\n");
				System.out.printf("Bipartite: %b\n", isBipartite);
			}
		}
		System.out.printf("There are %d connected components\n", numComp);
	}

	public static void DFS(int u) {
		System.out.printf(" %d", u);
		dfs_num[u] = DFS_BLACK;// 559

		for (int j = 0; j < AdjList[u].size(); j++) {
			Edge v = AdjList[u].get(j);

			if (dfs_num[v.to] == DFS_WHITE) {
				dist[v.to] = 1 - dist[u];
				DFS(v.to);
			} else if (dist[v.to] == dist[u]) {
				isBipartite = false;
			}
		}
	}
}

//class Edge {
//	Integer to, value;
//
//	public Edge(Integer to, Integer value) {
//		this.to = to;
//		this.value = value;
//	}
//
//}