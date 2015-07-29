/*UVA 200*/
package uva.tomo2;
/*se revisan las palabras y se mira la primer letra diferente, luego se hace el toposort
con el grafo que se arma y esa es la respuesta
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class RareOrder {
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
		input=new BufferedReader(new FileReader("_template"));
		
		ArrayList<String> words=new ArrayList<String>();
		for (int casesId = 1; ; casesId++) {
			String l=readln();
			if(l.equals("#")) break;
			words.add(l);
		}
		
		for (int i = 0; i < words.size(); i++) {
			crearArista(words.get(i));
		}
	}

	boolean [][]grafo
	private static void crearArista(String string) {
		
	}
	
	static LinkedList<Integer> topoSort(){
		LinkedList<Integer> sort=new LinkedList<Integer>();
		LinkedList<Integer> huer=new LinkedList<Integer>();
		
		for (int i = 0; i < ; i++) {
			//TODO: terminar de copiar el de victor, 
			//y quitar lo que estorbe, es el mejor
		}
	}
}
