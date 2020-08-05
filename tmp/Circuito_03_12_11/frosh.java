import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class frosh {
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
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("frosh.in"));
		readln();
		
		int cantidad=nextInt();
		int[] estudiantes=new int[cantidad];
		
		for (int i = 0; i < cantidad; i++) {
			readln();
			estudiantes[i]=nextInt();
		}
		dbg(estudiantes);
		dbg(mergeSort(estudiantes));
		System.out.println(swapsMerge);
		
//		swapsMerge=0;
//		int [] prueb=new int[1000000];
//		int i=1000000;
//		for (int j = 0; j < prueb.length; j++) {
//			prueb[j]=i--;
//		}
//		
////		Arrays.sort(prueb);
//		mergeSort(prueb);
////		dbg(prueb);
//		System.out.println(swapsMerge);
	}

	static int[] valuesMerge;
	static int sizeMerge;
	static long swapsMerge;
	
	static int[] mergeSort (int[] data) {
//		dbg("data",data, data.length);
		   if (data.length == 1)
		      return data;
		   int middle = data.length/ 2;
//		   dbg(middle);
//		   dbg("swap",swapsMerge);
		   int[] left = mergeSort(Arrays.copyOfRange(data, 0, middle));
		   int[] right = mergeSort(Arrays.copyOfRange(data, middle, data.length));
		   int[] result = new int[data.length];
		   int dPtr = 0;
		   int lPtr = 0;
		   int rPtr = 0;
		   
		   dbg("left",left);
		   dbg("right",right);
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

//	static void mergeSort(int[] values) {
//		swapsMerge=0;
//		valuesMerge=values;
//		sizeMerge=values.length;
//		
//		mergeSort(0, sizeMerge-1);
//	}

	static void mergeSort(int low, int high) {
		if(low<high){
			int middle=(low+high)/2;
			
			mergeSort(low ,middle);
			
			mergeSort(middle+1 ,high);
			
			merge(low, middle, high);
		}
		
	}

	private static void merge(int low, int middle, int high) {
		int[] helper= new int[sizeMerge];
		for (int i = low; i <=high; i++) {
			helper[i]=valuesMerge[i];
		}
		
		int i=low;
		int j=middle+1;
		int k=low;
//		dbg("helper",helper);
		
		while (i<=middle && j<=high) {
//			dbg("compare", helper[i].compareTo(helper[i]), )
			if(helper[i]<=helper[j]){
				valuesMerge[k]=helper[i];
				i++;
			}
			else {
				swapsMerge+=j-k;
				valuesMerge[k]=helper[j];
				j++;
			}
			k++;
		}
		while (i<=middle) {
			valuesMerge[k]=helper[i];
			k++;
			i++;
		}
		helper=null;
		
	}
	
	
	

	
}
