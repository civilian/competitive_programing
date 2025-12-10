//package usaco;
/*
ID: chamato1
LANG: JAVA
TASK: beads
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class beads {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object...o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
//		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("beads.in"));
		
		readln();
		size=nextInt();
		
		readln();
		String bead=next();
//		String bead=next().toLowerCase();
		neckles=(bead+bead).toCharArray();
		
		
		int cantidad=contar(ultTipoIn,0);
		
//		dbg("cantidad",cantidad, " size ", size);
		last=-1;
		while (true){
			
			int tmp=contar(last+1,0);//el primer white
			int ultimoWithe=last;
			if(ultTipoIn==neckles.length-1)
				break;
			
			tmp+=contar(ultTipoIn,tmp);
			last=ultimoWithe;
			
//			dbg(neckles.length);
//			dbg("tmp",tmp, "ultipoIN",ultTipoIn, "last",last);
			if(tmp>cantidad)
				cantidad=tmp;
			
			if(ultTipoIn==neckles.length-1)
				break;
			
			if(tmp==size)
				break;
		}
		
		output.println(cantidad);
		output.close();
	}

	static int size;
	
	static char[] neckles;
	static int last=0;//este es el ultimo que no white de un tipo
	
	static int ultTipoIn=0;//este es hasta donde conte la ultima vez
	
	static int contar(int i,int llevo) {
		char tipo = 'w';
		int ans = 0;
		for (int j = i,k=llevo; k < size && j<neckles.length; j++,k++) {
			
			if(neckles[j] != 'w'){
				
				if(tipo == 'w'){
					//cambio el tipo
					tipo = neckles[j];
				}
				
				if(tipo == neckles[j]){
					//marco el ultimo del tipo q no es white
					last = j;
				}
				else if(tipo != neckles[j]){
					ultTipoIn = j;
					return ans;
				}
			}
			ultTipoIn=j;//para white todo
			ans++;
		}
		return ans;
	}
	
}
