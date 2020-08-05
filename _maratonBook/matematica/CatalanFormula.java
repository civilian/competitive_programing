package maratonBook.matematica;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/*		(2n)!
 -------------
 ((n+1)!(n)!)*/

/*-Counts of unlabeled rooted binary trees with N vertices are exactly
 the famous Catalan numbers, 
 * -Re-interpreting the symbol X as an open parenthesis and Y as a 
 * close parenthesis, Cn counts the number of expressions containing 
 * n pairs of parentheses which are correctly matched:
 ((()))     ()(())     ()()()     (())()     (()())
 -Successive applications of a binary operator can be represented in 
 terms of a full binary tree. (A rooted binary tree is full if every 
 vertex has either two children or no children.) It follows that Cn 
 is the number of full binary trees with n + 1 leaves:
 -Cn is the number of non-isomorphic ordered trees with n + 1 vertices. 
 (An ordered tree is a rooted tree in which the children of each vertex 
 are given a fixed left-to-right order.)*/
public class CatalanFormula {

	public static void main(String[] args) {
		catalanNumber(10);
		System.out.println(hCatalanNumbers);
		System.out.println(hCatalanNumbers.get(3));
	}

	// CATALAN NUMBERS
	static int MAX_N = 1000;// no es absolutamente necesario
	static HashMap<Long, BigInteger> hCatalanNumbers = new HashMap<Long, BigInteger>(
			MAX_N);

	private static BigInteger catalanNumber(long n) {
		if (!hCatalanNumbers.containsKey(n)) {
			hCatalanNumbers.put(n,
					fact(2 * n).divide((fact(n + 1).multiply(fact(n)))// divide
							));// put
		}
		return hCatalanNumbers.get(n);
	}

	/*
	 * esta este metodo que aprovecha los calculos anteriores, pero tiene costo
	 * n para cada n con el for que tiene, y la otras alternativas del metodo
	 * necesita calculos decimales. private static double catR1(long n){
	 * if(catsR1.containsKey(n)){ return catsR1.get(n); } double sum = 0;
	 * for(int i = 0; i < n; i++){ sum += catR1(i) * catR1(n - 1 - i); }
	 * catsR1.put(n, sum); return sum; }
	 */

	/* Aux */
	static HashMap<Long, BigInteger> hMFactoriales = new HashMap<Long, BigInteger>();

	static {
		hMFactoriales.put(0L, BigInteger.ONE);
		hMFactoriales.put(1L, BigInteger.ONE);
	}

	static BigInteger fact(long n) {
		BigInteger tmp = hMFactoriales.get(n);
		if (tmp != null)
			return tmp;
		/* si no tiene nada utilizo tmp para las operaciones */
		/* hago la recursion */
		tmp = fact(n - 1);
		/* multiplico */
		tmp = tmp.multiply(BigInteger.valueOf(n));
		/* memoizo */
		hMFactoriales.put(n, tmp);
		return tmp;
	}

	// CATALAN NUMBERS END

	// CATALAN NUMBERS DP// UTILIZA VALORES DECIMALES ENTONCES ES POCO PRECISO

	// static long maxN = 100, catalanNumbers[] = new long[maxN];
	//
	// static long cat(long n){
	// if (n==0) {
	// return 1;
	// }
	// long num= (2*n +2)*(2*n +1);
	// long denum= (n +2)*(n +1);
	// return
	// }

	// CATALAN NUMBERS DP END

}
