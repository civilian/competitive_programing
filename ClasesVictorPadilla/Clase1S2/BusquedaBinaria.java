package ClasesVictorPadilla.Clase1S2;
/*SIEMPRE SE PRUEBA CON UN ESPACIO [NO,YES]*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class BusquedaBinaria {
	
	public static void main(String[] args) throws IOException {
		boolean [] arreglo=new boolean[10];
//		for (int i = 0; i < arreglo.length; i++) {
//			arreglo[i]=i;
//		}
		Arrays.fill(arreglo, false);
		arreglo[8]=true;
		arreglo[9]=true;
		int posicionEncontre=busquedaBinariaPrimOcurrencia(0, 9, arreglo);
		
		System.out.println(Arrays.toString(arreglo));
		System.out.println(posicionEncontre);
		
		double Y=25;
		double hi=Y/2;
		System.out.printf(" raiz cuadrada de %f", busquedaBinariaRaizCuadrada(0, hi, Y));
	}

	private static double busquedaBinariaRaizCuadrada(double lo, double hi, double Y) {
		double mid=0;
		double eps=0.0000001;
		while (Math.abs(hi-lo)>eps) {
			mid=(lo+hi)/2;
			double valor=mid*mid;
			if(valor>Y)
				hi=mid;
			else if(valor<=Y)
				lo=mid;
		}
//		double valor=lo*lo;
//		if(valor=Y)
//			return -1;
//		else {
//			return lo;
//		}
		return mid;
	}

	
	private static int busquedaBinariaPrimOcurrencia(int lo, int hi, boolean[] arreglo) {
		int mid;
		while (lo<hi) {
			mid=lo+((hi-lo)/2);
			if(checkCondition(arreglo[mid]))
				hi=mid;
			else 
				lo=mid+1;
		}
		
		if(!checkCondition(arreglo[lo]))
			return -1;/*hay algo malo en la busqueda binaria o 
					el espacio de busqueda esta raro*/
		else {
			return lo;
		}
	}
	
	private static boolean checkCondition(boolean b) {
		// TODO Auto-generated method stub
		return b;
	}
	
	/*binary_search(lo, hi, p):
	 * p es el predicado que se debe cumplir en el espacio de busqueda
   	while lo < hi:
      	mid = lo + (hi-lo)/2 //tener cuidado con esta condicion ya que
      						se pierde precicion
      	if p(mid) == true:
         	hi = mid
      	else:
         	lo = mid+1
          
   	if p(lo) == false:
      complain                // p(x) is false for all x in S!
      
   	return lo         // lo is the least x for which p(x) is true
	 * */
	
}
