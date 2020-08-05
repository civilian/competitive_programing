package uva.tomo117;

//Uva 11723.
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

public class NumberingRoads {
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(
	// new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("NumberingRoads"));
		double R, N;
		int ans, idCases = 1;
		while (true) {
			readln();
			R = nextInt();
			N = nextInt();
			if (R == 0 && N == 0) {
				break;
			}
			ans = (int) (Math.ceil(R / N) - 1);
			if (ans < 27) {
				System.out.printf("Case %d: %d\n", idCases++, ans);
			} else {
				System.out.printf("Case %d: impossible\n", idCases++);
			}

		}
		// output.close();

	}
}
