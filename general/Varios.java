package general;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Varios {
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	static boolean dif(Object...o) {
		for (int i = 0; i < o.length; i++) {
			for (int j = i; j < o.length; j++) {
				if(o[i]==o[j]) return false;
			}
		}
		return true;
	}
	
	private static void dbg(Object...out ) {
		System.out.println(Arrays.deepToString(out));
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		double s=Math.pow(10.0, 100)-1;
//		System.out.println(Math.exp(1.0));
//		System.out.printf("%+d\n",-1);
//		System.out.printf("%.2f\n",-1.0);
		int [][]matriz={{3,2},{5,6,4}};
		int [] a={5,4};
//		dbg(1,2 ,3 ,a,matriz);
		
//		output.print("a");
//		output.close();
		
		System.out.println(10000001);
		for (int i = 1; i <= 10000001; i++) {
			System.out.printf("%d %d\n",i,i);
		}
	}
}
