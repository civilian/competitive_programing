package uva.tomo9;

//Uva 913.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class JoanaandtheOddNumbers {
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
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("JoanaandtheOddNumbers"));

		long N, l, e, u, ans;
		while (true) {
			if (readln() == null) {
				break;
			}
			N = nextInt();
			l = (N - 1) >> 1;
			e = (l * (l + 1) + l + 1);
			u = 2 * e - 1;
			ans = u + u - 2 + u - 4;
			System.out.println(ans);
		}
	}
}
