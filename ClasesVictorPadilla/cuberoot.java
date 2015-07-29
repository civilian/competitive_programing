package ClasesVictorPadilla;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;


public class cuberoot {
	
	static BufferedReader input;
	static StringTokenizer _stk;
	
	static String readlnx() throws IOException {
		String l = input.readLine();
		if(l != null)
			_stk = new StringTokenizer(l);
		return l;
	}
	
	static String nextx() throws Exception {
		
		while(!_stk.hasMoreTokens()) readlnx();
		return _stk.nextToken();
	}
	/*
	static int nextInt() throws Exception {
		return Integer.parseInt(next());
	}*/
	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws Exception {
		//System.out.println(" hola \t   ".trim().length());
		//System.exit(0);
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		//readln();
		//while(!_stk.hasMoreTokens())
		
		//Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(input.readLine().trim());
		int procedos=0;
		int tO=t;
		while(t>0)
		{	
			String line;
			do {
				line = input.readLine().trim(); 
			}
			while(line.length()==0);
			
			
			BigInteger num = BigInteger.ONE;
			//try {
				num=new BigInteger(line);
			//}catch(NumberFormatException e) {
			//	continue;
			//}
			//try {
				busquedaBinaria(num);
				procedos++;
			/*}
			catch (NumberFormatException e) {
				// TODO: handle exception
				output.println("1 999999999999");
				//System.exit(0);
			}*/
				
			//output.println("1 99999999999");
			
			t--;
		}
		if(tO!=procedos){
			throw new Exception();
		}
		output.close();
	}
	
	static MathContext MC = MathContext.UNLIMITED;
	public static void busquedaBinaria(BigInteger n) throws Exception {
		
		BigDecimal EPS = new BigDecimal("0.00000000000001",MC);
		
		//BigDecimal EPS = BigDecimal.valueOf(1e-12,MC);
		
		BigDecimal mid = new BigDecimal("0",MC);
		BigDecimal two = new BigDecimal("2",MC);
		
		
		BigInteger loint = BigInteger.ZERO;
		BigInteger hiint = n;
		BigInteger nint = n;
		BigInteger midint = BigInteger.ZERO;
		BigInteger twoint = BigInteger.valueOf(2);
		
		while(loint.compareTo(hiint) < 0) 
		{
			midint = loint.add(hiint.subtract(loint).divide(twoint));
//			System.out.println("mid = "+midint.toString());
			BigInteger val = midint.multiply(midint.multiply(midint));
			int comp = val.compareTo(nint);
			if(comp >= 0)
				hiint = midint;
			else
				loint = midint.add(BigInteger.ONE);				
		}
		
		if(loint.multiply(loint).multiply(loint).compareTo(n)<0)
			throw new Exception();
		
		BigDecimal lo, hi;
		
		hi = new BigDecimal(loint.toString(), MC);
		lo = hi.subtract(BigDecimal.ONE, MC);
		BigInteger bajito=loint.subtract(BigInteger.ONE);
		
		if(bajito.multiply(bajito.multiply(bajito)).compareTo(n)>=0) {
			throw new Exception();
		}
		
		if(loint.multiply(loint.multiply(loint)).compareTo(n)==0) {
			String resp = loint.toString();
			if(resp.indexOf(".") == -1) {
				resp += ".";
				for(int i = 0; i < 10; i++)
					resp += "0";
			}
			/*else
				resp = resp.substring(0,12);*/
			int checksum = 0;
			
			for(int i=0; i<resp.length(); i++) {
				if(resp.charAt(i) != '.')
					checksum += resp.charAt(i) - '0';
			}
			checksum %= 10;
			//return  (checksum + " " + resp);
			output.print(checksum+" ");
			output.println(resp);
			return;
		}
		
		BigDecimal nd=new BigDecimal(n.toString(), MC);
		
		while(hi.subtract(lo).abs(MC).compareTo(EPS) > 0) {
			mid = (lo.add(hi,MC)).divide(two,MC);
			BigDecimal val = mid.multiply(mid.multiply(mid), MC);
			int comp = val.compareTo(nd);
			if(comp >= 0)
				hi= mid;
			else
				lo = mid;
		}
		
		//hi = hi.round((new MathContext(11, RoundingMode.DOWN)));
		
		String resp = hi.toPlainString();
		
		
		
		if(resp.indexOf(".") == -1) {
			resp += ".";
			for(int i = 0; i < 10; i++)
				resp += "0";
		}
		else {
			resp = (resp+"0000000000").substring(0, resp.indexOf('.')+11);
		}
		
		
		/*else
			resp = resp.substring(0,12);*/
		int checksum = 0;
		
		for(int i=0; i<resp.length(); i++) {
			if(resp.charAt(i) != '.')
				checksum += resp.charAt(i) - '0';
		}
		checksum %= 10;
		//return  (checksum + " " + resp);
		output.print(checksum+" ");
		output.println(resp);
	}

}


