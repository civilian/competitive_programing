package uva.tomo100;
/*Uva 10055*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class HashmattheBraveWarrior {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static long nextInt() {
		return Long.parseLong(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	 static PrintWriter output = new PrintWriter(new BufferedWriter(new
	 OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("HashmattheBraveWarrior"));
		long a,b;
		while (readln()!=null) {
			a=nextInt(); b=nextInt();
			if(b>a)
				output.println(b-a);
			else {
				output.println(a-b);
			}
		}
		 output.close();
	}
}
