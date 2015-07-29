package maratonBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/*Encuentra un ciclo en una lista enlazada o en una secuencia
que no se supone que se repite como numeros aleatorios, pero no sirve
para encontrar periodos de numeros racionales*/

/*IMPORTANTE encontrar un ciclo en una lista normal no aplica la 
advertencia*/

/*ADVERTENCIA Para encontrar un ciclo  se necesita una lista de 
3*M+2 con M mayor numero que puede tomar la lista(porque se empieza en seed)-, 
incluso buscando una mejor forma seria M con lo minimo lo mismo que solo
buscarlo en haset que seria mucho mas facil.
*/
/*Nombres:
 * Brent's Cycle Detection Algorithm (The Teleporting Turtle)
 * o Floyd's Tortoise and the Hare algorithm mas eficiente 
 */
public class CycleFinding {
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) {
//		int Z=7, 
//			I=5,
//			M=12,
//			L=4;
		
/*Input: lo mismo de arriba z, i, m,l
1111 1111 1111 1111
9999 9999 9999 9999
9876 5432 1234 1786
1234 5678 8956 8524
9999 1111 9999 1111
Output:
1
1
77
373
1*/
		int Z=5173, I=3849 ,M=3279 ,L=1511;
		ArrayList<Integer> ciclo=new ArrayList<Integer>();
//este for es un generador de numeros seudo random
	    for (int j = 0; j < 2*M; j++) {
	    	ciclo.add(L);
	    	L=(Z*L+I)%M;
			dbg(L);
		}
	    
	    
		cycleFindig(ciclo, 0);
	}
	
//es el mas rapido el conejo se mueve de a 1 y la tortuga cada potencia
//de 2 movimientos de el conejo se teletransporta a donde esta el conejo
//asi evito dejar la tortuga en partes donde no haya ciclo
	static int cycleFindig(ArrayList<Integer> array, int x0) {
		// first phase: search successive powers of two
		
//lam es largo del ciclo
	    int power=2 , lam = 1;
	    int turtle = x0;
	    int rabbit = x0+1;//to the element/node next to x0.
	    while (true){
//		    	dbg("rabbit", rabbit, "turtle ", turtle);
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
	    
	  //mu es primera aparicion del ciclo //mu no esta probado
	   /*PARA MU MUEVO RABIT LAM VECES, *LUEGO MUEVO RABBIT Y TURTLE
	    AL MISMO PASO MIENTRAS SEAN DIFERENTES *CONTANDO CADA PASO CON EL
	    MU OSEA MU++ Y CUANDO SEAN IGUALES YA ENCONTRE MU*/
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
