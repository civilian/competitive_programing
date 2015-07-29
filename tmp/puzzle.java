import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;


public class puzzle {
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
        //input = new BufferedReader(new FileReader("template.in"));
        
        bfs();
        
        while(true) {
        	String l = readln();
        	if(l.equals("0"))
        		break;
        	StringBuilder tablero = new StringBuilder(l.replaceAll(" ", ""));
        	
        	for(int i = 1; i < 3; i++) {
        		tablero.append(readln().replaceAll(" ", ""));
        	}
        	String ans = tableros.get(tablero.toString());        	
        	System.out.println((ans != null)? (ans.length()/2)+" "+ans : "Not solvable");
        }
    }
    
    static String mover(String t, String m) {
    	char[] nuevo = t.toCharArray();
    	if(m.equals("H1")) {
    		char tmp = nuevo[0];
    		nuevo[0] = nuevo[1];
    		nuevo[1] = nuevo[2];
    		nuevo[2] = tmp;
    	}
    	else if(m.equals("H2")) {
    		char tmp = nuevo[3];
    		nuevo[3] = nuevo[4];
    		nuevo[4] = nuevo[5];
    		nuevo[5] = tmp;
    	}
    	else if(m.equals("H3")) {
    		char tmp = nuevo[6];
    		nuevo[6] = nuevo[7];
    		nuevo[7] = nuevo[8];
    		nuevo[8] = tmp;
    	}
    	else if(m.equals("V1")) {
    		char tmp = nuevo[6];
    		nuevo[6] = nuevo[3];
    		nuevo[3] = nuevo[0];
    		nuevo[0] = tmp;
    	}
    	else if(m.equals("V2")) {
    		char tmp = nuevo[7];
    		nuevo[7] = nuevo[4];
    		nuevo[4] = nuevo[1];
    		nuevo[1] = tmp;
    	}
    	else if(m.equals("V3")) {
    		char tmp = nuevo[8];
    		nuevo[8] = nuevo[5];
    		nuevo[5] = nuevo[2];
    		nuevo[2] = tmp;
    	}
    	return new String(nuevo);
    }
    
    static void bfs() {
    	LinkedList<Nodo> g = new LinkedList<Nodo>();
    	String tini = "123456789", movsini = "";
    	
    	Nodo n = new Nodo(tini, movsini);
    	g.addLast(n);
    	tableros.put(tini, movsini);
    	
    	String[] movimientos = new String[]{ "H3", "V3", "H2", "V2", "H1", "V1" };
    	while(!g.isEmpty()) {
    		Nodo e = g.removeFirst();
    			    		
    		for(String m : movimientos) {
    			String r = mover(e.tablero, m);
    			if(!tableros.containsKey(r)) {
    				Nodo nt = new Nodo(r, m+e.movimientos);
    				tableros.put(nt.tablero, nt.movimientos);
    				g.addLast(nt);
    			}
    		}
    	}
    }
    
    static class Nodo {
    	String tablero, movimientos;

		public Nodo(String tablero, String movimientos) {
			this.tablero = tablero;
			this.movimientos = movimientos;
		}		
    }    
    
    static HashMap<String, String> tableros = new HashMap<String, String>();
}
