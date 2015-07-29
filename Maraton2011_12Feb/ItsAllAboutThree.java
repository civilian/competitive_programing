package Maraton2011_12Feb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


public class ItsAllAboutThree {
   
    ArrayList<Integer> listaPrimos=null;
   
    public ItsAllAboutThree() {
        listaPrimos=new ArrayList<Integer>();
        // TODO Apéndice de constructor generado automáticamente
    }
   
    boolean chequearPrimo(int n) {
    	int i=0;
        for(i=0; i<listaPrimos.size();i++)
        {
        	if(n==listaPrimos.get(i))
        		return true;
        	else if(n%listaPrimos.get(i)==0)
                return false;
        }
        if(listaPrimos.get(i-1)<n)
        {
        	for(int idy=listaPrimos.get(i-1); idy<n; idy++)
        	{
        		if(n%idy==0)
        		{
        			return false;
        		}
        	}
        }
        return true;
        // TODO Apéndice de método generado automáticamente
    }
   
    int esDivisiblePor(int n) {
    	int i=0;
        for(i=0; i<listaPrimos.size();i++)
        {
            if(n%listaPrimos.get(i)==0)
                return listaPrimos.get(i);
        }
        if(listaPrimos.get(i-1)<n)
        {
        	for(int idy=listaPrimos.get(i-1); idy<n; idy++)
        	{
        		if(n%idy==0)
        		{
        			return idy;
        		}
        	}
        }
        return -1;
        // TODO Apéndice de método generado automáticamente
    }
   
    private void listaNumerosPrimos(int n) {
        for(int idx=3; idx<n; idx+=2)
        {
            if(chequearPrimo(idx)){
                listaPrimos.add(idx);
            }
               
        }
        // TODO Apéndice de método generado automáticamente
    }
   
    boolean enSerie(int numero) {
        if(chequearPrimo(numero))
        {
        	//System.out.println("caso base");
            String dio=numero+"";
            if(dio.charAt(dio.length()-1)=='3')
            {
                return true;
            }else{
                return false;
            }
        }
        else
        {
        	//System.out.println("el otro");
        	int divisiblePor=esDivisiblePor(numero);
            if(enSerie(divisiblePor))
            {
                return enSerie((numero/divisiblePor)) ;
            }
            else return false;
        }
        // TODO Apéndice de método generado automáticame

    }
   
    public static void main(String[] args) {
       
        ItsAllAboutThree obj=new ItsAllAboutThree();
        obj.listaPrimos.add(2);
        obj.listaNumerosPrimos(100000);
        System.out.println(Arrays.deepToString(obj.listaPrimos.toArray()));
       
        Scanner sc= new Scanner(System.in);
        int probar=0;
        while(true)
        {
            probar=sc.nextInt();
            if(probar==-1)
            {
                return;
            }
           
            if(obj.enSerie(probar))
            {
                System.out.println(probar+" YES");
            }else System.out.println(probar+" NO");

           
        }
       
       
    }

}