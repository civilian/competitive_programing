package uva.tomo103;
//Uva 10305.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class OrderingTasks {
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
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("OrderingTasks"));
		int edges;
		while (true) {
			readln();

			V = nextInt();
			edges = nextInt();
			if(V==0 && edges==0)
				break;
			
			AdjList = new LinkedList[V];
			for (int i = 0; i < AdjList.length; i++)
				AdjList[i] = new LinkedList<Edge>();

			int desde, hacia;
			for (int i = 0; i < edges; i++) {
				readln();
				desde = nextInt()-1;
				hacia = nextInt()-1;

				AdjList[desde].add(new Edge(hacia, 0));
				indegree[hacia]++;
			}
			
			initTopologicalSort(V);

		}

	}

	static int V, total_neighbors, id, weight;
	static LinkedList<Edge>[] AdjList;
	static LinkedList<Integer> topoSort=new LinkedList<Integer>(); // global vector to store the
	// toposort
	static int MAX_VERTICES = 101;// DEPENDE DEL PROBLEMA
	static int[] indegree = new int[MAX_VERTICES];
//	static PriorityQueue<Integer> pDeg = new PriorityQueue<Integer>(101);

	 static Queue<Integer> pDeg = new LinkedList<Integer>();

	private static void initTopologicalSort(int V) {
		// creo el grafo
		pDeg.clear();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				pDeg.add(i);
			}
		}

		topologicalSort();
		// reverse topoSort
		System.out.printf("%d", topoSort.get(0)+1);
		for (int i = 1; i < topoSort.size(); i++)
			// TODO: El tamaño para cuando falla
			System.out.printf(" %d", topoSort.get(i)+1);
		System.out.printf("\n");
	}

	static void topologicalSort() {
		topoSort.clear();
		int to;
		int act;
		while (!pDeg.isEmpty()) {
			act = pDeg.poll();
			topoSort.add(act);
			for (int j = 0; j < AdjList[act].size(); j++) {
				to = AdjList[act].get(j).to;
				indegree[to]--;
				if (indegree[to] == 0) {
					pDeg.add(to);
				}
			}
		}
	}
}

class Edge implements Comparable {
	Integer to, value;

	public Edge(Integer toIn, Integer valueIn) {
		to = toIn;
		value = valueIn;
	}

	@Override
	public String toString() {
		return "pair [to=" + to + ", _second=" + value + "]";
	}

	@Override
	public int compareTo(Object o) {
		if (this.first() != ((Edge) o).first())
			return this.first() - ((Edge) o).first();
		else
			return this.second() - ((Edge) o).second();
	}

	Integer first() {
		return to;
	}

	Integer second() {
		return value;
	}
}
