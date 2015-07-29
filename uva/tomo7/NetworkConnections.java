package uva.tomo7;
/*Uva 793*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class NetworkConnections {
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
//		input = new BufferedReader(new FileReader("NetworkConnections"));
		readln();

		int numCases = nextInt();
		int C,
		i,j,S,U;
		char accion;
		readln();
		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();
			C=nextInt(); initSet(C+1);//el 0 se ignora
			i=j=S=U=0;
			while (true) {
				readln();
				if(!_stk.hasMoreElements()){
					break;
				}
				accion=next().charAt(0);
				if (accion=='c') {
					i=nextInt(); 
					j=nextInt();
					unionSet(i, j);
				}
				else if (accion=='q') {
					i=nextInt();
					j=nextInt();
					if(isSameSet(i, j)){
						S++;
					}
					else {
						U++;
					}
				}
//				dbg(accion, "i", i, "j",j, "sameSet", isSameSet(i, j));
			}
			enter(casesId);
			System.out.printf("%d,%d\n",S,U);
		}

	}
	
	private static void enter(int casesId) {
		if (casesId!=0) {
			System.out.println();
		}
	}

	static int[] pset,rank;

	static void initSet(int N) {
		rank = new int[N];
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
		if (rank[xroot] < rank[yroot]) {
			pset[xroot] = yroot;
		} else if (rank[xroot] > rank[yroot]) {
			pset[yroot] = xroot;
		} else {
			pset[yroot] = xroot;
			rank[xroot]++;
		}
	}// cambia con el union rank
}
