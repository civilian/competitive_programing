import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;


public class database {
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
		input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("database.in"));
		
		//readln();
		//int k = nextInt();
		//for(int id = 1; id <= k; id++) {
		while(readln() != null) {
			int n = nextInt(), m = nextInt();
			String[][] database = new String[n][m];
			
			HashMap<String, ArrayList<Integer>>[] memoria = new HashMap[m];
			for(int i = 0; i < m; i++) {
				memoria[i] = new HashMap<String, ArrayList<Integer>>();			
			}
			for(int i = 0; i < n; i++) {
				String l = readln();
				StringTokenizer stk = new StringTokenizer(l, ",");
				for(int j = 0; j < m; j++) {
					String dato = stk.nextToken();
					ArrayList<Integer> a = memoria[j].get(dato);
					if(a == null) {
						a = new ArrayList<Integer>();
					}
					a.add(i);
					memoria[j].put(dato, a);
					database[i][j] = dato;
				}
			}
			
			int r1 = -1, r2 = -1, c1 = -1, c2 = -1;
			out:
			for(int i = 0; i < n; i++) {				
				for(int j = 0; j < m; j++) {					
					ArrayList<Integer> a = memoria[j].get(database[i][j]);
					int nrow = -1;
					if(a.size() > 1) {
						for(Integer b : a) {
							if(b != i) {
								nrow = b;
								for(int c = j+1; c < m; c++) {
									if(database[i][c].equals(database[nrow][c])) {
										if(nrow < i) {
											r1 = nrow; r2 = i;									
										}
										else {
											r1 = i; r2 = nrow;
										}
										c1 = j; c2 = c;
										break out;
									}
								}
							}
						}												
					}
				}
			}
			
			if(r1 == -1 && r2 == -1 && c1 == -1 && c2 == -1) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
				System.out.println((r1+1) + " " + (r2+1));
				System.out.println((c1+1) + " " + (c2+1));
			}
		}
	}
}
