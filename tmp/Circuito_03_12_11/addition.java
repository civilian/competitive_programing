import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;


public class addition {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l=input.readLine();
		if(l!= null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){ return _stk.nextToken();}
	static Integer nextInt(){ return Integer.parseInt(_stk.nextToken());}
	
	static void dbg(Object...o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		
		String[] arra=new String[16];
		arra[0]="{}";
		arra[1]="{{}}";
		for (int i = 2; i < 16; i++) {
			arra[i]="{";
			for (int j = 0; j < i; j++) {
				arra[i]+=arra[j];
				if(j<i-1){
					arra[i]+=",";
				}
			}
			arra[i]+="}";
		}
//		dbg(arra);
		
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("addition.in"));
		readln();
		

		int num=nextInt();
		for (int i = 0; i < num; i++) {
			readln();
			String primerN=next();
			
			readln();
			String segundoN=next();
			int suma=busqueda(arra, primerN)+busqueda(arra, segundoN);
//			System.out.println(suma);
			String salida=arra[suma];
			System.out.println(salida);
		}
		
	}

	private static int busqueda(String[] arra, String primerN) {
		for (int i = 0; i < arra.length; i++) {
			if(arra[i].equals(primerN))
				return i;
		}
		return -25;
	}


}
