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
    System.out.println(duplicates);
    int ans = 0;
    for(Map.Entry<Character, Integer> entry : duplicates.entrySet()){
      ans += entry.getValue();
    }
    return ans;
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

