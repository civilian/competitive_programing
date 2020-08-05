package CF.C159;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;

public class Matchmaker {
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
		input = new BufferedReader(new FileReader("Matchmaker"));
		readln();

		int n = nextInt();
		int m = nextInt();

		int x = 0;
		int y = 0;
		int a = 0, b = 0;
		for (int i = 0; i < n; i++) {
			// voltiarlos b es el ancho
			readln();
			x = nextInt();
			y = nextInt();
			Point tmp = new Point(y, x);
			markers.add(tmp);
		}

		for (int i = 0; i < m; i++) {
			// voltiarlos b es el ancho
			readln();
			a = nextInt();
			b = nextInt();
			Point tmpC = new Point(b, a);
			caps.add(tmpC);
		}

		Comparator<Point> comp = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x) {
					return o1.x - o2.x;
				} else {
					return o1.y - o2.y;
				}
			}
		};

		Collections.sort(caps, comp);
		
//		dbg(caps);

	}

	static ArrayList<Point> markers = new ArrayList<Point>(100000),
			caps = new ArrayList<Point>(100000);
}
