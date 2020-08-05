package maratonBook.greedy;

public class CoinChangeEspecialCase {
	// DEMASIADO PELIGROSO, solo para algunos denominaciones de cambio en el
	// ejemplo del libro fueron:

	int[] denomin = new int[] { 25, 10, 5, 1 };
	int toChange = 42;
	// era solo escoger el mas grande y si era mas pequeño que lo que quedaba de
	// cambio utilizarlo
	// NO FUNCIONA PARA {4,3,1} con 6 centavos da 4,1,1 en vez de 3,3
}
