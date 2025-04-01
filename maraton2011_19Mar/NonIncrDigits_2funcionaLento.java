package Maraton2011_19Mar;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import sun.print.resources.serviceui;

public class NonIncrDigits_2funcionaLento {
	Vector <String> aux;

	public NonIncrDigits_2funcionaLento() {
		aux=new Vector<String>();
		for (int i = 0; i < 10; i++) {
			aux.add(""+i);
		}
		// TODO Auto-generated constructor stub
	}
	private static boolean isNonIncreasingDigits(String contar) {
		String revisar=contar;
//		for (int i = 0; i < revisar.length()-1; i++) {
			int numeroA=Integer.parseInt(revisar.charAt(0)+"");
			int numeroB=Integer.parseInt(revisar.charAt(1)+"");
			if(numeroA>numeroB)
			{
				return false;
			}
//		}
		// TODO Auto-generated method stub
		return true;
	}
	
	
	int cantidadDeNumeros(int digitos){
		Vector <String> numerosValidos;
		numerosValidos=(Vector<String>)aux.clone();
//		for (int i = 0; i < 10; i++) {
//			aux.add(""+i);
//			numerosValidos.add(""+i);
//		}
		int ii=(aux.get(0)).length();
		for (; ii < digitos; ii++) {
			numerosValidos=new Vector<String>();
			for (int i = 0; i < 10; i++) {
				int cantidad=aux.size();
				for (int j = 0; j < cantidad; j++) {
					String tmp=aux.get(j);
					tmp=i+tmp;
					if(isNonIncreasingDigits(tmp))
					{
						numerosValidos.add(tmp);
					}
				}
			}
			aux=(Vector<String>)numerosValidos.clone();
		}
		return numerosValidos.size();
	}
	


	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc=new Scanner(new File("NonIncrDigits"));
//		Scanner sc=new Scanner(System.in);
		int testCases=sc.nextInt();
		NonIncrDigits_2funcionaLento obj=new NonIncrDigits_2funcionaLento();
		
		for (int casesId = 0; casesId < testCases; casesId++) {
			int caso=sc.nextInt();
			
			int digitos=sc.nextInt();
//			BigInteger prueba= sc.nextBigInteger();
//			System.out.println(isNonIncreasingDigits(prueba));
//			System.out.println(cantidadDeNumeros(digitos, obj.numerosValidos,obj.aux));
			for (int i = 1; i < 65; i++) {
				System.out.println(obj.cantidadDeNumeros(i));	
			}
		}
	}
}


