package Maraton2011_12Feb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class IntentoEnMaratonTheDoubleHelix {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
       
       
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
           
            Collections.sort(array1);
            Collections.sort(array2);
           
            //System.out.println(Arrays.deepToString(array1.toArray()));
            //System.out.println(Arrays.deepToString(array2.toArray()));
            
            int suma=0;
            int sumaAux=0;
            int ultimaArrayAux=0;
            int id1=0;
            int id2=0;
            ArrayList<Integer> array=array1;
            ArrayList<Integer> arrayAux=array2;
           
            for(int id=0;id<array.size();id++)
            {
                //System.out.println("SumaEncuentro: "+suma+", SumaAuxEncuentro de array; "+sumaAux);
                //System.out.println(array.get(id));
                suma+=array.get(id);
                int busqueda=Collections.binarySearch(arrayAux, array.get(id));
                if (busqueda>0 && busqueda>ultimaArrayAux)
                {
                    //System.out.println("SumaEncuentro: "+suma+", SumaAuxEncuentro; "+sumaAux);
                    //System.out.println(array.get(id));
                   
                    for(int idaux=ultimaArrayAux;idaux<busqueda+1;idaux++)
                    {
                        //System.out.println("SumaEncuentro: "+suma+", SumaAuxEncuentro; "+sumaAux);
                        //System.out.println(arrayAux.get(idaux));
                        sumaAux+=arrayAux.get(idaux);
                    }

                    //System.out.println("Suma: "+suma+", SumaAux; "+sumaAux);
               
                   
                    if(suma<sumaAux){
                        ArrayList <Integer>tmpArray=null;
                        tmpArray=array;
                        array=arrayAux;
                        arrayAux=tmpArray;
                       
                       
                        suma=sumaAux;
                       
                        ultimaArrayAux=id+1;
                        id=busqueda+1;
                    }else
                    {
                        ultimaArrayAux=busqueda+1;
                        sumaAux=suma;
                    }
                   
                    System.out.println("decidi Suma: "+suma+", SumaAux; "+sumaAux);
                   
                    if(id==array.size()-1 && ultimaArrayAux<(arrayAux.size()-1))
                    {
                        System.out.println("entro");
                        ArrayList <Integer>tmpArray=null;
                        tmpArray=array;
                        array=arrayAux;
                        arrayAux=tmpArray;

                        int tmpSuma=suma;
                        suma=sumaAux;
                        sumaAux=tmpSuma;
                       
                        int tmp=ultimaArrayAux;
                        ultimaArrayAux=id;
                        id=tmp;                   
                    }
                }
            }
           
            if(suma>sumaAux) System.out.println(suma);
            else System.out.println(sumaAux);
        }
       
    }

}
