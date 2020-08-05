package MaratonArtOfProgramingContest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class WhatBaseIsThis_343 {
	
	static char[] chATodo="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();//es lo unico que cambia
//	for (char c = '0'; c <= 'Z'; c++) {
//		System.out.print(c);
//	}
	
	private char maximoDeUnString(String palabra) {
		char caracter=Character.MIN_VALUE;
		for (int i = palabra.length()-1; i > -1 ; i--) {
			if(palabra.charAt(i)>caracter)
			{
				caracter=palabra.charAt(i);
			}
			//System.out.println(resultado);
		}
		if (caracter=='0')return '1';
		return caracter;

	}
	
	private static int deCharAEntero(char letra) {
		return Arrays.binarySearch(chATodo, letra);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc= new Scanner(System.in);
		Scanner sc= new Scanner(new File("WhatBaseIsThis_343"));
		WhatBaseIsThis_343 obj=new WhatBaseIsThis_343();//por los metodos
		String x="", y="";
		
		while (true) {
			
//			System.out.println(x+" y= "+ y);
			if (!sc.hasNext())
				return;
			x=sc.next();
			y=sc.next();
			int i = obj.deCharAEntero(obj.maximoDeUnString(x))+1;
			int K = obj.deCharAEntero(obj.maximoDeUnString(y))+1;
			papa:
			for (; i < 37; i++) {
				for (; K < 37; K++) {
					if(Long.parseLong(x, i)==Long.parseLong(y, K))
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
