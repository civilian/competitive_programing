package baseNumberConversion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class SimpleBaseConversion {
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
		input=new BufferedReader(new FileReader("SimpleBaseConversion"));
//		int numCases=nextInt();
		
		for (int casesId = 1;; casesId++) {
			readln();
			String sLinea=next();
			if(sLinea.startsWith("0x")){
				System.out.println(Long.parseLong(sLinea.substring(2), 16));
			}else{
				int numero=Integer.parseInt(sLinea);
				if(numero<0)
					return;
				System.out.println("0x"+Integer.toHexString(numero).toUpperCase());
				
			}
		}
		
	}
}
