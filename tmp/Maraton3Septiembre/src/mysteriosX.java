import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;



public class mysteriosX {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("a.in"));

		while(true) {
			readln();
			
			int N=nextInt();
			
			if(N==0)
				break;
			
			//camaradas = new HashMap<Integer, LinkedList<Integer>>();
			m = new LinkedList[N];
			
			/*for(int i = 0; i < 100000; i++) {
				//LinkedList<Integer> l = new LinkedList<Integer>();
				m[i] = new LinkedList<Integer>();
				for(int j = i+1; j < 100+i; j++) {
					//System.out.println("i = "+i+" j = " + j);
					//m[i][j] = true;
					m[i].add(j);
				}
				//camaradas.put(i, l);
			}*/
			//camaradas.clear();
			
			
			for (int i = 0; i < N; i++) {
				readln();
				int C=nextInt();
				m[C] = new LinkedList<Integer>();
				//LinkedList<Integer> Nc=new LinkedList<Integer>();
				int T = nextInt();
				while (T-->0) {
					//Nc.add(nextInt());
					m[C].add(nextInt());
				}
				//camaradas.put(C, Nc);				
			}
			readln();
			
			int C1 = nextInt(), C2 = nextInt();
			//C1 = 1; C2 = 100000;
			busqueda = new LinkedList<Nodo>();
			visitados = new boolean[N];
			
			Nodo inicial = new Nodo(C1, 0);
			visitados[C1] = true;
			busqueda.addLast(inicial);
			int res = 0;
			//try {
				res = cantidadCamaradas(C2);
			/*}
			catch(Exception e) {
				System.out.println(C1 + " " + C2 + " " + (res-1));
				continue;
			}*/
			//System.out.println(Arrays.toString(visitados));
			System.out.println(C1 + " " + C2 + " " + (res-1));
			busqueda.clear();			
		}
	}
	
	// return longitud
	static int cantidadCamaradas(int fin) {
		while(!busqueda.isEmpty()) {		
			Nodo n = busqueda.removeFirst();
			int inicio = n.n;		
			
			visitados[inicio] = true;
			LinkedList<Integer> p = m[inicio];
		//	boolean[] p = m[inicio];
			if(inicio == fin) {
				return (n.p);
			}
			
			for(Integer v : p) {
				if(!visitados[v]) {
					if(v == fin)
						return n.p+1;
					Nodo nuevo = new Nodo(v, n.p+1);
					busqueda.addLast(nuevo);				
				}
			}
			
			//return cantidadCamaradas(fin);
		}
		return -1;
	}
	
	
	static boolean[] visitados;
	static LinkedList<Nodo> busqueda;
	//static HashMap<Integer,LinkedList<Integer>> camaradas;	
	static LinkedList<Integer>[] m;
}

class Nodo {
	public Nodo(int n, int p) {
		this.n = n;
		this.p = p;
	}

	int n, p;
}