import java.awt.Point;
import java.awt.Polygon;
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
import java.util.Comparator;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Discarder;

public class C {
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

	static ArrayList<Point> ds= new ArrayList<Point>(2100);
	static ArrayList<Point> ps= new ArrayList<Point>(2100);
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);

		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("C"));
		
		int d, p;
		
		while (true) {
			readln();
			d=nextInt();
			p=nextInt();
			
			if(d==0 && p==0){
				break;
			}
			
			ds.clear();
			ps.clear();
			int x1, y1, x2, y2;
			for (int i = 0; i < d; i++) {
				readln();
				x1=nextInt();
				y1=nextInt();
				x2=nextInt();
				y2=nextInt();
				
				ds.add(new Point(x1,y1));
				ds.add(new Point(x1,y2));
				ds.add(new Point(x2,y2));
				ds.add(new Point(x2,y1));
				if(i==0){
					ds.add(new Point(x1,y1));
					ds.add(new Point(x1,y2));
					ds.add(new Point(x2,y2));
					ds.add(new Point(x2,y1));
				}
			}
			for (int i = 0; i < p; i++) {
				readln();
				x1=nextInt();
				y1=nextInt();
				x2=nextInt();
				y2=nextInt();
				
				ps.add(new Point(x1,y1));
				ps.add(new Point(x1,y2));
				ps.add(new Point(x2,y2));
				ps.add(new Point(x2,y1));
				if(i==0){
					ps.add(new Point(x1,y1));
					ps.add(new Point(x1,y2));
					ps.add(new Point(x2,y2));
					ps.add(new Point(x2,y1));
				}
			}
			
			ArrayList<Point> a=ConvexHull.grahanScan(ds);
			Polygon polds=new Polygon();
			for (int i = 0; i < a.size(); i++) {
				polds.addPoint(a.get(i).x, a.get(i).y);
			}
			ArrayList<Point> b=ConvexHull.grahanScan(ps);
			Polygon polps=new Polygon();
			for (int i = 0; i < b.size(); i++) {
				polps.addPoint(b.get(i).x, b.get(i).y);
			}
			
			boolean can=true;
			for (int i = 0; i < b.size(); i++) {
				if(polds.contains(b.get(i))){
					can=false;
					break;
				}
			}
			
			System.out.println(can? "si" : "no");
		}
	}
}

class ConvexHull {
	
	static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static ArrayList<Point> grahanScan(ArrayList<Point> polygon) {
		// TODO Auto-generated method stub
		int min = 0;
		int length = polygon.size();

		for (int i = 0; i < length; i++) {
			Point get = polygon.get(i);
			if (get.y < polygon.get(min).y) {
				min = i;
			} else if (get.y == polygon.get(min).y) {
				if (get.x < polygon.get(min).x) {
					min = i;
				}
			}
		}

		final Point pivot = polygon.get(min);
		ArrayList<Point> sorted = (ArrayList<Point>) polygon.clone();
		Collections.sort(sorted, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.equals(o2)) {
					return 0;
				}
				if (angle_cmp(pivot, o1, o2)) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		sorted.add(0,pivot);
		dbg(pivot, sorted);
		Stack<Point> stack= new Stack<Point>();
		stack.push(sorted.get(length-1));
		stack.push(pivot);
		dbg("pila",stack);
		int i=1;
		while (i<length) {
			Point pt1 = stack.pop();
			Point pt2 = stack.peek();
			stack.push(pt1);
			if(isLeftTurn(pt1, pt2, sorted.get(i))){
				stack.push(sorted.get(i));
				i++;
			}
			else{
				stack.pop();
			}
		}
		
		ArrayList<Point> convex = new ArrayList<Point>();
		while (!stack.isEmpty()) {
			convex.add(stack.pop());
		}
		convex.remove(convex.size()-1);
		return convex;
	}

	private static int distance(Point a, Point b) {
		int dx= a.x - b.x, dy= a.y-b.y;
		return dx*dx + dy*dy;
	}
	
	private static int area(Point a, Point b, Point c) {
		return a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y * a.y; 
	}
	
	private static boolean angle_cmp(Point pivot, Point a, Point b) {
		if(area(pivot,a,b) == 0){
			return distance(pivot,a) < distance(pivot,b);
		}
		int d1x= a.x - pivot.x, d1y=a.y-pivot.y;
		int d2x= b.x - pivot.x, d2y=b.y-pivot.y;
		return (Math.atan2((double)d1y, (double)d1x))-Math.atan2((double)d2y, (double) d2x) < 0;
	}
	
	private static int turnTest(Point p, Point q, Point r) {
		int result= (r.x -q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
		if(result <0){
			return -1;
		}
		if(result > 0){
			return 1;
		}
		return 0;
	}
	
	private static boolean isLeftTurn(Point p, Point q, Point r) {
		return turnTest(p,q,r) > 0;
	}
}