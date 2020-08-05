package maraton2011_DelMonkey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class makingbook {
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
		//input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("book.in"));
		int id = 1;
		while(true) {
			String l = readln();
			if(l.equals("0"))
				break;
			
			int A = nextInt();			
			int B = nextInt();
			
			if(A > B) {
				int tmp = A;
				A = B;
				B = tmp;
			}			
			
			int[] digitos = new int[10];
			
			for(int i = A; i <= B; i++) {
				String s = i+"";
				for(Character c : s.toCharArray()) {
					digitos[c - '0']++;
				}
			}
			
			String out = "Case "+id+":";
			
			for(int i = 0; i < 10; i++)
				out += " "+i+":"+digitos[i];
			System.out.println(out);
			
			id++;
		}
		
		
	}
}
