package renamed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class BinomialShowdown {
	
	static BufferedReader input;
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("Combinations"));
		
		
		for (int casesId = 1; ; casesId++) {
			readln();
			int N, M;
			N=nextInt();
			M=nextInt();
			if(N==0&&M==0) return;
			System.out.println(C(N,M));
		}
		
	}

	static int gcd(int a,int b) {
		while (b > 0) {
			a = a % b;
			a ^= b; b ^= a; a ^= b; } return a;
	}
	
	
	static int C(int n,int k){
		if(n<k)return 0;
		int numerator=1,denominator=1,toMul,toDiv,i, tmpGcd=1;/*si no es long no se puede mandar por referencia*/
		if (k>n/2) k=n-k; /* use smaller k */ 
		for (i=k;i>0;i--) {
			toMul=n-k+i; 
			toDiv=i;
			
			tmpGcd=gcd(toMul,toDiv); /* always divide before multiply */
			toMul/=tmpGcd; toDiv/=tmpGcd;
			
			tmpGcd=gcd(numerator,toDiv); /* always divide before multiply */
			numerator/=tmpGcd; toDiv/=tmpGcd;
			
			tmpGcd=gcd(toMul,denominator); /* always divide before multiply */
			toMul/=tmpGcd; denominator/=tmpGcd;
			 
			numerator*=toMul; 
			denominator*=toDiv; 
		}
		return numerator/denominator; 
	} 
}
