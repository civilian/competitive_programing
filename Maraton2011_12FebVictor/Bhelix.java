package Maraton2011_12FebVictor;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Bhelix {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			readln();
			int N1 = nextInt();
			if(N1==0) break;
			
			
			TreeSet<Integer> set1 = new TreeSet<Integer>();
			for(int i=0; i<N1; i++) set1.add(nextInt());
			
			readln();
			int N2 = nextInt();
			TreeSet<Integer> set2 = new TreeSet<Integer>();
			for(int i=0; i<N2; i++) set2.add(nextInt());
			
			TreeSet comunes = new TreeSet<Integer>();
			for(Integer x: set1) {
				if(set2.contains(x))
					comunes.add(x);
			}
			//System.err.println(set1+"  "+set2);
			
			//System.err.println("comunes "+comunes);
			Iterator<Integer> it1 = set1.iterator();
			Iterator<Integer> it2 = set2.iterator();
			Iterator<Integer> itcom = comunes.iterator();
			int suma=0;
			while(true) {
				int suma1=0, suma2=0;
				int com = itcom.hasNext() ? itcom.next() : 1000000000;
				while(it1.hasNext() ) {
					int v = it1.next();
					suma1+=v;
					if(v==com) break;
				}
				while(it2.hasNext()) {
					int v = it2.next();
					suma2+=v;
					if(v==com) break;
				}
				//System.err.println(suma1+" "+suma2);
				suma += max(suma1,suma2);
				
				if(com==1000000000) break;
			}
			
			System.out.println(suma);
		}
	}

}
