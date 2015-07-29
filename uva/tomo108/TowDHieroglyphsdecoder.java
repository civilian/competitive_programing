package uva.tomo108;

//Uva 10851.
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

public class TowDHieroglyphsdecoder {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("TowDHieroglyphsdecoder"));

		// char let = 'b';
		// Integer b = (int) let;
		// output.println(b.toBinaryString(b));
		// 1100001
		readln();
		int tc = nextInt();
		for (int idC = 0; idC < tc; idC++) {
			if (idC != 0) {
				readln();
			}
			for (int lin = 0; lin < 10; lin++) {
				readln();
				l = next();
				largo = l.length();
				for (int i = 0; i < largo; i++) {
					H[lin][i] = (l.charAt(i) == '/') ? 0 : 1;
				}
			}

			ans = new StringBuilder(largo);
			for (int i = 1; i < largo - 1; i++) {
				bin = "";
				for (int lin = 8; lin > 0; lin--) {
					bin += H[lin][i];
				}
				// dbg(bin);
				val = Integer.parseInt(bin, 2);
				// dbg(val);
				letra = (char) val;
				// dbg(letra);
				ans.append(letra);
			}
			output.println(ans);
		}
		output.close();
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	static int[][] H = new int[10][80 + 7];
	static char letra;
	static int val, largo;
	static String bin;
	static StringBuilder ans;
	static String l;
}
