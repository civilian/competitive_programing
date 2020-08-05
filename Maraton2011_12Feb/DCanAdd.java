package Maraton2011_12Feb;

import java.util.Arrays;
import java.util.Scanner;

public class DCanAdd {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int cantidadDeCasos=0;
		cantidadDeCasos=sc.nextInt();
		for(int cantida=0; cantida<cantidadDeCasos; cantida++){
			//tamanno
			int N=sc.nextInt();
			
			int [][] matriz= new int[N] [N];
			//leer matriz
			for(int i=0; i<N;i++){
				for(int j=0; j<N; j++) {
					matriz[i][j]=sc.nextInt();
				}
			}
			
			//System.out.println(Arrays.deepToString(matriz));
			
			int []arriba= new int [N];
			int []izq= new int [N];
			
			arriba[0]=matriz[0][0];
			
			for(int m=0; m<N; m++)
			{
				izq[m]=matriz[m][0]-arriba[0];
			}
			for(int m=0; m<N; m++)
			{
				arriba[m]=matriz[0][m]-izq[0];
			}
			
			//System.out.println(Arrays.toString(izq)+"izquiedo");
			//System.out.println(Arrays.toString(arriba)+"arriba");
			boolean puede=true;
			outer:
			for(int i=0; i<N;i++){
				for(int j=0; j<N; j++) {
					/*System.out.println(matriz[i][j]);
					System.out.println(arriba[j]);
					System.out.println(izq[i]);*/
					if(matriz[i][j]!=arriba[j]+izq[i])
					{
						puede=false;
						break outer;
					}
				}
			}
			
			if(puede)System.out.println(cantida+1 +". YES");
			else System.out.println(cantida+1 +". NO");
		}
	}

}
