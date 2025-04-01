package maratonBook;

import java.util.ArrayList;
import java.util.LinkedList;

public class DivisorAndDivisibility {
	public static void main(String[] args) {
		// System.out.println(listaDivisoresRepetidosSimple(64));
		/* [2, 2, 2, 2, 2, 2] */
		// System.out.println(listaDivisoresRepetidosSimple(3));
		// System.out.println(listaDivisoresRepetidosSimple(527));

		ArrayList<Integer> listaPrim = new ArrayList<Integer>();
		// sieve(0, 20000000, listaPrim);
		sieve(0, 32000, listaPrim);
		System.out.println(listaPrim);
		// System.out.println(listaDivisoresRepetidosConPrimos(24567898,
		// listaPrim));
		/* [2, 12283949] */
	}

	static LinkedList<Integer> listaDivisoresRepetidosSimple(int n) {/*
																	 * con los
																	 * repetidos
																	 */
		LinkedList<Integer> salida = new LinkedList<Integer>();
		papa: for (int i = 2; i * i < n; i++) {/*
												 * ya no seria simple pero se
												 * puede hacer con +=2
												 * incluyendo el 2
												 */
			while (n % i == 0) {
				n /= i;
				salida.add(i);
				if (n == 1)
					return salida;
			}
		}
		salida.add(n);
		return salida;

	}

	static LinkedList<Integer> listaDivisoresRepetidosConPrimos(int n,
			ArrayList<Integer> listaPrimos) {/* con los repetidos */
		LinkedList<Integer> salida = new LinkedList<Integer>();
		papa: for (Integer elo : listaPrimos) {
			while (n % elo == 0) {
				n /= elo;
				salida.add(elo);
				if (n == 1)
					return salida;
			}
		}
		salida.add(n);
		return salida;

	}

	static int cuantosDivisoresConPrimos(int n, LinkedList<Integer> listaPrimos) {/*
																				 * con
																				 * los
																				 * repetidos
																				 */
		int salida = 1;
		int expo = 0;
		for (Integer elo : listaPrimos) {
			while (n % elo == 0) {
				// System.out.printf("%d ",elo);
				n /= elo;
				expo++;
			}
			salida *= (expo + 1);
			expo = 0;
			if (n == 1)
				break;
		}

		return salida;

	}

	/* Crea una lista de numeros primos desde L hasta U */
	static void sieve(int L, int U, ArrayList<Integer> salida) {/*
																 * no cambia
																 * arraylist o
																 * linkedlist
																 */
		/* no se le debe mandar L como 0 porque se indetermina */
		if (L == 0)
			L = 1;

		int i, j, d;
		d = U - L + 1; /* from range L to U, we have d=U-L+1 numbers */
		/* use flag[i] to mark whether (L+i) is a prime or not */

		boolean[] flag = new boolean[d];
		for (i = 0; i < d; i++)
			flag[i] = true; /* default: mark all to be true */

		/* si L es par desde 0 y si no desde 1 */
		for (i = (L % 2 != 0 ? 1 : 0); i < d; i += 2)
			flag[i] = false;

		/* sieve by prime factors starting from 3 to sqrt(U) */
		for (i = 3; i * i <= U; i += 2) {

			if (i > L && !flag[i - L])
				continue;

			/*
			 * choose the first number to be sieved -->=L divisible by i, and
			 * not i itself!
			 */
			j = L / i * i;
			if (j < L)
				j += i;
			if (j == i)
				j += i; /* if j is a prime number ,have to start form next one */

			j -= L; /* change j to the Índex representing j */
			for (; j < d; j += i)
				flag[j] = false;

		}

		if (L <= 1)
			flag[1 - L] = false;
		if (L <= 2)
			flag[2 - L] = true;

		for (i = 0; i < d; i++)
			if (flag[i])
				salida.add(L + i);
	}
}
