package ClasesVictorPadilla;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class PartySchedule {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("PartySchedule"));

		for (int casesId = 1;; casesId++) {
			readln();
			int B = nextInt();
			int N = nextInt();
			if (B == 0 && N == 0)
				return;
			
			fiestas = new ArrayList<Party>();
			for (int i = 0; i < N; i++) {
				readln();
				int costo = nextInt();
				int diversion = nextInt();

				Party tmp = new Party(costo, diversion);
				fiestas.add(tmp);
			}
			readln();
			// dbg(fiestas);
			System.out.println(fun(B, N - 1));
		}

	}

	static ArrayList<Party> fiestas;

	static int fun(int b, int fiesta) {
		if (b <= 0)
			return 0;
		if (fiesta < 0)
			return 0;

		int con = fun(b - fiestas.get(fiesta).costo, fiesta - 1)
				+ fiestas.get(fiesta).diversion;
		int sin = fun(b, fiesta - 1);

		int max = Math.max(con, sin);
		return max;
	}

}

class Party {
	@Override
	public String toString() {
		return "[costo=" + costo + ", diversion=" + diversion + "]";
	}

	int costo;// costo
	int diversion;

	public Party(int costo, int diversion) {
		this.costo = costo;
		this.diversion = diversion;
	}

}
