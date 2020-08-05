package uva.tomo1;
//Uva 108
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class MaximumSum_PruebaN4 { /* Maximum Sum */
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
//		input = new BufferedReader(new FileReader("MaximumSum"));
		int i, j;

		while (true) {

			F=C=n = nextIntS();
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					matrix[i][j] = nextIntS();
				}
			}
			preprocessSum();
			System.out.printf("%d\n", maxSumOn4());
		}
	}

	static int[][] matrix = new int[101][101];
	static int n,F,C;
	static int[][] preprocess=new int[101][101];

	private static void preprocessSum() {
		for (int i = 0; i < F; i++) {
			for (int j = 0; j < C; j++) {
				preprocess[i][j]=matrix[i][j];
				if (i > 0) {
					preprocess[i][j] += preprocess[i - 1][j];
					// matrix[i][j] = matrix[i][j] + matrix[i - 1][j];
				}
				if (j > 0) {
					preprocess[i][j] += preprocess[i][j - 1];
					// matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
				}
				if (i > 0 && j > 0) {
					preprocess[i][j] -= preprocess[i - 1][j - 1];
					// matrix[i][j] -= matrix[i - 1][j - 1];
				}
			}
		}
	}

	static int sumaO1(int ri, int ci, int rf, int cf) {
		int ans = preprocess[rf][cf];
		if (ri > 0) {
			ans -= preprocess[ri - 1][cf];
		}
		if (ci > 0) {
			ans -= preprocess[rf][ci - 1];
		}
		if (ri > 0 && ci > 0) {
			ans += preprocess[ri - 1][ci - 1];
		}
		return ans;
	}

	private static int maxSumOn4() {
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < F; i++)
			for (int j = 0; j < C; j++) {
				for (int k = i; k < F; k++)
					for (int l = j; l < C; l++) {
						maxSum = Math.max(maxSum, sumaO1(i, j, k, l));
					}
			}
		return maxSum;
	}

	private static int nextIntS() throws IOException {
		int resultado;
		try {
			resultado = nextInt();
		} catch (Exception e) {
			if (readln() == null)
				System.exit(0);

			resultado = nextIntS();
		}
		return resultado;
	}
}
