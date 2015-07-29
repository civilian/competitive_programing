package uva.tomo113;

//Uva 11389.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;

public class TheBusDriverProblem {
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
//		input = new BufferedReader(new FileReader("TheBusDriverProblem"));

		while (true) {
			readln();
			n = nextInt();
			d = nextInt();
			r = nextInt();
			if (n == 0 && d == 0 && r == 0) {
				break;
			}
			readln();
			mor.clear();
			for (int i = 0; i < n; i++) {
				mor.add(nextInt());
			}
			readln();
			eve.clear();
			for (int i = 0; i < n; i++) {
				eve.add(nextInt());
			}
			min=balanceLoad();
			System.out.println(min);
		}
	}

	private static int balanceLoad() {
		ans=0;
		Collections.sort(mor);
		Collections.sort(eve,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
//		dbg("mor",mor,"eve",eve);
		for (int i = 0; i < n; i++) {
			tmp=0;
			tmp=mor.get(i)+eve.get(i);
			tmp=tmp-d;
			if(tmp>0){
				ans+=tmp;
			}
		}
		ans*=r;
		return ans;
	}

	static int n = 0, d = 0, r = 0;
	static ArrayList<Integer> mor = new ArrayList<Integer>(101),
			eve = new ArrayList<Integer>(101);
	static int min, ans, tmp;
}
