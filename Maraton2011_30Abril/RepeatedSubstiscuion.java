package Maraton2011_30Abril;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class RepeatedSubstiscuion {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer (l, " ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("RepeatedSubstiscuion"));
		
		while(true) {
			readln();
			int n = nextInt();
			if(n == 0)
				break;
			HashMap<String, String> setrules = new HashMap<String, String>();
			
			for (int i = 0; i < n; i++) {
				readln();
				String a = next();
				String b = next();
				setrules.put(a, b);
			}
			readln();
			String start = next();
			readln();
			String end = next();
			
			LinkedList<Node> tree = new LinkedList<Node>();
			tree.addFirst(new Node(start, 0));
			boolean find = false;
			while(tree.size() != 0 && !find) {
				Node f = tree.removeFirst();
				if(f.a.equals(end)) {
					System.out.println(f.p);
					find = true;
					break;				
				}
				for(Map.Entry<String, String> x : setrules.entrySet()) {				
					String newstring = "";
					if(f.a.indexOf(x.getKey()) != -1)
						newstring = f.a.replaceAll(x.getKey(), x.getValue());
					if(newstring.equals(end)) {
						System.out.println(f.p+1);
						find = true;
						break;
					}
					if(!newstring.equals("") && newstring.length() <= end.length())
						tree.addLast(new Node(newstring, f.p+1));
				}
			}
			if(!find)
				System.out.println(-1);
		}		
	}
}

class Node {
	String a;
	int p;
	Node(String ap, int pp) {
		a = ap;
		p = pp;
	}
}
