package MaratonArtOfProgramingContest;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class BigMod {
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("BigMod"));
		while (sc.hasNext()) {
			
			long b=0,
			m=0,
			p=0;
			
			b=sc.nextLong();
			p=sc.nextLong();
			m=sc.nextLong();
			
			System.out.println(bigMod(b, p, m));
			
			//System.out.println(b.pow(p.intValue()).mod(m));
		}
	}
	
	static long bigMod(long b,long p,long m){
		long resultado=0;
		if(p==0)
			return 1;
		else if(p%2==0)
		{
			long parcial=bigMod(b, p/2, m)%m;
			return (parcial*parcial)%m;
		}
		else return ((b%m)*bigMod(b,p-1,m))%m;
	}
	
	/*   calculates a^b mod m   */
	/*es aparentemente mas lento*/
	static long   modpow(int a, int b, int m) {
	   long resultado = 1;   //   such that a0^b0 is always p*a^b
	   while (b != 0) {
	      if (b%2 == 0)   b/= 2;
	      else {
	         b = (b-1)/2;
	         resultado = (resultado*a)%m;
	      }
	      a = (a*a)%m;
	   }
	   return resultado%m;
	}

}
