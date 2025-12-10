package maratonBook.dp;

//Uva 674
import java.util.*;

class HowManyWaysChangingCoins { /* Coin Change */
	// O(NV) DP solution

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7500; j++) {
				memo[i][j] = -1; // we only need to initialize this once
			}
		}

		while (sc.hasNext()) {
			V = sc.nextInt();
			System.out.printf("%d\n", ways(0, V));
		}
	}

	// N and coinValue are fixed for this problem, max V is 7489
	private static int N = 5, V;
	private static int[] coinValue = new int[] { 1, 5, 10, 25, 50 };
	private static int[][] memo = new int[6][7500];

	private static int ways(int type, int value) {
		if (value == 0)
			return 1;
		if (value < 0 || type == N)
			return 0;
		if (memo[type][value] != -1)
			return memo[type][value];
		return memo[type][value] = ways(type + 1, value)
				+ ways(type, value - coinValue[type]);
	}

	// private static int waysRecursivo(int type, int value) {
	// if (value == 0)
	// return 0;
	// if (value < 0 || type == N)
	// return 1000000000;
	// if (memo[type][value] != -1)
	// return memo[type][value];
	// return memo[type][value] = Math.min(
	// waysRecursivo(type, value - coinValue[type]) + 1,
	// waysRecursivo(type + 1, value));
	// }
}
