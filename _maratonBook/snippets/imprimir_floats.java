package snippets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class imprimir_floats {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc=new Scanner(System.in);
		double d=sc.nextDouble();
		System.out.printf("%7.3f\n",d);//lun
	}
}
