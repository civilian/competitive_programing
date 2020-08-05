package uva.tomo3;
/*uva 350*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class PseudoRandomNumbers3 {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object...o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("PseudoRandomNumbers"));
		
		int lim=10000;
		for (int i = 1;; i++){
			readln();
			int Z=nextInt(), I=nextInt(),M=nextInt(),L=nextInt();
			int Zini=Z, Iini=I,Mini=M,Lini=L;
			
			if( Z==0&& I==0&& M==0&& L==0) break;
		    
		    HashSet<Integer> ciclo=new HashSet<Integer>();
		    Z=Zini; I=Iini;M=Mini;L=Lini;
		    
		    for (int j = 0;; j++) {
		    	
		    	if(!ciclo.add(L)){
		    		break;
		    	}
		    	L=(Z*L+I)%M;
//				dbg(L);
			}
		    
		    int first=L;
		    int j = 1;
		    for (;; j++) {
		    	L=(Z*L+I)%M;
				if(L==first){
//					System.out.println(j);
					break;
				}
			}
		    
			System.out.printf("Case %d: %d\n", i, j);
		}

	}
	
}
