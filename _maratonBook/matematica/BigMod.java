package maratonBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class BigMod {
	/*
	 * (a+b)%M=(a%M+ b%M)%M
	 */
	/*
	 * Big Mod (b^p)%m//la iterativa es mas lenta con int's //numeros muy
	 * grandes, se toma la iniciativa de dividir para conquistar.
	 */

	/* calculates b^p mod m */
	static long bigMod(long b, long p, long m) {
		/* n^0 =1, pero cuidado que n^0 mod 1=0 */
		if (p == 0)
			return 1;
		else if (p % 2 == 0) {
			long parcial = bigMod(b, p >> 1, m) % m;// /2
			return (parcial * parcial) % m;
		} else
			return ((b % m) * bigMod(b, p - 1, m)) % m;
	}

	/* calculates a^b mod m */
	/* es mas complicado de entender y es iterativo no recursivo */
	/* es aparentemente mas lento con int */
	static long modpow(int a, int b, int m) {
		long resultado = 1; // such that a0^b0 is always p*a^b
		while (b != 0) {
			if (b % 2 == 0)
				b >>= 1;
			else {
				b = (b - 1) >> 1;
				resultado = (resultado * a) % m;
			}
			a = (a * a) % m;
		}
		return resultado % m;
	}

	public static void main(String[] args) throws FileNotFoundException {
		dbg(bigMod(2, 10, 10000));
		dbg(modpow(2, 10, 10000));
		// TODO: pruebas
	}

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}
