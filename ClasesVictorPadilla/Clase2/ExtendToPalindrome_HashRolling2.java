package ClasesVictorPadilla.Clase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class ExtendToPalindrome_HashRolling2 {
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
		input=new BufferedReader(new FileReader("ExtendToPalindrome"));

		
		
		for (int casesId = 1;; casesId++) {
			String lienaIn=readln();
			
			if(lienaIn==null){
				return;
			}
			
			System.out.println(lienaIn.length());
			int largo=lienaIn.length();
			
			for (int i = 0; i <largo ; i++) {
				if(lienaIn.charAt(i)==lienaIn.charAt(largo-1))
				{
					
//					String palindromo=lienaIn.substring(i);
//					boolean esPalin=checkPalindrome(new StringBuilder(palindromo));
					boolean esPalin=checkPalindrome(lienaIn,i);
					
					if(esPalin){
						System.out.println(i);
						StringBuilder volteado=new StringBuilder();
						for (int j = i-1; j >-1; j--) {
							volteado.append(lienaIn.charAt(j));
						}
						
						lienaIn=lienaIn+volteado;
						break;
					}
				}
			}
			
			System.out.println(lienaIn);
			System.out.println(checkPalindrome(lienaIn, -1));

		}
	}
	
	private static boolean checkPalindrome(String lienaIn, int i) {
		int largo=lienaIn.length();
		int mitad=largo/2;
		
		for (int I = i+1; I < largo; I++) {
			largo--;
			char primero=lienaIn.charAt(I);
			char segundo=lienaIn.charAt(largo);
			
			if(lienaIn.charAt(I)!=lienaIn.charAt(largo)){
//				System.out.println(primero+ "  segundo>" +segundo);
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
}