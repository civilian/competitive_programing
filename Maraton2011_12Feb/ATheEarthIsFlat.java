package Maraton2011_12Feb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ATheEarthIsFlat {
	
	static int dondeU=0;
	
	private static String repetir(String expreion) {
		String salida="";
		//quito el ultimo que es el numero
		int tamannoExpresiones=(expreion.length()-1);
		int cuantasVeces=Integer.parseInt(expreion.charAt(tamannoExpresiones)+"");
		String exp=expreion.substring(0, tamannoExpresiones);
		
		
		for (int i = 0; i < cuantasVeces; i++) {
			salida+=exp;
		}
		return salida;
		// TODO Auto-generated method stub

	}
	
	static String parse(String linea, int donde) {
		// TODO Auto-generated method stub
		
		String expresiones="";
		
		for (; donde < linea.length(); donde++)//recorro la linea sacando las letras 
		{
			//le arranco pedazos a la linea y los computo
			char letra=linea.charAt(donde);
			dondeU=donde;
			if(letra=='(')//si es ( llamo recursivo
			{
				expresiones+=parse(linea, donde+1);
				donde=dondeU;
			}else if(letra==')')// si es ) mando a repetir lo que habia sacado
			{
				return repetir(expresiones);
			}else if (letra=='$')// si es solo e$ entonces no va a encontrar nada raro 
			{
				//ya hise todo tonces
				return expresiones;
			}else
			{
				expresiones+=letra;
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("ATheEarthIsFlat"));
		
		for (int casesId = 0;; casesId++) {
			String linea=sc.nextLine();
			linea=linea.trim();
			if(linea.startsWith("$"))
			{
				return;
			}
			linea=linea.replace(" ","" );
			//System.out.println(linea);
			System.out.println(parse(linea, 0));
		}
	}

}
