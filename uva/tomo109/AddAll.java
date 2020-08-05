package uva.tomo109;
/*Uva 10954 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AddAll {
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
//		input = new BufferedReader(new FileReader("AddAll"));

		for (int casesId = 0;; casesId++) {
			readln();
			int N=nextInt();
			if(N==0)
				break;
			numbers.clear();
			
			readln();
			for (int i = 0; i < N; i++) {
				numbers.add(nextInt());
			}
			int cost=0;
			int val=-1;
			while (numbers.size()>1) {
//				dbg(numbers);
				val=numbers.poll()+numbers.poll();
				cost+=val;
//				dbg("cost", cost, " val ",val);
				numbers.offer(val);
			}
			System.out.println(cost);
		}

	}
	
	static PriorityQueue<Integer> numbers=new PriorityQueue<Integer>(5000);
}
