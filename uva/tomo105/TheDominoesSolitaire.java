package uva.tomo105;

// Uva 10503
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class TheDominoesSolitaire {
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
		// input = new BufferedReader(new FileReader("TheDominoesSolitaire"));

		while (true) {
			readln();
			n = nextInt();
			if (n == 0) {
				break;
			}
			readln();
			m = nextInt();

			readln();
			ini.setLocation(nextInt(), nextInt());

			readln();
			fin.setLocation(nextInt(), nextInt());

			dominos.clear();
			dominos.add(ini);

			for (int i = 0; i < m; i++) {
				readln();
				dominos.add(new Point(nextInt(), nextInt()));
			}

			used = 0;

			pos = putD();
			// for (int i = 0; i <= 32; i++) {
			// System.out.printf(" %d", isOn(i));
			// }
			// System.out.println();
			// dbg(dominos);

			if (pos) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}

	private static boolean putD() {
		for (int i = 1; i <= m; i++) {
			if (putDR(i, 0, 0)) {
				return true;
			}
		}
		return false;
	}

	private static boolean putDR(int index, int llenados, int ant) {
		if (match(ant, index)) {
			setBit(index);
			ant = index;
			llenados++;
			if (llenados == n) {
				if (dominos.get(index).y == fin.x) {
					return true;
				} else {
					return false;
				}
			} else {
				for (int i = 1; i <= m; i++) {
					if (isOn(i) == 0) {
						if (putDR(i, llenados, ant)) {
							return true;
						}
						clearBit(i);
					}
				}
				return false;
			}
		}
		return false;
	}

	private static int setBit(int index) {
		return used = used | (1 << index);
	}

	private static int isOn(int j) {
		return used & (1 << j);
	}

	private static int clearBit(int j) {
		return used = used & ~(1 << j);
	}// los demas tienen que ser 1 por el &

	static Point tmp;

	private static boolean match(int ant, int index) {
		tmp = dominos.get(index);
		if (dominos.get(ant).y == tmp.x) {
			return true;
		} else if (dominos.get(ant).y == tmp.y) {
			dominos.set(index, new Point(tmp.y, tmp.x));
			return true;
		} else
			return false;
	}

	static int n = 0, m = 0;
	static Point ini = new Point(), fin = new Point();
	static ArrayList<Point> dominos = new ArrayList<Point>(15);
	static int used = 0;
	static boolean pos = false;

}
