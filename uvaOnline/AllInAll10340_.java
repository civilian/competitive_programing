package uvaOnline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class AllInAll10340_ {
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("AllInAll10340_"));
		
		//int testCases=sc.nextInt();
		
		for (int casesId = 0;; casesId++) {
			if (!sc.hasNext()) {
				return;
			}
			String palabra1="",palabra2="";
			
			palabra1=sc.next();
			palabra2=sc.next();
			
			/*if(palabra1.length()>palabra2.length())
			{
				String tmp=palabra1;
				palabra1=palabra2;
				palabra2=tmp;//estpido
			}*/
			int cantLetras=palabra1.length();
			int fromIndex=0;
			for (int i = 0; i <cantLetras ; i++) 
			{
				char letraBuscar=palabra1.charAt(i);
				
				int encontreEn=palabra2.indexOf(letraBuscar);
				if(encontreEn!=-1)
				{
					palabra2=palabra2.substring(encontreEn+1);
				}else
				{
					System.out.println("No");
					break;
				}
				if(i==cantLetras-1)
				{
					System.out.println("Yes");
				}
			}
		}
	}

}
