package uva.tomo4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class FibonacciFreeze {
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
		input=new BufferedReader(new FileReader("FibonacciFreeze"));
		
//		for (int casesId = 1; readln()!=null; casesId++) {
//			int n=nextInt();
//			System.out.printf("The Fibonacci number for %d is %d\n", n, fibRecursivoBig(n));
//		}
		
		System.out.println(fiboBig.toString());
	}
	
	static HashMap<Integer, BigInteger> fiboBig=new HashMap<Integer, BigInteger>();
	static{
		fiboBig.put(0, BigInteger.ZERO);
		fiboBig.put(1, BigInteger.ONE);
		for (int i = 2; i < 5001; i++) {
			fibRecursivoBig(i);
		}
	}
	
	static BigInteger fibRecursivoBig(Integer n){/*sirve mas alla de
												99*/
		BigInteger salida=fiboBig.get(n);
		if(salida==null){
			salida=fibRecursivoBig(n-1).add(fibRecursivoBig(n-2));
			fiboBig.put(n, salida);
		}
		return fiboBig.get(n);
	}
}
