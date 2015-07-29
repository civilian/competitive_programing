/*SE A�ADIO LO DE LA RAIZ QUE PARA EL PRIMER CICLO EN ListaFactoresPrimos Y QUE SE REUTILIZA EN EL
 * OTRO FOR PERO NO SE PROBO CONCIENSUDAMENTE*/
/*FALTA COPIAR Y PEGAR LOS RESULTADOS DE LAS PRUEBAS QUE SE LE DEBERIAN HACER A LOS QUE GENERAN LAS
 * LISTAS DE FACTORES COMUNES*/

/*RECORDAR ANNADIR EL 2 A LA LISTA*/
/*Acordarse que los primos van hasta la raiz por lo que al
 * hacer la lista hacerla hasta ese numero */

/*Para cosas peque�as No prime can end in 0,2,4,5,6, or 8,leaving only 1,3,7, and 9. It's fast & easy.
 */

/*
 todos prime: 104729, 1299709, 15485863, 179424673, 2147483647

 */
package maratonBook.matematica;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*los numeros primos son los que son divisibles por ellos
 * mismos y por 1*/
/*tener en cuenta que si el numero es negativo hay que sacarle el 
 * -1 en los factores*/
/*si tengo 4792  ya tengo todos los numeros primos entre 0 y (2^31)-1*/
public class PrimeNumber {
	public static void main(String[] args) {

		// System.out.println(isProbablePrime(511, 5));
		/* 5 como certainly es=0.96875 */
		// System.out.println(BigInteger.valueOf(511).isProbablePrime(100));

		// System.out.println(esPrimo(511));
		// LinkedList<Long> salida=new LinkedList<Long>();
		// listaFactoresPrimos(511, salida);/*[7, 73]*/
		// listaFactoresPrimos((int)Math.pow(2, 30), salida);
		// listaFactoresPrimosSinRep((int)Math.pow(2, 30), salida);
		// listaFactoresPrimosSinRepSimple((int)Math.pow(2, 30), salida);

		// System.out.println(salida);
		// System.out.println(listaPrimos.toString());
		// System.out.println(veces);
		// System.out.println(laprimos.length);
		sieve(32000);
		System.out.println(primes);
	}

	static int _sieve_size;
	static boolean[] bs; // 10^7 should be enough for most cases
	static List<Integer> primes = new ArrayList<Integer>(); // compact list of
															// primes in form of
															// vector<int>

	static void sieve(int upperbound) { // create list of primes in
										// [0..upperbound]
		_sieve_size = upperbound + 1; // add 1 to include upperbound
		bs = new boolean[_sieve_size];
		Arrays.fill(bs, true); // set all bits to 1
		bs[0] = bs[1] = false; // except index 0 and 1
		for (long i = 2; i < _sieve_size; i++)
			if (bs[(int) i]) {
				// cross out multiples of i starting from i * i!
				for (long j = i * i; j < _sieve_size; j += i)
					bs[(int) j] = false;
				primes.add((int) i); // also add this vector containing list of
										// primes
			}
	}

	boolean isPrime(long N) { // a good enough deterministic prime tester
		if (N < _sieve_size)
			return bs[(int) N]; // O(1) for small primes
		for (int i = 0; i < primes.size(); i++)
			if (N % primes.get(i) == 0)
				return false;
		return true; // it takes longer time if N is a large prime!
	} // note: only work for N <= (last prime in vi "primes")^2

	/*
	 * No tiene sentido ya que da lo mismo hacer sieve hasta un numero y luego
	 * continuar desde el mismo mas dos incluso usando las sumas anteriores
	 */
	// static void superSieve(/*int L,*/int U, int [] primosAnteriores, int []
	// cuantasVecesSiv, LinkedList<Long> resultado)

	/* Hay muchos mas metodos probabilisticos para saber si un numero es primo */
	/* prueba si un numero es o no */
	private static boolean isProbablePrime(int n, int k) {
		for (int j = 0; j < k; j++) {
			int a = (int) Math.random() * (n - 1);
			if (a < 2) {
				a += 2;
			}
			if (a != bigMod(a, n, n)) {
				return false;
			}
		}
		return true;
	}

