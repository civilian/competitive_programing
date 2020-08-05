import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class powercrisis {

	static BufferedReader input;
	static StringTokenizer _stk;
	static LinkedList<Integer> zonas;
	
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() { return _stk.nextToken(); }
		static int nextInt() { return Integer.parseInt(next()); }
	
	
	public static void main(String[] args) throws IOException {
		
		input = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			readln();
			int n = nextInt();
			if (n == 0) break;
			
			zonas = new LinkedList<Integer>();
			
			for(int i = 1; i <= n; i++)
				zonas.add(i);
			
			int m = 1;
			
			while(true) {
			
				LinkedList<Integer> copia = new LinkedList<Integer>(zonas);
				int pos = 0;
				while(copia.size() != 1) {
				
					//System.out.println(Arrays.toString(copia.toArray()));
					copia.remove(pos);
					pos += m-1;
					if(pos >= copia.size()) pos = pos % copia.size();
				}
				
				int res = copia.removeFirst();
				if (res == 13) {
					System.out.println(m);
					break;
				}
				m++;
			}
			
		}

	}
}
