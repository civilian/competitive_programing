package uva.tomo114;

/*Uva 11459*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class SnakesAndLadders {
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
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("SnakesAndLadders"));
		readln();

		int numCases = nextInt();

		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();
			int P = nextInt();
			int b = nextInt();
			int c = nextInt();
			HashMap<Integer, Integer> snakesLatters = new HashMap<Integer, Integer>();
			for (int i = 0; i < b; i++) {
				readln();
				int ini = nextInt(), fin = nextInt();
				snakesLatters.put(ini, fin);
			}
			
			Integer[] players = new Integer[P];
			Arrays.fill(players, 1);
			int act = -1;
			boolean terminado = false;
			
			for (int i = 0; i < c; i++) {
				readln();
				int dado = nextInt();
				if (!terminado) {
					act = (act + 1) % P;
					players[act] += dado;

					Integer ob = snakesLatters.get(players[act]);
					if (ob != null)
						players[act] = ob;

					if (players[act] >= 100) {
						players[act]=100;
						terminado = true;
					}
				}
			}

			for (int i = 0; i < players.length; i++) {
				System.out.printf("Position of player %d is %d.\n", i+1, players[i]);
			}
		}

	}
}
