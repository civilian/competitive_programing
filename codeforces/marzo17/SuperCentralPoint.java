package CF.marzo17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class SuperCentralPoint {
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("SuperCentralPoint"));

		int respuesta = 0, n;
		boolean left = false, right = false, upper = false, lower = false;

		int[] x, y;

		readln();
		n = nextInt();
		x = new int[n];
		y = new int[n];

		for (int i = 0; i < n; i++) {
			readln();

			x[i] = nextInt();
			y[i] = nextInt();
		}

		int idx;
		for (int i = 0; i < n; i++) {
			idx = 0;
			left = false;
			right = false;
			upper = false;
			lower = false;

			while (!(left && right && upper && lower) && idx < n) {
				if (x[idx] == x[i] && y[idx] > y[i])
					upper = true;
				if (x[idx] == x[i] && y[idx] < y[i])
					lower = true;
				if (x[idx] > x[i] && y[idx] == y[i])
					right = true;
				if (x[idx] < x[i] && y[idx] == y[i])
					left = true;
				idx++;
			}
			if ((left && right && upper && lower)) {
				respuesta++;
			}
		}

		System.out.println(respuesta);
	}
}
