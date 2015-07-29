package maratonBook;

public class Exponentiation {
	public static void main(String[] args) {
		System.out.println(exponent(4, 3));
		System.out.println(fastexp(4, 3));
		/* la formula built in a^n=exp(log(a)*n) */
		System.out.println(Math.pow(4, 3));/*
											 * es para mirar el resultado de la
											 * otra
											 */
		/*
		 * tiene problemas de redondeo da 63.99999999999998
		 */
		System.out.println(Math.exp(Math.log(4.0) * 3.0));
	}

	/* estandar "simple" exponent */
	static long exponent(long base, long power) {
		long i, result = 1;
		for (i = 0; i < power; i++)
			result *= base;
		return result;
	}

	/* Divide and conquer */
	static long sguare(long n) {
		return n * n;
	}

	static long fastexp(long base, long power) {
		if (power == 0)
			return 1;
		else if (power % 2 == 0)
			return sguare(fastexp(base, power / 2));
		else
			return base * (fastexp(base, power - 1));
	}
}
