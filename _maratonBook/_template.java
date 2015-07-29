package maratonBook;

/** @team=civilian, 
 * @author*/

import java.io.*;
import java.math.*;
import java.util.*;

public class _template {
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
		// input = new BufferedReader(new FileReader("_template"));
		// output.print("ja");
		// output.close();// Be sure to close the output at the end 
						//or maybe he does not print everything.
	}
}
