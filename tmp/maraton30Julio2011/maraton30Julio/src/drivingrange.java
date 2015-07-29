import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class drivingrange {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}
	
	static String next() { return _stk.nextToken(); }
	
	static int nextInt() { return Integer.parseInt(next()); }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		//input = new BufferedReader(new FileReader("drivingrange"));
				
		while(true) {
			readln();
			int n = nextInt();
			int m = nextInt();
			
			if(n == 0 && m == 0)
				break;
			
			ciudades = new HashMap[n];
			for(int i = 0; i < n; i++)
				ciudades[i] = new HashMap<Integer, Integer>();
			
			for(int i = 0; i < m; i++) {
				readln();
				int a = nextInt();
				int b = nextInt();
				int c = nextInt();
				Integer val = ciudades[a].get(b);
				if(val != null) {
					if(val > c) {
						ciudades[a].put(b, c);
					}
				}
				else {
					ciudades[a].put(b, c);
				}
				
				val = ciudades[b].get(a);
				if(val != null) {
					if(val > c) {
						ciudades[b].put(a, c);
					}
				}
				else {
					ciudades[b].put(a, c);
				}
			}
			
			visitados = new boolean[n];
			cola = new LinkedList<Integer>();
			times=0;
			max = Integer.MIN_VALUE;
			count = 0;
			//conexo(0, ciudades[0]);
			visitados[0] = true;
			cola.addLast(0);
			conexo();
			//System.out.println("count = " + count);
			//System.out.println(Arrays.toString(visitados));
			//System.out.println("times = " + times);
			//boolean pos = true;
//			for(int i = 0; i < n; i++) {
//				if(!visitados[i]) {
//					pos = false;
//					break;
//				}
//			}
//			if(!pos) {
//				System.out.println("IMPOSSIBLE");
//				continue;
//			}
			
			if(count != n) {
				System.out.println("IMPOSSIBLE");
				continue;
			}
			System.out.println(max);
		}
	}
	
	//static void conexo(int i, HashMap<Integer, Integer> hm) {
	static void conexo() {
		if(cola.isEmpty())
			return;
		
		int i = cola.removeFirst();
		//System.out.println("i = " + i);
		Set<Map.Entry<Integer, Integer>> con = ciudades[i].entrySet();
		
		count++;
		//System.out.println(Arrays.toString(visitados));
		for(Map.Entry<Integer, Integer> elemento : con) {
			int key = elemento.getKey();
			//System.out.println("key = ");
			int val = elemento.getValue();
			if(max < val)
				max = val;
			if(!visitados[key]) {				
				visitados[key] = true;
				cola.addLast(key);						
			}
			times++;
		}
		conexo();
	}
	static int times;
	static int max;
	static int count;
	static HashMap<Integer, Integer>[] ciudades;
	static boolean[] visitados;
	
	static LinkedList<Integer> cola;
	
}
