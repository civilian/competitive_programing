
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class he {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){
		return _stk.nextToken();
	}
	static int nextInt(){
		return Integer.parseInt(next());
	}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
//	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("he"));
		
		for (int casesId = 1;; casesId++) {
			readln();
			int A=nextInt(), D=nextInt();
			
			if(A==0&&D==0) break;
			
			int[] ata=new int[A];
			int[] def=new int[D];
			readln();
			int peqAta=Integer.MAX_VALUE;
			for (int i = 0; i < A; i++) {
				ata[i]=nextInt();
				if(ata[i]<peqAta)
					peqAta=ata[i];
			}
			readln();
			for (int i = 0; i < D; i++) {
				def[i]=nextInt();
			}
//			Arrays.sort(ata);
			Arrays.sort(def);
			
//			dbg(def, peqAta);
			
			if(def[1]>peqAta)
				System.out.println("Y");
			else
				System.out.println("N");
			
		}
//		output.close();
	}
}
