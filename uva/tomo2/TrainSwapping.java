package uva.tomo2;
//Uva 299
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

//TIENE BUEN TIEMOPO
public class TrainSwapping {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("TrainSwapping"));
		readln();

		int numCases=nextInt();
		
		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();
			
			int itrenes=nextInt();
			
			int trenes[]=new int [itrenes];
			readln();
			for (int i = 0; i < itrenes; i++) {
				trenes[i]=nextInt();
			}
			
			swapsMerge=0;
			valuesMerge=trenes;
			sizeMerge=trenes.length;
			
			mergeSort(0, sizeMerge-1);
			System.out.printf("Optimal train swapping takes %d swaps.\n", swapsMerge);
		}
		
		
	}
	static int [] valuesMerge;
	static int swapsMerge;
	static int sizeMerge;
	
	static void mergeSort(int low, int hi){
		if(low<hi){
			int mid=(low+hi)/2;
			mergeSort(low, mid);
			
			mergeSort(mid+1, hi);
			
			merge(low, mid, hi);
		}
		
	}

	static void merge(int low, int mid, int hi) {
		int [] helper=new int[sizeMerge];
		
		for (int i = low; i <= hi; i++) {
			helper[i]=valuesMerge[i];
		}
		
		int i=low;
		int j=mid+1;
		int k=low;
		
		while (i<=mid &&j<=hi) {
			if(helper[i]<=helper[j]){
				valuesMerge[k]=helper[i];
				i++;
			}else {
				swapsMerge+=j-k;
				
				valuesMerge[k]=helper[j];
				j++;
			}
			k++;
		}
		while (i<=mid) {
			valuesMerge[k]=helper[i];
			k++;
			i++;
		}
		helper=null;
	}
}
