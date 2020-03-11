import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AreSame {
	
	public static boolean comp(int[] a, int[] b) {
    try{
      //List list_a = Arrays.asList(a);
      if(a.length != b.length)
        return false;
      
      int[] square_a = IntStream.of(a)
                                  .map(n -> n*n)
                                  .toArray();
      //Collections.sort(square_a);
      Arrays.sort(square_a);
      Arrays.sort(b);
      for (int i=0; i < a.length; i++){
        //int square = square_a.pop();
        if (square_a[i] != b[i]){
          return false;
        }
      }
      return true;
    } catch(Exception e){
      return false;
    }
  }
}

import static org.junit.Assert.*;
import org.junit.Test;


public class AreSameTest {

	@Test
	public void test1() {
		int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
		int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
		assertEquals(true, AreSame.comp(a, b)); 
	}
}

import java.util.*;
import java.io.*;
import java.util.Arrays;

public class AreSame {
  
  public static boolean comp(int[] a, int[] b) {
    if ((a == null) || (b == null)){
          return false;
    }
    int[] aa = Arrays.stream(a).map(n -> n * n).toArray();
    Arrays.sort(aa);
    Arrays.sort(b);
    return (Arrays.equals(aa, b));
    
  }
}


