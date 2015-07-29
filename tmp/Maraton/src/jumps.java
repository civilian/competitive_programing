import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class jumps {
	
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
		//input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("jumps.in"));
		int id = 1;
		while(true) {
			readln();
			n = nextInt();
			
			if(n == 0)
				break;
			
			cuadrosVacios = new int[n];
			cantidad = new int[n];			
			int sumaTotal = 0;
			for(int i = 0; i < n; i++) {
				cuadrosVacios[i] = nextInt();
				cantidad[i] = nextInt();
				sumaTotal += cantidad[i];
			}
			//System.out.println("cantidad total = "+sumaTotal);
			visitados = new HashSet<Point>();
			Point inicio = new Point(cuadrosVacios[0], 0);
			
			visitados.add(inicio);
			pila = new LinkedList<Point>();
			pila.addFirst(inicio);
			
			mover();
			
			int res = sumaTotal - visitados.size();
			//System.out.println("visitado = " + visitados.toString());
			System.out.printf("Case %d, %d squares can not be reached.\n", id, res);
			
			id++;
			visitados.clear();
		}
	}
	
	static void mover() {
		while(!pila.isEmpty()) {
			Point elemento = pila.removeFirst();
			for(int i = 0; i < 8; i++) {
				int ddfila = elemento.y + dfila[i];
				int ddcol = elemento.x + dcolumna[i];
				//System.out.println("Posible coordenada = "+ddfila+" "+ddcol);
				if(valido(ddfila, ddcol)) {
					Point nuevo = new Point(ddcol, ddfila);
					if(!visitados.contains(nuevo)) {
						pila.addFirst(nuevo);
						//System.out.println("Visitado: "+ddfila + " " + ddcol + " padre: "+elemento.y+", "+elemento.x);
						visitados.add(nuevo);
					}
				}
			}
		}
	}
	
	
	static boolean valido(int fila, int columna) {
		return ((fila >= 0 && fila < n && 
				columna >= cuadrosVacios[fila] && columna < (cuadrosVacios[fila]+cantidad[fila])));
	}
	
	static HashSet<Point> visitados;
	
	static int[] cuadrosVacios;
	static int[] cantidad;
	
	static int[] dfila = {-2,-2,-1,-1,1,1,2,2};
	static int[] dcolumna = {-1,1,-2,2,-2,2,-1,1};
	
	static int n;
	
	static LinkedList<Point> pila;

}
