package uva.tomo100;

//Uva 10058.
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

public class JimmysRiddles {
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
		input = new BufferedReader(new FileReader("JimmysRiddles"));

		String riddle;
		while ((riddle = readln()) != null) {
			// There is always space before and after a comma (,). Therefore, it
			// is optional to clean up input with
			// replaceAll() and trim().
			output.println(riddle.replaceAll(" +", " ").trim()
					.matches(STATEMENT) ? "YES I WILL" : "NO I WON'T");
		}
		output.close();
	}

	static final String VERB = "(hate|love|know|like)s?";
	static final String NOUN = "(tom|jerry|goofy|mickey|jimmy|dog|cat|mouse)";
	static final String ARTICLE = "(a|the)";
	static final String ACTOR = "(" + NOUN + "|" + ARTICLE + " " + NOUN + ")";
	static final String ACTIVE_LIST = "(" + ACTOR + " and )*" + ACTOR;
	static final String ACTION = ACTIVE_LIST + " " + VERB + " " + ACTIVE_LIST;
	static final String STATEMENT = ACTION + "( , " + ACTION + ")*";

}
