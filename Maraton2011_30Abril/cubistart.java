package Maraton2011_30Abril;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class cubistart {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer (l, " ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			readln();
			int w = nextInt();
			int d = nextInt();
			
			if(w == 0 && d == 0)
				break;
			
			ArrayList<Integer> fw = new ArrayList<Integer>();
			ArrayList<Integer> sw = new ArrayList<Integer>();
			
			readln();
			for(int i = 0; i < w; i++)
				fw.add(nextInt());
			
			readln();
			for(int i = 0; i < d; i++)
				sw.add(nextInt());
			
			if(fw.size() < sw.size())
				System.out.println(cantidadCubos(fw, sw));
			else
				System.out.println(cantidadCubos(sw, fw));
		}		
	}
	
	static int cantidadCubos(ArrayList<Integer> a, ArrayList<Integer> b) {
		int respuesta = 0;
		
		for(Integer x : a) {
			int i = b.indexOf(x);
			if(i != -1)
				b.remove(i);
			respuesta += x;
		}
		
		for(Integer x : b)
			respuesta += x;
		return respuesta;
	}
}
