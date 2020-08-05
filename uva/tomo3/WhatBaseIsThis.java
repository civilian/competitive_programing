package uva.tomo3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class WhatBaseIsThis {
/**ICOMPLETO PERO FUNCIONA**/
	
	/**
	 * @param args
	 */
	private char maximoDeUnString(String palabra) {
		char caracter=Character.MIN_VALUE;
		for (int i = palabra.length()-1; i > -1 ; i--) {
			if(palabra.charAt(i)>caracter)
			{
				caracter=palabra.charAt(i);
			}
			//System.out.println(resultado);
		}
		return caracter;

	}
	
	private int charAEntero(char letra) {
		int digito=(int)letra;
		if(digito>47&& digito< 58)
			digito=digito-48;//'0'=49, 
		if(digito>64&&digito<91)
			digito=digito-55;//'A'=65,....'Z'=90
		//System.out.println(digito);
		return digito;//A=65 tonces devuelve A=10
	}
	
	public int convertirABaseDies(String numero, int base)
	{
		int resultado=0;
		//System.out.println(numero);
		for (int i = numero.length()-1, K=0; i > -1 ; K++,i--) {
			int digito=charAEntero(numero.charAt(i));
			resultado+=digito*Math.pow((double)base, (double)K);
			//System.out.println(resultado);
		}
		return resultado;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc= new Scanner(System.in);
		Scanner sc= new Scanner(new File("WhatBaseIsThis_343"));
		//sc.useDelimiter(Pattern.compile("[A-Z0-9]*"));
		WhatBaseIsThis obj=new WhatBaseIsThis();//por los metodos
		String x="", y="";
		
		while (true) {
			
//			System.out.println(x+" y= "+ y);
			if (!sc.hasNext())
				return;
			x=sc.next();
			y=sc.next();
			
			papa:
			for (int i = obj.charAEntero(obj.maximoDeUnString(x))+1; i < 37; i++) {
				for (int K = obj.charAEntero(obj.maximoDeUnString(y))+1; K < 37; K++) {
					if(obj.convertirABaseDies(x, i)==obj.convertirABaseDies(y, K))
					{
						System.out.println(x+" (base "+i +") = "+y+" (base "+K +")");
						break papa;
						//12 (base 3) = 5 (base 6)
					}
				}
				if(i==36)
					System.out.println(x+" is not equal to "+y+" in any base 2...36" );
			}
			//System.out.println(obj.convertirDeBaseADies(x, 10));
			
			//System.out.println(obj.maximoDeUnString(x));
			

			
		}
		
		
	}

}
