package ClasesVictorPadilla.Clase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class ClockHands {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l,":");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("ClockHands"));
		

//		int numCases=nextInt();
		
		for (int casesId = 1;; casesId++) {
			
			if(readln().equals("0:00"))
				return;
			
			int iHoras=nextInt();
			int iMinutos=nextInt();
			
			double horas=iHoras;
			double minutos=iMinutos;
			
			double gradosHora=((horas*60.000)+minutos)*0.500;
			
			double gradosMinutos=minutos*6;
			
			double diferencia=Math.abs(gradosMinutos-gradosHora);
			
			if(diferencia>180.000){
				diferencia=360.000-diferencia;
			}
			
			System.out.printf("%.03f\n", diferencia);
			
		}
		
	}
}
