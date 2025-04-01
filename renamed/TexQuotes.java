package renamed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TexQuotes {
	boolean uno=true;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(System.in);
//		Scanner sc=new Scanner(new File("TexQuotes"));
		
//		int testCases=sc.nextInt();
		TexQuotes obj=new TexQuotes();

		for (int casesId = 0;; casesId++) {
//			System.out.println(casesId);
			String linea=sc.nextLine();
			
			for (int i = 0; i < linea.length(); i++) {
				char letra=linea.charAt(i);
				if(letra=='"')
				{
					System.out.print(obj.cambiando());
				}else
				{
					System.out.print(letra);
				}	
			}
			if(sc.hasNext()){
				System.out.println();	
			}
			else
			{
				return;
			}
		}
	}
	
	private String cambiando() {
		// TODO Auto-generated method stub
		if(uno)
		{
			uno=false;
			return "``";
		}
		uno=true;
		return "''";
	}

}
