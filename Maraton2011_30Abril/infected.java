package Maraton2011_30Abril;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

private class infected {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer (l, " ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			readln();
			int n = nextInt();
			if(n == 0)
				break;
			iiLand=new char [n][n];
			int ci = -1, cj = -1; 
			
			for (int i = 0; i < n; i++) {
				String slines=readln();
				int p = -1;
				if(ci == -1 && cj == -1)
					p = slines.indexOf("@");
				if(p != -1) {
					ci = i;
					cj = p;
				}
					
				iiLand[i]=slines.toCharArray();
			}			
		
			LinkedList<Estado> tree = new LinkedList<Estado>();
			tree.addFirst(new Estado(0, iiLand, ci, cj, -1, -1));
			
			boolean find = false;
			int agregados = 0;
			outer:
			while(tree.size() != 0 && !find) {
				Estado f = tree.getFirst();
				
				if(curada(f.t)) {
					System.out.println(f.altura);
					find = true;
					break;				
				}
				
				for(int i = 0; i < 8; i++) {				
					if(agregados == 100000)
						break outer;
					
					int ii = f.ci+dx[i];
					int jj = f.cj+dy[i]; //
										
					if(!isValid(ii, jj, f.t.length))
						continue;
					
					char[][] movida = moverCarro(f.t, ii, jj);
					if(movida != null) {
						Estado nuevoEstado = new Estado(f.altura+1, infectar(movida), ii, jj, f.ci, f.cj);
						tree.addLast(nuevoEstado);
						agregados++;
					}
				}
				tree.removeFirst();
			}
			if(!find)
				System.out.println(-1);
		}		
	}
	 
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1,  0,  1,-1, 1,-1, 0, 1 };
	
	static boolean isValid(int i, int j, int n) {
		return (i >= 0 && i < n && j >= 0 && j < n);		
	}
	
	static boolean curada(char[][] w) {				
		for(int i = 0; i < w.length; i++) 
			for(int j = 0; j < w.length; j++) 
				if(w[i][j] == '#') 
					return false;
		
		return true;
	}
	
	static char[][] moverCarro(char[][] w, int newi, int newj) {
		char[][] copia = new char[w.length][w.length];
		for(int i = 0; i < w.length; i++) {
			for(int j = 0; j < w.length; j++) {
				if(w[i][j] == '@')
					copia[i][j] = '.';
				else
					copia[i][j] = w[i][j];
			}
		}
		if(w[newi][newj] != '#')
			copia[newi][newj] = '@';
		else
			return null;
		return copia;
	}
	
	static char[][] infectar(char[][] w) {
		char[][] copia = new char[w.length][w.length];		
		for(int i = 0; i < w.length; i++) {
			for(int j = 0; j < w.length; j++) {
				if(w[i][j] == '@') {
					copia[i][j] = '@';
					continue;
				}
				int cantInf = 0;
				for(int k = 0; k < 8; k++) {
					if(isValid(i+dx[k], j+dy[k], w.length)) {
						char x = w[i+dx[k]][j+dy[k]];
						if(x == '#' || x == '@')
							cantInf++;						
					}					
				}
				char dato = w[i][j];
				if(dato == '#' && cantInf >= 2 && cantInf <= 3) {
					copia[i][j] = '#';
				}
				else if(dato == '.' && cantInf == 3) {
					copia[i][j] = '#';
				}
				else {
					copia[i][j] = '.';
				}
			}
		}
		return copia;
	}

	static char [][] iiLand;
}

class Estado {
	int altura, ci, cj;
	int anti, antj;
	char[][] t;
	Estado(int a, char[][] w, int pi, int pj, int antii, int antjj) {
		altura = a;
		t = w;
		ci = pi;
		cj = pj;
		anti = antii;
		antj = antjj;
	}	
}
