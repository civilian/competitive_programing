package MaratonArtOfProgramingContest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

public class BigMod_rapido {
	
	
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextIntG(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("BigMod"));
		do {
			int b=0,
			m=0,
			p=0;
			
			b=nextInt();
			p=nextInt();
			m=nextInt();
			
			System.out.println(bigMod(b, p, m));
		}while (readln()!=null);
		
	}
	
	private static int nextInt() throws IOException {
		int resultado;
		try {
			resultado=nextIntG();
		} catch (Exception e) {
			if(readln()==null)
				System.exit(0);
			
			resultado=nextInt();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return resultado;
	}

	static long bigMod(int b,int p,int m){
		if(p==0)
			return 1;
		else if(p%2==0)
		{
			long parcial=bigMod(b, p/2, m)%m;
			return (parcial*parcial)%m;
		}
		else return ((b%m)*bigMod(b,p-1,m))%m;
	}

}
