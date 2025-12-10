package uva.tomo115;

/*Uva 11559 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class EventPlanning {
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
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("EventPlanning"));

		while (readln() != null) {
			int N = nextInt(), B = nextInt(), H = nextInt(), W = nextInt();
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				readln();
				int p = nextInt();

				readln();
				for (int j = 0; j < W; j++) {
					int a = nextInt();
					int tmpP = N * p;

					if (a >= N) {
						tmpP = N * p;
						if (tmpP < min)
							min = tmpP;
					}
				}
			}
			System.out.println(min > B ? "stay home" : min);
		}
	}
}
