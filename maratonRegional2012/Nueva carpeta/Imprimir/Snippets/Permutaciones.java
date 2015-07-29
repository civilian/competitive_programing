package maratonBook.snippets;

public class Permutaciones {

	public static void main(String[] args) {
		int[] P = new int[10];
		for (int i = 0; i < P.length; i++) {
			P[i] = i;
		}

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < P.length; i++) {
				System.out.printf("%d ", P[i]);
			}
			System.out.println();
			next_permutation(P);
			/*
			 * 0 1 2 3 4 5 6 7 8 9 
			 * 0 1 2 3 4 5 6 7 9 8 
			 * 0 1 2 3 4 5 6 8 7 9 
			 * 0 1 2 3 4 5 6 8 9 7 
			 * 0 1 2 3 4 5 6 9 7 8
			 */
		}
	}

	static boolean next_permutation(int a[]) {
		int limit;
		for (int i = a.length - 2, j; i >= 0; i--) {
			if (a[i + 1] > a[i]) {
				for (j = a.length - 1; a[j] <= a[i]; j--)
					;// solo ajusta los indices por eso tiene el ;
				swap(a, i, j);

				limit = (a.length - i - 1);// el -1 para no intercambiar un elo
											// con el mismo
				for (j = 1; j <= limit / 2; j++)
					swap(a, i + j, a.length - j);
				return true;
			}
		}
		return false;
	}

	static void swap(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
