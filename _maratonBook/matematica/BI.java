package maratonBook.matematica;

import java.math.BigInteger;

public class BI {
	public static void main(String[] args) {
		BigInteger b = new BigInteger("1000");
		BigInteger ans;
		ans = b.add(BigInteger.valueOf(4));// 1005

		BigInteger c = b.abs();// valor absoluto

		BigInteger d;
		d = b.subtract(BigInteger.valueOf(-1001));// -1

		d = b.multiply(BigInteger.valueOf(2));// 2000

		d = c.divide(BigInteger.valueOf(2));// 500

		d = c.remainder(b);// 

		d = c.mod(d);// diferente de remainder.
	}
}
