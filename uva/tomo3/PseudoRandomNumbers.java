package uva.tomo3;
/*uva 350*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class PseudoRandomNumbers {
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
		
		for (int i = 1;; i++){
			readln();
			int Z=nextInt(), I=nextInt(),M=nextInt(),L=nextInt();
			
			if( Z==0&& I==0&& M==0&& L==0) break;
			
			LinkedList<Integer> ciclo=new LinkedList<Integer>();
			int limi=3*M+2;
			limi=(limi==0)?4:limi;
		    for (int j = 0; j < limi; j++) {
		    	ciclo.add(L);
		    	L=(Z*L+I)%M;
//				dbg(L);
			}
			System.out.printf("Case %d: %d\n", i, cycleFindigLinked(ciclo));
		}

	}
	
//es el mas rapido el conejo se mueve de a 1 y la tortuga cada potencia
//de 2 movimientos de el conejo se teletransporta a donde esta el conejo
//asi evito dejar la tortuga en partes donde no haya ciclo
		static int cycleFindigLinked(LinkedList<Integer> array) {
	//CUIDADO CON UNA LISTA NULL O DE 1 SOLO ELEMENTO			
			
//			dbg("size", array.size(),"array ", array);
	// first phase: search successive powers of two
		    int power=2 , lam = 1;
		    
		    Iterator<Integer> rabbitIT=array.iterator();
		    Integer turtle=null, rabbit=null;
		    if(rabbitIT.hasNext())turtle=rabbitIT.next();
		    if(rabbitIT.hasNext())rabbit=rabbitIT.next();
		    
		    while (true){
//			    	dbg("rabbit", rabbit, "turtle ", turtle);
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
