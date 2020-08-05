package maratonBook.graphs;

//simp. Uva 122
import java.io.IOException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 //Graphs of Uva 122
 (11,LL) (7,LLL) (8,R) (5,) (5,) (4,L) (13,RL) (2,LLR) (1,RRR) (4,RR) ()
 R/ nodos dados mas de una vez
 5 4 11 7 2 8 13 4 1
 
 //
 (11,LL) (7,LLL) (8,R) (5,) (4,L) (13,RL) (2,LLR) (1,RRR) (4,RR) ()
 R/arbol no completo no se han dado valores
 5 4 11 7 2 8 13 4 1

 //
 (3,L) (4,R) ()
 arbol no completo no se han dado valores
 -1 3 4


 */
public class TreeRecursivo {

	private static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) throws IOException {
		// Scanner sc = new Scanner(new File("Treesonthelevel"));
		Scanner sc = new Scanner(System.in);
		String l, nodo;
		StringTokenizer tk;
		boolean annadio = true;// representa los errores cuando se annade un
								// nodo que esta repetido
		raiz = new Nodo();
		annadio = true;
		while (true) {
			nodo = sc.next();
			// dbg(nodo);
			if (nodo.equals("()")) {
				break;
			}

			tk = new StringTokenizer(nodo, "(,");
			val = Integer.parseInt(tk.nextToken());
			direccion = tk.nextToken();// sera que no?
			indexD = 0;
			annadio = annadirNodo(raiz) && annadio;//tener cuidadon el &&
			// dbg(raiz);
		}
		if (!annadio) {
			System.out.println("nodos dados mas de una vez");
		}
		out = new StringBuilder();
		ans = true;
		if (!imprimirPreOrder(raiz)) {
			System.out.println("arbol no completo no se han dado valores");
		}
		System.out.println(out);

	}

	static StringBuilder out;
	static int val, indexD;
	static Nodo raiz;
	static String direccion;
	static char d;

	static Queue<Nodo> q = new LinkedList<Nodo>();
	static boolean ans;

	private static boolean imprimirPreOrder(Nodo nod) {
		if (nod == null)
			return ans;
		ans = imp(nod) && ans;
		ans = imprimirPreOrder(nod.left) && ans;// asi para no tener problemas
												// con el false
		ans = imprimirPreOrder(nod.right) && ans;
		return ans;
	}

	static boolean imp(Nodo nod) {
		out.append(' ');
		out.append(nod.value);
		return nod.cambiado;// si le ha dado o no valor
	}

	static boolean annadirNodo(Nodo nod) {// retorna false en caso de nodos
											// repetidos
		d = direccion.charAt(indexD++);
		// dbg(nod);
		if (d == ')') {
			if (nod.value == -1) {
				nod.setValue(val);
				return true;
			} else {
				nod.setValue(val);
				return false;
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
			this.value = -1;// tener cuidado con los valores negativos usar el
							// cambiado
			this.cambiado = false;
		}

		boolean setValue(int value) {
			if (!cambiado) {
				this.value = value;
				cambiado = true;
				return cambiado;
			} else {
				return !cambiado;
			}
		}

		@Override
		public String toString() {
			return "Nodo , value=" + value + ",]\n \t"
					+ ((left == null) ? null : left.toString())
					+ ((right == null) ? null : right.toString());
		}

	}
}
