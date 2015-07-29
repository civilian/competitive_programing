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

public class BasicStringSkillsCharAnalisis {
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

		String t;
		t = readln();
		char act;
		int consonantes = 0, vocals = 0, digits = 0;
		String vo = "aeiou";
		for (int i = 0; i < t.length(); i++) {
			act = t.charAt(i);
			if (Character.isLetter(act)) {
				if (vo.contains(Character.toLowerCase(act) + "")) {
					vocals++;
				} else {
					consonantes++;
				}
			} else if (Character.isDigit(act)) {
				digits++;
			}
		}
		dbg("vocals", vocals, "consonantes ", consonantes, "digits ", digits);

		// output.close();
	}
}
