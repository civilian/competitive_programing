package maraton2011_08oct;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;


public class jay {
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
	
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new FileReader("jay.in"));
		while(true) {
			readln();
			int n = nextInt();
			if(n == 0)
				break;
			
			MiObjeto[] viajes = new MiObjeto[n];
			
			
			int prev = 0;
			for(int i = 0; i < n; i++) {
				readln();
				viajes[i] = new MiObjeto(nextInt(), prev, i);
				prev += nextInt();
			}
			MiObjeto[] viajes2 = Arrays.copyOf(viajes, n);
			
			Comparator<MiObjeto> comp = new Comparator<MiObjeto>(){				
				public int compare(MiObjeto o1, MiObjeto o2) {
					if(o1.t > o2.t)
						return -1;
					if(o1.t < o2.t)
						return 1;
					return 0;
				}
				
			};
			Arrays.sort(viajes, comp);
			//dbg(viajes2);
			int ans = 0;
			for(int i = 0; i < n; i++) {
				//dbg("viajes2 = ",viajes2);
				
				if(viajes2[viajes[i].pos].b <= 0)
					ans += viajes[i].t;
				else {
					ans += (viajes[i].t/2);
					int pos = viajes[i].pos;
					int igual = viajes[i].b;
					for(int j = pos; j < n; j++) {
						viajes2[j].b--;
					}
					for(int j = pos-1; j >= 0; j--) {
						if(viajes2[j].b == igual)
							viajes2[j].b--;
					}
				}
				//dbg("ans = ",ans);
			}
			System.out.println(ans);
		}
		
	}
	
	static class MiObjeto {
		int t, b, pos;

		public MiObjeto(int t, int b, int pos) {
			this.t = t;
			this.b = b;
			this.pos = pos;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "t = " + t + ", b = " + b +", pos = "+pos;
		}
		
	}
}
