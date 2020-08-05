package maratonBook.estructurasLibreriasPropias;

import java.math.BigInteger;
import java.util.*;

class bitManipulation {
	private static int setBit(int S, int j) {
		return S | (1 << j);
	}

	// a la izquierada en b posiciones

	private static int isOn(int S, int j) {
		return S & (1 << j);
	}

	private static int clearBit(int S, int j) {
		return S & ~(1 << j);
	}// los demas tienen que ser 1 por el &

	private static int toggleBit(int S, int j) {
		return S ^ (1 << j);
	}// cambiar el bit de 0->1 1->0

	private static int lowBit(int S) {
		return S & (-S);
	}// devuelve la 2^posicion para buscar la posicion seria:

	private static int primerOne(int S) {
		int pos = (int) Math.round((Math.log((double) lowBit(S)) / Math
				.log(2.0)));
		return pos;
	}

	private static int setAll(int n) {
		return (1 << n) - 1;
	}

	private static int modulo(int x, int N) {
		return ((x) & (N - 1));
	} // returns x % N, where N is a power of 2

	private static int isPowerOfTwo(int x) {
		return (x & (x - 1));
	}

	private static int nearestPowerOfTwo(int x) {
		return ((int) Math.pow(2.0,
				(int) ((Math.log((double) x) / Math.log(2.0)) + 0.5)));
	}

	private static void printSet(int _S) { // in binary representation
		System.out.printf("S = %2d = ", _S);

		/*
		 * Stack<Integer> st = new Stack<Integer>(); while (_S > 0) { st.push(_S
		 * % 2); _S /= 2; } while (!st.empty()) { // to reverse the print order
		 * System.out.printf("%d", st.peek()); st.pop(); }
		 * System.out.printf("\n");
		 */
		System.out.printf("%s\n", Integer.toBinaryString(_S));// mas rapido y
																// facil que
																// arriba
	}

