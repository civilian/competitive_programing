import java.util.HashMap;
import java.util.Map;
import java.lang.Character;

public class CountingDuplicates {
  public static int duplicateCount(String text) {
    HashMap<Character, Integer> duplicates = new HashMap<Character, Integer>();
    for (int i = 0; i< text.length(); i++){
      Character symbol = Character.toLowerCase(text.charAt(i));
      Integer count = duplicates.get(symbol);
      if(count == null){
        count = 0;
      } else {
        count = 1;
      }
      duplicates.put(symbol, count);
    }
    int ans = 0;
    for(Map.Entry<Character, Integer> entry : duplicates.entrySet()){
      ans += entry.getValue();
    }
    return ans;
  }
}

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class SolutionTest {
    @Test
    public void abcdeReturnsZero() {
        assertEquals(0, CountingDuplicates.duplicateCount("abcde"));
    }
    
    @Test
    public void abcdeaReturnsOne() {
        assertEquals(1, CountingDuplicates.duplicateCount("abcdea"));
    }
    
    @Test
    public void indivisibilityReturnsOne() {
        assertEquals(1, CountingDuplicates.duplicateCount("indivisibility"));
    }
    
    @Test 
    public void reallyLongStringContainingDuplicatesReturnsThree() {
        String testThousandA = new String(new char[1000]).replace('\0', 'a');
        String testHundredB = new String(new char[100]).replace('\0', 'b');
        String testTenC = new String(new char[10]).replace('\0', 'c');
        String test1CapitalA = new String(new char[1]).replace('\0', 'A'); 
        String test1d = new String(new char[1]).replace('\0', 'd'); 
        String test = test1d + test1CapitalA + testTenC + 
            testHundredB + testThousandA;
        

        assertEquals(3, CountingDuplicates.duplicateCount(test));
    }
    
    
}

public class CountingDuplicates {
    public static int duplicateCount(String text) {
      HashMap<Character, Integer> map = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            map.put(c, map.containsKey(c) ? 1 : 0);
        }
      return (int) map.values().stream().filter(e -> e > 0).count();
    }
}

import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class CountingDuplicates {
    private static Map<Character, Long> charFrequenciesMap(final String text) {
        return text.codePoints()
            .map(Character::toLowerCase)
            .mapToObj(c -> (char) c)
            .collect(groupingBy(identity(), counting()));
    }
    
    static int duplicateCount(final String text) {
        return (int) charFrequenciesMap(text).values().stream()
            .filter(i -> i > 1)
            .count();
    }
}

