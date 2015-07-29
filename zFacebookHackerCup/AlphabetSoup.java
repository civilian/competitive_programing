package zFacebookHackerCup;

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

public class AlphabetSoup {
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

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("alphabet_soup.txt"));
		readln();

		int numCases = nextInt();

		for (int casesId = 1; casesId <= numCases; casesId++) {
			String lin=readln();
			
//			dbg(lin);
			int[] lett=new int['Z'-'A'+2];
			for (int i = 0; i < lin.length(); i++) {
//				dbg(lin.charAt(i)-'A');
//				dbg(lin.charAt(i));
				char letra=lin.charAt(i);
				if(letra!=' ')
				lett[(lin.charAt(i)-'A')]++;
			}
//			dbg(lett);
			String iter="HACKERUP";
			int min=Integer.MAX_VALUE;
			
			for (int i = 0; i < iter.length(); i++) {
				char caracter=iter.charAt(i);
				int tmp=lett[iter.charAt(i)-'A'];
				
				if(caracter=='C')
					tmp=tmp/2;//redondeo hacia abajo
				
				if(tmp<min)
					min=tmp;
			}
			
			System.out.printf("Case #%d: %d\n",casesId,min);
		}
		// output.close();
	}
}
