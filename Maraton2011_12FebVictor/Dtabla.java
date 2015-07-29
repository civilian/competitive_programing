package Maraton2011_12FebVictor;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Dtabla {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		readln();
		int numCases=nextInt();
		for(int caseid=1; caseid<=numCases; caseid++) {
			readln();
			N = nextInt();
			mat = new int [N][N];
			for(int i=0; i<N; i++) {
				readln();
				for(int j=0; j<N; j++)
					mat[i][j]=nextInt();
			}
			
			int arr[]=new int[N], izq[]=new int[N];
			arr[0]=mat[0][0];
			izq[0]=0;
			
			for(int i=1;i<N; i++) {
				izq[i]=mat[i][0]-arr[0];
			}
			boolean ok=true;
			outer:
			for(int j=1; j<N; j++) {
				arr[j]=mat[0][j]-izq[0];
				
				for(int i=1; i<N; i++) {
					if(izq[i]+arr[j]!=mat[i][j]) {
						ok=false;
						break outer;
					}
				}
			}
				
			System.out.println(caseid+". "+(ok?"YES":"NO"));
			
		}
	}

	static int N;
	static int mat[][];
}
