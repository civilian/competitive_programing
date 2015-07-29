package uva.tomo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class The3nPlus1Problem {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("The3nPlus1Problem"));
		
		for (int casesId = 1; ; casesId++) {
			if(readln()==null)
				break;
			
			int iin=nextInt(), jin=nextInt();
			int smal=iin,big=jin;
//			dbg(iin, jin);
			if(iin>jin){
				smal=jin;
				big=iin;
			}
			
			int iteraciones= 0;
			while (smal<big+1) {
				int tmp=cantidadDeIteraciones(smal);
				if(tmp>iteraciones)
					iteraciones=tmp;
				smal++;
			}

			
			System.out.printf("%d %d %d\n", iin,jin, iteraciones);
		}
		
	}
	
	static int cantidadDeIteraciones(int n){
		int iteraciones=0;
		while(true){
			iteraciones++;
//			dbg(i,j);
			if(n==1) break;
			if(n%2!=0)
				n=3*n+1;
			else
				n=n/2;
//			dbg(i,j);
		}
		return iteraciones;
	}
}
