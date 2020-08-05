package uva.tomo1;
//Uva 108
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class MaximumSum { /* Maximum Sum */
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

			n = nextIntS();
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					matrix[i][j] = nextIntS();
					if (j > 0)
						matrix[i][j] += matrix[i][j - 1]; // pre-processing
				}
			}
			System.out.printf("%d\n", maxSumON3());
		}
	}

	static int[][] matrix = new int[101][101];
	static int n;

	private static int maxSumON3() {
		int left, right, row, possibleMax, currentMax = 0
//				,leftMax, rightMax, rowIni, rowFinMax, rowIniMax
				;
		boolean first = true;
		// O(n^3) greedy solution (Kadane's algorithm)
		for (left = 0; left < n; left++)
			for (right = left; right < n; right++) {
				possibleMax = 0;
//				rowIni = 0;
				for (row = 0; row < n; row++) {
					if (left > 0) {
						possibleMax += matrix[row][right]
								- matrix[row][left - 1];
					} else {
						possibleMax += matrix[row][right];
					}

					if (first || possibleMax > currentMax) {
						currentMax = possibleMax;
//						leftMax = left;
//						rightMax = right;
//						rowFinMax = row;
//						rowIniMax = rowIni;
						first = false;
					}
					// crucial part, immediately reset if running sum is
					// negative
					if (possibleMax < 0) {
						possibleMax = 0;
//						rowIni = row + 1;
					}

				}
			}
		return currentMax;
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
