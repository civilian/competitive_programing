import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class triangular {
  static BigInteger eval(BigInteger x) {
    BigInteger r = x.multiply(x);
    return r.add(x);
  }

  static boolean solve(BigInteger n) {
    BigInteger lo = BigInteger.ONE;
    BigInteger hi = n;
    BigInteger two = BigInteger.valueOf(2);

    while(lo.compareTo(hi) < 0) {
      BigInteger mid = lo.add((hi.subtract(lo)).divide(two));

      BigInteger val = eval(mid);

      if(val.compareTo(n.multiply(two)) >= 0) hi = mid;
      else lo = mid.add(BigInteger.ONE);
    }

    int compare = n.multiply(two).compareTo(eval(lo));
    return compare == 0;
  }

  public static void main(String args[]) {
    Scanner cin = new Scanner(System.in);

    while(true) {
      BigInteger n = cin.nextBigInteger();
      if(n.compareTo(BigInteger.ZERO) == 0)
        break;
      System.out.println(solve(n)? "SI" : "NO");
    }
  }
}
