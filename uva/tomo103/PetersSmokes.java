package uva.tomo103;

//Uva 10346.
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

public class PetersSmokes {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("PetersSmokes"));

		while (true) {
			if (readln() == null) {
				break;
			}
			n = nextInt();
			k = nextInt();
			fumados = 0;
			tmp = 1;
			while (true) {
				if (tmp <= 0) {
					break;
				}
				tmp = n / k;
				n = n - (k * tmp);
				fumados += (k * tmp);
				n += tmp;
			}
			fumados += n;
			output.println(fumados);
		}
		output.close();
	}

	static int n, k, fumados, tmp;
}