	/* ESTE MAIN SOLO TIENE PRUEBAS Y COMO USAR LAS COSAS */
	public static void main(String[] args) {
		int S, T;

		System.out
				.printf("1. Representation (all indexing are 0-based and counted from right)\n");
		S = 34;
		printSet(S);
		System.out.printf("\n");

		System.out
				.printf("2. Multiply S by 2, then divide S by 4 (2x2), then by 2\n");
		S = 34;
		printSet(S);
		S = S << 1;
		printSet(S);
		S = S >> 2;
		printSet(S);
		S = S >> 1;
		printSet(S);
		System.out.printf("\n");

		System.out.printf("3. Set/turn on the 3-th item of the set\n");
		S = 34;
		printSet(S);
		S = setBit(S, 3);
		printSet(S);
		System.out.printf("\n");

		System.out
				.printf("4. Check if the 3-th and then 2-nd item of the set is on?\n");
		S = 42;
		printSet(S);
		T = isOn(S, 3);
		System.out.printf("T = %d, %s\n", T, T != 0 ? "ON" : "OFF");
		T = isOn(S, 2);
		System.out.printf("T = %d, %s\n", T, T != 0 ? "ON" : "OFF");// 0->off
		System.out.printf("\n");

		System.out.printf("5. Clear/turn off the 1-st item of the set\n");
		S = 42;
		printSet(S);
		S = clearBit(S, 1);
		printSet(S);
		System.out.printf("\n");

		System.out
				.printf("6. Toggle the 2-nd item and then 3-rd item of the set\n");
		S = 40;
		printSet(S);
		S = toggleBit(S, 2);
		printSet(S);
		S = toggleBit(S, 3);
		printSet(S);
		System.out.printf("\n");

		System.out.printf("7. Check the first bit from right that is on\n");
		S = 40;
		printSet(S);
		T = lowBit(S);
		System.out.printf("T = %d (this is always a power of 2)\n", T);
		T = primerOne(S);
		System.out.printf("T = %d primer uno\n", T);// (este pasa de la potencia
													// a la posicion
		S = 52;
		printSet(S);
		T = lowBit(S);
		System.out.printf("T = %d (this is always a power of 2)\n", T);

		S = 11;
		T = lowBit(S);
		System.out.printf("T = %d (this is always a power of 2)\n", T);
		printSet(-S);
		System.out.printf("\n");

		System.out.printf("8. Turn on all bits in a set of size n = 6\n");
		S = setAll(6);
		printSet(S);
		System.out.printf("\n");

		System.out.printf("9. Other tricks (not shown in the book)\n");
		System.out.printf("8 %c 4 = %d\n", '%', modulo(8, 4));
		System.out.printf("7 %c 4 = %d\n", '%', modulo(7, 4));
		System.out.printf("6 %c 4 = %d\n", '%', modulo(6, 4));
		System.out.printf("5 %c 4 = %d\n", '%', modulo(5, 4));
		System.out.printf("is %d power of two? %d\n", 9, isPowerOfTwo(9));
		System.out.printf("is %d power of two? %d\n", 8, isPowerOfTwo(8));
		System.out.printf("is %d power of two? %d\n", 7, isPowerOfTwo(7));
		for (int i = 0; i <= 16; i++)
			System.out.printf("Nearest power of two of %d is %d\n", i,
					nearestPowerOfTwo(i));
		System.out.printf("\n");

		// boolean [] arreglo=new boolean [365];
		try {
			Bits a;
			BigInteger b = BigInteger.ZERO;// Biginteger usa la misma memoria
											// pero hace new de un arreglo cada
											// vez que hay setBit.
			a = new Bits(1429999999);//maximo tamanno de pila
			a.setBit(224);
			a.setBit(63);

			b = b.setBit(224);
			b = b.setBit(63);

			b = b.clearBit(224);

			System.out.printf("%d=%b \n", 63, b.testBit(63));

			a.clearBit(224);

			for (long i = 0; i < 366; i++) {
				a.setBit(i);
			}

			for (int i = 0; i < 366; i++) {
				b = b.setBit(i);
			}

			for (int i = 0; i < 366; i++) {
				System.out.printf("%d=%d ", i, a.isOn(i));
				System.out.printf("%d=%b \n", i, b.testBit(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

/*
 * A lo maximo hay un desperdicio de 64 bits si me paso de el tamanno maximo que
 * siempre tiene un poquito mas sale una excepcion, lo unico es que si hay que
 * tener en cuenta que es mas grande de lo que se necesita
 */
class Bits {
	static long[] bits;
	Long cuantos;

	public Bits(long booleans) throws Exception {// max en mi pc 845000000
													// y 1429999999 sin nada mas
		cuantos = booleans >> 6;
		cuantos++;// por si redondea hacia abajo
		// System.out.println(cuantos);
		if (cuantos > Integer.MAX_VALUE)
			throw new Exception("demasiado grande");
		this.bits = new long[cuantos.intValue()];
	}

	public static void setBit(long S) {
		bits[(int) (S / 64)] = setBit(bits[(int) (S / 64)], (int) (S % 64));
	}

	private static long setBit(long S, int j) {
		return S | (1 << j);
	}

	// a la izquierada en b posiciones

	public static long isOn(long S) {
		return isOn(bits[(int) (S / 64)], (int) (S % 64));
	}

	private static long isOn(long S, int j) {
		return S & (1 << j);
	}

	public static void clearBit(long S) {
		bits[(int) (S / 64)] = clearBit(bits[(int) (S / 64)], (int) (S % 64));
	}

	private static long clearBit(long S, int j) {
		return S & ~(1 << j);
	}

	private static long firstOne(long S) {
		long pos = 0;// TODO: probar, tambien puede llevar a cosas raras por que
						// hay mas de la cantidad de bits
		for (int i = 0; i < bits.length; i++) {
			if (bits[i] != 0)
				return pos += primerOne(bits[i]);
			else {
				pos += 64;
			}
		}

		return -1;
	}

	private static int primerOne(long S) {
		int pos = (int) Math.round((Math.log((double) lowBit(S)) / Math
				.log(2.0)));
		return pos;
	}

	private static long lowBit(long S) {
		return S & (-S);
	}// devuelve la 2^posicion para buscar la posicion seria:

}
