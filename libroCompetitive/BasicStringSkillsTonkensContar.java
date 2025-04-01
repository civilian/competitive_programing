package libroCompetitive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class BasicStringSkillsTonkensContar {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " .");// y puntos
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("_template"));
		readln();
		HashMap<String, Integer> cuantos = new HashMap<String, Integer>(100);
		Integer how;
		String s;
		int max = 0;
		while (_stk.hasMoreElements()) {// stringtokenizer los parte y los
										// guarda si no seria string[] o
										// linkedlist <string>
			s = next().toLowerCase();
			how = cuantos.get(s);
			if (how == null) {
				how = 0;
			}
			cuantos.put(s, ++how);
			max = Math.max(max, how);
		}

		for (Entry<String, Integer> pal : cuantos.entrySet()) {
			if (pal.getValue() == max) {
				System.out.printf("%s %d\n", pal.getKey(), pal.getValue());
			}
		}
	}
}
