package uva.tomo1;

//Uva 122.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;

public class Treesonthelevel {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Treesonthelevel"));
		String l, nodo;
		StringTokenizer tk;
		boolean maloArbol = false;
		while (true) {
			l = readln();
			if (l == null) {
				break;
			}
			raiz = new Nodo();
			maloArbol = false;
			while (true) {
				nodo = nextS();
				// dbg(nodo);
				if (nodo.equals("()")) {
					break;
				}

				tk = new StringTokenizer(nodo, "(,");
				val = Integer.parseInt(tk.nextToken());
				direccion = tk.nextToken();// sera que no?
				indexD = 0;
				maloArbol = maloArbol || annadirNodo(raiz);
				// dbg(raiz);
			}
			if (maloArbol) {
				output.println("not complete");
			} else {
				out = new StringBuilder();
				if (imprimir(raiz)) {
					output.println(out);
				} else {
					output.println("not complete");
				}
			}

		}
		output.close();

	}

	static StringBuilder out;
	static int val, indexD;
	static Nodo raiz;
	static String direccion;
	static char d;

	static Queue<Nodo> q = new LinkedList<Nodo>();

	private static boolean imprimir(Nodo nod) {
		// TODO Auto-generated method stub
		q.clear();
		if (nod.value == -1) {
			return false;
		}
		out.append(nod.value);
		q.add(nod.left);
		q.add(nod.right);
		Nodo cur;
		boolean ans = true;
		while (!q.isEmpty()) {
			cur = q.poll();

			if (cur == null)
				continue;

			ans = ans && imp(cur);
			q.add(cur.left);
			q.add(cur.right);
		}

		return ans;

	}

	static boolean imp(Nodo nod) {
		if (nod.value == -1) {
			return false;
		} else {
			out.append(' ');
			out.append(nod.value);
			return true;
		}
	}

	static boolean annadirNodo(Nodo nod) {
		d = direccion.charAt(indexD++);
		// dbg(nod);
		if (d == ')') {
			if (nod.value == -1) {
				nod.value = val;
				return false;
			} else {
				nod.value = val;
				return true;
			}
		} else {
			if (d == 'L') {
				if (nod.left == null) {
					nod.left = new Nodo();
				}
				return annadirNodo(nod.left);
			} else {
				if (nod.right == null) {
					nod.right = new Nodo();
				}
				return annadirNodo(nod.right);
			}
		}
	}

	private static String nextS() throws IOException {
		String resultado;
		try {
			resultado = next();
		} catch (Exception e) {
			if (readln() == null) {
				// output.println("shit");
				System.exit(0);
			}

			resultado = nextS();
		}
		return resultado;
	}

	static class Nodo {
		Nodo left, right;
		int value;
		boolean cambiado;

		public Nodo(Nodo left, Nodo right, int value, boolean cambiado) {
			this.left = left;
			this.right = right;
			this.value = value;
			this.cambiado = cambiado;
		}

		public Nodo() {
			this.value = -1;
			this.cambiado = false;
		}

		@Override
		public String toString() {
			return "Nodo , value=" + value + ",]\n \t"
					+ ((left == null) ? null : left.toString())
					+ ((right == null) ? null : right.toString());
		}

	}
}
