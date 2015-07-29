package uva.tomo5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class HeavyCargo {
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
		
		for (int casesId = 1; ; casesId++) {
			
		}
		
	}
	
	static int N,E;
	
	static HashMap<String, Integer> ids;
	static int getId(String s){
		Integer out=ids.get(s);
		if(out==null)
			ids.put(s, ids.size());
		else return out;
		
		return ids.get(s);
	}
	
	static class Edge{
		int to, 
	}
}
