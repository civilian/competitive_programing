package renamed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;



public class CountTheTrees {
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
		
//		FileWriter fw=new FileWriter("HowManyTrees");
//		for (int i = 1; i < 1001; i++) {
//			fw.write(i+"\n");
//		}
//		fw.flush();
		input=new BufferedReader(new InputStreamReader(System.in));
		
//		input=new BufferedReader(new FileReader("HowManyTrees"));
		
		
		while(true ) {
			readln();
			long n=nextInt();
			if(n==0)return;
			System.out.println(catalanNumber(n, catalanNumbers));
		}
		
	}
	
	static HashMap<Long, BigInteger> catalanNumbers=new HashMap<Long, BigInteger>();
	
	private static BigInteger catalanNumber(long n,HashMap<Long, BigInteger> listaNuCatalanes ){
		if(!catalanNumbers.containsKey(n)){
			catalanNumbers.put(n, fact(2 * n).divide(
									fact(n+1)//divide
									));//put
		}
		return catalanNumbers.get(n);
	}
	/*esta este metodo que aprovecha los calculos anteriores, pero tiene costo n para cada n
	 * con el for que tiene, y la otra alternativa del metodo necesita calculos decimales.
	 * 	private static double catR1(long n){
		if(catsR1.containsKey(n)){
			return catsR1.get(n);
		}
		double sum = 0;
		for(int i = 0; i < n; i++){
			sum += catR1(i) * catR1(n - 1 - i);
		}
		catsR1.put(n, sum);
		return sum;
	}*/
	
	/*Aux*/
	static HashMap<Long, BigInteger> hMFactoriales=new HashMap<Long, BigInteger>();
	
	static{
		hMFactoriales.put( 0L, BigInteger.ONE);
		hMFactoriales.put(1L, BigInteger.ONE);
	}
	
	static BigInteger fact(long n) {
		return fact(n, hMFactoriales);
	}
	static BigInteger fact(long n,HashMap<Long, BigInteger> listaFactoriales) {
		BigInteger tmp=hMFactoriales.get(n);
		if(tmp!=null)
			return tmp;
		/*si no tiene nada utilizo tmp para las operaciones*/
		/*hago la recursion*/
		tmp=fact(n-1, listaFactoriales);
		/*multiplico*/
		tmp=tmp.multiply(BigInteger.valueOf(n));
		/*memoizo*/
		listaFactoriales.put(n, tmp);
		return tmp;
		// TODO Auto-generated method stub	
	}
	
}
