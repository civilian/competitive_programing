package uva.tomo104;
/*Uva 10420*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class ListOfConquests {
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
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("ListOfConquests"));
		readln();

		int numCases=nextInt();
		ArrayList<String> alPaises=new ArrayList<String>(numCases);
		HashMap<String, Integer> hPaises=new HashMap<String, Integer>(numCases);
		Integer n;
		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			String pais=next();
			n=hPaises.get(pais);
			
			if(n==null){
				n=0;
				alPaises.add(pais);
			}
			n++;
			hPaises.put(pais, n);
		}
		Collections.sort(alPaises);
		for (String pais : alPaises) {
			System.out.printf("%s %d\n", pais, hPaises.get(pais));
		}
		
	}
}
