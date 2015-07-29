package libroCompetitive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

public class BasicStringSkillsTonkensOrdenar {
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
		ArrayList<String> linea = new ArrayList<String>(100);
		while (_stk.hasMoreElements()) {// stringtokenizer los parte y los
										// guarda si no seria string[] o
										// linkedlist <string>
			linea.add(next().toLowerCase());
		}
		Collections.sort(linea);
		System.out.println(linea);
		System.out.println(linea.get(0));
	}
}
