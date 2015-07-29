package viejo.zEntrenoEquipo.semana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class onstoringclothes {
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
	
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		
		input = new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("clothes.in"));
		readln();
		int T = nextInt();
		
		//for(int id = 0; id < T; id++) {
			//readln(); readln();
		
		for (int idCases = 0; idCases < T; idCases++) {
			readln();
			readln();
			int N = nextInt();
			if(N == 0) break;
						
			readln();
			int L = nextInt();
			int ptr = 0;
			
			boolean[] cajones = new boolean[N];
			Arrays.fill(cajones, VACIO); // true vacio, false lleno
			
			for(int i = 0; i < L; i++) {
				readln();
				char accion = next().charAt(0);
				int n = nextInt();
				
				if(accion == 'D') {
					int pasadas=0;
					
					int j = ptr; // buscar espacio
					int inicio=j;
					boolean espacio = true;
					do {			
						espacio = true;
						inicio = j; // inicio del segmento libre
						
						for(int k = 0; k < n+2; k++, j = (j+1)%N) { // buscar segmento libre
//							dbg(k,n+2);
							if(j==ptr){
								pasadas++;
								if (pasadas>2) {
									espacio=false;
									break;
								}
							}
							
							if(cajones[j]==LLENO) { // Si el cajon no está libre break;
								espacio = false;
								
								while (cajones[j]==LLENO) {
									j = (j+1)%N;//me paso los que esten llenos
								}
								break;
							}
						}if(espacio)
							break;
						//j = (j+1)%N;
					} while(j != ptr); // termino cuando vuelvo a donde inicie
					
					if(espacio) { // si hay espacio lo lleno							
						int k = (inicio+1)%N;
						//int k = inicio;
						
						for(int m = 0; m < n; m++, k = (k+1)%N)
							cajones[k] = LLENO;							
						
						System.out.printf("The launderer gives ticket %d.\n", inicio);
						ptr = k;
					}
					else if(!espacio) { // No hay espacio en los cajones
						System.out.printf("No space left, please come back later.\n");
					}
				}
				
				else {
					ptr = n;
					int tmp = (ptr-1 < 0)? N-1: ptr-1;
					System.out.printf("The launderer gives back batch %d.\n", n);
					if(cajones[tmp]==VACIO) {
						System.out.printf("%d is freed.\n", n);// Compruebo que el anterior este libre					
					}
					tmp = n;
					for(int j = (n+1)%N; ; j= (j+1)%N) { // Compruebo que el segmento esta libre
						if(cajones[j]==LLENO) {
							cajones[j] = VACIO;
							System.out.printf("%d is freed.\n", j);
						} else { // Termino cuando lleno a un cajon no lleno
							tmp = j;
							break;
						}
					}
					if(cajones[(tmp+1)%N]==VACIO) // Si el siguiente esta libre, este tambien es liberado
						System.out.printf("%d is freed.\n", tmp);
				}				
			}	
			if(idCases!=T-1)
				System.out.println(); 
		}
	}
	 static final boolean LLENO=true ;
	 static final boolean VACIO=false;
}
