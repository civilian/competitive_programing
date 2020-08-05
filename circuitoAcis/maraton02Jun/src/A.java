import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class A {
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

	static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("A"));

		int[] array;
		int actual, pos_actual, contador, n;
		while (true) {
			readln();
			n = nextInt();
			if (n == 0)
				break;

			array = new int[n];
			for (int i = 0; i < array.length; i++) {
				array[i] = -1;
			}

			for (int i = 1; i < n + 1; i++) {
				actual = i;
				readln();
				pos_actual = nextInt();
				contador = 0;
				while (pos_actual > -1 && contador < n) {
					if (array[contador] == -1) {
						pos_actual--;
					}
					if (pos_actual == -1) {
						array[contador] = actual;
					}
					contador++;
				}
			}
			System.out.printf("%d", array[0]);
			for (int i = 1; i < array.length; i++) {
				System.out.printf(",%d", array[i]);
			}
			System.out.println();
		}

	}
}
