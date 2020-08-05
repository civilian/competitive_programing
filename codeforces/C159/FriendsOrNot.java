package CF.C159;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class FriendsOrNot {
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("FriendsOrNot"));
		readln();

		int n = nextInt(), d = nextInt();
		log.clear();
		for (int i = 0; i < n; i++) {
			readln();
			A = next();
			B = next();
			ti = nextInt();
			log.put(A + " " + B, ti);
			da = log.get(B + " " + A);
			
			if (da != null) {
				dt = ti - da;
				if (dt <= d && dt > 0) {
					if (A.compareTo(B) <= 0) {
						min = A;
						max = B;
					} else {
						min = B;
						max = A;
					}
					friends.add(min + " " + max);
				}
			}

//			dbg("A", A, "B", B, "log", log);
		}

		System.out.println(friends.size());
		Iterator<String> it = friends.iterator();
		while (it.hasNext()) {
			String pareja = (String) it.next();
			System.out.println(pareja);
		}

	}

	static Integer da = null;
	static HashMap<String, Integer> log = new HashMap<String, Integer>(1000);
	static int dt = Integer.MAX_VALUE;
	static HashSet<String> friends = new HashSet<String>();
	static String min = "", max = "";
	static String A, B;
	static int ti;
}
