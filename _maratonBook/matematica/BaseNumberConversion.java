package maratonBook.matematica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class BaseNumberConversion {
	/*
	 * Base Number Conversion siempre utilizar 10 como una base intermedia y
	 * cambiar los metodos para utilizar los char(deCharAEntero) que es la forma
	 * mas facil. Funciona como Long.parseLong(str,radix), tostring La
	 * diferencia este sirve para bases >=37 Tener en cuenta la misma estructura
	 * que los integer.parseInt y toString, si esta en string esta en una base
	 * Para el echo de pasar a vectores tambien fuciona la estructura mencionada
	 * y que estos estan en vector{..,d2,d1}Base ibase
	 */
	static char[] chATodo = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();// aqui quedaria posicion con simbolo

	/*
	 * tambien Long.toString(numero,radix) pero tener en cuenta la salida en
	 * Mayuscula y esas cosas
	 */

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws Exception {

		// generar chATodo aunque puede cambiar
		// for (char c = '0'; c <= 'Z'; c++) {
		// System.out.print(c);
		// }

		int a = Integer.parseInt(87 + "", 15) + Integer.parseInt(78 + "", 15);// parseInt==integer.parseInt
																				// solo
																				// que
																				// parseInt
																				// base>=37
		System.out.println(Integer.toString(a, 15));// 110
													// parseInt==integer.parseInt
													// base>=37 pero se tiene
													// que cambiar chATodo que
													// simbo=numero
		int b = parseInt(87 + "", 15) + parseInt(78 + "", 15);
		dbg(toString(b, 15));// 110

		int iBase = 16;
		long lNumero = 500;

		Vector<Integer> xEnB = toVector(10, 2);// n to
												// vector{..,d2,d1}Base
												// ibase
		dbg(xEnB); // (10,2)=>[1, 0, 1, 0]

		// 16 10 FEDCBA9876
		long enDecimal = parseInt(xEnB, 2);// vector{..d2,d1}=>long
		dbg(enDecimal);// 10
		// System.out.println(deVectorDecimalANumeroEnBase(xEnB));
		// System.out.println(Long.toString(500, 16));
		// System.out.println(enDecimal);

		Vector<Integer> numeroDecimal = toVector("0123456"
				+ "789ABCDEFGHIJKLMNOPQRSTUVWXYZ");// string{..d2,d1}=>vector{..d1}base10
		dbg(numeroDecimal);
		// String numero=deVectorDecimalANumeroEnBase(numeroDecimal);
		// System.out.println(numero);
		// numeroDecimal=deNumeroAVectorDecimal("abcdefghijklmnopqrstuvwxyz");
		// System.out.println(numeroDecimal);

		// System.out.println(Long.parseLong("80685", 16));

	}

	// Integer.parseInt cualquier base
	static int parseInt(String numero, int base) throws Exception {
		int resultado = 0;
		// dbg(numero);
		for (int i = numero.length() - 1, K = 0; i > -1; K++, i--) {
			int digito = deCharAEntero(numero.charAt(i));

			if (digito >= base)
				throw new Exception();
			resultado += digito * Math.pow((double) base, (double) K);
			// dbg(resultado);
		}
		return resultado;
	}

	// to string cualquier base cambiando el chATodo los simbolos de los numeros
	static String toString(int numero, int base) {
		String sRespuesta = "";
		Vector<Integer> numeroDecimal = toVector(numero, base);
		for (int iI = 0; iI < numeroDecimal.size(); iI++) {
			sRespuesta += chATodo[numeroDecimal.get(iI)];
		}
		return sRespuesta;
	}

	private static Vector<Integer> toVector(long x, int base) {
		/*
		 * DigitoDeNumeroEnBase_EnPosicion= se hace posicion veces numero%base o
		 * lo que es lo mismo es el residou de las divisiones consecutivas
		 */
		Vector<Integer> respuesta = new Vector<Integer>();
		do {
			int tmp = (int) (x % base);// cuidado con los long

			respuesta.add(0, tmp);
		} while ((x /= base) != 0);
		return respuesta;
		// prueba el integer.Max un vector de unos y 10=1,0,1,0
	}

	private static Vector<Integer> toVector(String string) {
		Vector<Integer> salida = new Vector<Integer>();
		for (int iI = 0; iI < string.length(); iI++) {
			int digito = deCharAEntero(string.charAt(iI));// depende del
															// problema
			salida.add(digito);
		}
		return salida;
	}

	/* base^posicion*DigitoEnLaPosicion+..... */
	/*
	 * Convierte un vector de "digitos" a un entero long en base 10. el numero
	 * esta escrito de derecha a izquierda comenzando por el mas significativo
	 * por lo que hay que cogerlo alrevez
	 */
	private static long parseInt(Vector<Integer> vNumero, long iBase)
			throws Exception {
		long resultado = 0;
		for (int iI = vNumero.size() - 1, iJ = 0; iI > -1; iI--, iJ++) {
			/* El digito no puede ser un long */
			Integer iDigito = vNumero.get(iJ);

			/* si es una base ilegal */
			if (iDigito >= iBase)
				throw new Exception();

			/* el valor de la base si puede ser un long aunque seria raro */
			long valorBase = (long) Math.pow((double) iBase, (double) iI);
			resultado += iDigito * valorBase;
		}
		return resultado;
		// prueba:
	}

	// toStringo con el vector y el chATodo cambiando
	private static String toString(Vector<Integer> numeroDecimal) {
		String sRespuesta = "";
		for (int iI = 0; iI < numeroDecimal.size(); iI++) {
			sRespuesta += chATodo[numeroDecimal.get(iI)];
		}
		return sRespuesta;
	}

	private static char maximoDeUnString(String palabra) {
		/* depende de el problema es mas facil en la conversion */
		char caracter = Character.MIN_VALUE;
		for (int i = palabra.length() - 1; i > -1; i--) {
			if (palabra.charAt(i) > caracter) {
				caracter = palabra.charAt(i);
			}
			// System.out.println(resultado);
		}
		return caracter;
	}

	private static int deCharAEntero1(char letra) {
		/*
		 * Depende del problema y hay algunos de mas caracteres entre mayusculas
		 * y minusculas
		 */
		int digito = (int) letra;
		if (digito >= '0' && digito <= '9')
			digito = digito - '0';// '0'=49,
		if (digito >= 'A' && digito <= 'Z')
			digito = digito - 'A' + 10;// 'A'=65,....'Z'=90
		if (digito >= 'a' && digito <= 'z')// los limites
			digito = digito - 'a' + 36;/*
										 * la formula es -El Char primero +Valor
										 * en que empieza
										 */
		// System.out.println(digito);
		return digito;// A=65 tonces devuelve A=10
	}

	private static int deCharAEntero(char letra) {
		return Arrays.binarySearch(chATodo, letra);
	}

	/* es una especie de suma rara que no creo que se necesite */
	private static Vector<Integer> sumaBNimSum(Vector<Integer> largo,
			Vector<Integer> cortico, int base) {
		Vector<Integer> tmp = new Vector<Integer>();
		int largoL = largo.size();
		int largoC = cortico.size();
		if (largoC > largoL) {
			tmp = cortico;
			cortico = largo;
			largo = tmp;
		}
		Collections.reverse(largo);
		Collections.reverse(cortico);

		tmp = new Vector<Integer>();

		int i = 0;
		for (; i < cortico.size(); i++) {
			int sume = (cortico.get(i) + largo.get(i)) % base;
			tmp.add(i, sume);
		}
		for (int j = i; j < largo.size(); j++) {
			tmp.add(j, largo.get(j));
		}

		Collections.reverse(tmp);
		return tmp;
	}

}
