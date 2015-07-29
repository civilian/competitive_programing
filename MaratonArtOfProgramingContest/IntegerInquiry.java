package MaratonArtOfProgramingContest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Locale;
import java.util.StringTokenizer;

public class IntegerInquiry {
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
		input=new BufferedReader(new FileReader("IntegerInquiry"));
		

//		int numCases=nextInt();
		String sLiena;
		BigInteger biResultado=BigInteger.valueOf(0);
		BigInteger biSumando=BigInteger.valueOf(0);
		for (int casesId = 1;; casesId++) {
			sLiena=readln();
			
			if(sLiena.equals("0")){
				System.out.println(biResultado);
				return;
			}
			
			biSumando=new BigInteger(sLiena);
			
			biResultado=biResultado.add(biSumando);
		}
		
	}
}
