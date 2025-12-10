package uva.tomo4;

import java.awt.AWTException;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MatrixChainMultiplication {
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
		input=new BufferedReader(new FileReader("MatrixChainMultiplication"));
		
		for (int casesId = 1; ; casesId++) {
			readln();
			int n=nextInt();
			//x filas y columnas
			matrices=new HashMap<Character, Matriz>();
			for (int i = 0; i < n; i++) {
				readln();
				char mat=next().charAt(0);
				int x=nextInt(),y=nextInt();
				Matriz tmp=new Matriz(x,y, 0);
				matrices.put(mat, tmp);
			}
			while (readln()!=null) {
				String e=next();
				expression= e.toCharArray();
				try {
					pos=0;
					System.out.println(parse(expression).multipli);
				} catch (AWTException ex) {
					// TODO Auto-generated catch block
					System.out.println("error");
				}
			}
			return;
		}
		
	}
	static HashMap<Character, Matriz> matrices;
	static char[] expression;
	static int pos=0;
	
	static long multipli=1L;
	static Matriz dummy= new Matriz(-1, -1, -1);
	
	static Matriz parse(char[] tk) throws AWTException{
			char letra=tk[pos];
			
			if(Character.isLetter(letra)){
				
				Matriz derecha=matrices.get(letra);
				Matriz izq;
				++pos;
				izq= calculaIzq(tk);
				return multiplicar(derecha, izq);
			}else if (letra=='(') {
				++pos;
				Matriz derecha=parse(tk);
				Matriz izq;
				++pos;
				izq= calculaIzq(tk);
				return multiplicar(derecha, izq);
				
			}else if (letra==')') {
				return dummy;
		}
		return null;
	}
	
	static Matriz multiplicar(Matriz der, Matriz izq) throws AWTException  {
		if(izq.equals(dummy))
			return der;
		
		int p=der.p;
		int s=izq.s;
		if(der.s!=izq.p)
			throw new AWTException("");
		long multipli=der.p*der.s*izq.s;
		Matriz resultado=new Matriz(p, s, multipli+izq.multipli+der.multipli);
		return resultado;
	}
	
	static Matriz calculaIzq(char [] tk) throws AWTException{
		if(pos>=tk.length){
			return dummy;
		}else
			return parse(tk);
	}
	
	static class Matriz{
		

		int p=0, s=0;
		long multipli=0;
		
		public Matriz(int p, int s, long multipli) {
			super();
			this.p = p;
			this.s = s;
			this.multipli = multipli;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (multipli ^ (multipli >>> 32));
			result = prime * result + p;
			result = prime * result + s;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			Matriz other = (Matriz) obj;
			if (multipli != other.multipli)
				return false;
			if (p != other.p)
				return false;
			if (s != other.s)
				return false;
			return true;
		}
		
	}
}
