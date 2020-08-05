import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class froshweek {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() { return _stk.nextToken(); }
	
	static int nextInt() { return Integer.parseInt(next()); }
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		//input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("frowshweek.in"));
		input = new BufferedReader(new FileReader("out.txt"));
		
		while(readln() != null) {
			int n = nextInt();
			
			int[] data = new int[n];
			
			for (int i = 0; i < data.length; i++) {
				readln();
				data[i] = nextInt();
			}
			
			long swaps = 0;
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 1; j < data.length-i; j++) {
					if(data[j-1] > data[j]) {
						int tmp = data[j-1];
						data[j-1] = data[j];
						data[j] = tmp;						
						swaps++;
					}
				}
				//System.out.println("data = " + Arrays.toString(data) + " swaps = " + swaps);
			}
			System.out.println(swaps);
			/*PrintStream out = new PrintStream(new File("out.txt"));			
			out.println(1000000);
			for(int i = 1000000; i > 0; i--)
				out.println(i);
			out.flush();
			out.close();*/
		}	
	}
}
