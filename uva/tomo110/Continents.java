package uva.tomo110;

//Uva 11094
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Continents {
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
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Continents"));
		int N, M, fi, ci;
		char land, watter=' ', ya;
		while (readln() != null) {
			M = nextInt();
			N = nextInt();
			grid=new char[M][N];//no se si esta bn
			R=M;
			C=N;
			for (int i = 0; i < M; i++) {
				grid[i]=readln().toCharArray();//eso si estan juntos
			}

			readln();
			fi=nextInt();
			ci=nextInt();
			
			land=grid[fi][ci];
//			papa:
//			for (int i = 0; i < M; i++) {
//				for (int j = 0; j < N; j++) {
//					if (grid[i][j]!=land) {
//						watter=grid[i][j];//908
//						break papa;
//					}
//				}
//			}
			ya=(char) (land+1);
//			ya++;
			floodfill(fi, ci, land, ya);
//			dbg(grid);
//			dbg(land);
			int max=0,tmp=0;//0 por si no hay nadie
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					tmp=floodfill(i, j, land, ya);
					max=Math.max(max, tmp);
				}
			}
//			dbg(grid);
			System.out.println(max);
			readln();
		}

	}

	static int[] dr = { 1, 0, -1, 0};// S,E,N,W,SW
	static int[] dc = { 0, 1,  0, -1};

	static char[][] grid;
	static int R, C;

	static int floodfill(int r, int c, char c1, char c2) {
		if (r < 0 || r >= R)
			return 0;
		if (c == -1)
			c = C - 1;
		if (c == C)
			c = 0;

		if (grid[r][c] != c1)
			return 0;
		grid[r][c] = c2;
		int ans = 1;
		for (int i = 0; i < 4; i++) {
			ans += floodfill(r + dr[i], c + dc[i], c1, c2);
		}
		return ans;
	}
}
