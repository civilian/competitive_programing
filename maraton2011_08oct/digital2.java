package maraton2011_08oct;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;


public class digital2 {
	static BufferedReader input;
	static StringTokenizer _stk;
	
	static String readln() throws IOException {
		String l = input.readLine();
		if(l != null)
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
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new FileReader("digital.in"));
		
		while(true) {
			readln();
			int n = nextInt(), m = nextInt();
			if(n == 0 && m == 0)
				break;
			readln();
			int grado = nextInt();
			coeficientes = new int[grado+1];
			readln();
			for(int i = 0; i <= grado; i++) {				
				coeficientes[i] = nextInt();
			}
			//dbg(coeficientes);
			//int count = 0;
			//int limite = (n < m)? n : m;
			for(int mi = 0; mi <= m; mi++) {
				long c = evaluarP(mi, n, grado);
				elementos.add(c);
			}
			//dbg(elementos);
			System.out.println(elementos.size());
			elementos.clear();
		}
	}
	
	
	//static HashSet<BigInteger> elementos = new HashSet<BigInteger>();static HashSet<BigInteger> elementos = new HashSet<BigInteger>();
	static HashSet<Long> elementos = new HashSet<Long>();
	static int[] coeficientes;
	
	
	static long bigMod(long b, long p, long m) {
		long resultado = 0;
		
		if(p == 0)
			return 1;
		else if(p%2 == 0) {
			long parcial = bigMod(b, p/2, m)%m;
			return (parcial*parcial)%m;
		}
		
		return resultado;
	}
	
	static long evaluarP(int m, int n, int grado) {
		long ans = 0;
		long modn = n+1;
		long pot = 1;
		
		for(int i = 0; i <= grado; i++) {
			if(i > 0)
				pot = (pot*m)%modn;
			//long val = (coeficientes[i]%modn)*bigMod(m, i, modn);
			long val = (coeficientes[i]%modn)*pot;
			val %= modn;
			ans += val;
		}
		
		return (ans%modn);
	}
	
	/*
	static BigInteger evaluarP(int m, int n, int grado) {
		BigInteger ans = BigInteger.ZERO;
		BigInteger M = new BigInteger(""+m);
		BigInteger modN = new BigInteger(""+(n+1));
		BigInteger pot = BigInteger.ONE;
		
		for(int i = 0; i <= grado; i++) {
			BigInteger c = new BigInteger(""+coeficientes[i]);
			if(i > 0)
				pot = pot.multiply(M);
			BigInteger val = pot.mod(modN);
			val = val.multiply(c.mod(modN));//.mod(new BigInteger(""+n)); 
			val = val.mod(modN);
			ans = ans.add(val);
			//dbg("ans = ", ans);
		}
		ans = ans.mod(modN);
		//System.out.println();
		return ans;
	}*/
}
//18446744073709551616