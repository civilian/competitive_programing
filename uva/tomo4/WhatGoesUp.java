package uva.tomo4;

//Uva 481
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class WhatGoesUp {
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

	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WhatGoesUp"));
		n = 0;
		while (readln() != null) {
			X[n] = nextInt();
			n++;
		}
		longestIncreasingSecuenceShow();
		output.println(lnis);
		output.println("-");
		mostrarSolucion();
		
		output.close();
	}

	static final int MAX_N = 200007;
	static int[] X = new int[MAX_N], A = new int[MAX_N];
	static int n, lnis = 0;
	static int[] memoShow = new int[MAX_N];

	private static void longestIncreasingSecuenceShow() {
		// O(n log k) algorithm
		X[n + 7] = Integer.MAX_VALUE;// en n+7 siempre va estar el que hace la
										// busqueda binaria correcta

		int i;
		for (A[0] = 0, i = lnis = 1; i < n; i++) { // O(n)
			int l = upperboundLIShow(A, lnis, X[i]); // find insertion point,
														// O(k)
			lnis = Math.max(lnis, l + 1);// el largo de la nueva es insertion
											// point mas 1
			A[l] = i;
			memoShow[i] = (l == 0) ? -1 : A[l - 1];
		}
	}

	private static int upperboundLIShow(int[] A, int len, int key) {
		int mid, lo = 0, hi = len + 1;// ya que todos los redondeos son hacia
										// abajo tengo que incluir algo para que
										// llegue a hi

		A[len] = n + 7;// esto es para definir bien la busqueda binaria y no
						// tener que reiniciar todo A ante de hacer un caso
						// acorcarse poner en n+7 la constante segun increasing
						// o decresing
		int ans = 1;
		// dbg("key", key,"len",len, "A", A);
		while (lo < hi) {
			mid = lo + ((hi - lo) / 2);// evitar los redondeos hacia arriba
			if (X[A[mid]] >= key) {
				hi = mid;
				ans = mid;
			} else
				lo = mid + 1;
		}
		return ans;
	}

	private static void mostrarSolucion() {
		Stack<Integer> secuence = new Stack<Integer>();
		int siguiente = A[lnis - 1];
		for (int i = 0; i < lnis; i++) {
			secuence.push(X[siguiente]);
			siguiente = memoShow[siguiente];
		}
		while (!secuence.isEmpty()) {
			output.println(secuence.pop());
		}
	}

}
