import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;


public class H {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l, " ");
		return l;
	}
	
	static String next(){
		return _stk.nextToken();
	}
	
	static int nextInt(){
		return Integer.parseInt(next());
	}
	
	static void dbg(Object...objects){
		System.out.println(Arrays.deepToString(objects));
	}
	static ArrayList<Character>  genes=new ArrayList<Character> (81);
	static ArrayList<Persona>  M=new ArrayList<Persona> (1000);
	static ArrayList<Persona>  F=new ArrayList<Persona> (1000);
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("H"));
		
		
		while (true) {
			String lg=readln();
			if (lg==null) {
				break;
			}
			lg=next();
			genes.clear();
			M.clear();
			F.clear();
			
			for (int i = 0; i < lg.length(); i++) {
				genes.add(lg.charAt(i));
			}
			
			while (true) {
				String p=readln();
				if (p.equals("***")) {
					break;
				}
				String name=next();
				String sx=next();
				String gp=next();
				
				Persona parent=new Persona(name);
				for (int i = 0; i < gp.length(); i++) {
					int aqui=gp.charAt(i)-'0';
					parent.genes.add(aqui);
				}
				
				if(sx.equals("M")){
					M.add(parent);
				}else {
					F.add(parent);
				}
			}
			
			HashMap<String, ArrayList<Pareja>> parejas=new HashMap<String, ArrayList<Pareja>>(100000);
			
			for (int i = 0; i < M.size(); i++) {
				for (int j = 0; j < F.size(); j++) {
					String	h=hijo(i,j);
					ArrayList<Pareja> pareTmp=parejas.get(h);
					if(pareTmp==null)
					{
						pareTmp=new ArrayList<Pareja>(100);
					}
					pareTmp.add(new Pareja(F.get(j).nombre,M.get(i).nombre));
					parejas.put(h,pareTmp);
				}
			}
			while(true)
			{
				String s=readln();
				if(s.equals("***"))
					break;
				
				String name=next();
//				String sx=next();
				String gs=next();
				System.out.print(name+" by ");
				ArrayList<Pareja> papas=parejas.get(gs);
				if(papas!=null){
					Collections.sort(papas);
				}
//				dbg(papas);
				parejas.put(gs, papas);
				imprimirPadres(papas);
				System.out.println();
			}
		}
		
		
	}

	  static void imprimirPadres(ArrayList<Pareja> papas) {
		if(papas!=null)
		{
			System.out.print(papas.get(0).mujer+"-"+papas.get(0).hombre);
			for (int i = 1; i < papas.size(); i++) {
				System.out.print(" or "+papas.get(i).mujer+"-"+papas.get(i).hombre);
			}
		}
	}

	static String hijo(int m, int f) {
		// TODO Auto-generated method stub
		StringBuilder hijo=new StringBuilder();
		for (int i = 0; i < genes.size(); i++) {
			if(genes.get(i)=='D')
			{
				hijo.append(M.get(m).genes.get(i)|F.get(f).genes.get(i));
					
			}
			else
			{
				hijo.append(M.get(m).genes.get(i)&F.get(f).genes.get(i));
			}
		}
		return hijo.toString();
	}
}
class Pareja implements Comparable<Pareja>{
	String mujer;
	String hombre;
	public Pareja(String mujer, String hombre) {
//		super();
		this.mujer = mujer;
		this.hombre = hombre;
	}
	@Override
	public int compareTo(Pareja o) {
		if(this.mujer.equals(o.mujer)){
			return this.hombre.compareTo(o.hombre);
		}else {
			return mujer.compareTo(o.mujer);
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mujer+" - "+hombre;
	}
}


class Persona{
	String nombre;
	ArrayList<Integer> genes=new ArrayList<Integer>(81);
	public Persona(String nombre) {
		this.nombre = nombre;
//		this.genes = genes;
	}
	
}
