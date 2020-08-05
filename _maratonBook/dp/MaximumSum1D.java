package maratonBook.dp;

/*IMPORTANTE: hay que probarlos todos*/
import java.util.Arrays;
import java.util.Scanner;

/*
 * 6 1 1 1 1 1 1 
 7 1 1 -1 -1 1 1 1 
 5 -3 -2 -1 -3 -4
 R/ SUMA 1D
 3
 -1
 -8
 R/ MAXSUMON  O MAXSUMON2, son iguales
 [maxSum, 6, ini, 0, fin, 5]
 [maxSum, 3, ini, 0, fin, 6]
 [maxSum, -1, ini, 2, fin, 2]
 */
public class MaximumSum1D {

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m;
		int desde, hasta;
		int suma;
		while (true) {
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				array[i] = sc.nextInt();
			}

			// SUMA 1D
			preprocesMaxSum();// cuidado con el n ya que el preproces no se
								// reinicia todo el arreglo
			System.out.println(suma1D(2, 4));
			// SUMA 1D END

			// SUMAON2
			maxSumON2();
			// SUMAON2 END

			// SUMAON
			maxSumON();
			// SUMAON END

		}
	}

	static int n;
	static int MAX_INT = 500;// MAXIMO TAMAÑO DEL PROBLEMA
	static int[] array = new int[MAX_INT];

	/* MAX SUM ON2 */

	static int[] preroces = new int[MAX_INT];

	// se puede realizar en la lectura
	private static void preprocesMaxSum() {
		preroces[0] = array[0];
		for (int i = 1; i < n; i++) {
			preroces[i] = array[i] + preroces[i - 1];
		}
	}

	// se puede realizar en la lectura, y si todo se hace array remplaza a
	// preproces
	private static void preprocesMaxSumLowMemory() {
		for (int i = 1; i < n; i++) {
			array[i] += array[i - 1];// si no se necesita el array luego
			// preroces[i] = array[i] + preroces[i - 1];
		}
	}

	private static int suma1D(int desde, int hasta) {
		// antes realizar el preproces
		if (desde > n || hasta > n || desde < 0 || hasta < 0 || desde > hasta)
			throw new ArrayIndexOutOfBoundsException();
		if (desde == 0)
			return preroces[hasta];
		return preroces[hasta] - preroces[desde - 1];
		// se puede hacer en el for de la leida ademas sobre el mismo array de
		// entrada si no se necesita para mas
	}

	private static int maxSumON2() {
		preprocesMaxSum();// NECESITA EL PREPROCESS PARA EL SUMA, poner cuidado
							// con el n
		int sum = 0;
		int maxSum = 0, iniMax = 0, finMax = 0;
		boolean first = true;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				sum = suma1D(i, j);
				if (first || sum > maxSum) {
					maxSum = sum;
					iniMax = i;
					finMax = j;
					first = false;
				}
			}
		}
		dbg("maxSum", maxSum, "ini", iniMax, "fin", finMax);
		return maxSum;
	}

	/* END MAX SUM ON2 */

	/* MAX SUM ON */

	// static int MAX_SUM = 20007;
	// static int[] array = new int[MAX_SUM];
	// static int n;

	static int iniMax, finMax;
	static long maxSum;

	// return la max sum que se presenta primero
	private static long maxSumON() {
		long sum = 0;
		int ini = 0, fin = 0;
		maxSum = -1000000000;
		iniMax = 0;
		finMax = 0;

		boolean first = true;

		for (int i = 0; i < n; i++) {
			sum += array[i];
			if (first || sum > maxSum) {
				maxSum = sum;
				iniMax = ini;
				finMax = i;
				first = false;
			}
			if (sum < 0) {
				sum = 0;
				ini = i + 1;
			}
			fin = i;
		}
		dbg("maxSum", maxSum, "ini", iniMax, "fin", finMax);
		return maxSum;
	}

	/* END MAX SUM ON */

}
