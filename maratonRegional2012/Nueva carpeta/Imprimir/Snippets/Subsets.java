package maratonBook.snippets;

public class Subsets {
	public static void main(String[] args) {
		subsetsOFSize(2);
		// {}
		// {0 }
		// {1 }
		// {0 1 }
	}

	private static void subsetsOFSize(int N) {
		int P[] = new int[N];
		for (int i = 0; i < P.length; i++) {
			P[i] = i;
		}
		for (int i = 0; i < (1 << N); i++) {
			System.out.printf("{");// para mostrar el vacio
			for (int j = 0; j < P.length; j++) {
				if ((i & (1 << j)) != 0) {
					System.out.printf("%d ", P[j]);
				}
			}
			System.out.println("}");// para mostrar el vacio
		}
	}

}
