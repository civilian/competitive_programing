package CF.marzo17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class BurningMidnightOil {
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

//	static void dbg(Object... o) {
//		System.out.println(Arrays.deepToString(o));
//	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("BurningMidnightOil"));
		int n = 0, k = 0, v = 0;

		readln();
		n = nextInt();
		k = nextInt();
		v = binarySearch(0, n+1, n, k);

		System.out.println(v);

	}

	private static int binarySearch(int lo, int hi, int val, int k) {
		int mid;
		int ans = -1;
		
		int times = 1+ (int) Math.ceil(Math.log((hi - lo)) / Math.log(2.0));
//		dbg(times);
		for (int i = 0; i < times; i++){
			mid = lo + ((hi - lo) / 2);
			if (sirve(mid, val, k)) {
				hi = mid;
				ans = hi;
			} else
				lo = mid + 1;
		}

		return ans;
	}

	private static boolean sirve(int mid, int val, int k) {
		int sum = 0, tmp = 1, p = 0;
//		dbg("mid",mid);
		while (tmp > 0) {
			tmp = (int) (mid / Math.pow(k, p));
			sum += tmp;
			p++;
//			dbg("sum",sum, "p",p,"tmp",tmp, "val",val);
		}
		if (val <= sum)
			return true;
		return false;
	}

}
