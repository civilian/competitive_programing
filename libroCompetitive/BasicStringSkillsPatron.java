package libroCompetitive;

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

import javax.swing.text.StyledEditorKit.BoldAction;

public class BasicStringSkillsPatron {
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("_template"));

		String l, p;
		l = readln();
		p = readln();

		boolean has = false;

		for (int i = 0; i < l.length(); i++) {
			if (l.startsWith(p, i)) {
				System.out.printf("%d ", i);
				has = true;
			}
		}
		
		if (!has) {
			System.out.println(-1);
		} else {
			System.out.println();
		}
		// output.close();
	}
}
