package ClasesVictorPadilla.Clase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Wertyu {
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		
		String [] let={"1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9",
				"0",
				"-",
				"=",
				"Q",
				"W",
				"E",
				"R",
				"T",
				"Y",
				"U",
				"I",
				"O",
				"P",
				"[",
				"]",
				"\\",
				"A",
				"S",
				"D",
				"F",
				"G",
				"H",
				"J",
				"K",
				"L",
				";",
				"'",
				"Z",
				"X",
				"C",
				"V",
				"B",
				"N",
				"M",
				",",
				".",
				"/"};
		ArrayList<String> letras=new ArrayList<String>(Arrays.asList(let));
		Scanner sc=new Scanner(new File("Wertyu"));
		
//		int testCases=sc.nextInt();
//		sc.nextLine();
		for (int casesId = 0;; casesId++) {
			if(!sc.hasNext())
			{
				return;
			}
			String linea=sc.nextLine();
			
			String resultado="";
			int indice=-1;
			for (int i = 0; i < linea.length(); i++) {
				char buscar=linea.charAt(i);
				if(buscar==' ')
				{
					resultado+=" ";
				}else{
					indice=letras.indexOf(buscar+"");
					resultado+=letras.get(indice-1);
					
				}
			}
			System.out.println(resultado);
		}
	}

}
