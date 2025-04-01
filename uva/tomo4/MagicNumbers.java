package uva.tomo4;

/*Uva 471*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class MagicNumbers {
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
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("MagicNumbers"));
		readln();

		int numCases = (int) nextInt();

		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();
			readln();
			long N = nextInt();
			long maxVal=10000000000l;
			if (N!=0) {
				maxVal =  (10000000000l / N) + 1l;
			}
			enter(casesId);
//			dbg(maxVal);
			long numera = 0;
			for (int d = 1; d <= maxVal; d++) {
//				dbg(d,"rp d",noReps(d), "rp numera",noReps(numera), "numera", numera,Ints);
				if (noReps(d)) {
					numera=N*d;
					if(noReps(numera)){
						System.out.printf("%d / %d = %d\n",numera,d,N);
					}
				}
			}
		}

	}

	static HashSet<Integer> Ints = new HashSet<Integer>(10);

	private static boolean noReps(long d) {
		Ints.clear();
		String digits = d + "";
		for (char c : digits.toCharArray()) {
			if (!Ints.add((c - '0'))) {
				return false;
			}
		}
		return true;
	}
	
	private static void enter(int casesId) {
		if (casesId != 0) {
			System.out.println();
		}
	}
}
