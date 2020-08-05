package uva.tomo106;
//Uva 10684.
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

public class Thejackpot {
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

//	static void dbg(Object... o) {
//		System.out.println(Arrays.deepToString(o));
//	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Thejackpot"));
		while (true) {
			n = nextIntS();
			if (n == 0) {
				break;
			}
			for (int i = 0; i < n; i++) {
				array[i] = nextIntS();
			}

//			dbg(array);
			// preprocesMaxSum();
//			dbg(array);
			long res = maxSumOn();
			if (res > 0) {
				output.printf("The maximum winning streak is %d.\n", res);
			} else {
				output.println("Losing streak.");
			}

		}
		output.close();
	}

	private static long maxSumOn() {
		// TODO Auto-generated method stub
		long sum = 0;
		int ini = 0, fin = 0, iniMax = 0, finMax = 0;
		long maxSum = -1000000000;

		for (int i = 0; i < n; i++) {
			sum += array[i];
			if (sum > maxSum) {
				maxSum = sum;
				iniMax = ini;
				finMax = i;

			}
			if (sum < 0) {
				sum = 0;
				ini = i + 1;
			}
			fin = i;
		}
		return maxSum;
	}

	static int[] array = new int[10000 + 5];
	static int n;

	static int nextIntS() throws IOException {
		int resultado;
		try {
			resultado = nextInt();
		} catch (Exception e) {
			if (readln() == null) {
				System.exit(0);
			}
			resultado = nextIntS();
		}
		return resultado;
	}
}
