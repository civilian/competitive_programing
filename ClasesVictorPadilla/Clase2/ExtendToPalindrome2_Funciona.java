package ClasesVictorPadilla.Clase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class ExtendToPalindrome2_Funciona {
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
		
//		int numCases=nextInt();
		
		
		for (int casesId = 1;; casesId++) {
			
			String lienaIn=readln();
			
			if(lienaIn==null){
				return;
			}
			
			StringBuilder sbReves=new StringBuilder();
			
			for (int i = lienaIn.length()-1; i >-1 ; i--) {
				sbReves.append(lienaIn.charAt(i));
			}
//			System.out.println(sbReves);
//			System.out.println(lienaIn);
			for (int i = 0; i < lienaIn.length(); i++) {
				if(check(i,sbReves,lienaIn)){
					System.out.print(lienaIn.substring(0,i));
					System.out.println(sbReves);
					break;
				}
			}
		}
	}

	private static boolean check(int i, StringBuilder sbReves, String lienaIn) {
		
		for (int j = i, ik=0; j < lienaIn.length(); j++,ik++) {
			if (sbReves.charAt(ik)!=lienaIn.charAt(j)) {
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}
}
