package renamed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LetterFrecuency {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(System.in);
//		Scanner sc=new Scanner(new File("LetterFrecuency"));
		
		int testCases=sc.nextInt();
		sc.nextLine();
		
		for (int casesId = 0;casesId<testCases; casesId++) {
			Integer [] contar=new Integer[26];
			for (int i = 0; i < contar.length; i++) {
				contar[i] = new Integer(0);
			}
			
			String linea=sc.nextLine();
			linea=linea.toLowerCase();
			
			for (int i = 0; i < linea.length(); i++) {
				char letra = linea.charAt(i);
				if(Character.isLetter(letra))
				{
					int indice=letra-'a';
					if(!(indice>26))
					contar[indice]++;
				}
			}
			
			ArrayList<Integer> tmp=new ArrayList<Integer>(Arrays.asList(contar));
			Integer masimo=new Integer(Collections.max(tmp));
			int indice=tmp.indexOf(masimo);
//			if(masimo.equals(Integer.valueOf(0)))
//			{
//				continue;
//			}
			do{
				//acordarme que cuando reste algo de char lo tengo que volver a sumar
				System.out.print((char)(indice+'a'));
				tmp.set(indice, masimo+100);
				indice=tmp.indexOf(masimo);
			}while (indice!=-1);
			System.out.println();
		}
		
	}

}
