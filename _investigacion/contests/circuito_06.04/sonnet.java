import java.io.*;
import java.util.*;

public class sonnet {

  static boolean invalid(Character ch) {
    String seq = " ¡!,.:;¿?-";
    if(seq.indexOf(ch) != -1) return true;
    return Character.isWhitespace(ch);
  }

  static String clean(String s) {
    StringBuffer t = new StringBuffer();
    for(int i = 0; i < s.length(); i++) {
      if(!invalid(s.charAt(i)))
        t.append(s.charAt(i));
    }
    String r = t.toString();
    if(r.endsWith("s")) return r.substring(0, r.length()-1);
    return r;
  }

  public static void main(String args[]) throws IOException {
    //Locale.setDefault(Locale.US);

    BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    //PrintWriter cout = new PrintWriter(System.out);

    //if(!Locale.getDefault().toString().equals("en_US"))
    //  throw new RuntimeException(Locale.getDefault().toString());

    Set<String> rhymes = new TreeSet<String>();
    rhymes.add("ABBAABBACDECDE");
    rhymes.add("ABBAABBACDEDCE");
    rhymes.add("ABBAABBACDCDCD");

    String line = cin.readLine();

    while(line != null) {
      ArrayList<String> suffix = new ArrayList<String>();

      StringTokenizer stk = new StringTokenizer(line);
      while(stk.hasMoreTokens()) {
        suffix.add(stk.nextToken().toLowerCase());
      }

      String title = cin.readLine();

      ArrayList<String> poema = new ArrayList<String>();
      while(true) {
        line = cin.readLine();
        if(line == null || line.length() == 0) break;
        //cout.println(line);
        poema.add(clean(line).toLowerCase());
        //System.out.println(poema.get(poema.size()-1));
      }

      //cout.println("sz = " + poema.size());

      if(poema.size() != 14) {
        System.out.print(title + ": Not a chance!");
        line = cin.readLine();
        if(line != null)
          System.out.println();
        continue;
      }
      //cout.println("cantidad_p = " + cantidad_p);
      StringBuffer sb = new StringBuffer();

      int n = poema.size();

      for(int i = 0; i < n; i++) {
        for(int j = 0; j < suffix.size(); j++) {
          if(poema.get(i).endsWith(suffix.get(j))) {
            sb.append((char)('A'+j));
            break;
          }
        }
      }

      String ans = sb.toString();
      if(ans.length() != 14 || !rhymes.contains(ans)) {
        System.out.print(title + ": Not a chance!");
      }
      else {
        System.out.print(title + ": " + ans);
      }
      line = cin.readLine();
      if(line != null)
        System.out.println();
    }
    //cout.close();
  }
}
