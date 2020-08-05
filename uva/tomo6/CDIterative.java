package uva.tomo6;
/*Uva 624.*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CDIterative {
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
//		input = new BufferedReader(new FileReader("CD"));

		int N, cd_length, track[] = new int[25];
		while (readln() != null) {
			cd_length = nextInt();
			N = nextInt();
			for (int i = 0; i < N; i++)
				track[i] = nextInt();
			;
			int optimum_mask = 0, optimum_sum = 0;
			for (int mask = 0, sum = 0; mask < (1 << N); sum = 0, mask++) {
				for (int i = 0; i < N; i++)
					sum += ((1 << i) & mask) != 0 ? track[i] : 0;

				if (sum > optimum_sum && sum <= cd_length) {
					optimum_sum = sum;
					optimum_mask = mask;
				}
			}
			for (int i = 0; i < N; i++)
				if (((1 << i) & optimum_mask) != 0)
					System.out.printf("%d ", track[i]);

			System.out.printf("sum:%d\n", optimum_sum);
		}
	}

}
