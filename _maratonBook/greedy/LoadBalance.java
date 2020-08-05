package maratonBook.greedy;
//Uva 410
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

public class LoadBalance {
	/*
	 * Tengo unos "pesos"(costos,watever), y tengo que encontrar la mejor forma
	 * que encajen en una medida(o en algun lugar con esa medida que es lo
	 * mismo), de a grupos de un n dado, en este caso 2, entonces pongo esos
	 * pesos en dos grupos completando la cantidad que falte para llenar todas
	 * las cajas, lo mas grandes y los mas pequeños y solo junto el mas pequeño
	 * con el mas grande ya que cualquier otra cosa hace que sean mas grandes y
	 * no sea optimo el espacio
	 */
	public static void main(String[] args) throws IOException {
		balanceLoad();
	}

	static int tmp;
	static int S, C;
	static ArrayList<Integer> mass = new ArrayList<Integer>(11);
	static ArrayList<Integer>[] chambers = new ArrayList[6];

	static int porCamara = 2;

	private static void balanceLoad() {
		tmp = porCamara * C;
		for (int i = S; i < tmp; i++) {
			mass.add(0);
		}

		Collections.sort(mass);
		// dbg(mass);

		for (int i = 0; i < C; i++) {
			chambers[i].set(0, mass.get(i));
			chambers[i].set(1, mass.get(mass.size() - (i + 1)));
		}

	}

}
