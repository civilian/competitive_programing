import java.io.*;
import java.util.*;
import java.math.*;

public class exponente {
  static BigInteger pow(int a, int b) {
    BigInteger x = BigInteger.ONE;
    BigInteger y = BigInteger.valueOf(a);

    while(b > 0) {
      if((b & 1) != 0)
        x = x.multiply(y);
      y = y.multiply(y);
      b >>>=1;
    }

    return x;
  }

  static class Pair implements Comparable<Pair> {
    public int b, e;

    Pair(int b, int e) {
      this.b = b;
      this.e = e;
    }

    public int compareTo(Pair o) {
      double x = e * Math.log(b);
      double y = o.e * Math.log(o.b);

      return Double.compare(x, y);
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter cout = new PrintWriter(System.out);
    StringTokenizer stk = null;
    int run = 1;

    while(true) {
      stk = new StringTokenizer(cin.readLine());
      int n = Integer.parseInt(stk.nextToken());

      if(n == 0)
        break;

      BigInteger ans = BigInteger.ZERO;

      Pair[] a = new Pair[n];

      for(int i = 0; i < n; i++) {
        stk = new StringTokenizer(cin.readLine());
        int b = Integer.parseInt(stk.nextToken());
        int e = Integer.parseInt(stk.nextToken());
        a[i] = new Pair(b, e);
      }
      Arrays.sort(a);

      cout.println(pow(a[n-1].b, a[n-1].e));
    }
    cout.close();
  }
}
