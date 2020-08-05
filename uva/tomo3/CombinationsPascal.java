package uva.tomo3;

//Uva 369.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class CombinationsPascal {

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

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Combinations"));

		trianguloDePascal(101);
		for (int casesId = 1;; casesId++) {
			readln();
			int N, M;
			N = nextInt();
			M = nextInt();
			if (N == 0 && M == 0)
				break;
			output.printf("%d things taken %d at a time is %d exactly.\n", N,
					M, C(N, M));
		}

		output.close();
		// C(30,14);

	}

	// static {
	// trianguloDePascal(101);
	// }

	private static int C(int n, int k) {
		if (k > n) {
			return 0;
		}
		return trian.get(n).get(k);
	}

	// COMBINATIONS DP
	static ArrayList<ArrayList<Integer>> trian;

	private static void trianguloDePascal(int N) {
		trian = new ArrayList<ArrayList<Integer>>(N);
		int act = 0;
		ArrayList<Integer> fila, filaAux = null;
		int tamannoFila = 0;

		int tmp;

		while (true) {
			act = tamannoFila++;
			int ultimo = tamannoFila - 1;
			fila = new ArrayList<Integer>(tamannoFila);
			trian.add(act, fila);
			for (int i = 0; i < tamannoFila; i++) {
				if (i == 0) {
					fila.add(i, 1);
					continue;
				} else if (i == ultimo) {
					fila.add(i, 1);
				} else {
					tmp = filaAux.get(i) + (filaAux.get(i - 1));
					fila.add(i, tmp);
				}
			}
			if (act > N) {// System.out.print(salida);
				break;
			}
			filaAux = fila;
		}
		// dbg(trian);
	}

	// COMBINATIONS DP END
}
