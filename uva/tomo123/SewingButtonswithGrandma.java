package uva.tomo123;

//Uva  12316 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.StringTokenizer;

public class SewingButtonswithGrandma {

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		InputReader cin = new InputReader(System.in);
		// Scanner cin = new Scanner(System.in);
		// Scanner cin = new Scanner(new FileInputStream(new File("in.txt")));
		int m, k;

		int MAXN = 55;
		BigInteger choose[][] = new BigInteger[MAXN][MAXN];
		choose[0][0] = BigInteger.ONE;
		for (int i = 0; i < MAXN; ++i) {
			for (int j = 0; j < MAXN; ++j) {
				if (i != 0 || j != 0)
					choose[i][j] = BigInteger.ZERO;
				if (i > 0)
					choose[i][j] = choose[i][j].add(choose[i - 1][j]);
				if (i > 0 && j > 0)
					choose[i][j] = choose[i][j].add(choose[i - 1][j - 1]);
			}
		}

		BigInteger dp[][] = new BigInteger[k + 1][m + 1];
		int avail[] = new int[k];
		
		while (true) {
			m = cin.nextInt();
			k = cin.nextInt();
			if (m == 0 && k == 0)
				break;

			for (int i = 0; i < k; ++i) {
				avail[i] = cin.nextInt();
			}

			
			for (int b = 1; b <= m; ++b) {
				dp[k][b] = BigInteger.ZERO;
			}
			dp[k][0] = BigInteger.ONE;

			for (int i = k - 1; i >= 0; --i) {
				for (int b = 0; b <= m; ++b) {
					dp[i][b] = dp[i + 1][b];
					for (int n = 1; n <= avail[i] && b - n >= 0; n++) {
						for (int s = 1; s <= n; ++s) {
							dp[i][b] = dp[i][b].add(dp[i + 1][b - n].multiply(
									choose[b - n + 1][s]).multiply(
									choose[n - 1][s - 1]));
						}
					}
				}
			}
			System.out.println(dp[0][m]);
		}
	}

}

class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char nextChar() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}
