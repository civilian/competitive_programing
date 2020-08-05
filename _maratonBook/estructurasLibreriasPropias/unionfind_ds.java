package maratonBook.estructurasLibreriasPropias;

import java.util.*;

class unionfind_ds {
	public static void main(String[] args) {
		System.out.println("Assume that there are 5 disjoint sets initially");
		initSet(5); // create 5 disjoint sets
		unionSet('A' - 'A', 'B' - 'A'); // unionSet(A, B)
		unionSet('A' - 'A', 'C' - 'A'); // unionSet(A, C)
		unionSet('D' - 'A', 'B' - 'A'); // unionSet(D, B)
		System.out.println("findSet(A) = " + findSet('A' - 'A')); // will return
																	// 0 (lazy
																	// update)
		System.out.println("findSet(A) = " + findSet('A' - 'A')); // will return
																	// 0 (the
																	// parent ID
																	// of 'A',
																	// 'B', 'C',
																	// 'D')
		System.out.println("findSet(B) = " + findSet('B' - 'A')); // will return
																	// 0 (the
																	// parent ID
																	// of 'A',
																	// 'B', 'C',
																	// 'D')
		System.out.println("findSet(C) = " + findSet('C' - 'A')); // will return
																	// 0 (the
																	// parent ID
																	// of 'A',
																	// 'B', 'C',
																	// 'D')
		System.out.println("findSet(D) = " + findSet('D' - 'A')); // will return
																	// 0 (the
																	// parent ID
																	// of 'A',
																	// 'B', 'C',
																	// 'D')
		System.out.println("findSet(E) = " + findSet('E' - 'A')); // will return
																	// 4 (the
																	// parent ID
																	// of 'E')

		System.out.printf("size of set(A)=%d\n", sizeOfSet('A' - 'A'));

		System.out.println("isSameSet(A, E) = "
				+ isSameSet('A' - 'A', 'E' - 'A')); // will return false
		System.out.println("isSameSet(A, B) = "
				+ isSameSet('A' - 'A', 'B' - 'A')); // will return true
	}

	// Union-Find Disjoint Sets Library
	static int[] pset, setSize, rank;
	static int _numDisjointSets;

	static void initSet(int N) {
		rank = new int[N];
		setSize = new int[N];
		Arrays.fill(setSize, 1);
		_numDisjointSets = N;
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
		int xroot = findSet(i), yroot = findSet(j);
		if (xroot == yroot)
			return;
		_numDisjointSets--;
		if (rank[xroot] < rank[yroot]) {
			pset[xroot] = yroot;
			setSize[yroot] += setSize[xroot];
		} else if (rank[xroot] > rank[yroot]) {
			pset[yroot] = xroot;
			setSize[xroot] += setSize[yroot];
		} else {
			pset[yroot] = xroot;
			rank[xroot]++;
			setSize[xroot] += setSize[yroot];
		}
	}

	// private static void unionSet(int i, int j) {// si las cosas son muy
	// grandes
	// // como millones mejor el de
	// // arriba
	// int x = findSet(i), y = findSet(j);
	// pset[x] = y;
	// setSize[x] += setSize[y];
	// }

	static int numDisjointSets() {
		return _numDisjointSets;
	}

	static int sizeOfSet(int i) {
		return setSize[findSet(i)];
	}
}
