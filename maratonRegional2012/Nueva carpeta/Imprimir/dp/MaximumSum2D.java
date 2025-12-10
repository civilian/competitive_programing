package maratonBook.dp;

//Uva 108
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*4
 0 -2 -7  0 
 9  2 -6  2
 -4  1 -4  1 
 -1 8  0 -2
 R/maxSumON3
 15

 4
 1 1 1 -1
 -1 -1 -1 -1
 -1 -1 1 -1
 -1 -1 -1 -1
 R/maxSumON3
 3
 */
class MaximumSum2D { /* Maximum Sum */

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		// input = new BufferedReader(new FileReader("MaximumSum"));
		int i, j;
		int n;
		while (true) {
			n = sc.nextInt();
			F = n;
			C = n;
			for (i = 0; i < F; i++) {
				for (j = 0; j < C; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			/* Max sum ON3 */
			preprocessON3();
			dbg(maxSumON3());
			/* End Max sum ON3 */

			/* Matriz Sum O(1) */
			preprocessSum();
			dbg("suma", sumaO1(1, 0, 3, 1));
			// [suma, 15]
			// [suma, -6]
			dbg("On4", maxSumOn4());
			// [On4, 15]
			// [On4, 3]
			/* End Matriz Sum O(1) */
		}
	}

	/* MAX SUM ON3 */
	static int F, C;
	static int[][] matrix = new int[101][101];
	static int[][] preprocess = new int[101][101];

	// Se Puede hacer en la lectura
	private static void preprocessON3() {
		for (int i = 0; i < F; i++) {
			preprocess[i][0] = matrix[i][0];
			for (int j = 1; j < C; j++) {
				preprocess[i][j] = matrix[i][j] + preprocess[i][j - 1]; // pre-processing
			}
		}
	}

	private static int maxSumON3() {
		int left, right, row, possibleMax, currentMax = 0
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
		return currentMax;
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

	/* MATRIX SUM O(1) */
	// static int F, C;
	// static int[][] matrix = new int[101][101];
	// static int n;
	// static int[][] preprocess=new int[101][101];

	private static void preprocessSum() {
		for (int i = 0; i < F; i++) {
			for (int j = 0; j < C; j++) {
				preprocess[i][j] = matrix[i][j];
				if (i > 0) {
					preprocess[i][j] += preprocess[i - 1][j];
					// matrix[i][j] += matrix[i - 1][j];
				}
				if (j > 0) {
					preprocess[i][j] += preprocess[i][j - 1];
					// matrix[i][j] += matrix[i][j - 1];
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

}
