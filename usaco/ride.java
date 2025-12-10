
/*
ID: chamato1
LANG: JAVA
TASK: ride
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

public class ride {
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
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
//		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("ride.in"));
		
		char[] l1=readln().trim().toCharArray();
		char[] l2=readln().trim().toCharArray();
		int comet=1, gruop=1;
		for (int i = 0; i < l1.length; i++) {
			int actual=(l1[i]-'A'+1);
//			dbg(actual);
			comet=(comet*actual);
		}
//		System.out.println();
		//TODO: ESTA FALLANDO
		for (int i = 0; i < l2.length; i++) {
			int actual=(l2[i]-'A'+1);
//			dbg(actual);
			gruop=(gruop*actual);
		}
//		dbg("comet", comet);
		output.println((comet%47==gruop%47)? "GO":"STAY");
		output.close();
	}
	
	static void dbg(Object...o) {
		System.out.println(Arrays.deepToString(o));
	}
}
