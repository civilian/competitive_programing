package maraton2011_DelMonkey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;




public class Molde {
	
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
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		
		
	}

}
