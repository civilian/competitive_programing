import java.io.*;
import java.util.*;
import java.awt.*;
import static java.lang.Math.*;

public class Hlogo {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l = input.readLine();
		if(l!=null) _stk = new StringTokenizer(l," ");
		return l;
	}
	static String next() { return _stk.nextToken(); }
	static int nextInt() { return Integer.parseInt(next()); }
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			readln();
			String line = next();
			if(line.equals("Q")) break;
			
			int polis=0;
			int x = 0, y = 0;
			
			HashSet<Point> visited = new HashSet<Point>();
			visited.add(new Point(0,0));
			for(char c : line.toCharArray()) {
				switch(c) 
				{
				case 'U': y++; break;
				case 'D': y--; break;
				case 'L': x--; break;
				case 'R': x++; break;
				default: continue;
				}
				Point p = new Point(x,y);
				if(visited.contains(p))
					polis++;
				else visited.add(p);
			}
			System.out.println(polis);
		}
	}

}
