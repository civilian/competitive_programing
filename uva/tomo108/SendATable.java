package uva.tomo108;
/*uva 10820*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class SendATable {
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
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("SendATable"));
		
		results.put(1, 1);
		totientSiev(50001);
		for (int casesId = 0; ; casesId++) {
			readln();
			int n=nextInt();
			if(n==0)break;
			System.out.println(calculate(n));
		}
		
		
		
//		for (int casesId = 1; casesId<50001; casesId++) {
//			int n=casesId;
//			if(n==0)break;
//			System.out.println(calculate(n));
//		}
		
	}
	
	static int calculate(int n) {
		Integer o=results.get(n);
		if(o==null){
			for (int i = desdeFor; i < n+1; i++) {
				results.put(i, results.get(i-1)+2*(nrp[i]));
				desdeFor++;
			}
			return results.get(n);
		}
		return o;
	}
	
	static int desdeFor=2;
	static HashMap<Integer, Integer> results=new HashMap<Integer, Integer>();
	
//	 public static int totient(int num){ //euler's totient function calculator. returns totient
//		    int count=0;
//		    for(int a=1;a<num;a++){ //definition of totient: the amount of numbers less than num coprime to it
//		      if(GCD(num,a)==1){ //coprime
//		        count++;
//		      }
//		    }
//		    return(count);
//	}
//	 
//	static int GCD(int a,int b) {
//		while (b > 0) {
//			a = a % b;
//			a ^= b; b ^= a; a ^= b; } return a;
//	}
	
	private static int[] nrp;
	//el limite es inclusivo
	public static void totientSiev(int limit){
		limit++;
		nrp=new int[limit];
		for(int i=0;i<limit;i++)
		     nrp[i]=i;
		for(int i=2;i<limit;i++)
		     if(nrp[i]==i)
		     {
		          nrp[i]--;
		          for(int j=i<<1;j<limit;j+=i)
		               nrp[j]=nrp[j]*(i-1)/i;
		     }
		
		nrp[1]=0;
	}
}
