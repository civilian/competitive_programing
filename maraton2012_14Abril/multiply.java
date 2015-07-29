package maraton2012_14Abril;

//LA livearchive 5928
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;


public class multiply {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null){
			_stk=new StringTokenizer(l," ");
		}
		return l;
	}
	
	static String next(){
		return _stk.nextToken();
	}
	static int nextInt(){
		return Integer.parseInt(next());
	}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("multiply.in"));
		String a,b;
		long esp,aint;
		long actM;
		LinkedList<String> salida=new LinkedList<String>();
		long rayita=-1;
		long espDies;
		long dies;
		String out;
		long mult;
		for (int id = 1; ; id++) {
			readln();
			a=next();
			b=next();
			
			if(a.equals("0")&&b.equals("0")){
				break;
			}
			aint=Long.parseLong(a);
			
			int lar=b.length();
			salida.clear();
			esp=0;
			actM=0;
			salida.clear();
			rayita=-1;
			espDies=0;
			dies=1;
//			dbg(b,a, lar);
			for (int i = (lar-1); i >-1; i--) {
//				dbg(i);
				long n=b.charAt(i)-'0';
				if(n==0){
					dies*=10;
					espDies++;
					continue;
				}else {
					actM=n*aint*dies;
//					System.out.println(espacios(esp));
					out=actM+espacios(esp);
					esp++;
					rayita=Math.max(rayita, out.length());
					salida.add(out);
					esp+=espDies;
					espDies=0;
					dies=1;
				}
			}
			String ini=String.format("Problem %d",id);
//			dbg(rayita);
//			dbg(ini.length());
//			dbg( Math.abs(rayita-ini.length()));
			
			String espacios="";
			mult=aint*Long.parseLong(b);
			String m=mult+"";
			rayita=Math.max(rayita, m.length());
//			if(rayita>ini.length()){
//				for (int i = 0; i < Math.abs(rayita-ini.length()); i++) {
//					espacios+=" ";
//				}
//			}
//			dbg(espacios.length());
			System.out.println(ini+espacios);
			System.out.println(imprimirNumero(a,rayita));
			System.out.println(imprimirNumero(b,rayita));
			System.out.println(imprimirRayita(rayita));
			imprirmirResultados(salida,rayita);
			if (salida.size()>1) {
				System.out.println(imprimirRayita(rayita));
				System.out.println(mult);
			}
		}
		
	}

	private static void imprirmirResultados(LinkedList<String> salida, long max) {
		for (String s : salida) {
			System.out.println(imprimirNumero(s, max));
		}
	}

	private static String imprimirRayita(long rayita) {
		String l="";
		for (int i = 0; i < rayita; i++) {
			l+="-";
		}
		return l;
	}

	private static String imprimirNumero(String a,long max) {
		String l="";
		int size=a.length();
//		a=a.replaceAll(" ", "");
		if(max>size){
			for (int i = 0; i < max-a.length(); i++) {
				l+=" ";
			}
		}
		l+=a.replaceAll(" ", "");
		return l;
	}

	private static String espacios(long esp) {
		String salida="";
		for (int i = 0; i < esp; i++) {
			salida+=" ";
		}
		return salida;
	}
}
