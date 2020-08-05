package maratonBook.snippets;

public class OperacionesPorParejasEnUnArreglo {
	public static void main(String[] args) {
		porParejasExcluyendoIguales();
		//TODO hacer por trios
	}
	
	static void porParejasExcluyendoIguales() {
		int[] Array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < Array.length; i++) {
			for (int j = i+1; j < Array.length; j++) {
				System.out.printf("%d %d\n", Array[i], Array[j]);
			}
		}
	}
	
	static void porParejasConIguales() {
		int[] Array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < Array.length; i++) {
			for (int j = i; j < Array.length; j++) {
				System.out.printf("%d %d\n", Array[i], Array[j]);
			}
		}
	}
}
