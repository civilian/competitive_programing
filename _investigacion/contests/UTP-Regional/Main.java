import java.io.*;
import java.util.*;
import java.math.BigDecimal;

public class Main {

  class Edge {
    public int u;
    public BigDecimal p;
    public Edge(int u, BigDecimal p) {
      this.u = u;
      this.p = new BigDecimal(p.toString());
    }

    public Edge() {
      this.u = 0;
      this.p = BigDecimal.ZERO;
    }
  }

  class State {
    int u;
    BigDecimal p;

    public State(int u, BigDecimal p) {
      this.u = u;
      this.p = new BigDecimal(p.toString());
    }
  }

  static final int maxn = 510;

  int n, m;
  LinkedList<Edge>[] g;
  BigDecimal[] safety;
  boolean[] seen;

  BigDecimal dfs(int u) {
    Stack<State> stk = new Stack<State>();
    stk.push(new State(u, BigDecimal.ZERO));

    while (!stk.empty()) {
      State curr = stk.pop();

      u = curr.u;
      BigDecimal p = curr.p;
      seen[u] = true;

      for (Edge e : g[u]) {
        int v = e.u;
        BigDecimal next = e.p;

        

        if (!seen[v]) {
        }
      }
    }

    if (seen[u])
      return safety[u];
    seen[u] = true;

    for (Edge e : g[u]) {
      int v = e.u;
      BigDecimal p = e.p;

      BigDecimal curr = p.multiply(dfs(v));
      if (curr.compareTo(safety[u]) > 0)
        safety[u] = curr;
    }
    return safety[u];
  }

  @SuppressWarnings("unchecked")
  void solve() {
    Reader cin = new Reader();
    while (true) {
      n = cin.nextInt();
      m = cin.nextInt();

      g = new LinkedList[n+1];
      seen = new boolean[n+1];
      safety = new BigDecimal[n+1];

      for (int i = 1; i <= n; i++)
        g[i] = new LinkedList();

      safety[1] = BigDecimal.ONE;
      for (int i = 2; i <= n; i++)
        safety[i] = BigDecimal.ZERO;

      for (int i = 0; i < m; i++) {
        int u = cin.nextInt(), v = cin.nextInt();
        double p = cin.nextDouble();
        g[v].add(new Edge(u, BigDecimal.valueOf(p)));
      }

      BigDecimal ans = dfs(n);
      if (ans.compareTo(BigDecimal.ZERO) == 0)
        System.out.println("Impossible");
      else {
        double x = ans.doubleValue();
        x = Math.log10(x);
        System.out.printf("10^%.4f\n", x);
      }
      if (cin.readln() == null)
        break;
    }
  }

  public static void main (String[] args) {
    Locale.setDefault(Locale.US);
    new Main().solve();
  }
}

class Reader {
  BufferedReader cin;
  StringTokenizer stk;

  public Reader() {
    cin = new BufferedReader(new InputStreamReader(System.in));
  }
  public String readln() {
    try { return cin.readLine(); } catch (Exception e) { }
    return null;
  }
  public String next() {
    if (stk == null || !stk.hasMoreTokens()) stk = new StringTokenizer(readln());
    return stk.nextToken();
  }
  public int nextInt() { return Integer.parseInt(next()); }
  public double nextDouble() { return Double.parseDouble(next()); }
}
