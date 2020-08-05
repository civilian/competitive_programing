package CF.mayo14;
/*EL CONTAINS DE LINE2D SIEMPRE RETURNA FALSO*/
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Polygons {
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
	
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Polygons"));
		
		readln();
		int N=nextInt();
		Polygon A=new Polygon(), B=new Polygon();
		
		for (int i = 0; i < N; i++) {
			readln();
			A.addPoint(nextInt(), nextInt());
		}
		
		readln();
		int M=nextInt();
		double x, y;
		boolean in=true;
		int [] xsA,ysA;
//		Line2D.Double bounds= new Line2D.Double();
		for (int i = 0; i < M; i++) {
			readln();
			x=nextInt();
			y=nextInt();
			if (!A.contains(x, y)) {
				in=false;
				break;
			}else {
				xsA=A.xpoints;
				ysA=A.ypoints;
//				dbg(A.npoints);
				for (int j = 0; j < A.npoints-1; j++) {
					dbg(i);
					dbg("i",j, "x y x1 1",xsA[j], ysA[j], xsA[j+1], ysA[j+1]);
					bounds.setLine(xsA[j], ysA[j], xsA[j+1], ysA[j+1]);
					if (bounds.contains(x, y)) {
						in=false;
						break;
					}
				}
			}
		}
		
		System.out.printf("%s\n", (in)? "YES": "NO");

	}
}
