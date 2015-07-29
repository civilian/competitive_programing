package ClasesVictorPadilla.Clase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class WordScramble {
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("WordScramble"));
		
//		int testCases=sc.nextInt();
		
		for (int casesId = 0;; casesId++) {
			
			if(!sc.hasNext()){
				return;
			}
			String linea=sc.nextLine();
			
			StringTokenizer st=new StringTokenizer(linea);
			while (st.hasMoreTokens()) {
				String palabra=st.nextToken();
				Character [] palabraProcesando=new Character[palabra.length()];
				for (int i = 0; i < palabra.length(); i++) {
					palabraProcesando[i]=palabra.charAt(i);
				}
				
				List a=Arrays.asList(palabraProcesando);
				Collections.reverse(a);
				String salida="";
				for (Iterator iterator = a.iterator(); iterator.hasNext();) {
					salida+= (Character) iterator.next();
				}
				
				
				System.out.print(salida+" ");
			}
			System.out.println();
			
		}
	}

}
