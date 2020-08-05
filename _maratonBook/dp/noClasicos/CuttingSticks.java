package maratonBook.dp.noClasicos;

//Uva 10003
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class CuttingSticks { /* Cutting Sticks */

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	// Top-Down DP
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("CuttingStciks"));
		// Scanner sc = new Scanner(System.in);
		int i, j, l, n;

		while (true) {
			l = sc.nextInt();
			if (l == 0)
				break;

			arr[0] = 0;
			n = sc.nextInt();
			for (i = 1; i <= n; i++)
				arr[i] = sc.nextInt();
			arr[n + 1] = l;

			for (i = 0; i < 55; i++)
				for (j = 0; j < 55; j++)
					memo[i][j] = -1;

			// start with left = 0 and right = n + 1
//			System.out.printf("The minimum cutting is %d.\n", cut(0, n + 1));
			 System.out.printf("The minimum cutting is %d.\n", cutShow(0, n +
			 1));
			 imprimirSol(0, n + 1);
			System.out.println();
		}
	}

	private static void imprimirSol(int i, int j) {
		if (i + 1 == j)
			return;
		System.out.printf("%d, ", arr[memoShow[i][j]]);
		imprimirSol(i, memoShow[i][j]);
		imprimirSol(memoShow[i][j], j);
	}

	private static int[] arr = new int[55];
	private static int[][] memo = new int[55][55];

	private static int cut(int left, int right) {
		if (left + 1 == right)
			return 0;
		if (memo[left][right] != -1)
			return memo[left][right];

		int ans = 2000000000;
		int tmp = 2000000000;
		for (int i = left + 1; i < right; i++) {
			tmp = cut(left, i) + cut(i, right) + (arr[right] - arr[left]);
			ans = Math.min(ans, tmp);
		}
		return memo[left][right] = ans;
	}

	private static int[][] memoShow = new int[55][55];

	private static int cutShow(int left, int right) {
		if (left + 1 == right)
			return 0;
		if (memo[left][right] != -1)
			return memo[left][right];

		int ans = 2000000000;
		int tmp;
		 int minI = -1;
		for (int i = left + 1; i < right; i++) {
			tmp = cutShow(left, i) + cutShow(i, right) + (arr[right] - arr[left]);
//			dbg(tmp);
			if (ans > tmp) {
				ans = tmp;
				 minI = i;
			}
		}

		 memoShow[left][right] = minI;
		return memo[left][right] = ans;
	}

}
