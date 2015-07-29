package uva.tomo118;

/*Uva 11858*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class FroshWeek {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l=input.readLine();
		if(l!= null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){ return _stk.nextToken();}
	static Integer nextInt(){ return Integer.parseInt(_stk.nextToken());}
	
	static void dbg(Object...o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("frosh.in"));
		while (readln()!=null) {
			int cantidad=nextInt();
			int[] estudiantes=new int[cantidad];
			
			for (int i = 0; i < cantidad; i++) {
				readln();
				estudiantes[i]=nextInt();
			}
			mergesortIni(estudiantes);
			System.out.println(swapsMerge);
		}
//		/*Prueba para el merge sort contando los swaps*/
//		int[] prueb=new int[500];
//		int i = 500;
//		for (int j = 0; j < prueb.length; j++) {
//			prueb[j]=i--;
//		}
////		System.out.println(Arrays.toString(prueb));
//		mergesortIni(prueb);
//		dbg("pruebaCambios swap",swapsMerge);//124750
	}

	static long swapsMerge;
	
	static int[] mergesortIni (int[] data) {
		swapsMerge=0;
		return mergesort(data);
	}
	
	static int[] mergesort (int[] data) {
//		dbg("data",data, data.length);
		   if (data.length == 1)
		      return data;
		   int middle = data.length/ 2;
//		   dbg(middle);
//		   dbg("swap",swapsMerge);
		   int[] left = mergesort(Arrays.copyOfRange(data, 0, middle));
		   int[] right = mergesort(Arrays.copyOfRange(data, middle, data.length));
		   int[] result = new int[data.length];
		   int dPtr = 0;
		   int lPtr = 0;
		   int rPtr = 0;
		   while (dPtr < data.length) {
		      if (lPtr == left.length) {
		         result[dPtr] = right[rPtr];
		         rPtr++;         
		      } else if (rPtr == right.length) {
		         result[dPtr] = left[lPtr];
		         lPtr++;
		      } else if (left[lPtr] <= right[rPtr]) {
		         result[dPtr] = left[lPtr];
		         lPtr++;
		      } else {
		    	 swapsMerge+=left.length-lPtr;
		         result[dPtr] = right[rPtr];
		         rPtr++;         
		      }
		      dPtr++;
		   }
		   return result;
		}
	
}
