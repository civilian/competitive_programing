package uva.tomo112;

/*Uva 11242*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

public class TourDeFrance {
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
//		input = new BufferedReader(new FileReader("TourDeFrance"));

		int f, r;
		ArrayList<Double> front = new ArrayList<Double>(10), rear = new ArrayList<Double>(
				10);
		ArrayList<Double> ratio = new ArrayList<Double>(120);
		double tmp, max = -1;
		while (true) {
			readln();
			f = nextInt();
			if (f == 0) {
				break;
			}
			r = nextInt();

			readln();
			front.clear();
			rear.clear();
			ratio.clear();
			for (int i = 0; i < f; i++) {
				front.add((double) nextInt());
			}
			readln();
			for (int i = 0; i < r; i++) {
				rear.add((double) nextInt());
			}
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < f; j++) {
					ratio.add(rear.get(i) / front.get(j));
				}
			}

			Collections.sort(ratio);
			
//			dbg(ratio);
			
			max=-1;
			for (int i = 0; i < ratio.size()-1; i++) {
				tmp=ratio.get(i+1)/ratio.get(i);
				if(tmp>max){
					max=tmp;
				}
			}
			
			System.out.printf("%.2f\n",max);
		}

	}
}
