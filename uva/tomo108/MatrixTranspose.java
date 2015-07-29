package uva.tomo108;

/*Uva 10895*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MatrixTranspose {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String rl() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String nxt() {
		return _stk.nextToken();
	}

	static int nxtI() {
		return Integer.parseInt(nxt());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("MatrixTranspose"));

		while (rl() != null) {
			int m = nxtI(), n = nxtI();
			AdjList = new ArrayList[m];
			for (int f = 0; f < m; f++) {
				AdjList[f]=new ArrayList<Edge>();
				
				rl();
				int r = nxtI();
				idx.clear();
				for (int i = 0; i < r; i++) {
					idx.add(nxtI());
				}
				rl();
				for (int i = 0; i < r; i++) {
					AdjList[f].add(new Edge(idx.get(i), nxtI()));
				}
			}

			AdjList = transpose(n);
			System.out.printf("%d %d",AdjList.length,m);
			System.out.println();
			for (int f = 0; f < AdjList.length; f++) {
				System.out.printf("%d",AdjList[f].size());
				StringBuilder vals=new StringBuilder();
				for (int i = 0; i < AdjList[f].size(); i++) {
					System.out.printf(" %d",AdjList[f].get(i).to);
					vals.append(espacio(i)+AdjList[f].get(i).cost);
				}
				System.out.println();
				System.out.println(vals.toString());
			}
		}

	}

	private static String espacio(int i) {
		if(i!=0)
			return " ";
		return "";
	}

	private static ArrayList<Edge>[] transpose(int n) {
		ArrayList<Edge>[] out;
		out=new ArrayList[n];
		for (int i = 0; i < n; i++) {
			out[i]=new ArrayList<Edge>();
		}
		for (int i = 0; i < AdjList.length; i++) {
			for (Edge e : AdjList[i]) {
				out[e.to-1].add(new Edge(i+1, e.cost));
			}
		}
		
		return out;
	}

	static ArrayList<Edge>[] AdjList;
	static ArrayList<Integer> idx = new ArrayList<Integer>(79);
	static int V;

}

class Edge {
	int to, cost;

	public Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "[to=" + to + ", cost=" + cost + "]";
	}

}
