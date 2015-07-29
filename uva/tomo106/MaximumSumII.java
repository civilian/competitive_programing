package uva.tomo106;

//Uva 10656.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MaximumSumII {
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
//		input = new BufferedReader(new FileReader("MaximumSumII"));
		int N, in;
		boolean allzero;
		ArrayList<Integer> secuence = new ArrayList<Integer>(1001);
		while (true) {
			readln();
			N = nextInt();
			if (N == 0) {
				break;
			}
			allzero = true;
			secuence.clear();
			for (int i = 0; i < N; i++) {
				readln();
				in = nextInt();
				if (in != 0) {
					allzero = false;
					secuence.add(in);
				}
			}

			if (allzero) {
				System.out.println(0);
			} else {
				for (int i = 0; i < secuence.size(); i++) {
					System.out.printf("%s%d", espacio(i), secuence.get(i));
				}
				System.out.println();
			}
		}

	}

	private static String espacio(int i) {
		if (i != 0) {
			return " ";
		}
		return "";
	}
}
