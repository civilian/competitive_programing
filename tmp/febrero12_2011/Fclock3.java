import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import static java.lang.Math.*;

public class Fclock3 {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	static double nextDouble() { return Double.parseDouble(next()); }
	
	public static void main(String[] args) throws IOException {
		//System.out.println(gcd(12, 16));
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		readln();
		int numCases=nextInt();
		for(int caseid=1; caseid<=numCases; caseid++) {
			long hora1=0, hora2=0;
			
			readln();
			long  h = nextInt();
			long m = nextInt();
			long s = nextInt();
			long u = nextInt();
			m += h*60;
			s += m*60;
			u += s*100;
			hora1 = u;
			
			readln();
			h = nextInt();
			m = nextInt();
			s = nextInt();
			u = nextInt();
			m += h*60;
			s += m*60;
			u += s*100;
			hora2 = u;
			
			readln();
			//double radio = nextDouble();
			BigDecimal radio = new BigDecimal(next());
			
			
			long horaTotal = 12*60*60*100;
			long horaInter = hora2-hora1;
			
			long div=gcd(horaInter,horaTotal);
			horaTotal/=div;
			horaInter/=div;
			
			MathContext mc = new MathContext(1000);
			//double x = (horaInter*PI*radio*radio)/horaTotal;
			//BigDecimal.
			BigDecimal x = radio.multiply(radio,mc).multiply(new BigDecimal(""+PI),mc).multiply(new BigDecimal(""+horaInter),mc).divide(new BigDecimal(""+horaTotal),mc);
			//long val = x.multiply(new BigDecimal("1000"),mc).longValue();
			//long d = (long)round(x.multiply(new BigDecimal("1000"),mc).doubleValue());
			String res = x.toPlainString();
			int idx = res.indexOf(".");
			if(idx==-1) res += ".000";
			res = (res+"000000").substring(0, idx+5);
			if(res.charAt(res.length()-1)>='5')
				res = res.substring(0,res.length()-2)+ (char)(res.charAt(res.length()-2)+1);
			else
				res = res.substring(0,res.length()-1);
			//double d = Double.parseDouble(res);
			//System.out.printf("%d. %.03f\n",caseid,d);
			System.out.println(caseid+". "+res);
			
		}
	}

	static long gcd(long a, long b) {
		return b==0?a:gcd(b,a%b);
	}
}
