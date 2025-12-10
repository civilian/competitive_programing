package uva.tomo109;

//Uva 
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger; // BigInteger class is inside package java.math

class Krakovia { /* UVa 10925 - Krakovia */

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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Krakovia"));

		int caseNo = 1;
		while (true) {
			readln();
			int N = nextInt(), F = nextInt(); // N bills, F friends
			if (N == 0 && F == 0)
				break;
			BigInteger sum = BigInteger.ZERO; // BigInteger has this constant
												// ZERO
			for (int i = 0; i < N; i++) { // sum the N large bills
				readln();
				BigInteger V = new BigInteger(next()); // for reading next
				// BigInteger!
				sum = sum.add(V); // this is BigInteger addition
			}
			output.println("Bill #" + (caseNo++) + " costs " + sum
					+ ": each friend should pay "
					+ sum.divide(BigInteger.valueOf(F)));
			output.println(); // the line above is BigInteger division
		}

		output.close();
	}
} // divide the large sum to F friends
