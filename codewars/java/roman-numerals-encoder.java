import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

// best solution
public class Conversion {

    public String solution(int number) {
        String romanNumbers[] = {"M", "CMXC", "CM", "D", "CDXC", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int arab[] = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(romanNumbers[i]);
            }
            i++;
        }
        return result.toString();
    }
}

public class Conversion {

    private static TreeMap<Integer, String> MAP;
    static { 
      MAP = new TreeMap<Integer, String>(Collections.reverseOrder());
      MAP.put( 1, "I" );
      MAP.put( 4, "IV" );
      MAP.put( 5, "V" );
      MAP.put( 9, "IX" );
      MAP.put( 10, "X" );
      MAP.put( 40, "XL" );
      MAP.put( 50, "L" );
      MAP.put( 90, "XC" );
      MAP.put( 100, "C" );
      MAP.put( 400, "CD" );
      MAP.put( 500, "D" );
      MAP.put( 900, "CM" );
      MAP.put( 1000, "M" );
    }
    
    public String solution(int n) {     
        StringBuilder builder = new StringBuilder();
        for( Map.Entry<Integer, String> entry: MAP.entrySet() ){
          int i = entry.getKey();
          String s = entry.getValue();
          while( n>=i ){
            builder.append(s);
            n -= i;
          }      
        }
        return builder.toString();
    }
    

}

public class Conversion {

    public String solution(int n) {
        final String[] digit = {"", "I", "II", "III", "IV", "V", "VI", "VII",
                                  "VIII", "IX"};
        final String[] ten = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX",
                                  "LXXX", "XC"};
        final String[] hundred = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC",
                                  "DCCC", "CM"};
        final String[] thousand = {"", "M", "MM", "MMM"};
        
        String result="";
        result = thousand[n/1000] + hundred[n%1000/100] + ten[n%100/10] +
                  digit[n%10];
        return result;
    }
}

// Tests

public class ConversionTest {

    private Conversion conversion = new Conversion();

    @Test
    public void shouldConvertToRoman() {
        assertEquals("solution(1) should equal to I", "I", conversion.solution(1));
        assertEquals("solution(4) should equal to IV", "IV", conversion.solution(4));
        assertEquals("solution(6) should equal to VI", "VI", conversion.solution(6));
    }
}