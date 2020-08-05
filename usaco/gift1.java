package usaco;
/*
ID: chamato1
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class gift1 {
	
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
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
//		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("gift1.in"));
		
//		int testCases=sc.nextInt();
		
		readln();
		int cantidadDeGente=nextInt();
		
		String [] gente=new String[cantidadDeGente];
		HashMap<String, Integer> regalos=new HashMap<String, Integer>();
		
		for (int i = 0; i < cantidadDeGente; i++) {
			readln();
			gente[i]=next();
			regalos.put(gente[i], new Integer(0));
		}
		
		for (int i = 0; i < cantidadDeGente; i++) {
			readln();
			String quien=next();
			
			readln();
			int dinero=nextInt();
			int quienes=nextInt();
			
//				System.out.println(quien+" da "+dinero+ " a cuantos "+ quienes);
			
			Integer daTotal=regalos.get(quien);
			
			if(quienes!=0)
			daTotal+=(dinero%quienes)-dinero;
			
			regalos.put(quien, daTotal);
			Integer dioATodos=0;
			
			if(quienes!=0)
			dioATodos=dinero/quienes;
			
			for (int J = 0;  J< quienes; J++) {
				readln();
				String aQuien=next();
				Integer da=regalos.get(aQuien);
				da+=dioATodos;
				
				regalos.put(aQuien, da);
			}
		}
		
		for (int i = 0; i < gente.length; i++) {
			output.println(gente[i]+" "+ regalos.get(gente[i]));
		}
		
		output.close();
	}

}
