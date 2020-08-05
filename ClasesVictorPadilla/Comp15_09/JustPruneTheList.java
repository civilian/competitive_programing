package ClasesVictorPadilla.Comp15_09;

//Uva 12049
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class JustPruneTheList {
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
		input = new BufferedReader(new FileReader("JustPruneTheList"));
		int testCases;
		int x;
		int y;
		HashSet<Integer> lista = new HashSet<Integer>(10001);
		readln();
		testCases = nextInt();
		int cantidad;
		int tmp;
		for (int i = 0; i < testCases; i++) {
			readln();
			x = nextInt();
			y = nextInt();
			lista.clear();
			readln();
			cantidad = x;
			for (int j = 0; j < x; j++) {
				lista.add(nextInt());
			}
			readln();
			for (int j = 0; j < y; j++) {
				tmp = nextInt();
				if (lista.contains(tmp)) {
					cantidad--;
				} else {
					cantidad++;
				}
			}
			System.out.println(cantidad);
		}

	}
}