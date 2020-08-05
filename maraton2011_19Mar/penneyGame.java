package Maraton2011_19Mar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class penneyGame {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(new File("penneyGame"));
//		Scanner sc=new Scanner(System.in);
		
		int testCases=sc.nextInt();
		
		
		

		
		for (int casesId = 0; casesId < testCases; casesId++) {
			HashMap<String, Integer> contar=new HashMap<String, Integer>();
			contar.put("TTT", 0);
			contar.put("TTH", 0);
			contar.put("THT", 0);
			contar.put("THH", 0);
			contar.put("HTT", 0);
			contar.put("HTH", 0);
			contar.put("HHT", 0);
			contar.put("HHH", 0);
			
			int caso=sc.nextInt();
			sc.nextLine();
			String linea=sc.nextLine();
			int tamannoLinea=linea.length();
				for (int i = 0; i < linea.length()-2; i++) {
					String quien=linea.substring(i,i+3);
//					System.out.println(quien);
					int cuanto=contar.get(quien)+1;
					contar.put(quien, cuanto);
				}
				System.out.print(caso);
				
				String imprimir=contar.get("TTT").intValue()+" ";
				imprimir+=contar.get("TTH").intValue()+" ";
				imprimir+=contar.get("THT").intValue()+" ";
				imprimir+=contar.get("THH").intValue()+" ";
				imprimir+=contar.get("HTT").intValue()+" ";
				imprimir+=contar.get("HTH").intValue()+" ";
				imprimir+=contar.get("HHT").intValue()+" ";
				imprimir+=contar.get("HHH").intValue()+" ";

				System.out.println(" "+imprimir);
		}
	}
}
