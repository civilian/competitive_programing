import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Etree {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	
	public static void main(String[] args) throws Exception {
		new Thread(new ThreadGroup("bolita"), new Runnable() {
			public void run() {
				try {
					main2(null);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}, "nombre" ,50000000).start();
	}
	
	
	public static void main2(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		readln();
		int numCases=nextInt();
		for(int caseid=1; caseid<=numCases; caseid++) {
			
			Arbol arboles[]=new Arbol[2];
			for(int i=0; i<2; i++) {
				readln();
				
				lista.clear();
				while(_stk.hasMoreTokens()) {
					String t = next();
					if(t.equals("end")) break;
					lista.add(t);
				}
				arboles[i] = leerArbol();
			
			}
			System.out.println(equiv(arboles[0],arboles[1]));
		}
	}

	static LinkedList<String> lista = new LinkedList<String>();
	
	static Arbol leerArbol() {
		String dato = lista.removeLast();
		if(dato.equals("nil")) return null;
		Arbol res = new Arbol();
		res.dato = dato.charAt(0);
		res.der = leerArbol();
		res.izq = leerArbol();
		return res;
	}
	
	static boolean equiv(Arbol a1, Arbol a2) {
		if(a1==null && a2!=null) return false;
		if(a1!=null && a2==null) return false;
		if(a1==null && a2==null) return true;
		if(a1.dato!=a2.dato) return false;
		
		return ( equiv(a1.izq, a2.izq) && equiv(a1.der, a2.der) ) ||
			(equiv(a1.izq, a2.der) && equiv(a1.der, a2.izq));
	}
	
	static class Arbol {
		char dato;
		Arbol izq, der;
	}
}
