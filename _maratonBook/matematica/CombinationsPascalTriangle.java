package maratonBook.matematica;

//Uva 485
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 100 94 
 * R/1192052400*/
public class CombinationsPascalTriangle {

	public static void main(String[] args) {
		int N = 100;
		trianguloDePascal(N);

		Scanner sc = new Scanner(System.in);

		System.out.println(C(sc.nextInt(), sc.nextInt()));
	}

	static ArrayList<ArrayList<BigInteger>> trian;

	private static BigInteger C(int n, int k) {
		if (k > n) {
			return BigInteger.ZERO;
		}
		return trian.get(n).get(k);
	}

	private static void trianguloDePascal(int N) {
		trian = new ArrayList<ArrayList<BigInteger>>(N);
		int act = 0;
		ArrayList<BigInteger> fila, filaAux = null;
		int tamannoFila = 0;

		BigInteger tmp;

		while (true) {
			act = tamannoFila++;
			int ultimo = tamannoFila - 1;
			fila = new ArrayList<BigInteger>(tamannoFila);
			trian.add(act, fila);
			for (int i = 0; i < tamannoFila; i++) {
				if (i == 0) {
					fila.add(i, BigInteger.ONE);
					continue;
				} else if (i == ultimo) {
					fila.add(i, BigInteger.ONE);
				} else {
					tmp = filaAux.get(i).add(filaAux.get(i - 1));
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

	private static void dbg(Object... objects) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.deepToString(objects));
	}
}
