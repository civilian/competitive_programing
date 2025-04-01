package maratonBook.matematica;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class RoundNumbers {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		System.out.println(roundTwoDecimals(1.9999));
		System.out.println(roundTwoDecimalsCut(1.9999));
	}

	static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		String convertido = twoDForm.format(d);
		// twoDForm.setRoundingMode(RoundingMode.DOWN); asi trunco en vez de
		// redondear
		return Double.parseDouble(convertido);
	}

	static double roundTwoDecimalsCut(double d) {
		// # quiere decir que si el numero es 0 se puede omitir "0.3", con 0 si
		// el numero es 0 no se omite eje "0.30"
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		twoDForm.setRoundingMode(RoundingMode.DOWN);// asi trunco en vez de
		// redondear
		String convertido = twoDForm.format(d);
		return Double.parseDouble(convertido);
	}
}
