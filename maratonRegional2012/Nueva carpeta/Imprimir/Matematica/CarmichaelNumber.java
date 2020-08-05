package maratonBook.matematica;

/*Si los necesito es porque me los definen pero son tambien son los que tienen
 por lo menos 3 diferentes factores primos y pasa el test de fermat (bigMod(a,inicial,inicial)==a)
 para todos los numeros menores a el 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CarmichaelNumber {
	public static void main(String[] args) {

		LinkedList<Long> salida = new LinkedList<Long>();
		sieve(1000000);/*
								 * hasta el numero que sea el sqrt del numero de
								 * charmachael necesite o hasta 24' y para
								 * seguros 12'
								 */

		for (int i = 9; i < 65000; i += 2) {
			if (isCarmachaelNumber(i)) {
				salida.add((long) i);
				System.out.println(i);
			}
		}
	}

	/* hasta 40000 algo */
	// static int [] numerosCarmachael={561, 1105, 1729, 2465, 2821, 6601, 7107,
	// 8911, 10585, 15841, 18361, 24073, 29341, 41041, ...}

	// 561, 1105, 1729, 2465, 2821, 6601, 8911, 10585, 15841, 29341, 41041,
	// 46657, 52633, 62745, and 63973.
	static HashSet<Integer> hSPrim = new HashSet<Integer>();

	/* y si, no cambia nada */
	// static int [] prim={.....};

	/*
	 * cogo la lista de factores primos, la pruebo, y luego la vuelvo
	 * isCarmachaelNumber
	 */
	static boolean isCarmachaelNumber(long numero) {
		int tmp = 2;
		long inicial = numero;
		int cantFactPrimos = 0;
		papa: while (numero != 1) {
			int raiz = (int) Math.ceil(Math.sqrt(numero));
			/* se muere con hashset */
			for (Integer elo : primes) {
				tmp = elo;
				if (elo > raiz)
					break;
				if (numero % elo == 0) {
					cantFactPrimos++;

					numero /= elo;
					if (numero % elo == 0)
						return false;
					continue papa;
				}
			}

			tmp += 2;
			for (int i = tmp; i < raiz; i += 2) {
				if (numero % i == 0) {
					cantFactPrimos++;

					numero /= i;
					if (numero % i == 0)
						return false;
					continue papa;
				}
			}
			cantFactPrimos++;
			break;
		}
		if (cantFactPrimos > 2) {
			for (int a = 2; a <= inicial - 1; a++) {
				if (bigMod(a, inicial, inicial) != a) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	static long bigMod(long b, long p, long m) {
		if (p == 0)
			return 1;
		else if (p % 2 == 0) {
			long parcial = bigMod(b, p / 2, m) % m;
			return (parcial * parcial) % m;
		} else
			return ((b % m) * bigMod(b, p - 1, m)) % m;
		// TODO Auto-generated method stub
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

}
