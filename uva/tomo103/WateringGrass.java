package uva.tomo103;
//Uva 10382.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

public class WateringGrass {
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
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WateringGrass"));
		int n, i, j, l, x, w, r, count, f, y, top;
		Interval e;
		ArrayList<Interval> list = new ArrayList<Interval>(10048);
		double newr, lastr, dx = 0, wi;
		double N = 1e-6;

		while (readln() != null) {
			n = nextInt();
			l = nextInt();
			w = nextInt();
			newr = 0;
			lastr = 0;
			count = 0;
			wi = (w * w) / 4.0;

			list.clear();

			for (i = 0; i < n; i++) {
				readln();
				x = nextInt();// centro
				r = nextInt();// radio

				e = new Interval();

				// Convierto el problema a uno de intervalos: 
				// es un circulo que
				// tiene su centro en la banda entonces solo cubre el intervalo
				// hasta el dx sacado con las operacion de los triangulos
				// rectangulos
				dx = Math.sqrt((double) (r) * r - wi);
				e.lef = x - dx;
				e.rig = x + dx;
				list.add(e);
			}

			Collections.sort(list);

			 dbg(list);
			for (count = i = j = 0; i < n; i = j) {
				newr = 0;
				while (j < n && list.get(j).lef <= lastr) {
					if (list.get(j).rig > newr)
						newr = list.get(j).rig;
					j++;
				}
				lastr = newr;
				count++;
				if (j == i || l < lastr + N)
					break;
			}
			System.out.printf("%d\n", lastr + N > l ? count : -1);
		}

	}
}

class Interval implements Comparable<Interval> {
	double lef, rig;

	@Override
	public int compareTo(Interval o) {
		if (lef != o.lef) {
			return Double.compare(lef, o.lef);
		} else {
			return Double.compare(o.rig, rig);
		}
	}

	@Override
	public String toString() {
		return "[lef=" + lef + ", rig=" + rig + "]";
	}

}
