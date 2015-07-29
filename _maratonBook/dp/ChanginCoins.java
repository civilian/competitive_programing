package maratonBook.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ChanginCoins {

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("ChanginCoins"));

		for (int i = 1; i < coin.length; i++) {
			coin[i] = -1;
		}
		coin[0] = 0;
		// dbg(memo);
		while (sc.hasNext()) {
			int value = sc.nextInt();
			System.out.printf("%d\n", coins(value));
			mostrar(value);
		}
	}

	static int N = 5,// tipos monedas
			coinValue[] = { 1, 5, 10, 25, 50 }, // valores monedas
			coin[] = new int[510],// valor maximo
			memoShow[] = new int[510];// valor maximo

	// N and coinValue are fixed for this problem, max V is 7489

	static int coins(int value) {
		if (value < 0)
			return 1000000000;
		if (coin[value] != -1)
			return coin[value];

		int min = 1000000000;
		int tmp = 0;
		for (int i = 0; i < coinValue.length; i++) {
			tmp = coins(value - coinValue[i]) + 1;
			if (tmp < min) {
				min = tmp;
				memoShow[value] = coinValue[i];
			}
		}
		return coin[value] = min;
	}

	static void mostrar(int value) {
		if (value == 0) {
			System.out.println();
			return;
		}
		System.out.printf("%d, ", memoShow[value]);
		mostrar(value - memoShow[value]);
	}

}
