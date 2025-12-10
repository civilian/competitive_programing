package uva.tomo5;

/*Uva 514*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class Rails {
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

	// static void dbg(Object... o) {
	// // System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("Rails"));
		while (true) {
			readln();
			int N = nextInt();
			A.clear();

			if (N == 0)
				break;
			else
				enter();

			for (int i = N; i > 0; i--) {
				A.push(i);
			}
			// dbg("A",A);
			while (true) {
				B.clear();
				itA = A.iterator();

				readln();
				B.addLast(nextInt());
				// dbg("B",B);
				if (B.peek() == 0) {
					break;
				}

				for (int i = 1; i < N; i++) {
					B.addLast(nextInt());
				}
				// dbg("A",A);
				// dbg("B",B);
				System.out.println(can() ? "Yes" : "No");
			}
		}

	}

	private static boolean can() {
		S.clear();
		while (true) {
			aEstacion();
			// dbg("canA",A);
			// dbg("canB",B);
			// dbg("canS",S);
			while (true) {
				if (B.peek().equals(S.peek())) {
					// dbg("canEA",A);
					// dbg("canEB",B);
					// dbg("canES",S);
					S.remove();
					B.remove();
					if (B.isEmpty()) {
						return true;
					}
				} else {
					if (!itA.hasNext()) {
						return false;
					}
					break;
				}
			}
		}
	}

	private static void aEstacion() {
		if (itA.hasNext()) {
			int elo = itA.next();
			S.push(elo);
		}
		// TODO Auto-generated method stub

	}

	static boolean ft = true;

	private static void enter() {
		if (ft)
			ft = false;
		else
			System.out.println();
	}

	static LinkedList<Integer> A = new LinkedList<Integer>(),
			S = new LinkedList<Integer>();
	static LinkedList<Integer> B = new LinkedList<Integer>();
	static Iterator<Integer> itA;
}
