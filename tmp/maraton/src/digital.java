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


public class digital {
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
		calcularMatriz();
		System.out.println("end");
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
			int limite = (n < m)? n : m;
			for(int mi = 0; mi <= limite; mi++) {
				BigInteger c = evaluarP(mi, n, grado);
				elementos.add(c);
			}
			//dbg(elementos);
			System.out.println(elementos.size());
			elementos.clear();
		}
	}
	//precomputo la matriz
	static void calcularMatriz() {
		Arrays.fill(mat[0], BigInteger.ZERO);
		
		for(int i = 1; i < 100000; i++) {
			mat[i][0] = BigInteger.ONE;
		}
		long count = 0;
		for(int i = 1; i < 100000; i++) {
			for(int j = 1; j < 11; j++) {
				mat[i][j] = mat[i][j-1].multiply(new BigInteger(""+i));
				System.out.println(count++);
			}
		}
	}
	
	static HashSet<BigInteger> elementos = new HashSet<BigInteger>();
	static int[] coeficientes;
	static BigInteger[][] mat = new BigInteger[100000][11];
	
	static BigInteger evaluarP(int m, int n, int grado) {
		BigInteger ans = BigInteger.ZERO;
		BigInteger M = new BigInteger(""+m);
		
		for(int i = 0; i <= grado; i++) {
			BigInteger val = M.pow(i).multiply(new BigInteger(""+coeficientes[i]));//.mod(new BigInteger(""+n)); 
			ans = ans.add(val);
			//dbg("ans = ", ans);
		}
		ans = ans.mod(new BigInteger(""+(n+1)));
		//System.out.println();
		return ans;
	}
}
//18446744073709551616