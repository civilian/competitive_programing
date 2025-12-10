package maratonBook.matematica;

import java.util.ArrayList;
import java.util.LinkedList;

public class PrimeNumberNoNecesario {

	public static void main(String[] args) {
		/*
		 * se añade el primer numero, porque ni -1,0,1 son primos
		 */
		// listaPrimos.add(2);
		/* para sieve no se tiene que añadir nada */
		// sieve(1, 24000000,listaPrimos);
		// sieve(1, 46342,listaPrimos);
		// listaNumerosPrimos(100000);
		// System.out.println(listaPrimos);
	}

	// SIEVE LOW AND UP LIMITS

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

	// SIEVE LOW AND UP LIMITS

	// LISTA DE NUMEROS PRIMOS POR PASOS

	static ArrayList<Integer> listaPrimos = new ArrayList<Integer>();

	/*
	 * utilizar resultados precompilados no funciona para lo que habria que
	 * aumentar una sentencia que esta comentada en lista de numeros
	 */
	// static Integer [] tmp={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, ....
	// static ArrayList<Integer> listaPrimos=new ArrayList<Integer>(
	// Arrays.asList(tmp));

	/*
	 * Saca todos los numeros primos hasta un numero n
	 */
	static public void listaNumerosPrimos(int n) {
		/* para los de precompilados */
		/*
		 * int i = 0; for (; i < tmp.length; i++) { listaPrimos.add(tmp[i]); }
		 */
		for (int idx = 3/* tmp[i-1] */; idx < n; idx += 2) {
			if (esPrimo(idx)) {
				listaPrimos.add(idx);
			}

		}
	}

	/* recordar lo de 1 no primo */
	/* si se quiere hacer el chequear primo mas simple solo el for ultimo */
	static boolean esPrimo(int n) {
		int i = 0;
		int raiz = (int) Math.sqrt(n) + 1;/*
										 * La raiz puede ser muy grande por
										 * tiempo por lo q se saltaria en el
										 * primero
										 */
		for (i = 0; i < listaPrimos.size(); i++) {
			if (listaPrimos.get(i) > raiz)
				break;
			if (n == listaPrimos.get(i))
				return true;
			else if (n % listaPrimos.get(i) == 0)
				return false;
		}
		/*
		 * cuando ya recorrio toda la cache y no estaba sigue hasta el n con la
		 * restriccion de stop-at-sqrt
		 */
		for (int idy = listaPrimos.get(listaPrimos.size() - 1); idy * idy < n; idy++) {
			if (n % idy == 0) {
				return false;
			}
		}
		return true;
	}

	// LISTA DE NUMEROS PRIMOS POR PASOS END

	// LISTA PRIMOS MAS ALLA DEL MAX_PRIME

	/* Hace la lista de los factores primos sin repetir factores */
	private static LinkedList<Long> listaFactoresPrimosSinRep(long numero,
			LinkedList<Long> salida) {
		int tmp = 2;
		papa:
		/*
		 * aqui depende de si el problema especifica o no el numero 1 como
		 * factor
		 */
		while (numero != 1) {
			long raiz = (long) Math.sqrt(numero) + 1;/*
													 * si el numero es
													 * asquerosamente grande
													 * puede quela raiz tome
													 * demasiado tiempo
													 */
			/*
			 * Alternativas simplemente se quita de el recorrido de la lista y
			 * se queda i*i<numero en el for abajo
			 */
			for (Integer elo : listaPrimos) {
				tmp = elo;
				if (elo > raiz)
					break;
				if (numero % elo == 0) {
					salida.add((long) elo);
					do
						numero = numero / elo;
					while (numero % elo == 0);
					continue papa;
				}

			}
			// if(BigInteger.valueOf(numero).isProbablePrime(5)){//con 5 de
			// cert.. da 0.96875
			// /*posiblemente es primo*/
			// salida.add(numero);
			// }/*y si no es definitivamente compuesto*/
			tmp = tmp + 2;

			for (int i = tmp; i * i < numero; i += 2) {
				if (numero % i == 0) {
					salida.add((long) i);
					do
						numero = numero / i;
					while (numero % i == 0);
					continue papa;
				}
			}
			salida.add(numero);
			break;
		}
		/* si se necesita el 1 se puede añadir aqui */
		// salida.add((long)1);
		return salida;
	}

	// LISTA PRIMOS MAS ALLA DEL MAX_PRIME
}
