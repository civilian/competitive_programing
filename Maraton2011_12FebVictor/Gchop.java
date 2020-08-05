package Maraton2011_12FebVictor;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Gchop {
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
		
		for(int caseid=1; ; caseid++) {
			readln();
			String line = next();
			if(line.equals("bye")) break;
			
			int N = line.length();
			int[][] cont = new int[N][N];
			for(int i=0; i<N; i++) {
				cont[i][i]=line.charAt(i)-'0';
				for(int j=i+1; j<N; j++) {
					cont[i][j] = cont[i][j-1]+line.charAt(j)-'0';
				}
			}
			
			System.out.println(Arrays.deepToString(cont));
			
			int dp[][]=new int[N][N];
			for(int i=N-1; i>=0; i--) {
				dp[i][N-1]=1;
				int salto=1;
				if(i+salto>=N) continue;
				for(int j=i; j<N-1; j++, salto++) {
					
					for(int jj=j+1; jj<N; jj++) {
						if(cont[i+salto][jj]>=cont[i][j])
							dp[i][j]+=dp[i+salto][jj];
					}
				}
			}
			
			System.out.println(Arrays.deepToString(dp));
			
			int sum=0;
			for(int i=0; i<N; i++) sum+=dp[0][i];
			System.out.println(caseid+". "+sum);
		}
	}

}
