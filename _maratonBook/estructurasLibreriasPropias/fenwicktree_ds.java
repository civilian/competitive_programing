package maratonBook.estructurasLibreriasPropias;

import java.util.*;

class fenwicktree_ds {
	/*
	 * RQM=Range Sum Query, que es la suma en un intervalo para esto sirve
	 * 
	 * Es un "arbol" que se guarda en un arreglo haciendo que tenga la suma de
	 * la frecuencia de una cantidad de numeros (tabla de responsabilidad), que
	 * se hace con los bits de el index
	 * 
	 * Importante: si contiene frecuencias negativas indexOf no funciona hay que
	 * hacerlo lineal
	 */

	public static void main1(String[] args) {
		// idx 0 1 2 3 4 5 6 7 8 9 10, no index 0!
		ArrayList<Integer> ft;
		ft = ft_create(10); // ft = {-,0,0,0,0,0,0,0, 0,0,0}
		ft_adjust(ft, 2, 1); // ft = {-,0,1,0,1,0,0,0, 1,0,0}, idx 2,4,8 => +1
		ft_adjust(ft, 4, 1); // ft = {-,0,1,0,2,0,0,0, 2,0,0}, idx 4,8 => +1
		ft_adjust(ft, 5, 2); // ft = {-,0,1,0,2,2,2,0, 4,0,0}, idx 5,6,8 => +2
		ft_adjust(ft, 6, 3); // ft = {-,0,1,0,2,2,5,0, 7,0,0}, idx 6,8 => +3
		ft_adjust(ft, 7, 2); // ft = {-,0,1,0,2,2,5,2, 9,0,0}, idx 7,8 => +2
		ft_adjust(ft, 8, 1); // ft = {-,0,1,0,2,2,5,2,10,0,0}, idx 8 => +1
		ft_adjust(ft, 9, 1); // ft = {-,0,1,0,2,2,5,2,10,1,1}, idx 9,10 => +1
		System.out.printf("%d\n", ft_rsq(ft, 1, 1)); // 0 => ft[1] = 0
		System.out.printf("%d\n", ft_rsq(ft, 1, 2)); // 1 => ft[2] = 1
		System.out.printf("%d\n", ft_rsq(ft, 1, 6)); // 7 => ft[6] + ft[4] = 5 +
														// 2 = 7
		System.out.printf("%d\n", ft_rsq(ft, 1, 10)); // 11 => ft[10] + ft[8] =
														// 1 + 10 = 11
		System.out.printf("%d\n", ft_rsq(ft, 3, 6)); // 6 => rsq(1, 6) - rsq(1,
														// 2) = 7 - 1

		ft_adjust(ft, 5, 2); // update demo
		System.out.printf("Index: ");
		for (int i = 0; i < ft.size(); i++)
			System.out.printf("%d ", i);
		System.out.printf("\n");
		System.out.printf("FT   : ");
		for (int i = 0; i < ft.size(); i++)
			System.out.printf("%d ", ft.get(i));
		System.out.printf("\n");

		System.out.printf(
				"minimun index that covers at least a frec=%d is %d\n", 7,
				indexOf(ft, 7));
	}

	public static void main(String[] args) {
		// idx 0 1 2 3 4 5 6 7 8 9 10, no index 0!
		int[][] ft;
		FenwickTree2D o = new FenwickTree2D();
		ft = o.ft_create(4, 5); // ft =
								// {-,0,0,0,0,0}{-,0,0,0,0,0}{-,0,0,0,0,0}{-,0,0,0,0,0}{-,0,0,0,0,0}
		o.ft_adjust(ft, 4, 5, 1);
		dbg(o.ft_rsq(ft, 1, 1));
		o.ft_adjust(ft, 1, 1, 1);
		dbg(ft);
		dbg(o.ft_rsq(ft, 4, 5));
	}

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));

	}

	private static int LSOne(int S) {
		return (S & (-S));
	}

	private static ArrayList<Integer> ft_create(int n) {
		ArrayList<Integer> v = new ArrayList<Integer>(n + 1);
		for (int i = 0; i <= n; i++)
			v.add(0);
		// initialization: n + 1 zeroes, ignoring index 0, just using
		// index[1..n]
		return v;
	}

	private static int ft_rsq(ArrayList<Integer> ft, int b) { // returns RSQ(1,
																// b)
		int sum = 0;
		for (; b > 0; b -= LSOne(b))
			sum += ft.get(b);
		return sum;
	}

	private static int ft_rsq(ArrayList<Integer> ft, int a, int b) { // returns
																		// RSQ(a,
																		// b)
		return ft_rsq(ft, b) - (a == 1 ? 0 : ft_rsq(ft, a - 1));
	}

	// adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
	private static void ft_adjust(ArrayList<Integer> ft, int k, int v) {
		// note: n=ft.size()-1
		for (; k < (int) ft.size(); k += LSOne(k))
			ft.set(k, ft.get(k) + v);
	}

	/* Encuentra el indice/score minimo que por lo menos la frecuencia value */
	static int indexOf(ArrayList<Integer> ft, int value) {
		int lo = 0, hi = ft.size(), mid = 0, ans = -1;

		while (lo < hi) {
			mid = lo + ((hi - lo) / 2);
			if (ft_rsq(ft, mid) >= value) {
				hi = mid;
				ans = mid;
			} else {
				lo = mid + 1;
			}
		}
		return ans;
	}
}

class FenwickTree2D {
	/*
	 * Importante: si contiene frecuencias negativas indexOf no funciona hay que
	 * hacerlo lineal
	 */

	public static int LSOne(int S) {
		return (S & (-S));
	}

	public static int[][] ft_create(int nf, int nc) {
		int[][] v = new int[nf + 1][];
		for (int f = 0; f <= nf; f++) {
			v[f] = new int[nc + 1];
		}
		// initialization: n + 1 zeroes, ignoring index 0, just using
		// index[1..n][1..] ....
		return v;
	}

	public static int ft_rsq(int[][] ft, int x, int y) { // returns RSQ(1,
															// b)
		int sum = 0;
		while (x > 0) {
			sum += ft_rsqC(ft, x, y);
			x -= LSOne(x);
		}

		return sum;
	}

	public static int ft_rsqC(int[][] ft, int x, int y) {
		int sum = 0;
		while (y > 0) {
			sum += ft[x][y];
			y -= LSOne(y);
		}
		return sum;
	}

	public static int ft_rsq(int[][] ft, int x, int y, int x1, int y1) { // returns
																			// RSQ(a,
																			// b)
		int tx, ty;
		tx = (x != 1) ? x - 1 : x;
		ty = (y != 1) ? y - 1 : y;
		int val;
		val = (y == 1 && x == 1) ? 0 : ft_rsq(ft, tx, ty);

		return ft_rsq(ft, x1, y1) - val;
	}

	// adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
	public static void ft_adjust(int[][] ft, int x, int y, int val) {
		// note: n=ft.size()-1
		while (x < ft.length) {
			ft_adjustC(ft, x, y, val);
			// this function should update array tree[x]
			x += LSOne(x);
		}
	}

	// adjusts value of the k-th element by v (v can be +ve/inc or -ve/dec)
	public static void ft_adjustC(int[][] ft, int x, int y, int v) {
		// note: n=ft.size()-1
		while (y < ft[1].length) {
			// dbg("lenthC",ft[1].length, "lenthF",ft.length,"x",x,"y",y);
			ft[x][y] += v;
			y += LSOne(y);
		}
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}
