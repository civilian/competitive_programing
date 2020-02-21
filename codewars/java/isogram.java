public class isogram {
    public static boolean  isIsogram(String str) {
        int[] letters = new int['z'- 'a'];
        str = str.toLowerCase();
        for (int i=0; i<str.length(); i++){
          int index = (str.charAt(i)) -'a';
          if (letters[index] == 1){
            return false;
          }
          letters[index]++;
        }
        return true;
    } 
}

public class isogramBest {
  public static boolean  isIsogram(String str) {
    return str.length() == str.toLowerCase().chars().distinct().count();
  } 
}
