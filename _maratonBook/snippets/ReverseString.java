package maratonBook.snippets;

import java.util.Arrays;

public class ReverseString {
	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));

	}
	
	public static void main(String[] args) {
		String palabra = "alrevez";
		StringBuilder alrevez = reverseString(palabra);
		dbg(palabra, alrevez);
	}

	private static StringBuilder reverseString(String palabra) {
		return new StringBuilder(palabra).reverse();
	}
}
