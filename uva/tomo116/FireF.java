package uva.tomo116;

//Uva 11624.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
 
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class FireF {
   static BufferedReader input;
 
   static StringTokenizer _stk;
 
   static String l;
 
   static String readln() throws IOException {
      l = input.readLine();
      if (l != null)
         _stk = new StringTokenizer(l);
      return l;
   }
 
   static String next() {
      return _stk.nextToken();
   }
 
   static int nextInt() {
      return Integer.parseInt(next());
   }
 
   // static void dbg(Object... o) {
   // System.out.println(Arrays.deepToString(o));
   // }
 
	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
 
   public static void main(String[] args) throws IOException {
      Locale.setDefault(Locale.US);
      input = new BufferedReader(new InputStreamReader(System.in));
      input = new BufferedReader(new FileReader("Fire"));
      int testCases = Integer.parseInt(input.readLine());
      for (int idCases = 0; idCases < testCases; idCases++) {
         //fire.clear();
 
         readln();
         R = nextInt();
         C = nextInt();
         
         head = tail = 0;
         for (int i = 0; i < R; i++) {
            l=input.readLine();
            for (int j = 0; j < C; j++) {
               maze[i][j] = l.charAt(j);
//               visited[i][j] = false;
               if (maze[i][j] == 'J') {
                  //joe.setLocation(i, j);
                  q[tail++] = new State(i, j, 0, true);
//                  visited[i][j] = true;
               } else if (maze[i][j] == 'F') {
                  //fire.add(new Point(i, j));
                  q[tail++] = new State(i, j, 0, false);
//                  visited[i][j] = true;
               }
            }
         }
 
         // BFS(joe);
         minTime = INF;
         BFSMultiSourceShortesPath();
 
         if (minTime < INF) {
            output.println(minTime);
         } else {
            output.println("IMPOSSIBLE");
         }
      }
      output.close();
   }
 
   // BFS SIMPLE
   static char[][] maze = new char[1000 + 7][1000 + 7];
//   static boolean[][] visited = new boolean[1000 + 7][1000 + 7];
   //static Point joe = new Point(0,0);
//   static ArrayList<Point> fire = new ArrayList<Point>(1000 * 1000 + 5);
   //static LinkedList<Point> fire = new LinkedList<Point>();
   static int R, C;
   static final int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
   static final int INF = 1000000000;
   static int minTime;
   static State[] q = new State[1001 * 1001];
   static int head, tail;
 
   static boolean isValid(int r, int c) {
      return (r > -1 && r < R && c > -1 && c < C);
   }
 
   public static void BFSMultiSourceShortesPath() {
      // BFS routine
 
      //Queue<State> q = new LinkedList<State>();
      
      //q.offer(new State(joe.x, joe.y, 0, true));
      //q[tail++] = new State(joe.x, joe.y, 0, true);
      //visited[joe.x][joe.y] = true;
      /*
      for (Point p : fire) {
         q[tail++] = new State(p.x, p.y, 0, false);
         //q.offer(new State(p.x, p.y, 0, false));// simulo un estado conectado
                                       // con todos los source
      }*/
 
      int rH, cH;
      while (head < tail) {
      //while (!q.isEmpty()) {
         //State u = q.poll();
         State u = q[head++];
         
         if(u.joe && maze[u.x][u.y] == 'F') continue;
 
         for (int i = 0; i < 4; i++) {
            rH = u.x + dx[i];
            cH = u.y + dy[i];
            if(u.joe) {
               if (isValid(rH, cH) /* && distFire[rH][cH] == INF */
                     /*&& !visited[rH][cH]*/ && maze[rH][cH] == '.') {
                     
                  maze[rH][cH] = 'Y';//funciona porque F no se detiene por esto
//                  visited[rH][cH] = true;
                  //q.offer(new State(rH, cH, u.cost + 1, u.joe));
                  q[tail++] = new State(rH, cH, u.cost + 1, u.joe);
                  
               } else if(rH < 0 || rH >= R || cH < 0 || cH >= C) {
                  minTime = u.cost + 1;
                  return;              
               }
            } else {
               if (isValid(rH, cH) && maze[rH][cH] != '#' && maze[rH][cH] != 'F') {
                  maze[rH][cH] = 'F';
                  //q.offer(new State(rH, cH, u.cost + 1, u.joe));
                  q[tail++] = new State(rH, cH, u.cost + 1, u.joe);
               }
            }
         }
      }
   }
 
   static class State {
 
      int x, y, cost;
      boolean joe;
 
      public State(int x, int y, int cost, boolean joe) {
         this.x = x;
         this.y = y;
         this.cost = cost;
         this.joe = joe;
      }
 
   }
   
   static class Point{
      int x,y;
 
      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      public void setLocation(int x, int y) {
         this.x = x;
         this.y = y;
      }
   }
}