/*TODO: COMPLETAR TODO ESTO y utilizarlo*/
package maratonBook;

/*Numeros racionales expresados como un arreglo de dos puestos
 * haciendo que estos numeros que tienen N/D, N=numerador y D=denominador
 * queden con a[0]=N y a[1]=D*/
public class RationalNumbers {

	public static void main(String[] args) {

	}

	static public int[] multiplyFractions(int[] a, int[] b) {
		int[] c = { a[0] * b[0], a[1] * b[1] };
		return c;
	}

	static public int[] addFractions(int[] a, int[] b) {
		int denom = LCM(a[1], b[1]);
		int[] c = { denom / a[1] * a[0] + denom / b[1] * b[0], denom };
		return c;
	}

	static public void reduceFraction(int[] a) {
		int b = GCD(a[0], a[1]);
		a[0] /= b;
		a[1] /= b;
	}

	static int GCD(int a, int b) {
		while (b > 0) {
			a = a % b;
			a ^= b;
			b ^= a;
			a ^= b;
		}
		return a;
	}

	static int LCM(int m, int n) {
		return (m * n) / GCD(m, n);
	}
}
