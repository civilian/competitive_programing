package uva.tomo2;

//Uva 231
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import usaco.a;

class TestingTheCATCHER { /* What Goes Up */
	// O(nk) LNIS DP (longest NON-increasing sequence)

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
//		input = new BufferedReader(new InputStreamReader(System.in));
		 input = new BufferedReader(new FileReader("TestingTheCATCHER"));

		int caseNo = 1;

		while (true) {
			n = 0;
			while (true) { // read this test case
				readln();
				X[n] = nextInt();
				if (X[n] == -1)
					break;
				n++;
			}

			if (X[0] == -1)
				break; // end of input

			longestNonIncreasingSecuence();

			if (caseNo != 1)
				System.out.printf("\n");
			System.out.printf("Test #%d:\n", caseNo++);
			System.out.printf("  maximum possible interceptions: %d\n", lnis);
		}
	}

	static final int MAX_N = 200000;
	static int[] X = new int[MAX_N], A = new int[MAX_N];
	static int n, lnis = 0;

	private static void longestNonIncreasingSecuence() {
		// O(n log k) algorithm
		int i;
		for (A[0] = X[0], i = lnis = 1; i < n; i++) { // O(n)
			int l = upperbound(A, lnis, X[i]); // find insertion point, O(k)
			lnis = Math.max(lnis, l + 1);// el largo de la nueva es insertion
											// point mas 1
			A[l] = X[i];
		}
	}

	private static int upperbound(int[] A, int len, int key) {
		// please convert this code to binary search to achieve
		// O(log k) instead of O(k) performance :)
		int mid, lo = 0, hi = len + 1;// ya que todos los redondeos son hacia
										// abajo tengo que incluir algo para que
										// llegue a hi
		A[len]=Integer.MIN_VALUE;//agregado luego del envio
		int ans = 1;
		while (lo < hi) {
			mid = lo + ((hi - lo) / 2);// evitar los redondeos hacia arriba
			if (A[mid] < key) {
				hi = mid;
				ans = mid;
			} else
				lo = mid + 1;
		}
		return ans;
	}
}
