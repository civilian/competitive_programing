package maratonBook.snippets;

import java.util.Arrays;

public class PegarEspacios {
	
	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) {
		String palabra = "Dragon";
		int tamannoEsperado = palabra.length() + 7;

		String nuevaPalabra = pegarEspaciosDelante(palabra, tamannoEsperado);
		String nuevaPalabraTras = pegarEspaciosAtras(palabra, tamannoEsperado);
		dbg(palabra, nuevaPalabra, nuevaPalabraTras);
	}

	

	private static String pegarEspaciosDelante(String palabra,
			int tamannoEsperado) {
		if (tamannoEsperado > palabra.length() ) {
			int cuantos=tamannoEsperado-palabra.length();
			for (int i = 0; i < cuantos; i++) {
				palabra+= " ";
			}
		}
		return palabra;
	}
	
	private static String pegarEspaciosAtras(String palabra,
			int tamannoEsperado) {
		if (tamannoEsperado > palabra.length() ) {
			int cuantos=tamannoEsperado-palabra.length();
			for (int i = 0; i < cuantos; i++) {
				palabra= " "+palabra;
			}
		}
		return palabra;
	}

}
