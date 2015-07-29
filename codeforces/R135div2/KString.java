package CF.R135div2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class KString {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("kString"));

		// while (true) {
		// if (readln() == null)
		// break;
		readln();
		k = nextInt();
		readln();
		s = next().toCharArray();
		Arrays.fill(letras, 0);
		for (char c : s) {
			letras[c - 'a']++;
		}
		can = true;
		for (int i = 0; i < letras.length; i++) {
			if ((letras[i] % k) != 0) {
				can = false;
				break;
			}
		}
		if (!can) {
			System.out.println(-1);
		} else {
			ansCorta = new StringBuilder(1000 + 7);
			ansLarga = new StringBuilder(1000 + 7);
			for (int i = 0; i < letras.length; i++) {
				if (letras[i] != 0) {
					veces = letras[i] / k;
					for (int j = 0; j < veces; j++) {
						ansCorta.append((char) (i + 'a'));
					}
				}
			}
			for (int j = 0; j < k; j++) {
				ansLarga.append(ansCorta);
			}
			System.out.println(ansLarga);
		}
		// }
	}

	static StringBuilder ansCorta, ansLarga;
	static boolean can;
	static int k, veces, letras[] = new int[('z' - 'a') + 7];
	static char[] s;
}
