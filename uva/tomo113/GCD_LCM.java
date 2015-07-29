package uva.tomo113;
/*uva 11388*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class GCD_LCM {
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
//		input=new BufferedReader(new FileReader("GCD_LCM"));
		readln();

		int numCases=nextInt();
		
		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			int g=nextInt(), l=nextInt();
			
			if((l%g)==0)
				System.out.printf("%d %d\n", g,l);
			else
				System.out.printf("%d\n", -1);
		}
		
	}
}
