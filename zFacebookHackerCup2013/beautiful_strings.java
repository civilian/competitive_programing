package zFacebookHackerCup2013;

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

public class beautiful_strings {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("beautiful_strings"));
		readln();

		int numCases = nextInt();

		int letras[] = new int[26 + 7];
		String l;
		int ans;
		for (int casesId = 1; casesId <= numCases; casesId++) {
			Arrays.fill(letras, 0);

			l = readln().toLowerCase();
			for (int i = 0; i < l.length(); i++) {
				if (Character.isLetter(l.charAt(i))) {
					letras[l.charAt(i) - 'a']--;
				}
			}

			Arrays.sort(letras);
			ans = 0;
			for (int i = 0, por = 26; por > 0; i++, por--) {
				ans += por * letras[i];
			}

			System.out.printf("Case #%d: %d\n", casesId, ans * -1);
		}
		// output.close();
	}
}
