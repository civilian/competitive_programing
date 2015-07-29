package Maraton2011_12Feb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class TheDoubleHelix {
	

	private int sumarDesdeHastaArray(ArrayList<Integer> array, int desde, int hastaIncluido) {
		int suma=0;
		if(desde>=array.size())
		{
			return 0;
		}
        for(int id=desde;id<hastaIncluido+1;id++)
        {
            //System.out.println("SumaEncuentro: "+suma+", SumaAuxEncuentro de array; "+sumaAux);
            //System.out.println(array.get(id));
            suma+=array.get(id);
         }
        return suma;
    }




    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        TheDoubleHelix obj=new TheDoubleHelix();
       
        int tamano1,tamano2;
        ArrayList<Integer> array1,array2;
       
       
        SCANNER:while(true)
        {

            array1=new ArrayList<Integer>();
            array2=new ArrayList<Integer>();
            tamano1=sc.nextInt();
            if(tamano1==0) return;
           
            for(int idx=0;idx<tamano1;idx++)
            {
                array1.add(sc.nextInt());
            }
           
            tamano2=sc.nextInt();
            for(int idx=0;idx<tamano2;idx++)
            {
                array2.add(sc.nextInt());
            }
           
            //System.out.println(Arrays.deepToString(array1.toArray()));
            //System.out.println(Arrays.deepToString(array2.toArray()));
           
            //Collections.sort(array1);
            //Collections.sort(array2);
           
            //System.out.println(Arrays.deepToString(array1.toArray()));
            //System.out.println(Arrays.deepToString(array2.toArray()));
            int suma1=0;
            int suma2=0;
            int sumaRes=0;
            int busqueda=0;
            int desdeArreglo2=0;
            int desdeArreglo1=0;
            
            int hastaArreglo2=0;
            int hastaArreglo1=0;
            for(int id=0;id<array1.size();id++)
            {
                //System.out.println("SumaEncuentro: "+suma+", SumaAuxEncuentro de array; "+sumaAux);
                //System.out.println(array.get(id));
                //suma+=array1.get(id);
                busqueda=Collections.binarySearch(array2, array1.get(id));
                if(busqueda>-1)
                {
                	hastaArreglo2=busqueda;
                	hastaArreglo1=id;
                	suma2=obj.sumarDesdeHastaArray(array2, desdeArreglo2, hastaArreglo2);
                	suma1=obj.sumarDesdeHastaArray(array1, desdeArreglo1, hastaArreglo1);
                	if(suma1>suma2)
                	{
                		sumaRes+=suma1;
                	}else sumaRes+=suma2;
                	suma1=0;
                	suma2=0;
                	
                	desdeArreglo2=hastaArreglo2+1;
                	desdeArreglo1=hastaArreglo1+1;
                }

            }//for
        	suma2=obj.sumarDesdeHastaArray(array2, desdeArreglo2, array2.size()-1);
        	suma1=obj.sumarDesdeHastaArray(array1, desdeArreglo1, array1.size()-1);
        	if(suma1>suma2)
        	{
        		sumaRes+=suma1;
        	}else sumaRes+=suma2;
        	
            System.out.println(sumaRes);
            sumaRes=0;
        }//while
    }
            
       
 }

