package uva.tomo2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class Quadtrees {
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
		input=new BufferedReader(new FileReader("Quadtrees"));
		readln();

		int numCases=nextInt();
		
		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			String arbolA=next();
			donde=0;
		}
		
	}
	static int donde;
	
	QTree parsear(String expresion){
		QTree salida;
		char root=expresion.charAt(donde);
		donde++;
		if(root=='f'||root=='e')
		{
			QTree arbol=new QTree(null,root);
			return arbol;
		}
		QTree[] sons= new QTree[4];
		for (int i = 0; i < 4; i++) {
			donde++;
			
		}
		return salida;
	}
	
	class QTree{
		QTree[] hijos;
		char valor=0;
		public QTree(QTree[] hijos, char valor) {
			this.hijos = hijos;
			this.valor = valor;
		}
		
	}
}
