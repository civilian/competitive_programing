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

public class WordScramble_1MasRapido {
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("WordScramble"));
		
//		int testCases=sc.nextInt();
		
		for (int casesId = 0;; casesId++) {
			
			if(!sc.hasNext()){
				return;
			}
			String linea=sc.nextLine();
			
			StringTokenizer st=new StringTokenizer(linea);
			String salida="";
			while (st.hasMoreTokens()) {
				String palabra="";
				palabra=st.nextToken();
				String b="";
				
				for (int i = 0; i < palabra.length(); i++) {
					b=palabra.charAt(i)+b;
				}
				
				salida+=b+" ";
			}
			System.out.println(salida.trim());
		}
	}

}
