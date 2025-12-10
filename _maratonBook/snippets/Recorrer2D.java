package maratonBook.snippets;

public class Recorrer2D {
	public static void main(String[] args) {
	}

	static int[] df = { 0, 0, -1, 1 }, dc = { -1, 1, 0, 0 };
	static int R, C;

	static void recorrer(int r, int c) {
		for (int i = 0; i < df.length; i++) {
			r = r + df[i];
			c = c + dc[i];
			if (isValid(r, c)) {
				System.out.printf("%d %d\n", r, c);
			}
		}
	}

	static boolean isValid(int r, int c) {
		return (r > -1 && r < R && c > -1 && c < C);// esta dentro
	}
}
