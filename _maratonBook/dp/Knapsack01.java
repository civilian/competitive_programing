package maratonBook.dp;

/*Uva 10130*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 3
 72 17
 44 23
 31 24
 1
 26
 * A=72 +++++++++++++++++++++++ 
 6
 64 26
 85 22
 52 4
 99 18
 39 13
 54 9
 4
 23
 20
 20
 26
  * A=514 

  6
 64 26
 85 22
 52 4
 99 18
 39 13
 54 9
 1
 19
*/
public class Knapsack01 {

	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws FileNotFoundException {
		int i, T, G, ans;
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new File("SuperSale"));

		for (int k = 0; k < memo.length; k++)
			for (int j = 0; j < memo[0].length; j++){
				memo[k][j] = -1;
				memoShow[k][j]=false;
			}

		N = sc.nextInt();// items
		for (i = 0; i < N; i++) {
			V[i] = sc.nextInt();
			W[i] = sc.nextInt();// pesos y valor
		}

		// En este problema en particular el tamaño de la mochila cambia pero no
		// sus objetos, muestra que solo se debe limpiar el memo cuando cambien
		// los objetos asi sea 1
		ans = 0;
		G = sc.nextInt();// cuantos diferentes pesos
		// dbg("N ",N, "G ",G, " V ", V, " W ", W, " memo ",memo);
		while (G-- != 0) {
			MW = sc.nextInt();
//			ans += value(0, MW);// diferentes pesos maximos
			ans +=valueShow(0, MW);
			
			int j=MW;
			for (i = 0; i <= N; i++) {
				if (memoShow[i][j]) {
					System.out.printf(" %d,",V[i]);
					j-=W[i];
				}	
			}
		}
		System.out.println();
		System.out.printf("%d\n", ans);
	}

	static int MAX_N = 1010;
	static int MAX_W = 40;

	static int N, MW, V[] = new int[MAX_N], W[] = new int[MAX_N],
			memo[][] = new int[MAX_N][MAX_W];

	/*
	 * computa El mayor precio posible de los items que tienen peso W y valor V,
	 * con un limite de peso MW
	 */
	static int value(int id, int w) {
		if (id == N || w == 0)
			return 0;
		if (memo[id][w] != -1)
			return memo[id][w];
		if (W[id] > w)
			return memo[id][w] = value(id + 1, w);
		return memo[id][w] = Math.max(value(id + 1, w),
				V[id] + value(id + 1, w - W[id]));
	}

	static int noCogerlo, cogerlo;
	static boolean[][] memoShow = new boolean[MAX_N][MAX_W];

	/*
	 * computa El mayor precio posible de los items que tienen peso W y valor V,
	 * con un limite de peso MW
	 */
	static int valueShow(int id, int w) {
		if (id == N || w == 0)
			return 0;
		if (memo[id][w] != -1)
			return memo[id][w];
		if (W[id] > w){
			memoShow[id][w]=false;
			return memo[id][w] = valueShow(id + 1, w);
		}
		noCogerlo = valueShow(id + 1, w);
		cogerlo = V[id] + valueShow(id + 1, w - W[id]);
		
		if (noCogerlo > cogerlo) {
			memoShow[id][w] = false;
			return memo[id][w] = noCogerlo;
		} else {
			memoShow[id][w] = true;
			return memo[id][w] = cogerlo;
		}
	}
}
