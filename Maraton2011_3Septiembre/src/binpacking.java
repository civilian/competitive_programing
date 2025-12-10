package Maraton2011_3Septiembre.src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;




public class binpacking {
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
			for(int i = 0; i < n; i++) {
				readln();
				items.add(nextInt());
			}
			Collections.sort(items);
			int respuesta = 0;
			int tam = l;
			int cant = 0;
			System.out.println(items.toString());
			//for(int i = 0; i < items.size(); i++) {
			for(int i = items.size()-1; i > -1; i--) {
				int mayor = items.remove(i);
				if(i == 0) {
					respuesta++;
					break;
				}
					
				for(int j = items.size()-1; j > -1; j--) {
					int tamt = mayor + items.get(j);
					if(tamt <= l) {
						//System.out.println("mayor = "+mayor + " otro = "+ items.get(j) );
						items.remove(j);
						i = items.size();
						respuesta++;
						//System.out.println(items.toString());
						break;
					}
					else if(j == 0) {
						//System.out.println("mayor = " + mayor);
						respuesta++;
					}
				}				
				
				/*int index = items.size()-(i+1);
				int it = items.get(index);
				if(it < tam) {
					if(cant < 2) {
						tam -= it;
						cant++;
						items.remove(index);
					}
					else {
						respuesta++;
						cant = 0;
						//i = 0;
					}
					continue;
				}
				else if(it > tam) {
					//if(i == items.size()-1) {
						respuesta++;
						//i = 0;
						//items.remove(index);
					//}
					continue;
				}
				else {
					i = 0;
					respuesta++;
					items.remove(index);
				}*/
			}
			System.out.println(respuesta);
		}
//		int numCases=nextInt();
//		for (int i = 0; i < numCases; i++) {
//			
//		}
	}
	
	/*static boolean check(int bins, int tam) {
		
		return false;
	}*/
}
