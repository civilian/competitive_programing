package Maraton2011_30Abril;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public class separate {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer (l, " ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("separate"));
		
		while(true) {
			readln();
			int n = nextInt();
			int m = nextInt();
			
			if(n == 0 && m == 0)
				break;
			
			Point[] pBlack=new Point[n];
			Point[] pWhite=new Point[n];
			
			int[] blackx = new int[n];
			int[] whitex = new int[m];
			int[] blacky = new int[n];
			int[] whitey = new int[m];
			for(int i = 0; i < n+m; i++) {
				readln();
				if(i < n) {
					blackx[i] = nextInt();
					blacky[i] = nextInt();
				}
				else {
					whitex[i-n] = nextInt();
					whitey[i-n] = nextInt();
				}
			}
//			System.out.println(Arrays.toString(blackx));
//			System.out.println(Arrays.toString(blacky));
//			System.out.println(Arrays.toString(whitex));
//			System.out.println(Arrays.toString(whitey));
			
			Polygon pb = new Polygon(blackx, blacky, blackx.length);
			Polygon wb = new Polygon(whitex, whitey, whitex.length);
			
//			Rectangle2D rb = pb.getBounds2D();
//			Rectangle2D rw = wb.getBounds2D();
			boolean bPegan=false;
			if(!bPegan){
				System.out.println("YES");
			}else System.out.println("NO");
		}		
	}
}

