package ClasesVictorPadilla.Comp15_09;

//Uva 10924
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class PrimeWords {
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

	// static void dbg(Object... o) {
	// System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("PrimeWords"));

		String l;
		int val;
		char let;
		sieve(52 * 21 + 7);
		while (true) {
			l = readln();
			if (l == null) {
				break;
			}
			val = 0;
			for (int i = 0; i < l.length(); i++) {

				let = l.charAt(i);
				if (Character.isUpperCase(let)) {
					// dbg(l.charAt(i), l.charAt(i) - 'A' + 27);
					val += l.charAt(i) - 'A' + 27;
				} else {
					val += l.charAt(i) - 'a' + 1;
					// dbg(l.charAt(i), l.charAt(i) - 'a' + 1);
				}
			}
			if (isPrime(val) || val == 1) {
				System.out.println("It is a prime word.");
			} else {
				System.out.println("It is not a prime word.");
			}
		}

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

	static boolean isPrime(long N) { // a good enough deterministic prime tester
		if (N < _sieve_size)
			return bs[(int) N]; // O(1) for small primes
		for (int i = 0; i < primes.size(); i++)
			if (N % primes.get(i) == 0)
				return false;
		return true; // it takes longer time if N is a large prime!
	} // note: only work for N <= (last prime in vi "primes")^2
}
