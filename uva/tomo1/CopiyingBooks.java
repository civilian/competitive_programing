package uva.tomo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CopiyingBooks {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l);
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("CopiyingBooks"));
		readln();

		int numCases=nextInt();
		
		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			M=nextInt(); K=nextInt();
			readln();
			pagLibros=new int[M];
			long high=0;
			for (int i = 0; i < M; i++) {
				pagLibros[i]=nextInt();
				high+=pagLibros[i];
			}
			long cantidadPaginas=busquedaBinaria(0, high);
//			dbg(cantidadPaginas, " M ",M," k ", K,casesId);
			
			int slack=0;
			while (true) {
				int tmpCantidadPersonal = 0;
				int personas=1;
				for (int i=M-1; i >= slack; i--) {
					tmpCantidadPersonal += pagLibros[i];
//					dbg("perso ",tmpCantidadPersonal);
					if (tmpCantidadPersonal > cantidadPaginas) {
						tmpCantidadPersonal = pagLibros[i];
						personas++;
//						System.out.printf("/ ");
					}
				}
//				dbg("personas ",personas, " slakc=", slack,"K",K );
				if(personas+slack==K)
					break;
				else{ 
					slack=K-personas;
				}
			}
			StringBuilder salida=new StringBuilder();
			
			for (int i = 0; i < slack; i++) {
				salida.append(String.format("%d / ", pagLibros[i]));
			}
			long tmpCantidadPersonal = 0;
			String salidaFin="";
			for (int j=M-1; j >= slack; j--) {
				tmpCantidadPersonal += pagLibros[j];
				if (tmpCantidadPersonal > cantidadPaginas) {
					tmpCantidadPersonal = pagLibros[j];
					salidaFin="/ "+salidaFin;
				}
				salidaFin=String.format("%d ", pagLibros[j])+salidaFin;
			}
//			salidaFin=String.format("%d\n", pagLibros[i])+salidaFin;
			System.out.println(salida+salidaFin.substring(0,salidaFin.length()-1));
			
//			for (; i < M-1; i++) {
//				tmpCantidadPersonal += pagLibros[i];
////				dbg("perso ",tmpCantidadPersonal);
//				if (tmpCantidadPersonal > cantidadPaginas) {
//					tmpCantidadPersonal = pagLibros[i];
//					System.out.printf("/ ");
//				}
//				System.out.printf("%d ", pagLibros[i]);
//			}
//			System.out.printf("%d\n", pagLibros[i]);

		}
		
	}
	static int [] pagLibros;
	static int M, K, slack;
	
	static long busquedaBinaria(long lo, long hi){
		long mid;
		while (lo<hi) {
			mid=lo+((hi-lo)/2);
			if(puedenCopiarlos(mid))
				hi=mid;//descarto la parte abajo
			else
				lo=mid+1;//no pueden con esa cantidad de paginas
				//subo ese mas 1
			
//			dbg(" hi ",hi," lo ",lo, mid);
		}
		
//		if(!puedenCopiarlos(lo))
//			System.out.println("la cague");
		 return lo;
	}

	private static boolean puedenCopiarlos(long cantidadPaginas) {
		int i=0;
		int j = 0;
//		dbg(K,M);
		for (; j < K; j++) {
			long tmpCantidadPersonal=0;
			if(i==M){//para mirara cuantos quedan sin hacer nada
				return true;
			}
			for (; i < M; i++) {
				tmpCantidadPersonal+=pagLibros[i];
				if(tmpCantidadPersonal>cantidadPaginas){
					break;
				}
			}
		}
//		dbg("cantidadpag=",cantidadPaginas, i , M, K);
		if(i==M)
		return true;
		else return false;
	}
}

