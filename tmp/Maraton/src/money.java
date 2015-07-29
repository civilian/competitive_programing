import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class money {
	
	static BufferedReader input;
	static StringTokenizer _stk;
	
	static String readln() throws IOException {
		String l = input.readLine();
		
		if(l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() {
		return _stk.nextToken();
	}
	
	static int nextInt() {
		return Integer.parseInt(next());
	}
	
	static double nextDouble() {
		return Double.parseDouble(next());
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("money.in"));
		int id = 1;
		while(true) {
			String l = readln();
			double inversion = nextDouble();
			double interes = nextDouble();
			int particion = nextInt();
			
			double invinicial = inversion;
			
			if(l.equals("0.00 0.00 0"))
				break;
			double tasa = ((interes/100)/particion);
			DecimalFormat format = new DecimalFormat("0.00");
			format.setRoundingMode(RoundingMode.DOWN);
			
			for(int i = 0; i < particion; i++) {
				//System.out.println("incremento "+i+" = "+Double.parseDouble(format.format(inversion*tasa)));					
				inversion += Double.parseDouble(format.format(inversion*tasa));
				//System.out.println("inversion "+i+" = "+format.format(inversion));
			}
			//inversion /= 100;
			//interes /= 100;
			
			
			//String out = "Case " + id + ". $" + format.format(invinicial) + " at " + format.format(interes) + "% APR compounded " + particion + " times yields $" + inversion;
			System.out.printf("Case %d. $%.2f at %.2f%c APR compounded %d times yields $%.2f\n", id, invinicial, interes, '%', particion, inversion);
			//System.out.println(out);
			
			id++;
		}
		
	}

}
