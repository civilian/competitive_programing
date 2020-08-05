package maraton2011_DelMonkey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class kinarow {
	
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
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("kinarow.in"));
		
		readln();
		int T = nextInt();
		int ganX = 0;
		int ganO = 0;
		for(int id = 1; id <= T; id++) {
			readln();
			M = nextInt();
			N = nextInt();
			int K = nextInt();
			
			tablero = new char[N][M];
			for(int i = 0; i < N; i++) {
				tablero[i] = readln().toCharArray();
				//System.out.println(Arrays.toString(tablero[i]));
			}
			if(ganar(K) == 'x') ganX++;
			else if(ganar(K) == 'o') ganO++;
		}
		System.out.printf("%d:%d\n", ganX, ganO);
	}
	
	static boolean gana(int i, int j, int K, char p) {
		int sum = 1;
		int idd = 0;
		if(K > M && K > N) {
			idd = 2;
		}
		for(; idd < 4; idd++) {
			//System.out.println("idd = " + idd);
			sum = 1;
			int ii = i+dy[idd], jj = j+dx[idd];
			while(valido(ii, jj) && tablero[ii][jj] == p) {
				sum++;
				//System.out.println("Movimiento = " + ii + ", " + jj);
				//System.out.println("tablero[ii][jj] = " + tablero[ii][jj]);
				//System.out.println("dx = " + dx[idd] + " dy =" + dy[idd]);
				if(sum == K) {
					//System.out.println("jugador = "+ p +" sum = "+sum + " pos = "+i+","+j + " posfin = "+ii+", "+jj);
					
					return true;
				}
				ii = ii+dy[idd];
				jj = jj+dx[idd];
				//System.out.println("Nuevo Movimiento = " + ii + ", " + jj);
			}
		}
		
		return false;
	}
	
	static char ganar(int K) {
		char ganador = ' ';
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tablero[i][j] == '.') {
					continue;
				}
				else {
					char p = tablero[i][j];
					if(gana(i, j, K, p))
						return p;
				}
			}
		}
		
		return ganador;
	}
	
	static boolean valido(int fila, int col) {		
		return ((fila >= 0 && fila < N) && (col >= 0 && col < M));
	}
	// 1. derecha, 2. abajo, 3. diag der, 4. diag izq
	static int[] dx = { 1, 0, 1, -1};
	static int[] dy = { 0, 1, 1, 1};
	static int N, M;
	static char[][] tablero;

}
