import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class lookandsay {
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
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("lookandsay.in"));
		
		readln();
		int numcases=nextInt();
		
		for (int i = 0; i < numcases; i++) {
			readln();
			String linea=next();
			
			char tmp=linea.charAt(0);
			
			int suma=1;
			String salida="";
			for (int j = 1; j < linea.length(); j++) {
				if(tmp==linea.charAt(j)){
					suma++;
				}
				else {
					salida+=suma+""+tmp;
					tmp=linea.charAt(j);
					suma=1;
				}
			}
			salida+=suma+""+tmp;
			
			System.out.println(salida);
		}
		
		
	}

	
}
