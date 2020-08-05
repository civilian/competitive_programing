package uva.tomo100;

import general.DibujarPoligonos;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

import maraton2011_08oct.digital;

public class TreesOnMyIsland {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
//	static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("TreesOnMyIsland"));

		
		for (int casesId = 1;; casesId++) {
			readln();
			int p=nextInt();
			if(p==0) break;
			Polygon island=new Polygon();
			for (int i = 0; i < p; i++) {
				readln();
				island.addPoint(nextInt(), nextInt());
			}
			
			Rectangle cuad=island.getBounds();
			int xGr=(int) (cuad.getMaxX()+1);
			int yGr=(int) (cuad.getMaxY()+1);
			int trees=0;
			
			dbg("x=",island.xpoints, " y=", island.ypoints);
			dbg(island.contains(1001,1001));
			dbg(island.contains(2,2));
			
//			DibujarPoligonos a=new DibujarPoligonos();
//			a.p=island;
//			a.dibuja(island);
			
			for (int i = (int) cuad.getMinX(); i <xGr ; i++) {
				for (int j = (int) cuad.getMinY(); j < yGr; j++) {
					if(island.contains(i, j)){
//						System.out.printf("%d %d", i,j);
//						System.out.println();
						trees++;
					}
				}
			}
			
			System.out.println(trees);
		}
		
//		output.close();
	}
}
