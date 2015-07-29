package viejo.zEntrenoEquipo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;


public class bender {
	
	static BufferedReader input;
	static StringTokenizer _stk;
	
	static String readln() throws IOException {
		String l = input.readLine();
		if(l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() {
		return _stk.nextToken();
	}
	
	static int nextInt() {
		return Integer.parseInt(next());
	}
	
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		
		input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("bender.in"));
		HashMap<String, HashMap<String, String>> tabla = new HashMap<String, HashMap<String,String>>();
		
		HashMap<String, String> tmp = new HashMap<String, String>();
		tmp.put("+z", "+z");
		tmp.put("-z", "-z");
		tmp.put("+y", "+y");
		tmp.put("-y", "-y");
		
		tabla.put("+x", tmp);
		
		tmp = new HashMap<String, String>();
		tmp.put("+z", "-x");
		tmp.put("-z", "+x");
		tmp.put("+y", "+z");
		tmp.put("-y", "+z");
		
		tabla.put("+z", tmp);
		
		tmp = new HashMap<String, String>();
		tmp.put("+z", "+x");
		tmp.put("-z", "-x");
		tmp.put("+y", "-z");
		tmp.put("-y", "-z");
		
		tabla.put("-z", tmp);
		
		tmp = new HashMap<String, String>();
		tmp.put("+z", "+y");
		tmp.put("-z", "+y");
		tmp.put("+y", "-x");
		tmp.put("-y", "+x");
		
		tabla.put("+y", tmp);
		
		tmp = new HashMap<String, String>();
		tmp.put("+z", "-y");
		tmp.put("-z", "-y");
		tmp.put("+y", "+x");
		tmp.put("-y", "-x");
		
		tabla.put("-y", tmp);
		
		
		while(true) {
			readln();
			int L = nextInt();
			
			if(L == 0)
				break;
			
			String[] movs = new String[L-1];
			readln();
			for(int i = 0; i < L-1; i++)
				movs[i] = next();
			
			String dir = "+x";
			
			for(String s : movs) {
				if(s.equals("No")) continue;
				dir = tabla.get(dir).get(s);
			}
			System.out.println(dir);
		}
	}
}
