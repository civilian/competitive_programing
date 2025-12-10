//package usaco;
/*
ID: chamato1
LANG: JAVA
TASK: friday
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class friday {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object...o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
//		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("friday.in"));
		readln();
		int N=nextInt();
		
		int[] dias=new int[7];
		int dia=0;
		int leap=0;
		for (int i = 1900; i < 1900+N; i++) {
			
			if(isleap(i))
				leap=1;
			else leap=0;
			
			for (int j = 0; j < 12; j++) {
				dia+=13;
				dia%=7;
				dias[dia]++;
				
//				dbg("13" ,dia);
				dia+=meses[leap][j]-13;
				dia%=7;
				
//				dbg("fin mes" ,dia, i, "leap", leap);
			}
		}
		
//		dbg(dias);
		output.printf("%d",dias[6]);
		output.printf(" %d",dias[0]);
		for (int i = 1; i < 6; i++) {
			output.printf(" %d",dias[i]);
		}
		output.println();
		output.close();
	}
	
	static boolean isleap(int i) {
		if(i % 100 == 0){
			if(i % 400 ==0)
				return true;
		}
		else if(i % 4 == 0)
				return true;
		return false;
	}

	static int [][] meses=new int[][]
	                    {{31,28,31,30,31,30,31,31,30,31,30,31} 
						,{31,29,31,30,31,30,31,31,30,31,30,31}};
}
