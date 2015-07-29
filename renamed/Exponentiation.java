package renamed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.StringTokenizer;

public class Exponentiation {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String nextG(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(nextG());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("Exponentiation"));
		

//		int numCases=nextInt();
		
		for (int casesId = 1;; casesId++) {
			String sLinea=readln();
			if(sLinea==null)return;
			/*R^n*/
			String sR=next();
			BigDecimal bdR=new BigDecimal(sR);
			
			int dN=nextInt();
			bdR=bdR.pow(dN);
			bdR=bdR.stripTrailingZeros();
			
			String sResultado=bdR.toPlainString();
			if(sResultado.startsWith("0."))
			sResultado=sResultado.substring(1);
			
			System.out.println(sResultado);
		}
		
	}
	
	static String next() throws IOException{
		try {
			return nextG();
		} catch (Exception e) {
			if(readln()==null)
				System.exit(0);
			return next();
			// TODO: handle exception
		}
		
	}
}
