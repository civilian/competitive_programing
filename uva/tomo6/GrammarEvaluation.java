package uva.tomo6;

//Uva 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class GrammarEvaluation {
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
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("GrammarEvaluation"));

		readln();
		int tc = nextInt();
		for (int i = 0; i < tc; i++) {

			linea = readln().replaceAll(" ", "");
			// System.out.println(linea);
			donde = -1;

			try {
				System.out.println(parse());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERROR");
			}
		}

	}

	static int donde = 0, a, b, ans;
	static String linea;
	static char op;

	static int parse() throws Exception {
		// le arranco pedazos a la linea y los computo
		donde++;
		char letra = linea.charAt(donde);
		dbg("ini", letra, donde);
		if (Character.isDigit(letra)) {
			a = Integer.parseInt(letra + "");
			op = operador();
			if (op != 'N') {

				b = parse();
				dbg(letra, donde);
				return a + b;
			}
			return a;
		} else if (letra == '(')// si es ( llamo recursivo
		{
			a = parse();
			op = operador();
			if (op != 'N') {
				b = parse();
				return a + b;
			}
			return a;
		}
		return -200;
	}

	private static char operador() throws Exception {
		donde++;
		if (donde >= linea.length() || linea.charAt(donde) == ')') {
			return 'N';
		}
		dbg(linea.charAt(donde));
		op = linea.charAt(donde);
		if ("+-*/%".contains(op + "")) {
			return op;
		}
		throw new Exception();

	}
}
