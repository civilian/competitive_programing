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

public class ExtendToPalindrome_HashRolling {
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

		
		
		for (int casesId = 1;; casesId++) {
			String lienaIn=readln();
			
			if(lienaIn==null){
				return;
			}
			System.out.println(lienaIn.length());
			checkPalindrome(new StringBuilder(lienaIn));

		}
	}

	private static boolean checkPalindrome(StringBuilder lienaIn) {
		int largo=lienaIn.length();
		if(largo==1){
			System.out.println(lienaIn);
			return false;
		}
		int mitad=largo/2;

		
		LinkedList <Character> ultima=new LinkedList<Character>();
		LinkedList <Character> principio=new LinkedList<Character>();
		LinkedList <Character> voltear=new LinkedList<Character>();
		
		for (int I = 0; I < mitad; I++) {
			largo--;
			principio.add(lienaIn.charAt(I));
			ultima.add(lienaIn.charAt(largo));
		}
		
		if((lienaIn.length()%2)==1)
		{
			principio.add(lienaIn.charAt(mitad));
		}
		
		for (int I = 0; I < mitad; I++) {
			if(principio.get(0)!=ultima.get(0)){
				Character ultimo=ultima.remove(ultima.size()-1);
				Character primeroDelPrimero=principio.remove(0);
				
				voltear.add(primeroDelPrimero);
				principio.add(ultimo);
			}else{
				int J;
				for (J = 1; J < ultima.size(); J++) {
					if(principio.get(J)!=ultima.get(J)){
						break;
					}
				}
				if(J==ultima.size()){
					break;
				}
			}
		}
		StringBuilder respuesta=new StringBuilder();
		
		for (int i = 0; i < voltear.size(); i++) {
			respuesta.append(voltear.get(i));
		}
		for (int i = 0; i < principio.size(); i++) {
			respuesta.append(principio.get(i));
		}
		for (int i = ultima.size()-1; i >-1 ; i--) {
			respuesta.append(ultima.get(i));
		}
		for (int i = voltear.size()-1; i >-1 ; i--) {
			respuesta.append(voltear.get(i));
		}
		System.out.println(respuesta);
		// TODO Auto-generated method stub
		return true;
	}
	
}