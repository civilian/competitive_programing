package uva.tomo113;

/*Uva 11340*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class Newspaper {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("Newspaper"));
		readln();

		int numCases = nextInt();

		for (int casesId = 0; casesId < numCases; casesId++) {
			costos.clear();
			readln();
			int K = nextInt();
			for (int i = 0; i < K; i++) {
				readln();
				Character c = next().charAt(0);
				Integer v = nextInt();
				costos.put(c, v);
			}
			readln();
			int M = nextInt();
			long pagarCentavos = 0;
			for (int i = 0; i < M; i++) {
				String l = readln();
				for (Character c : l.toCharArray()) {
					pagarCentavos += valor(c);
				}
			}
			System.out.println(valorDolares(pagarCentavos));
		}

	}

	private static String valorDolares(long pagarCentavos) {
		double out = pagarCentavos / 100.0;
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		String sout = df.format(out);
		sout += "$";
		return sout;
	}

	private static long valor(Character c) {
		Integer out = costos.get(c);
		out = (out == null) ? 0 : out;
		return out;
	}

	static HashMap<Character, Integer> costos = new HashMap<Character, Integer>(
			100);
}
