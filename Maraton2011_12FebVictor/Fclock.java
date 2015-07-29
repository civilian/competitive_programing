import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Fclock {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	static double nextDouble() { return Double.parseDouble(next()); }
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		readln();
		int numCases=nextInt();
		for(int caseid=1; caseid<=numCases; caseid++) {
			long hora1=0, hora2=0;
			
			readln();
			long  h = nextInt();
			long m = nextInt();
			long s = nextInt();
			long u = nextInt();
			m += h*60;
			s += m*60;
			u += s*100;
			hora1 = u;
			
			readln();
			h = nextInt();
			m = nextInt();
			s = nextInt();
			u = nextInt();
			m += h*60;
			s += m*60;
			u += s*100;
			hora2 = u;
			
			readln();
			double radio = nextDouble();
			
			
			long horaTotal = 12*60*60*100;
			long horaInter = hora2-hora1;
			
			double x = (horaInter*PI*radio*radio)/horaTotal;
			System.out.printf("%d. %.03f\n",caseid,x);
		}
	}

}
