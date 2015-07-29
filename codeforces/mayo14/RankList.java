package CF.mayo14;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;

public class RankList {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("RankList"));
		int n, k;
		Point[] teams;
		readln();

		n = nextInt();
		k = nextInt()-1;

		teams = new Point[n];// 50

		for (int i = 0; i < n; i++) {
			readln();
			teams[i] = new Point(nextInt(), nextInt());
		}

		Comparator<Point> comp = new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x > o2.x)
					return -1;
				else if (o1.x == o2.x && o1.y < o2.y)
					return -1;
				else
					return 1;
			}
		};

		Arrays.sort(teams, comp);

		int respuesta = 1;
		Point act = teams[0];

		for (int i = 1; i < n; i++) {
			if (act.equals(teams[i])) {
				respuesta++;
			} else {
				if (i > k) {
					break;
				}
				respuesta = 1;
				act = teams[i];
			}
		}

		System.out.println(respuesta);
	}
}
