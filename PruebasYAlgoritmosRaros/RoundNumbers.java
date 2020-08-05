package PruebasYAlgoritmosRaros;

import java.text.DecimalFormat;

public class RoundNumbers {
	static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        String convertido=twoDForm.format(d);
        convertido=convertido.replace(',', '.');
      return Double.parseDouble(convertido);
	}
	
	public static void main(String[] args) {
		System.out.println(roundTwoDecimals(1.9999));
	}
}
