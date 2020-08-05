package maratonBook.matematica;
/*Greatest comon divisor
 * se implementa diferente para velocidad pero, 
 * GCD(a,b)=GCD(b,(a mod b)*/

/*Lowest comon multiple, (m*n)/GCD(m,n)*/
import java.util.Scanner;

public class GCD_LCM {
	
	static int GCD(int a,int b) {
		while (b > 0) {
			a = a % b;
			a ^= b; b ^= a; a ^= b; } return a;
	}
	
	static int LCM(int m, int n){
		return (m*n)/GCD(m, n);
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
//		int numCases=sc.nextInt();
		int a=0;
		int b=0;
		
		for (int casesId= 0; /*casesId< numCases*/
		; casesId++) {
			a=sc.nextInt();
			b=sc.nextInt();
			
			System.out.println(LCM(a, b));
			
		}
	}
	
}
