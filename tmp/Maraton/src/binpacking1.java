import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;




public class binpacking1 {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("b.in"));
		while(true) {
			readln();		
			int n = nextInt();
			if(n == 0)
				break;
			readln();
			int l = nextInt();
			ArrayList<Integer> items = new ArrayList<Integer>();
//			PriorityQueue<Integer> leer=new PriorityQueue<Integer>();
			for(int i = 0; i < n; i++) {
				readln();
				items.add(nextInt());
			}
			Collections.sort(items);
			
//			items=new ArrayList<Integer>(leer);
			int respuesta = 0;
			
			//System.out.println(items.toString());
			//for(int i = 0; i < items.size(); i++) {
			
			int tamt=0;
			int pos = 0;
			//for(int pos = items.size()-1; pos > -1; pos--) {
			while(true) {
				pos=items.size()-1;
				if(pos<0)
					break;
				
				int mayor = items.remove(pos);
				
				if(items.size()==0){
					respuesta++;
					break;
				}
				
				if(mayor == l) {
					respuesta++;
					continue;
				}
				
				int tamannoPos= l-mayor;
//				System.out.println("ajjaja"+mayor);
				
				int k=Collections.binarySearch(items, tamannoPos);
				//System.out.println(items.toString());
				//System.out.println("mayor = "+mayor+ " k = "+k+" tamannoPos = "+ tamannoPos );
				
				if(k==items.size())
				{
					respuesta++;
					int tmp = items.remove(k-1);
					//System.out.printf("sise mayor %d otro %d respuesta = %d\n",mayor, tmp, respuesta);
					continue;
				}
				else if(k>=0)
				{
					int tmp = items.remove(k);
					respuesta++;
					//System.out.printf("mayor %d otro %d respuesta = %d\n",mayor, tmp, respuesta);
					continue;
				}
				else if(k<0){
					int tmp = ((k*-1)-2);
					if(mayor + items.get((tmp >= 0)? tmp: 0) > l) {
						respuesta++;
						continue;
					}
					int tmp2 = items.remove((tmp >= 0)? tmp: 0);
					
					//System.out.printf("mayor %d otro %d respuesta = %d\n",mayor, tmp2, respuesta);
//					System.out.println("i"+i);
					respuesta++;
				}
			}
			System.out.println(respuesta);
		}

	}
	
	/*static boolean check(int bins, int tam) {
		
		return false;
	}*/
}
