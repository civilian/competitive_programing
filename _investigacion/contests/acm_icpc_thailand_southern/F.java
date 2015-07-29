import java.io.*;
import java.util.*;

public class F {
  public static void main(String[] args) throws IOException {
    BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter cout = new PrintWriter(System.out);

    for (String ln = cin.readLine(); ln != null; ln = cin.readLine())
      if (ln.matches("da+dd?(i|y)"))
        cout.println("She called me!!!");
      else
        cout.println("Cooing");

    cout.close();
  }
}
