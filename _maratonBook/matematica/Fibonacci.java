package maratonBook.matematica;

/*desborda un long con 92, para mayores biginteger
 Fib(O) = 0 
 Fib(l) = 1 
 Fib(n) = Fib(n--1) + Fib(n--2)

 fibGoldenRatio(i) es preciso hasta 70.

 //Pruebas
 2->1
 3->2
 20->6765
 0=1,1=1,2=1, i 92 -6246583658587674878

 * */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Fibonacci {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		long val = sc.nextLong();

		System.out.println(fibRecursivoBig((int) val));

		// FIBO DP SIMPLE
		System.out.println(fibDP(val));
		// FIBO DP SIMPLE END

		// FIBO RECURSIVO BIGINTEGER
		System.out.println(fibDPBigMemo(val));
		// FIBO RECURSIVO BIGINTEGER END

		// FIBONACCI LOG(N)
		System.out.println(fibMasRapido(val));
		// FIBONACCI LOG(N) END

		// FIBONACCI GOLDEN RATIO
		System.out.println(fibGoldenRatio(val));
		// FIBONACCI GOLDEN RATIO END

	}

	// FIBO DP SIMPLE

	static long fibDP(long n) {/* O(n) */
		long a = 0, b = 1, i, c = n;
		for (i = 2; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

	// FIBO DP SIMPLE END

	// FIBO RECURSIVO BIGINTEGER

	static HashMap<Integer, BigInteger> fiboBig = new HashMap<Integer, BigInteger>();
	static {
		fiboBig.put(0, BigInteger.ZERO);
		fiboBig.put(1, BigInteger.ONE);
	}

	static BigInteger fibRecursivoBig(Integer n) {/*
												 * sirve mas alla de 99
												 */
		BigInteger salida = fiboBig.get(n);
		if (salida == null) {
			salida = fibRecursivoBig(n - 1).add(fibRecursivoBig(n - 2));
			fiboBig.put(n, salida);
		}
		return fiboBig.get(n);
	}

	// FIBO RECURSIVO BIGINTEGER END

	// FIBO DP BIGINTEGER

	static HashMap<Long, BigInteger> fiboBigDP = new HashMap<Long, BigInteger>();
	static {
		fiboBigDP.put(0l, BigInteger.ZERO);
		fiboBigDP.put(1l, BigInteger.ONE);
	}

	static BigInteger fibDPBigMemo(long n) {/*
											 * O(n) el mas rapido para muchos
											 */
		BigInteger a = BigInteger.ZERO, b = BigInteger.ONE, c = fiboBigDP
				.get(n);
		long i;
		if (c == null) {
			for (i = 2; i <= n; i++) {
				c = a.add(b);
				a = b;
				b = c;
				fiboBigDP.put(i, c);
			}
		}// if
		return c;
	}

	// FIBO DP BIGINTEGER END

	// FIBONACCI LOG(N), UTILIZA POTENCIACION DE MATRICES [[1 1] [0 1]]=MAT FIB

	private static long fibMasRapido(long n) {/* funciona el log(n) */
		long i, h, j, k, t;
		i = h = 1;
		j = k = 0;
		while (n > 0) {
			if (n % 2 == 1) { // if n is odd
				t = j * h;
				j = i * h + j * k + t;
				i = i * k + t;
			}
			t = h * h;
			h = 2 * k * h + t;
			k = k * k + t;
			n = (long) n / 2;
		}
		return j;
	}

	// FIBONACCI LOG(N) END

	// FIBONACCI GOLDEN RATIO

	static double gRatio = (1.0 + Math.sqrt(5.0)) / 2.0;
	static double denum = Math.sqrt(5.0);

	private static long fibGoldenRatio(long i) {
		double num = Math.pow(gRatio, i);

		return (long) Math.round(num / denum);
	}

	// FIBONACCI GOLDEN RATIO END
}
