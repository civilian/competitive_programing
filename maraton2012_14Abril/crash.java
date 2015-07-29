import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class crash {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null) {
			_stk = new StringTokenizer(l, " ");
		}
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("crash"));

		ArrayList <Radio> gorel = new ArrayList<Radio>();
		int N;
		while (true) {
			readln();
			N = nextInt();
			if (N == 0) {
				break;
			}
			gorel.clear();
			readln();
			gorel.add(new Radio(nextInt(), nextInt(), nextInt()));
			for (int i = 1; i < N; i++) {
				readln();
				Radio act = new Radio(nextInt(), nextInt(), nextInt());
				
				for (int j = 0; j < gorel.size(); j++) {
					Radio radio = gorel.get(j);
					if(radio==null){
						continue;
					}
					double restaY = act.y - radio.y;
					double restaX = act.x - radio.x;
					double dist = Math.sqrt(Math.pow(restaY, 2.0)
							+ Math.pow(restaX, 2.0));
//					dbg("dist",dist, "restaY", restaY , "restaX", restaX);
					if (dist <= radio.r || dist <= act.r) {
						double puntMedioX = (act.x + radio.x) / 2;
						double puntMedioY = (act.y + radio.y) / 2;
						double radioMedio = Math.sqrt(Math.pow(radio.r, 2)
								+ Math.pow(act.r, 2));
						gorel.set(j, null);
						act.x=puntMedioX;
						act.y=puntMedioY;
						act.r=radioMedio;
						j=-1;
					}
				}
				gorel.add(act);
			}
//			dbg(gorel);
			int cantidad=0;
			for (Radio radio : gorel) {
				if(radio!=null)
					cantidad++;
			}
			System.out.println(cantidad);
		}

	}
}

class Radio {
	double x, y, r;

	public Radio(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public String toString() {
		return "[r=" + r + ", x=" + x + ", y=" + y + "]";
	}
	
}
