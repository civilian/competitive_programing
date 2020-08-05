package uva.tomo8;
/*Uva 893*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class Y3KProblem {
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
//		input=new BufferedReader(new FileReader("Y3KProblem"));
		while (true) {
			readln();
			int N=nextInt(),
				D=nextInt(),
				M=nextInt(),
				Y=nextInt();
			if (N==0 &&D==0 &&M==0 &&Y==0) {
				break;
			}
			
			GregorianCalendar fecha=new GregorianCalendar(Y,M-1,D);
			fecha.add(GregorianCalendar.DATE, N);
			
			SimpleDateFormat form=new SimpleDateFormat("d M yyyy");
			System.out.println(form.format(fecha.getTime()));
		}
		
	}
}
