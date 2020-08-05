package renamed;

import java.io.*;
import java.util.*;

public class JollyJumpers {
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
//			input = new BufferedReader(new FileReader("JollyJumpers"));
			
			// int testCases=nextInt();
			papa:
			for(int casesId=0;; casesId++)
			{
				String hay=readln();
				if(hay==null)
				{
					return;
				}
				
				int N=nextInt();
				
				boolean [] DanReesta=new boolean[N-1];
				for (int i = 0; i < DanReesta.length; i++) {
					DanReesta[i]=false;
				}
				int A=nextInt();
				
				if(N==1)
				{
					System.out.println("Jolly");
					continue papa;
				}
				
				int B=nextInt();
				
				resta(A,B,DanReesta);
				
				for (int i = 0; i < N-2; i++) {
				// System.out.println(i);
					A=B;
					B=nextInt();
					resta(A,B,DanReesta);
				}
				
				for (int i = 0; i < DanReesta.length; i++) {
					if(!DanReesta[i])
					{
					System.out.println("Not jolly");
					continue papa;
					}
				}
				
				System.out.println("Jolly");
			}
		
		}
		
	private static boolean resta(int a, int b, boolean[] danReesta) {
		
		int c=(int)Math.abs(a-b);
		
		int largo=danReesta.length;
		if(c>largo || c==0)
		{
			return false;
		}else
		{
			danReesta[c-1]=true;
			return true;
		}
		// TODO Apéndice de método generado automáticamente
		
	}
}


