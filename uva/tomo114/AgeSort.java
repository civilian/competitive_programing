package uva.tomo114;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class AgeSort {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static byte nextByte(){return Byte.parseByte(next());}
	
	static int nextInt(){return Integer.parseInt(next());}
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("AgeSort"));
		
		for (int casesId = 0;; casesId++) {
			readln();
			int cant=nextInt();
			if(cant==0)break;
			byte[] valores=new byte[cant];
			
			//countins sort embebido
			int[] counts=new int[100-1+1];
			
			readln();
			for (int i = 0; i < cant; i++) {
				valores[i]=nextByte();
				counts[valores[i]-1]++;
			}
			
			int current=0;
			
			for (byte i = 0; i < counts.length; i++) {
				for (int j = current; j < current+counts[i]; j++) {
					valores[j]=(byte) (i+1);
				}
				current+=counts[i];
			}
			//y luego lo muestro
			for (int i = 0; i < valores.length-1; i++) {
				output.printf("%d ", valores[i]);
			}
			output.println(valores[cant-1]);
		}
		output.close();
	}
}
