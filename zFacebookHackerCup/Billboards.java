package zFacebookHackerCup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Billboards {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
//		System.out.println(Arrays.deepToString(o));
	}

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("billboards.txt"));
		readln();
		
		int numCases = nextInt();

		for (int casesId = 1; casesId <= numCases; casesId++) {
			String l=readln();
			W=nextInt();
			H=nextInt();
			tokens=new String[_stk.countTokens()];
			for (int i = 0;_stk.hasMoreTokens(); i++) {
				tokens[i]=next();
			}
			int minLe=busquedaBinaria(0, Math.min(W, H)+2);
			System.out.printf("Case #%d: %d\n",casesId,minLe);
			
		}
		// output.close();
	}
	static String[] tokens;
	static int W,H;
	static String S;
	
	private static int busquedaBinaria(int lo, int hi) throws Exception {
		int mid=-1;
		while (lo+1 < hi) {
			mid = lo + ((hi - lo) / 2);
			dbg(mid,lo,hi);
			dbg(posTamannoLetra(mid));
			if (posTamannoLetra(mid))
				lo = mid;
			else
				hi = mid-1;
		}

		if (!posTamannoLetra(lo))
			throw new Exception();

		return hi;
	}
	
	static boolean posTamannoLetra(int n){
		int alto=n,largoAc=0,largoLinea=0;
		boolean primeraPalabra=true;
		int stringActual=0;
		String token=tokens[stringActual++];//es un posIncremento tener muy en cuenta
		while (true) {
			if(alto>H)
				return false;
			
			largoAc=token.length();
			largoAc*=n;
			if(largoAc>W)
				return false;
			
			int tmp=largoLinea+largoAc+
						(((primeraPalabra)?0:1)*n);//es una segunda palabra?
//			dbg("largoLinea",largoLinea);
			if(tmp>W){
				alto+=n;
				largoLinea=0;
				continue;
			}
			else{
				if(stringActual>=tokens.length){
					if(alto<=H)
						return true;
				}
					
				else{
					largoLinea=tmp;
					token=tokens[stringActual++];//es un posIncremento tener muy en cuenta
					primeraPalabra=false;
				}
			}
			
			
		}
		
	}
}
