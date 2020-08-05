package uva.tomo119;

//Uva 11953
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Battleships {
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
	// System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Battleships"));
		int N;
		readln();
		int testCases = nextInt();// 712-722-733
		int idCases = 0;
		while (idCases < testCases) {
			readln();
			R = C = N = nextInt();
			
			grid = new char[N][N];
			for (int i = 0; i < N; i++) {
				grid[i] = readln().toCharArray();// si, estan juntos
			}
			
			// dbg(grid);
			// dbg(land);
			int ships = 0, tmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp = floodfill(i, j);
					ships += (tmp > 0) ? 1 : 0;
				}
			}
			// dbg(grid);
			System.out.printf("Case %d: %d\n", idCases + 1, ships);

			idCases++;
		}

	}

	static int[] dr = { 1, 0, -1, 0 };// S,E,N,W
	static int[] dc = { 0, 1, 0, -1 };

	static char[][] grid;
	static int R, C;

	static char c2 = '-';

	static int floodfill(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return 0;
		
		if (grid[r][c] == '.' || grid[r][c] == c2)
			return 0;

		int ans = (grid[r][c] == '@') ? 0 : 1;

		grid[r][c] = c2;
		
		for (int i = 0; i < 4; i++) {
			ans += floodfill(r + dr[i], c + dc[i]);
		}
		return ans;
	}
}
