package snippets;

import java.util.Scanner;

public class string_reemplazar {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
//		String s=sc.nextLine();
		String s="line: a70 and z72 will be replaced, but aa24 and a872 will not";
		System.out.println(s.replaceAll("( |^)+[a-z][0-9][0-9]( |$)+", " *** "));
	}
}
