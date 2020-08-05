/*TODO: PROBAR upperboundLDS,*/
package maratonBook.dp;

//Uva 231
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class LongestNonIncreasingSecuence { /* What Goes Up */
	// O(nlog(k)) LNIS DP (longest NON-increasing sequence)

	/*
	 * Importante -1 es para terminar la secuencia
	 * 
	 * 10 9 2 3 8 8 1 -1 //longestNonIncreasingSecuence(); lnis Respuesta 5
	 * //longestNonIncreasingSecuenceShow(); mostrarSolucion(); Respuesta 1 8 8
	 * 9 10
	 * 
	 * -5 -4 -3 -2 -1 //longestNonIncreasingSecuence(); lnis Respuesta 1
	 * //longestNonIncreasingSecuenceShow(); mostrarSolucion(); Respuesta -2
	 * 
	 * 23 34 21 44 43 42 41 41 -1 //longestNonIncreasingSecuence(); lnis
	 * Respuesta 5 //longestNonIncreasingSecuenceShow(); mostrarSolucion();
	 * Respuesta 41 41 42 43 44
	 * 
	 * 389 207 155 300 299 170 158 65 -1 //longestNonIncreasingSecuence(); lnis
	 * Respuesta 6 //longestNonIncreasingSecuenceShow(); mostrarSolucion();
	 * Respuesta 65 158 170 299 300 389
	 */
	public static void main(String[] args) throws IOException {

		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new File("TestingTheCATCHER"));
		while (true) {
			n = 0;
			while (true) { // read this test case
				X[n] = sc.nextInt();
				if (X[n] == -1)
					break;
				n++;
			}

			if (X[0] == -1)
				break; // end of input

			// longestNonIncreasingSecuence();
			longestNonIncreasingSecuenceShow();
			System.out.printf("  maximum possible interceptions: %d\n", lnis);
			mostrarSolucion();
			// maximum possible interceptions: 4
		}
	}

	// LNIS
	static final int MAX_N = 50;
	static int[] X = new int[MAX_N], A = new int[MAX_N];
	static int n, lnis = 0;

	private static void longestNonIncreasingSecuence() {
		// O(n log k) algorithm
		int i;
		lnis=0;
		for (A[0] = X[0], i = lnis = 1; i < n; i++) { // O(n)
			int l = upperboundLNIS(A, lnis, X[i]); // find insertion point,
													// O(log(k))
			lnis = Math.max(lnis, l + 1);// el largo de la nueva es insertion
											// point mas 1
			A[l] = X[i];
		}
	}

	private static int upperboundLNIS(int[] A, int len, int key) {
		int mid, lo = 0, hi = len + 1;// ya que todos los redondeos son hacia
										// abajo tengo que incluir algo para que
										// llegue a hi

		int ans = 1;
		A[len] = Integer.MIN_VALUE;// esto es para definir bien la busqueda
									// binaria y no tener que reiniciar todo A
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

	// END LNIS

	// LDS
	// Solo es cambiar esta funcion para ir de LNIS a LDS
	private static int upperboundLDS(int[] A, int len, int key) {
		// O(log k)
		int mid, lo = 0, hi = len + 1;// ya que todos los redondeos son hacia
										// abajo tengo que incluir algo para que
										// llegue a hi
		A[len] = Integer.MIN_VALUE;// esto es para definir bien la busqueda
									// binaria y no tener que reiniciar todo A
		int ans = 1;
		while (lo < hi) {
			mid = lo + ((hi - lo) / 2);// evitar los redondeos hacia arriba
			if (A[mid] <= key) {// para lis se cambia por <
				hi = mid;
				ans = mid;
			} else
				lo = mid + 1;
		}
		return ans;
	}

	// LDS END
	// LONGEST NON INCREASING SECUENCE SHOW

	// Cambiar a LIS o LDS= (<=, A[len]=integer.MaxValue) cambiar el ,> o >=

	// static final int MAX_N = 200007;
	// static int[] X = new int[MAX_N], A = new int[MAX_N];
	// static int n, lnis = 0;
	static int[] memoShow = new int[MAX_N];

	private static void longestNonIncreasingSecuenceShow() {
		// O(n log k) algorithm
		X[n + 7] = Integer.MIN_VALUE;// en n+7 siempre va estar el que hace la
										// busqueda binaria correcta

		int i;
		lnis=0;
		for (A[0] = 0, i = lnis = 1; i < n; i++) { // O(n)
			int l = upperboundLNIShow(A, lnis, X[i]); // find insertion point,
														// O(k)
			lnis = Math.max(lnis, l + 1);// el largo de la nueva es insertion
											// point mas 1
			A[l] = i;
			memoShow[i] = (l == 0) ? -1 : A[l - 1];
		}
	}

	private static int upperboundLNIShow(int[] A, int len, int key) {
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
			if (X[A[mid]] < key) {
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
			System.out.println(secuence.pop());
		}
	}

	// END LONGEST NON INCREASING SECUENCE SHOW

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}
