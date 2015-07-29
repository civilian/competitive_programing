package ClasesVictorPadilla.Clase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class ExtendToPalindrome {
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
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("ExtendToPalindrome"));
		
//		int numCases=nextInt();
		
		
		for (int casesId = 1;; casesId++) {
			String lienaIn=readln();
			
			if(lienaIn==null){
				return;
			}
			System.out.println(lienaIn.length());
			if(checkPalindrome(lienaIn))
			{
				
			}
			System.out.println(lienaIn);
//			else{
//				volverPalindromo(lienaIn);
//			}
		}
	}

	private static void volverPalindromo(String lienaIn) {
		
		StringBuilder tmp=new StringBuilder(lienaIn);
		StringBuilder palindromoDer=new StringBuilder();
		
		palindromoDer=volverPalindromoPorDerecha(tmp);
		
		System.out.println(palindromoDer);
//		tmp=new StringBuilder(lienaIn);
//		StringBuilder palindromoIzq=new StringBuilder();
//		palindromoIzq=volverPalindromoPorIzquierda(tmp);
		
//		if(palindromoDer.length()>palindromoIzq.length()){
//			System.out.println(palindromoIzq);
//		}else{
//			System.out.println(palindromoDer);
//		}
		// TODO Auto-generated method stub
	}

//
//	private static StringBuilder volverPalindromoPorIzquierda(StringBuilder tmp) {
//		int largo=tmp.length()-1;
//		
//		StringBuilder copia=new StringBuilder();
//		copia.append(tmp);
////		
////		System.out.println(tmp.charAt(largo)+" por izquierda");
////		System.out.println(copia);
//		
//		for (int i =largo, j=0 ; i >-1 ; i--, j++) {	
//			copia.insert(j,tmp.charAt(i));
//			
////			System.out.println(tmp.charAt(i)+" por izquierda");
////			System.out.println(copia);
//			
//			if(checkPalindrome(copia))
//			{
//				return copia;
//			}
//		}
//		// TODO Auto-generated method stub
//		return null;
//	}

	private static StringBuilder volverPalindromoPorDerecha(StringBuilder tmp) {
		int largo=tmp.length();
		
		for (int i = 0; i < largo ; i++) {
			tmp.insert(largo,tmp.charAt(i));
			
//			System.out.println(tmp.charAt(i)+" por derecha");
//			System.out.println(tmp);
			if(checkPalindrome(tmp))
			{
				return tmp;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	private static boolean checkPalindrome(StringBuilder lienaIn) {
		int largo=lienaIn.length();
		int mitad=largo/2;
		
		for (int I = 0; I < mitad+1; I++) {
			largo--;
			char primero=lienaIn.charAt(I);
			char segundo=lienaIn.charAt(largo);
			if(lienaIn.charAt(I)!=lienaIn.charAt(largo)){
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	private static boolean checkPalindrome(String lienaIn) {
		int largo=lienaIn.length();
		int mitad=largo/2;
		
		for (int I = 0; I < mitad+1; I++) {
			largo--;
			char primero=lienaIn.charAt(I);
			char segundo=lienaIn.charAt(largo);
			if(lienaIn.charAt(I)!=lienaIn.charAt(largo)){
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
}
