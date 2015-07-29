package libroCompetitive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import copyJava.TreeMap;

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
public class e4_2_2_1 {
	public static void main(String[] args) throws IOException {
		// LECTURA
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		AdjList = new LinkedList[V];
		for (int i = 0; i < AdjList.length; i++)
			AdjList[i] = new LinkedList<Edge>();
		// assign blank vectors of pair<int, int>s to AdjList

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

		BFS();
	}

	static int V, E, a, b, counter;

	static Integer s;

	static LinkedList<Edge>[] AdjList;
	static HashMap<Integer, Integer> mapper;

	static HashMap<Integer, Integer> reverseMapper;
	static Integer[] p;// addition: the predecessor/parent vector

	public static void BFS() {
		// as an example, we start from this source
		// see Figure 4.2
		s = mapper.get(35);

		// BFS routine
		ArrayList<Integer> dist = new ArrayList<Integer>();
		dist.addAll(Collections.nCopies(V, -1));
		dist.set(s, 0); // distance to source is 0 (default)
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.offer(s);// start from source
		p = new Integer[V];
		Arrays.fill(p, -1); // to store parent information
		// (p must be a global variable!)
		int layer = -1;// for our output printing purpose
		boolean isBipartite = true; // addition of one boolean flag,
		// initially true

		while (!q.isEmpty()) {
			int u = q.peek();
			q.poll();// queue: layer by layer!
			if (!dist.get(u).equals(layer))
				System.out.printf("\nLayer %d:", dist.get(u));
			layer = dist.get(u);
			System.out.printf(", visit %d", reverseMapper.get(u));
			// reverseMapper maps index to actual vertex label
			for (int j = 0; j < AdjList[u].size(); j++) {
				Edge v = AdjList[u].get(j); // for each
													// neighbors of
													// u
				if (dist.get(v.to) == -1) {
					dist.set((v.to), dist.get(u) + 1);// v unvisited +
															// reachable
					p[v.to] = u; // addition: the parent of vertex v->first
										// is u
					q.offer(v.to); // enqueue v for next step
				} else if ((dist.get(v.to) % 2) == (dist.get(u) % 2))// same
																			// parity
					isBipartite = false;
			}
		}

		System.out.printf("\nShortest path: ");
		printPath(mapper.get(30));
		System.out.printf("\n");// 2 lines here
		System.out.printf("isBipartite? %b\n", isBipartite);
	}

	static void printPath(int u) {
		// simple function to extract information from `vi p'
		if (u == s) {
			System.out.printf("%d", reverseMapper.get(u));
			return;
		}
		printPath(p[u]);// recursive call: to make the output format: s -> ...
						// -> t
		System.out.printf(" %d", reverseMapper.get(u));
	}

}

//class IntegerPair {
//	Integer _first, _second;
//
//	public IntegerPair(Integer f, Integer s) {
//		_first = f;
//		_second = s;
//	}
//
//}
