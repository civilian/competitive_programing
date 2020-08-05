package maratonBook.matematica;

/*Con long se desborda como con 20 a lo maximo hacer pruebas*/
import java.math.BigInteger;
import java.util.HashMap;

public class Factorial {
	public static void main(String[] args) {
		System.out.println(fact(18));
		System.out.println(hMFactoriales);
		/*
		 * BigInteger fac = BigInteger.ONE;// :) for (int i = 2; i <= 25; i++)
		 * fac = fac.multiply(BigInteger.valueOf(i)); // wow :)
		 * System.out.println(fac);
		 */
	}

	static HashMap<Long, BigInteger> hMFactoriales = new HashMap<Long, BigInteger>();

	static {
		hMFactoriales.put(0L, BigInteger.ONE);
		hMFactoriales.put(1L, BigInteger.ONE);
	}

	static BigInteger fact(long n) {
		BigInteger tmp = hMFactoriales.get(n);
		if (tmp != null)
			return tmp;
		/* si no tiene nada utilizo tmp para las operaciones */
		/* hago la recursion */
		tmp = fact(n - 1);
		tmp = tmp.multiply(BigInteger.valueOf(n));
		hMFactoriales.put(n, tmp);
		return tmp;
	}
}
