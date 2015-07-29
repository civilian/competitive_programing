package maratonBook.estructurasLibreriasPropias;

import java.util.*;

class segmenttree_ds {
	/*
	 * es un arbol que permite buscar el minimo en un intervalo (RMQ) en log(n)
	 * y que se llena en nlog(n)
	 */
	/* Solo guarda el menor en el intervalo y funciona como un heap */
	/*
	 * Para cambiar a maximo se cambia todo lo que sea < por >, menos lo de
	 * fuera de rango
	 */

	/* Si hago la busqueda por fuera ya sea por izquierda o por derecha da -1 */

	public static void main(String[] args) {
		int[] A = new int[] { 8, 7, 3, 9, 5, 1, 10 }; // the original array
		st_create(A);
		dbg(st);

		System.out.printf("              idx    0, 1, 2, 3, 4, 5  , 6\n");
		System.out.printf("              A is { 8, 7, 3, 9, 5, 1  , 10 }\n");
		System.out.printf("RMQ(1, 3) = %d\n", st_rmq(A, 1, 3)); // answer is
																// index 2
		System.out.printf("RMQ(4, 6) = %d\n", st_rmq(A, 4, 6)); // answer is
																// index 5
		System.out.printf("RMQ(3, 4) = %d\n", st_rmq(A, 3, 4)); // 4
		System.out.printf("RMQ(0, 0) = %d\n", st_rmq(A, 0, 0)); // 0
		System.out.printf("RMQ(0, 1) = %d\n", st_rmq(A, 0, 1)); // 1
		System.out.printf("RMQ(0, 6) = %d\n", st_rmq(A, 0, 6)); // 5

		System.out.printf("              idx    0, 1, 2, 3, 4, 5  , 6\n");
		System.out.printf("Now, modify A into { 8, 7, 3, 9, 5, 100, 10 }\n");
		st_update_point(A, 5, 100); // update A[5] from 1 to 100
		System.out.printf("These values do not change\n");
		System.out.printf("RMQ(1, 3) = %d\n", st_rmq(A, 1, 3)); // 2
		System.out.printf("RMQ(3, 4) = %d\n", st_rmq(A, 3, 4)); // 4
		System.out.printf("RMQ(0, 0) = %d\n", st_rmq(A, 0, 0)); // 0
		System.out.printf("RMQ(0, 1) = %d\n", st_rmq(A, 0, 1)); // 1
		System.out.printf("These values change\n");
		System.out.printf("RMQ(0, 6) = %d\n", st_rmq(A, 0, 6)); // 5->2
		System.out.printf("RMQ(4, 6) = %d\n", st_rmq(A, 4, 6)); // 5->4
		System.out.printf("RMQ(4, 5) = %d\n", st_rmq(A, 4, 5)); // 5->4
	}

	private static int[] st;

	// Segment Tree Library: The segment tree is stored like a heap array
	public static void st_build(int[] A, int vertex, int L, int R) {
		if (L == R) // as L == R, either one is fine
			st[vertex] = L; // store the index
		else { // recursively compute the values in the left and right subtrees
			int nL = 2 * vertex, nR = 2 * vertex + 1;// aqui se arman los
														// indices de el heap,
														// 2,3, 4,5 6,7
			st_build(A, nL, L, (L + R) / 2);// derecha
			st_build(A, nR, (L + R) / 2 + 1, R);// izquierda
			int lContent = st[nL], rContent = st[nR];
			int lValue = A[lContent], rValue = A[rContent];
			// se queda con el indice de el menor igual de sus hijos el mas a la
			// izq.
			st[vertex] = (lValue <= rValue) ? lContent : rContent;// para
			// maximo se
			// cambia a
			// >
		}
	}

	public static void st_create(int[] A) {
		// if original array size is N,
		// the required segment tree array length is 2*2^(floor(log2(N)) + 1);
		int len = (int) (2 * Math.pow(2.0,
				Math.floor((Math.log((double) A.length) / Math.log(2.0)) + 1)));
		st = new int[len];
		// for (int i = 0; i < len; i++)
		// st[i] = 0; // create vector with length `len' and fill it with
		// // zeroes
		st_build(A, 1, 0, (int) A.length - 1); // recursively build the segment
												// tree
	}

	public static int st_rmq(int[] A, int vertex, int L, int R, int i, int j) {
		if (i > R || j < L)// [L R] [i j] || [i j] [L R]
			return -1; // current segment outside query range
		if (L >= i && R <= j)// [i[L R]j]
			return st[vertex]; // current segment inside query range

		// compute the minimum position in the left and right part of the
		// interval
		int p1 = st_rmq(A, 2 * vertex, L, (L + R) / 2, i, j);
		int p2 = st_rmq(A, 2 * vertex + 1, (L + R) / 2 + 1, R, i, j);

		// return the position where the overall minimum is
		if (p1 == -1)
			return p2; // if we try to access segment outside query
		if (p2 == -1)
			return p1; // same as above
		return (A[p1] <= A[p2]) ? p1 : p2;// se cambia a > para maximun
	}

	public static int st_rmq(int[] A, int i, int j) { // overloading, simpler
														// argument
		return st_rmq(A, 1, 0, (int) A.length - 1, i, j);
	}

	public static int st_update_point(int[] A, int node, int b, int e, int idx,
			int new_value) {
		// this update code is still preliminary, i == j
		// must be able to update range in the future!
		int i = idx, j = idx;

		// if the current interval does not intersect
		// the update interval, return this st node value!
		if (i > e || j < b)
			return st[node];

		// if the current interval is included in the update range,
		// update that st[node]
		if (b == i && e == j) {
			A[i] = new_value; // update the underlying array
			return st[node] = b; // this index
		}

		// compute the minimum position in the
		// left and right part of the interval
		int p1, p2;
		p1 = st_update_point(A, 2 * node, b, (b + e) / 2, idx, new_value);
		p2 = st_update_point(A, 2 * node + 1, (b + e) / 2 + 1, e, idx,
				new_value);

		// return the position where the overall minimum is
		return st[node] = (A[p1] <= A[p2]) ? p1 : p2;
	}

	public static int st_update_point(int[] A, int idx, int new_value) {
		return st_update_point(A, 1, 0, (int) A.length - 1, idx, new_value);
	}

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}
