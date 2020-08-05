package maratonBook.matematica;

public class Logaritmos_Exponenciacion {
	public static void main(String[] args) {
		// log_b(a)(base b)
		double a = 1024.0, b = 2.0;
		double deno = Math.log(a);
		double numera = Math.log(b);
		System.out.println(deno / numera);

		// cantidad de digitos de un numero
		System.out.println(Math.floor(1 + Math.log10(a)));
		
		//raiz n de a
		a=8;
		double n=3;
		System.out.println(Math.pow(a, 1/n));

	}
}
