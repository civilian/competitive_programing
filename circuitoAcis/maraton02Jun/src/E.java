import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class E {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	static HashMap<String, Objeto> hash;
	static PriorityQueue<Objeto> cola;
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("E"));

		int x, y, z, caso=0;
		
		while (true) {
			readln();
			x = nextInt();
			y = nextInt();
			z = nextInt();

			
			if (x == 0 && y == 0 && z == 0) {
				System.out.println("  "+((x>9)?x:(" "+x))+
						"  "+((y>9)?y:(" "+y))+
						"  "+((z>9)?z:(" "+z)));
				System.out.println("============");
				break;
			}
			
			hash=new HashMap<String, Objeto>();
			cola=new PriorityQueue<Objeto>();
			
			Objeto estadoIni=new Objeto(x, y, z, 0, null);
			hash.put(x + "-" + y + "-" + z, estadoIni);
			
			boolean encontro=true;
			
			cola.add(estadoIni);
			
			while (!esFinal(cola.peek())) {
				if((x+y+z)%3!=0){
					break;
				}
				distribuir();
				if(cola.size()==0){
					encontro=false;
					break;
				}
			}
			String salida="";
			if(encontro){
				Objeto actual=cola.peek();
				while (actual!=null) {
					salida=("  "+((actual.x>9)?actual.x:(" "+actual.x))+
							"  "+((actual.y>9)?actual.y:(" "+actual.y))+
							"  "+((actual.z>9)?actual.z:(" "+actual.z))+
							"\n")+salida;
					actual=actual.padre;
				}
				System.out.println(salida.substring(0,salida.length()-1));
			}
			else{
				System.out.println("  "+((x>9)?x:(" "+x))+
						"  "+((y>9)?y:(" "+y))+
						"  "+((z>9)?z:(" "+z)));
			}
			
			System.out.println("============");
		}

	}
	
	static boolean esFinal (Objeto in){
		return in.x==in.y && in.y==in.z;
	}
	
	static void distribuir (){
		Objeto primero=cola.poll();
		int x, y, z;
		x=primero.x;
		y=primero.y;
		z=primero.z;
		
		if(x-y >= 0 && !hash.containsKey((x-y)+"-"+y*2+"-"+z)){
			cola.add(new Objeto(x-y,y*2,z,primero.costo+1,primero));
			hash.put((x-y)+"-"+y*2+"-"+z, new Objeto(x-y,y*2,z,primero.costo+1,primero));
		}
		if(y-x >= 0 && !hash.containsKey(x*2+"-"+(y-x)+"-"+z)){
			cola.add(new Objeto(x*2,y-x,z,primero.costo+1,primero));
			hash.put(x*2+"-"+(y-x)+"-"+z, new Objeto(x*2,y-x,z,primero.costo+1,primero));
		}
		if(x-z >= 0 && !hash.containsKey((x-z)+"-"+y+"-"+z*2)){
			cola.add(new Objeto(x-z,y,z*2,primero.costo+1,primero));
			hash.put((x-z)+"-"+y+"-"+z*2, new Objeto(x-z,y,z*2,primero.costo+1,primero));
		}
		if(z-x >= 0 && !hash.containsKey(x*2+"-"+y+"-"+(z-x))){
			cola.add(new Objeto(x*2,y,z-x,primero.costo+1,primero));
			hash.put(x*2+"-"+y+"-"+(z-x), new Objeto(x*2,y,z-x,primero.costo+1,primero));
		}
		if(y-z >= 0 && !hash.containsKey(x+"-"+(y-z)+"-"+z*2)){
			cola.add(new Objeto(x,y-z,z*2,primero.costo+1,primero));
			hash.put(x+"-"+(y-z)+"-"+z*2, new Objeto(x,y-z,z*2,primero.costo+1,primero));
		}
		if(z-y >= 0 && !hash.containsKey(x+"-"+y*2+"-"+(z-y))){
			cola.add(new Objeto(x,y*2,z-y,primero.costo+1,primero));
			hash.put(x+"-"+y*2+"-"+(z-y), new Objeto(x,y*2,z-y,primero.costo+1,primero));
		}
	}
}

class Objeto implements Comparable<Objeto>{
	int costo;
	String estado;
	int x, y, z;
	Objeto padre;

	public Objeto(int x, int y, int z,int costo, Objeto padre) {
		this.costo = costo;
		this.x = x;
		this.y = y;
		this.z = z;
		this.padre = padre;

		estado = x + "-" + y + "-" + z;
	}

	@Override
	public int compareTo(Objeto o) {
		return this.costo-o.costo;
	}

}
