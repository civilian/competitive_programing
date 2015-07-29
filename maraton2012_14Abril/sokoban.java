import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class sokoban {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null){
			_stk=new StringTokenizer(l," ");
		}
		return l;
	}
	
	static String next(){
		return _stk.nextToken();
	}
	static int nextInt(){
		return Integer.parseInt(next());
	}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	
	static char estado[][];
	static int wX, wY;
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("sokoban"));
		
		int r, c, game=0;
		ArrayList<Point> target;
		String linea;
		
		while (true) {
			readln();
			r=nextInt();
			c=nextInt();
			
			if(c==0 && r==0){
				break;
			}
			
			game++;
			estado=new char[r][c];
			target= new ArrayList<Point>();
			
			for (int i = 0; i < r; i++) {
				linea=readln();
				for (int j = 0; j < c; j++) {
					estado[i][j]=linea.charAt(j);
					if(estado[i][j]=='+' || estado[i][j]=='B' || estado[i][j]=='W'){
						Point tmp=new Point(i,j);
						target.add(tmp);
					}
					if(estado[i][j]=='w' || estado[i][j]=='W'){
						wX=i;
						wY=j;
					}
				}
			}
			
			char mov;
			linea=readln();
			for (int i = 0; i < linea.length(); i++) {
				mov=linea.charAt(i);
				if(mov=='U'){
					mover(-1, 0);
				}
				if(mov=='D'){
					mover(1, 0);
				}
				if(mov=='L'){
					mover(0, -1);
				}
				if(mov=='R'){
					mover(0, 1);
				}
				if(terminado(target)){
					break;
				}
			}
			
			System.out.println("Game "+game+": "+(terminado(target)? "complete":"incomplete"));
			for (int i = 0; i < r; i++) {
				System.out.println(estado[i]);
			}
		}
		
		
	}

	private static boolean terminado(ArrayList<Point> target) {
		for (Point point : target) {
			if(estado[point.x][point.y]!='B'){
				return false;
			}
		}
		return true;
	}

	private static void mover(int dirX, int dirY) {
		if(estado[wX+dirX][wY+dirY]=='.'){
			estado[wX+dirX][wY+dirY]='w';
			
			if(estado[wX][wY]=='W'){
				estado[wX][wY]='+';
			}
			else{
				estado[wX][wY]='.';
			}
			wX+=dirX;
			wY+=dirY;
		}
		else if(estado[wX+dirX][wY+dirY]=='+'){
			estado[wX+dirX][wY+dirY]='W';
			
			if(estado[wX][wY]=='W'){
				estado[wX][wY]='+';
			}
			else{
				estado[wX][wY]='.';
			}
			wX+=dirX;
			wY+=dirY;
		}
		else if(estado[wX+dirX][wY+dirY]=='b'){
//			System.out.println(estado[wX+dirX+dirX][wY+dirY+dirY]);
			if(estado[wX+dirX+dirX][wY+dirY+dirY]=='.'){
				estado[wX+dirX+dirX][wY+dirY+dirY]='b';
				estado[wX+dirX][wY+dirY]='w';
				
				if(estado[wX][wY]=='W'){
					estado[wX][wY]='+';
				}
				else{
					estado[wX][wY]='.';
				}
				wX+=dirX;
				wY+=dirY;
			}
			if(estado[wX+dirX+dirX][wY+dirY+dirY]=='+'){
//				System.out.println((wX+dirX+dirX) +" "+ (wY+dirY+dirY));
//				System.out.println(wX+" "+wY);
				estado[wX+dirX+dirX][wY+dirY+dirY]='B';
				estado[wX+dirX][wY+dirY]='w';
				
				if(estado[wX][wY]=='W'){
					estado[wX][wY]='+';
				}
				else{
					estado[wX][wY]='.';
				}
				wX+=dirX;
				wY+=dirY;
			}
		}
		else if(estado[wX+dirX][wY+dirY]=='B'){
			if(estado[wX+dirX+dirX][wY+dirY+dirY]=='.'){
				estado[wX+dirX+dirX][wY+dirY+dirY]='b';
				estado[wX+dirX][wY+dirY]='W';
				
				if(estado[wX][wY]=='W'){
					estado[wX][wY]='+';
				}
				else{
					estado[wX][wY]='.';
				}
				wX+=dirX;
				wY+=dirY;
			}
			if(estado[wX+dirX+dirX][wY+dirY+dirY]=='+'){
				estado[wX+dirX+dirX][wY+dirY+dirY]='B';
				estado[wX+dirX][wY+dirY]='W';
				
				if(estado[wX][wY]=='W'){
					estado[wX][wY]='+';
				}
				else{
					estado[wX][wY]='.';
				}
				wX+=dirX;
				wY+=dirY;
			}
		}
		else if(estado[wX+dirX][wY+dirY]=='#')
		{
			return;
		}
	}
}
