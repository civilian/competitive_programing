package CF.abril17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MysteriousNumbers1 {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("MysteriousNumbers1"));

		long a, b;
		String sA, sB;

		readln();
		a = nextInt();
		b = nextInt();

		sA = a + "";
		sB = b + "";

		int cuantos;
		if (sA.length() > sB.length()) {
			cuantos = sA.length() - sB.length();
			// dbg(sA.length() - sB.length());
			for (int i = 0; i < cuantos; i++) {
				sB = "0" + sB;
			}
		} else {
			cuantos = sB.length() - sA.length();
			// dbg(sB.length() - sA.length());
			for (int i = 0; i < cuantos; i++) {
				sA = "0" + sA;
			}
		}

		String total = "";
		int num1, num2;
		// dbg("a", sA,"b", sB);
//		for (int i = 0; i < sA.length(); i++) {
//			num1 = Integer.parseInt(sA.charAt(i) + "");
//			num2 = Integer.parseInt(sB.charAt(sB.length() - (i + 1)) + "");
//			total += ((num1 + num2) % 10) + "";
//		}

		String reverB = "";
		for (int i = sB.length() - 1; i > -1; i--) {
			reverB = reverB + sB.charAt(i);
		}
		//
		// dbg(reverB);
		int suma = Integer.parseInt(sA) + Integer.parseInt(reverB);
//		String out=suma+"";
//		if (out.length()>sA.length()) {
//			out=out.substring(1);
//		}
//		System.out.println(suma);
//		System.out.println(Integer.parseInt(total));
		System.out.println(suma);

	}
}
