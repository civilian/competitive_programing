package Maraton2011_19Mar;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class NonIncrDigits_1BigIntegerNoFunciona {
	private static boolean isNonIncreasingDigits(BigInteger contar) {
		String revisar=contar.toString();
		int a=0;
		for (int i = 0; i < revisar.length()-1; i++) {
			int numeroA=Integer.parseInt(revisar.charAt(i)+"");
			int numeroB=Integer.parseInt(revisar.charAt(i+1)+"");
			if(numeroA>numeroB)
			{
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	static int cantidadDeNumeros(int digitos){
		int salida=0;
		
		BigInteger contar=new BigInteger("0");
//		int contar=0;
		String que=contar.toString()+"";
		int tamannoContar=que.length();
//		int tamannoContar=contar+"".length();
		while (tamannoContar<digitos+1) {//reviso el largo de el contar
			boolean isnot=isNonIncreasingDigits(contar);
			if(isnot)//averiguo si es decreasing y le sumo a salida
			{
				salida++;
			}
//			contar++;
			contar.add(BigInteger.valueOf(1));//voy sumando de a 1
			int cuanto=contar.intValue();
			que=contar+"";
			tamannoContar=que.length();//saco el largo luego de sumar
//			tamannoContar=contar+"".length();//saco el largo luego de sumar
		}
		
		return salida;
	}
	


	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(new File("NonIncrDigits"));
//		Scanner sc=new Scanner(System.in);
		int testCases=sc.nextInt();
		
		for (int casesId = 0; casesId < testCases; casesId++) {
			int caso=sc.nextInt();
			
			int digitos=sc.nextInt();
//			BigInteger prueba= sc.nextBigInteger();
//			System.out.println(isNonIncreasingDigits(prueba));
			System.out.println(cantidadDeNumeros(digitos));
		}
	}
}