	/* calculates b^p mod m */
	static long bigMod(long b, long p, long m) {
		/* n^0 =1, pero cuidado que n^0 mod 1=0 */
		if (p == 0)
			return 1;
		else if (p % 2 == 0) {
			long parcial = bigMod(b, p / 2, m) % m;
			return (parcial * parcial) % m;
		} else
			return ((b % m) * bigMod(b, p - 1, m)) % m;
	}

	/*
	 * Hace la lista de los factores primos de un numero de forma simple y
	 * rapida
	 */
	private static LinkedList<Long> listaFactoresPrimosSimple(long numero,
			LinkedList<Long> salida) {
		papa:
		/*
		 * aqui depende de si el problema especifica o no el numero 1 como
		 * factor
		 */
		while (numero != 1) {
			// long raiz=(long)Math.sqrt(numero);/*si el numero es
			// asquerosamente grande puede que
			/* la raiz tome demasiado tiempo */
			/* Alternativas se queda asi como esta i*i<numero en el for abajo */
			for (int i = 2; i * i < numero; i += 2) {
				if (numero % i == 0) {
					numero = numero / i;
					salida.add((long) i);
					continue papa;
				}
			}
			salida.add(numero);
			break;
		}
		/* si se necesita el 1 se puede a�adir aqui */
		// salida.add((long)1);
		return salida;
	}

	// second part

	List<Integer> primeFactors(long N) { // remember: vi is vector of integers,
											// long is long long
		List<Integer> factors = new ArrayList<Integer>(); // vi `primes'
															// (generated by
															// sieve) is
															// optional
		int PF_idx = 0;
		long PF = primes.get(PF_idx); // using PF = 2, 3, 4, ..., is also ok
		while (N != 1 && (PF * PF <= N)) { // stop at sqrt(N), but N can get
											// smaller
			while (N % PF == 0) {
				N /= PF;
				factors.add((int) PF);
			} // remove this PF
			PF = primes.get(++PF_idx); // only consider primes!
		}
		if (N != 1)
			factors.add((int) N); // special case if N is actually a prime
		return factors; // if pf exceeds 32-bit integer, you have to change vi
	}

	// third part

	long numPF(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = 0;
		while (N != 1 && (PF * PF <= N)) {
			while (N % PF == 0) {
				N /= PF;
				ans++;
			}
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans++;
		return ans;
	}

	long numDiffPF(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = 0;
		while (N != 1 && (PF * PF <= N)) {
			if (N % PF == 0)
				ans++; // count this pf only once
			while (N % PF == 0)
				N /= PF;
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans++;
		return ans;
	}

	long sumPF(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = 0;
		while (N != 1 && (PF * PF <= N)) {
			while (N % PF == 0) {
				N /= PF;
				ans += PF;
			}
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans += N;
		return ans;
	}

	long numDiv(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
		while (N != 1 && (PF * PF <= N)) {
			long power = 0; // count the power
			while (N % PF == 0) {
				N /= PF;
				power++;
			}
			ans *= (power + 1); // according to the formula
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans *= 2; // (last factor has pow = 1, we add 1 to it)
		return ans;
	}

	long sumDiv(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
		while (N != 1 && (PF * PF <= N)) {
			long power = 0;
			while (N % PF == 0) {
				N /= PF;
				power++;
			}
			ans *= ((long) Math.pow((double) PF, power + 1.0) - 1) / (PF - 1); // formula
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans *= ((long) Math.pow((double) N, 2.0) - 1) / (N - 1); // last one
		return ans;
	}

	long EulerPhi(long N) {
		int PF_idx = 0;
		long PF = primes.get(PF_idx), ans = N; // start from ans = N
		while (N != 1 && (PF * PF <= N)) {
			if (N % PF == 0)
				ans -= ans / PF; // only count unique factor
			while (N % PF == 0)
				N /= PF;
			PF = primes.get(++PF_idx);
		}
		if (N != 1)
			ans -= ans / N; // last factor
		return ans;
	}

}
