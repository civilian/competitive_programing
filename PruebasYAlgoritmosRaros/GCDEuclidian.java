package PruebasYAlgoritmosRaros;

import java.util.Scanner;

public class GCDEuclidian {
	
	static int GCD(int a,int b) {
		while (b > 0) {
			a = a % b;
			a ^= b; b ^= a; a ^= b; } return a;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int numCases=sc.nextInt();
		int a=0;
		int b=0;
		
		for (int casesId= 0; casesId< numCases; casesId++) {
			a=sc.nextInt();
			b=sc.nextInt();
			
			System.out.println(GCD(a, b));
			
		}
	}
	
}
