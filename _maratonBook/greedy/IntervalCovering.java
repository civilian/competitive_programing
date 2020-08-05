package maratonBook.greedy;

//Uva 10382.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IntervalCovering {
	/*
	 * Se tiene un espacio que se tiene que cubrir con la menor cantidada de
	 * intervalos sobre este espacio como ir en mio hasta univalle entonces para
	 * esto se siguen 3 pasos:
	 * 
	 * 1.Se convierte el problema en uno de intervalos por ejemplo pasar de
	 * circulos a intervalos
	 * 
	 * 2.Se ordenan los intervalos el lado left en forma creciente y si hay
	 * empate el right en forma decreciente
	 * 
	 * 3.
	 */

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new File("WateringGrass"));
		Interval e;
		double dx = 0, wi;
		int x, r, w;
		while (sc.hasNext()) {
			n = sc.nextInt();
			l = sc.nextInt();
			w = sc.nextInt();
			newr = 0;
			lastr = 0;
			count = 0;
			wi = (w * w) / 4.0;

			list.clear();

			for (i = 0; i < n; i++) {
				x = sc.nextInt();// centro
				r = sc.nextInt();// radio

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

			// 2.ORDENAR
			Comparator<Interval> comp = new Comparator<Interval>() {
				@Override
				public int compare(Interval o1, Interval o2) {
					if (o1.lef != o2.lef) {
						return Double.compare(o1.lef, o2.lef);
					} else {
						return Double.compare(o2.rig, o1.rig);
					}
				}
			};
			Collections.sort(list, comp);

			dbg(list);

			intervalCovering();
			System.out.printf("%d\n", lastr + N > l ? count : -1);
		}
	}

	static int n, i, j, l, count;
	static ArrayList<Interval> list = new ArrayList<Interval>(10048);
	static double newr, lastr;
	static double N = 1e-6;

	static void intervalCovering() {
		lastr = 0.0;// comienzo
		count = i = j = 0;
		for (; i < n; i = j) {
			newr = 0;
			while (j < n && list.get(j).lef <= lastr) {// el intervalo j cubre
														// desde el principio
														// actual
				if (list.get(j).rig > newr) {
					// tomo el que llegue hasta mas a la derecha posible
					newr = list.get(j).rig;
				}
				j++;
			}
			lastr = newr;// como ya escogi mi anterior intervalo corro el limite
							// derecho
			count++;// y cuento que lo escogi
			if (j == i || l < lastr + N)// no avance con los intervalos o llegue
										// al final
				break;
		}
	}
}

class Interval {
	double lef, rig;

	@Override
	public String toString() {
		return "[lef=" + lef + ", rig=" + rig + "]";
	}

}
