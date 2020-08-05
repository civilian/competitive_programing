import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class pizza {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null) {
			_stk = new StringTokenizer(l, " ");
		}
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
//		input = new BufferedReader(new FileReader("pizza"));

		int bestD=-1;
		double bestCost = 1000000000.0;
		int N;
		int[] dSS = new int[11], pSS = new int[11];
		double bestCostTmp;
		double area;
		double radioCuadrado;
		for (int id = 1;; id++) {
			readln();
			N = nextInt();
			if (N == 0)
				break;

			for (int j = 0; j < N; j++) {
				readln();
				dSS[j] = nextInt();
				pSS[j] = nextInt();
			}
			bestCost = 1000000000.0;
			for (int j = 0; j < N; j++) {
				double a=(double)dSS[j];
				radioCuadrado=(a/2)*(a/2);
				area=Math.PI*radioCuadrado;

				bestCostTmp = (double) pSS[j] / area;
//				dbg(bestCostTmp);
				if (bestCostTmp < bestCost) {
					bestCost = bestCostTmp;
					bestD = dSS[j];
				}
			}
			
			System.out.printf("Menu %d: %d\n", id, bestD);
		}

	}
}
