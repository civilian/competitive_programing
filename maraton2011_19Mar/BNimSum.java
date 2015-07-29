package Maraton2011_19Mar;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class BNimSum {
	
	BNimSum(){}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(new File("BNimSum"));
//		Scanner sc=new Scanner(System.in);
		int testCases=sc.nextInt();
		
		BNimSum obj=new BNimSum();
		
		for (int casesId = 0; casesId < testCases; casesId++) {
			int caso=sc.nextInt();
			int base=sc.nextInt();
			int x=sc.nextInt();
			int y=sc.nextInt();
			
//			obj.prueba();
			
			Vector<Integer> xEnB=deDecimalABase(x,base);
			Vector<Integer> yEnB=deDecimalABase(y,base);
			Vector<Integer> suma=sumaBNimSum(xEnB,yEnB,base);
			
			System.out.println(deBaseADecimal(suma,base));
			
		}
	}



	private static int deBaseADecimal(Vector<Integer> suma, int base) {
		Collections.reverse(suma);
		int resultado=0;
		for (int i = 0; i < suma.size(); i++) {
			Integer digito=suma.get(i);
			int valorBase=(int)Math.pow((double)base,(double)i);
			resultado+=digito*valorBase;
		}
		return resultado;
		// TODO Auto-generated method stub
		
	}



	private static Vector <Integer> sumaBNimSum(Vector<Integer> largo, Vector<Integer> cortico,int base) {
		
		Vector<Integer> tmp=new Vector<Integer>();
		int largoL=largo.size();
		int largoC=cortico.size();
		if(largoC>largoL)
		{
			tmp=cortico;
			cortico=largo;
			largo=tmp;
		}
		Collections.reverse(largo);
		Collections.reverse(cortico);
		
		tmp=new Vector<Integer>();
		
		int i = 0;
		for (; i < cortico.size(); i++) {
			int sume=(cortico.get(i)+largo.get(i))%base;
			tmp.add(i,sume);
		}for (int j = i; j < largo.size(); j++) {
			tmp.add(j,largo.get(j));
		}

		Collections.reverse(tmp);
		return tmp;
		// TODO Auto-generated method stub
	}



	private static Vector<Integer> deDecimalABase(int x, int base) {
		Vector<Integer> respuesta=new Vector<Integer>();
		
		do{
			respuesta.add(x%base);
		}while((x/=base)!=0);
		
		Collections.reverse(respuesta);
		return respuesta;
		// TODO Auto-generated method stub
	}



//	private void prueba() {
//		// TODO Auto-generated method stub
//		Integer a=new Integer(435);
//		System.out.println(a.toBinaryString(435));
//	}
}

