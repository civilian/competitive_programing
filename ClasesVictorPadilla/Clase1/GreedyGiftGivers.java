package ClasesVictorPadilla.Clase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GreedyGiftGivers {
	
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("GreedyGiftGivers"));
		
//		int testCases=sc.nextInt();
		
		for (int casesId = 0;; casesId++) {
			
			int cantidadDeGente=sc.nextInt();
			sc.nextLine();
			
			String [] gente=new String[cantidadDeGente];
			HashMap<String, Integer> regalos=new HashMap<String, Integer>();
			
			for (int i = 0; i < cantidadDeGente; i++) {
				gente[i]=sc.next();
				regalos.put(gente[i], new Integer(0));
			}
			sc.nextLine();
			
			for (int i = 0; i < cantidadDeGente; i++) {
				
				String quien=sc.next();
				
				int dinero=sc.nextInt();
				int quienes=sc.nextInt();
				
//				System.out.println(quien+" da "+dinero+ " a cuantos "+ quienes);
				
				Integer daTotal=regalos.get(quien);
				
				if(quienes!=0)
				daTotal+=(dinero%quienes)-dinero;
				
				regalos.put(quien, daTotal);
				Integer dioATodos=0;
				
				if(quienes!=0)
				dioATodos=dinero/quienes;
				
				for (int J = 0;  J< quienes; J++) {
					String aQuien=sc.next();
					Integer da=regalos.get(aQuien);
					da+=dioATodos;
					
					regalos.put(aQuien, da);
				}
				sc.nextLine();
			}
			
			for (int i = 0; i < gente.length; i++) {
				System.out.println(gente[i]+" "+ regalos.get(gente[i]));
			}
			
			
			if(sc.hasNext()){
				System.out.println();
			}else return;
		}

	}

}
