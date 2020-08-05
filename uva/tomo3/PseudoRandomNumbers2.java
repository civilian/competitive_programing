package uva.tomo3;
/*uva 350*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class PseudoRandomNumbers2 {
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
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("PseudoRandomNumbers"));
		
		int lim=10000;
		for (int i = lim; i >0 ; i--)
			for (int jn = lim; jn >0 ; jn--)
				for (int kn = lim; kn > 0; kn--)
					for (int ln = lim; ln >0; ln--){
			readln();
			int Z=i, I=jn,M=kn,L=ln;
			int Zini=Z, Iini=I,Mini=M,Lini=L;
			
			if( Z==0&& I==0&& M==0&& L==0) break;
			
			
			LinkedList<Integer> cic=new LinkedList<Integer>();
			int limit=3*M+2;
//			limit=(limit>10000)?10000:limit;
			
		    for (int j = 0; j <limit ; j++) {
		    	cic.add(L);
		    	L=(Z*L+I)%M;
//				dbg(L);
			}
		    int k=cycleFindigLinked(cic);
		    
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
		    
		    if(j!=k){
		    	dbg("j ",j," k ",k);
		    	dbg("Z", Zini ," I ",Iini," M ",Mini," L ",Lini);
		    	return;
		    }
//			System.out.printf("Case %d: %d\n", i, cycleFindig(ciclo, 0));
		}

	}
	
//es el mas rapido el conejo se mueve de a 1 y la tortuga cada potencia
//de 2 movimientos de el conejo se teletransporta a donde esta el conejo
//asi evito dejar la tortuga en partes donde no haya ciclo
	static int cycleFindig(ArrayList<Integer> array, int x0) {
		// first phase: search successive powers of two
		
//		dbg("size", array.size(),"array ", array);
	    int power=2 , lam = 1;
	    int turtle = x0;
	    int rabbit = x0+1;//to the element/node next to x0.
	    while (true){
//	    	dbg("rabbit", rabbit, "turtle ", turtle);
	    	if(rabbit==array.size()){
	    		return -1;
	    	}

	    	if(array.get(rabbit).equals(array.get(turtle)) ){
	    		return lam;
	    	}
	    	
	        if (power == lam){ //time to start a new power of two
	            turtle = rabbit;
	            power *= 2;
	            lam = 0;
	         }
	        
	    	rabbit = rabbit+1;//next
	    	lam += 1;
	    }

	}
	
	
//es el mas rapido el conejo se mueve de a 1 y la tortuga cada potencia
//de 2 movimientos de el conejo se teletransporta a donde esta el conejo
//asi evito dejar la tortuga en partes donde no haya ciclo
	static int cycleFindigLinked(LinkedList<Integer> array) {
//CUIDADO CON UNA LISTA NULL O DE 1 SOLO ELEMENTO			
		
//		dbg("size", array.size(),"array ", array);
// first phase: search successive powers of two
	    int power=2 , lam = 1;
	    
	    Iterator<Integer> rabbitIT=array.iterator();
	    Integer turtle=null, rabbit=null;
	    if(rabbitIT.hasNext())turtle=rabbitIT.next();
	    if(rabbitIT.hasNext())rabbit=rabbitIT.next();
	    
	    while (true){
//		    	dbg("rabbit", rabbit, "turtle ", turtle);
	    	if(!rabbitIT.hasNext()){
	    		return -1;
	    	}

	    	if(rabbit.equals(turtle) ){
	    		return lam;
	    	}
	    	
	        if (power == lam){ //time to start a new power of two
	            turtle = rabbit;
	            power *= 2;
	            lam = 0;
	         }
	        
	        rabbit=rabbitIT.next();
	    	lam += 1;
	    }

	}
}
