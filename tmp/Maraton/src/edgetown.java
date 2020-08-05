import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;


public class edgetown {
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
		input = new BufferedReader(new FileReader("edgetown.in"));
			
		while(true) {
			readln();
			int V = nextInt();
			
			if(V == 0)
				break;
			
			grafoOriginal = new int[V+1][V+1];
			grafoModificado = new int[V+1][V+1];
			
			for(int i = 0; i <= V; i++) {
				Arrays.fill(grafoOriginal[i], 10000);
				Arrays.fill(grafoModificado[i], 10000);
			}
			
			for(int i = 0; i < V; i++) {
				readln();
				int ii = nextInt();
				while(_stk.hasMoreTokens()) {
					int j = nextInt();
					grafoOriginal[ii][j] = 1;
					//grafoOriginal[j][ii] = 1;
				}
			}
			
			for(int i = 0; i < V; i++) {
				readln();
				int ii = nextInt();
				while(_stk.hasMoreTokens()) {
					int j = nextInt();
					grafoModificado[ii][j] = 1;					
				}
			}
			
			readln();
			int A = nextInt(), B = nextInt();
			
			for(int k = 1; k <= V; k++) {
				for(int i = 1; i <= V; i++) {
					for(int j = 1; j <= V; j++) {
						grafoOriginal[i][j] = Math.min(grafoOriginal[i][j], grafoOriginal[i][k] + grafoOriginal[k][j]);
						grafoModificado[i][j] = Math.min(grafoModificado[i][j], grafoModificado[i][k] + grafoModificado[k][j]);
					}
				}
			}
			//dbg("orginal = ", grafoOriginal);
			//dbg("modificado = ", grafoModificado);
			boolean valido = true;
			out:
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(i == j)
						continue;
					int ecuacion = A*grafoOriginal[i][j] + B;
					if(grafoModificado[i][j] > ecuacion) {
						valido = false;
						break out;
					}
				}
			}
			System.out.println((valido)? "Yes":"No");
		}
	}
	
	static int[][] grafoOriginal;
	static int[][] grafoModificado;
}
