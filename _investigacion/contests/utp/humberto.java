import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class humberto {
  static long eval(long x) {
    return x*x + 2L*x + 1;
  }

  static long solve(long n) {
    long lo = 1, hi = n;

    while(lo < hi) {
      long mid = lo + (hi-lo) / 2;

      long val = eval(mid);

      if(val >= n) hi = mid;
      else lo = mid+1;
    }
    return lo+1;
  }

  public static void main(String args[]) {
    Scanner cin = new Scanner(System.in);

    while(true) {
      long n = cin.nextLong();
      if(n == 0)
        break;
      if(n == 1) {
        System.out.println(1);
      }
      else {
        System.out.println(solve(n));
      }
    }
  }
}
