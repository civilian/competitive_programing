package uva.tomo100;

//Uva 10074.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class TaketheLand {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("TaketheLand"));

		// 217
		while (true) {
			readln();
			F = nextInt();
			C = nextInt();
			if (F == 0 && C == 0) {
				break;
			}
			for (int i = 0; i < F; i++) {
				readln();
				for (int j = 0; j < C; j++) {
					matrix[i][j] = (nextInt() == 0) ? 1 : -10099;
				}
			}
			preprocessON3();
			output.println(maxSumON3());
		}
		output.close();
	}

	/* MAX SUM ON3 */
	static int F, C;
	static int[][] matrix = new int[100 + 5][100 + 5];
	static int[][] preprocess = new int[100 + 5][100 + 5];

	// Se Puede hacer en la lectura
	private static void preprocessON3() {
		for (int i = 0; i < F; i++) {
			preprocess[i][0] = matrix[i][0];
			for (int j = 1; j < C; j++) {
				preprocess[i][j] = matrix[i][j] + preprocess[i][j - 1]; // pre-processing
			}
		}
	}

	private static long maxSumON3() {
		int left, right, row;
		long possibleMax, currentMax = 0;
		// ,leftMax, rightMax, rowIni, rowFinMax, rowIniMax
		;
		boolean first = true;
		// O(n^3) greedy solution (Kadane's algorithm)
		for (left = 0; left < C; left++)
			for (right = left; right < C; right++) {
				possibleMax = 0;
				// rowIni = 0;
				for (row = 0; row < F; row++) {
					if (left > 0) {
						possibleMax += preprocess[row][right]
								- preprocess[row][left - 1];
					} else {
						possibleMax += preprocess[row][right];
					}

					if (first || possibleMax > currentMax) {
						currentMax = possibleMax;
						// leftMax = left;
						// rightMax = right;
						// rowFinMax = row;
						// rowIniMax = rowIni;
						first = false;
					}
					// crucial part, immediately reset if running sum is
					// negative
					if (possibleMax < 0) {
						possibleMax = 0;
						// rowIni = row + 1;
					}

				}
			}
		return (currentMax < 0) ? 0 : currentMax;
	}

	// Se Puede hacer en la lectura
	private static void preprocessON3LowMemory() {
		for (int i = 0; i < F; i++) {
			for (int j = 1; j < C; j++) {
				matrix[i][j] += matrix[i][j - 1]; // pre-processing
			}
		}
	}

	/* END MAX SUM ON3 */
}
