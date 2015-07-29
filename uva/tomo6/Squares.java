package uva.tomo6;
/*Uva 636*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

public class Squares {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o){
//		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("Squares"));
			
//		int a=61;
//		int base=8;
//		int abase=Integer.parseInt(a+"",base);
//		dbg(abase);
//		int rt=(int) Math.sqrt(abase);
//		dbg(Math.sqrt(abase));
//		dbg(rt*rt);
		
		for (int casesId = 0;; casesId++) {
			readln();
			int n=nextInt();
			if(n==0) return;
			dbg(n);
			for (int i = 2; i < 100; i++) {/*tempo*/
				long nBase;
				try {
					nBase = deBaseADecimal(n+"", i);
				} catch (NumberFormatException e) {
					continue;
				}
				dbg(nBase);
				int a=(int) Math.sqrt(nBase);
				dbg(a*a);
				if(a*a==nBase){
					System.out.println(i);
					break;
				}
			}
			
		}
		
	}
	
	static private long deBaseADecimal(String numero, int base) throws NumberFormatException {
		/* refinamiento de las versiones con vectores */
		long resultado = 0;
		// System.out.println(numero);
		for (int i = numero.length() - 1, K = 0; i > -1; K++, i--) {
			int digito = numero.charAt(i)-'0';

			if (digito >= base)
				throw new NumberFormatException();
			resultado += digito * pow(base, K);
			// System.out.println(resultado);
		}
		return resultado;
	}
	
	static private long pow(int base, int exp) {
		long result=1;
		for (int i = 0; i < exp; i++) {
			result*=base;
		}
		return result;
	}
}
