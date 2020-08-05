package maratonBook.paradigmas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class BinarySearch {
	/*
	 * La cantidad de iteraciones una de una busqueda binaria (bisection method)
	 * es O(log2((a-b)/eps)) the error is smaller that eps(usually 1e-9 is small
	 * enougth)
	 * 
	 * El epsilon para busquedas entre enteros es 1 probado con
	 * PowerOfCriptography
	 */

	public static void main(String[] args) {
		double Y = 1e13;
		double hi = Y / 2;// cosas asi al ser log2(n/epsilon) no impactan casi
							// nada
		// al fin y al cabo se mueve de a mitades en enteros por las diferencias
		System.out.printf(" raiz cuadrada de %f da %f\n", Y,
				busquedaBinariaRaizCuadrada(0, hi, Y));

		// al fin y al cabo se mueve de a mitades en enteros por las diferencias
		System.out.printf(" raiz cuadrada de %f da %f\n", Y,
				busquedaBinariaRaizCuadradaFor(0, hi, Y));

		System.out.println("siempre las pruebas deben incluir"
				+ Arrays.toString(new boolean[] { true, true }));//

		System.out.println(raizIntBig(BigInteger.valueOf(Long.MAX_VALUE)));
		// 3037000500
		System.out.println(raizIntBig(BigInteger.valueOf((Integer.MAX_VALUE))));
		// 46341
		System.out.printf("x=> %d, x^2=> %d\n", Integer.MAX_VALUE,
				(46341l * 463411));
		// x=> 2147483647, x^2=> 21474929151

		int posicionEncontre = busquedaBinaria(0, 9, 16);
		System.out.printf("busqueda binaria sobre el arreglo dio %d\n",
				posicionEncontre);
		posicionEncontre = busquedaBinaria(0, 1, 0);
		System.out.printf("busqueda binaria sobre el arreglo dio %d\n",
				posicionEncontre);
	}

	// public static void main(String[] args) {
	// System.out.println(Integer.MAX_VALUE);
	// double a=1e308;
	// System.out.println(a);
	// // Double d=new Double(null);
	// System.out.println(Double.POSITIVE_INFINITY);
	// }

	private static double busquedaBinariaRaizCuadrada(double lo, double hi,
			double Y) {
		double mid = 0;
		double eps = 0.000000001;
		double ans = 0;
		while (Math.abs(hi - lo) > eps) {
			mid = (lo + hi) / 2;
			double valor = mid * mid;
			if (valor >= Y) {
				hi = mid;
				ans = mid;
			} else if (valor < Y)
				lo = mid;
		}
		// double valor=lo*lo;
		// if(valor!=Y)
		// return -1;
		// else {
		// return lo;
		// }
		return ans;
	}

	/*
	 * Encuentra el primer objeto en un espacio de busqueda que cumpla un
	 * predicado descartando mitades de el espacio de busqueda(una raiz
	 * cuadrada)
	 */
	private static int busquedaBinaria(int lo, int hi, int val) {// como
																	// redondeos
																	// hacia
																	// abajo
																	// tener en
																	// cuenta
																	// que hi es
																	// +1 por lo
																	// menos
		int mid;
		int ans = -1;
		while (lo < hi) {// se puede quedar en ciclo dependiendo del problem se
							// puede cambiar por un ciclo //hi-1 // lo+1 da lo
							// mismo
			mid = lo + ((hi - lo) / 2);// evitar los redondeos hacia arriba
			if (mid * mid >= val) {// se pone un predicado que se debe cumplir
				/*
				 * aqui lo que falta es lo de los iguales, donde? en la raiz se
				 * ve, al parecer no hace diferencia solo es, se cumple tonces
				 * descarto la parte de arriba, no se cumple? ps voy pa arriba
				 */
				hi = mid;
				ans = hi;// depende de como plantee el espacio de busqueda como
							// guardo el ans en este caso porque incluye el
							// igual
			} else
				lo = mid + 1;/*
							 * en numeros reales es: lo=mid;//en otras palabras
							 * no se decarga el mid. Tambien en numeros reales
							 * se puede cortar por un epsilon(felix 10^-9) como
							 * la raiz o con una cantidad de iteraciones que
							 * puede dar mas precision en algunos momentos
							 */
		}
		if (ans * ans != val)
			return -1;/*
					 * hay algo malo en la busqueda binaria o el espacio de
					 * busqueda esta raro, como que no se encuentra en todo el
					 * intervalo
					 */
		else {
			return ans;
		}
	}

	private static int busquedaBinariaSimple(int lo, int hi, int val) {
		int mid;
		int ans = -1;
		while (lo < hi) {
			mid = lo + ((hi - lo) / 2);// evitar los redondeos hacia arriba
			if (mid * mid >= val) {// se pone un predicado que se debe cumplir
				hi = mid;
				ans = hi;
			} else
				lo = mid + 1;
		}
		if (ans * ans != val)
			return -1;
		else {
			return ans;
		}
	}

	/*
	 * binary_search(lo, hi, p): p es el predicado que se debe cumplir en el
	 * espacio de busqueda while lo < hi: mid = lo + (hi-lo)/2 //tener cuidado
	 * con esta condicion ya que se pierde precicion if p(mid) == true: hi = mid
	 * else: lo = mid+1
	 * 
	 * if p(lo) == false: complain // p(x) is false for all x in S!
	 * 
	 * return lo // lo is the least x for which p(x) is true
	 */

	private static BigInteger raizIntBig(BigInteger n) {
		BigInteger loint = BigInteger.ZERO;
		BigInteger hiint = n;
		BigInteger nint = n;
		BigInteger midint = BigInteger.ZERO;
		BigInteger twoint = BigInteger.valueOf(2);
		BigInteger ans = BigInteger.ZERO;

		while (loint.compareTo(hiint) < 0) {
			midint = loint.add(hiint.subtract(loint).divide(twoint));
			// esta es la parte
			// tricky
			// System.out.println("mid = "+midint.toString());
			BigInteger val = midint.multiply(midint);
			int comp = val.compareTo(nint);
			if (comp >= 0) {// cumple el predicado
				hiint = midint;
				ans = hiint;
			} else
				loint = midint.add(BigInteger.ONE);
		}
		// falta el complain de cuando en el low no se cumple

		return ans;

	}

	private static double busquedaBinariaRaizCuadradaFor(double lo, double hi,
			double Y) {
		double mid = 0;
		double eps = 10e-9;
		double ans = 0;
		/*
		 * La formula es log2((b-a)/eps) y si eps es 1e-9 se puede pasar a
		 * log2((b-a)*1e9)
		 */
		// lg((10^101-0)*10^9)~=366
		int times = (int) Math.ceil(Math.log((hi - lo) / eps) / Math.log(2));// al
																				// parecer
																				// no
																				// tiene
																				// problemas
																				// de
																				// precision
		for (int i = 0; i < times; i++) {
			mid = (lo + hi) / 2;
			double valor = mid * mid;
			if (valor >= Y) {
				hi = mid;
				ans = mid;
			} else if (valor < Y)
				lo = mid;
		}
		// double valor=lo*lo;
		// if(valor!=Y)
		// return -1;
		// else {
		// return lo;
		// }
		return ans;
	}

}
