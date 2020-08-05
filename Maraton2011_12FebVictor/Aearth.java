import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Aearth {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l.trim()," ()$",true);
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	
	
	public static String parser()
	{
		StringBuilder cadena = new StringBuilder();
		String ant="";
		while(true)
		{
			String c=next();
			if(c.equals(" "))
				continue;
			if(c.equals(")"))
			{
				String s=cadena.toString();
				int x =Integer.parseInt(ant);
				if(x==0) return "";
				for(int i=1;i<x;i++)
					cadena.append(s);
				return cadena.toString();
			}
			else if(c.equals("("))
				cadena.append(parser());
			else if(Character.isLetter(c.charAt(0)))
				cadena.append(c);
			ant=c;	
		}
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			readln();
			String c=next();
			if(c.equals("("))
				System.out.println(parser());
			else if(c.equals("$"))
				break;
			else
				System.out.println(c);
		}
	}

}

