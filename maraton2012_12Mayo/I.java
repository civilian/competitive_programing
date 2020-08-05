
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class I {

    static BufferedReader input;
    static StringTokenizer _stK;

    static String readln() throws IOException {
        String l = input.readLine();
        if (l != null) {
            _stK = new StringTokenizer(l, " ");
        }
        return l;
    }

    static String next() {
        return _stK.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    static void dbg(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
//        input = new BufferedReader(new FileReader("I"));
      input=new BufferedReader(new InputStreamReader(System.in));
        
        int id=0;
        while (true) {
            id++;
            readln();
            int X0 = nextInt(), Y0 = nextInt();
            int X1 = nextInt(), Y1 = nextInt();
//            dbg("x0, y0 x1 y1 ", X0, Y0, X1, Y1);

            if (X0 == 0 && Y0 == 0 && X1 == 0 && Y0 == 0) {
                break;
            }
            Point2D.Double L0 = new Point2D.Double(X0, Y0);
            Point2D.Double L1 = new Point2D.Double(X1, Y1);

            readln();

            Line2D.Double sepLine = new Line2D.Double(new Point(nextInt(), nextInt()),
                                                new Point(nextInt(), nextInt()));

            Line2D.Double lovers = new Line2D.Double(L0, L1);
            if (!sepLine.intersectsLine(lovers)) {
                double resp=L0.distance(L1);
                resp=resp/2.0;
                System.out.printf("Case %d: %.3f\n",id,resp);
            }else{
                double resp1=distanceP1(L0,L1,sepLine);
                double resp2=distanceP2(L0,L1,sepLine);
                
                if (resp1<resp2) {
                    System.out.printf("Case %d: %.3f\n",id,resp1);
                }else{
                    System.out.printf("Case %d: %.3f\n",id,resp2);
                }
            
            }

        }


    }

    private static double distanceP1(Double L0, Double L1, Line2D.Double sepLine) {
        double resp=L0.distance(sepLine.getP1());
        resp+=L1.distance(sepLine.getP1());
        resp=resp/2.0;
        return  resp;
    }
    
    private static double distanceP2(Double L0, Double L1, Line2D.Double sepLine) {
        double resp=L0.distance(sepLine.getP2());
        resp+=L1.distance(sepLine.getP2());
        resp=resp/2.0;
        return  resp;
    }
}
