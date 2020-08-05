package uva.tomo9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class Permutations {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	static long nexLong(){return Long.parseLong(next());}
	static BigInteger nextBigi(){return new BigInteger(next());}
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("Permutations"));
		readln();

		int numCases=nextInt();
		
		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			String s=next();
			char[] array=s.toCharArray();
			Arrays.sort(array);
			StringBuilder orde=new StringBuilder();
			for (char c : array) {
				orde.append(c);
			}
			readln();
			BigInteger cual=nextBigi();
			permutacionN(orde, cual);
		}
		output.close();
//		System.out.println(fact(3));
	}
	
	static void permutacionN(StringBuilder s, BigInteger target){
		BigInteger tamannoIntervalo=fact(s.length()-1);
		if (target.compareTo(BigInteger.ZERO)==0) {
			output.println(s);
			return;
		}
		
		int limite=s.length();
		char primero=0;
		BigInteger i;
		int j;
		for (i = BigInteger.ZERO,j=0; i.compareTo(target)<=0 ; i=i.add(tamannoIntervalo),j++) {
			j=j%limite;
			primero=s.charAt(j);
		}
		
		BigInteger nTarget=target.subtract(i.subtract(tamannoIntervalo));
		s.replace(j-1, j, "");
//		System.out.println(primero);
		output.print(primero);
		permutacionN(s, nTarget);
	}
	
	static HashMap<Integer, BigInteger> hMFactoriales=new HashMap<Integer, BigInteger>();
	
	static{
		hMFactoriales.put( 0, BigInteger.ONE);
		hMFactoriales.put(1, BigInteger.ONE);
	}
	
	static BigInteger fact(Integer n) {
		BigInteger tmp=hMFactoriales.get(n);
		if(tmp!=null)
			return tmp;
		tmp=fact(n-1);
		tmp=tmp.multiply(BigInteger.valueOf(n));
		hMFactoriales.put(n, tmp);
		return tmp;
		// TODO Auto-generated method stub	
	}
	
}
