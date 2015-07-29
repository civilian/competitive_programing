package uva.tomo108;

//Uva 10878.
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

public class Decodethetape {
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

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Decodethetape"));

		readln();
		while (true) {
			l = readln();
			let = l.charAt(1);
			if (let == '_') {
				// System.out.printf(ans.toString());
				break;
			}
			bin = "";
			for (int i = 1; l.charAt(i) != '|'; i++) {
				if (l.charAt(i) == '.') {
					continue;
				}
				bin += (l.charAt(i) == ' ') ? 0 : 1;
			}
			val = Integer.parseInt(bin, 2);
			let = (char) val;
			// dbg(let);
			output.append(let);
		}
		output.close();
	}

	static String l, bin;
	static StringBuilder ans = new StringBuilder(10000);
	static int val;
	static char let;

}
