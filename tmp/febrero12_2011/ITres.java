import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class ITres {
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
		sieve();
		
		while(true) {
			readln();
			int num = nextInt();
			if(num==-1) break;
			
			boolean ok=true;
			for(int pri : primos) {
				/*
				if(pri==num) {
					if(num%10!=3)
						ok=false;
					break;
				}*/
				if(pri>num) break;
				if(num%pri==0)
					if(pri%10!=3) {
						ok=false;
						break;
					}
			}
			
			System.out.println(num+" "+(ok? "YES" : "NO"));
		}
	}

	static boolean divisible[]=new boolean[1000000+1];
	static LinkedList<Integer> primos=new LinkedList<Integer>();
	
	static void sieve() {
		divisible[1]=true;
		int raiz=1000;
		for(int i=2; i<=raiz; i++)
			if(!divisible[i])
				for(int k=i+i; k<divisible.length; k+=i)
					divisible[k]=true;
		
		for(int i=2; i<divisible.length; i++)
			if(!divisible[i]) primos.add(i);
		//System.out.println(primos);
	}
}
