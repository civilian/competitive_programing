package maratonBook.dp;

/*NO LO ENTIENDO Y NO TENGO EL CONSULTAR SOLO EL PREPROCESO
 http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=lowestCommonAncestor#Range_Minimum_Query_%28RMQ%29*/
import java.util.Scanner;

public class RangeMinimunQueryStatic {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		preprocess();
	}

	static int MAXN = 1000;
	static int[] A = new int[MAXN];
	static int N;
	static int M[][] = new int[MAXN][(int) (Math.log(MAXN) + 2)];// [N][LOGN]

	private static void preprocess() {
		int i, j;

		// initialize M for the intervals with length 1
		for (i = 0; i < N; i++)
			M[i][0] = i;
		// compute values from smaller to bigger intervals
		for (j = 1; 1 << j <= N; j++) {
			for (i = 0; i + (1 << j) - 1 < N; i++) {
				if (A[M[i][j - 1]] < A[M[i + (1 << (j - 1))][j - 1]]) {
					M[i][j] = M[i][j - 1];
				} else {
					M[i][j] = M[i + (1 << (j - 1))][j - 1];
				}
			}
		}
	}
}
