package viejo.zEntrenoEquipo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;


//Maraton Nacional 2008 

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
	
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		
		input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("puzzle.in"));
				
		while(true) {
			String l = readln().replaceAll(" ", "");
			
			if(l.equals("0"))
				break;
			
			char[][] board = new char[3][3];
			board[0] = l.toCharArray();
			for(int i = 1; i < 3; i++) {
				l = readln();
				board[i] = l.replaceAll(" ", "").toCharArray();
			}
			System.out.println(busqueda(board));
		}
	}
	
	static char[][] moverHorizontal(char[][] b, int fila) {
		char[][] nuevo = new char[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				nuevo[i][j] = b[i][j];
		
		nuevo[fila][0] = b[fila][2];
		nuevo[fila][1] = b[fila][0];
		nuevo[fila][2] = b[fila][1];
		
				
		return nuevo;
	}
	
	static char[][] moverVertical(char[][] b, int col) {
		char[][] nuevo = new char[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				nuevo[i][j] = b[i][j];
		
		nuevo[0][col] = b[1][col];
		nuevo[1][col] = b[2][col];
		nuevo[2][col] = b[0][col];
				
		return nuevo;
	}
	
	
	static String busqueda(char[][] b) {		
		LinkedList<State> arbol = new LinkedList<State>();
		HashSet<Integer> visitados = new HashSet<Integer>();
		
		arbol.addFirst(new State(b, ""));
		int d = 1;
		while(!arbol.isEmpty()) {
			State el = arbol.removeFirst();
			visitados.add(el.hashCode());
			d = el.movimientos.length()/2;
			//if(el.equals(finalState)) {
			if(el.hashCode() == solucionHash) {
				dbg(el.b);
				return (el.movimientos.length()/2)+" "+el.movimientos;
			}
			
			if(d > 36)
				return "Not solvable";
			
			for(int i = 0; i < 3; i++) {
				char[][] nuevo = moverHorizontal(el.b, i);
				String movs = el.movimientos+"H"+(i+1);
				State eln = new State(nuevo, movs);
				if(!visitados.contains(eln.hashCode())) {
					arbol.addLast(eln);
					d++;
				}
			}
			
			for(int i = 0; i < 3; i++) {
				char[][] nuevo = moverVertical(el.b, i);
				String movs = el.movimientos+"V"+(i+1);
				State eln = new State(nuevo, movs);
				if(!visitados.contains(eln.hashCode()))
					arbol.addLast(eln);
			}
		}
		
		return "Not solvable";
	}
	
	static final State finalState = new State(new char[][]{{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}}, "");
	static final int solucionHash = finalState.hashCode();
	
	static class State {
		char[][] b;
		String movimientos;
		
		public State(char[][] b, String movimientos) {
			this.b = b;
			this.movimientos = movimientos;
		}

		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + Arrays.deepHashCode(b);			
			return result;
		}

		@Override
		public boolean equals(Object obj) {			
			final State other = (State) obj;
			if (!Arrays.deepEquals(b, other.b))
				return false;
			
			return true;
		}
		
		
	}
}


