package maratonBook.snippets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MapaerAIndices {
	private static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("MapaerAIndices"));

		while (sc.hasNext()) {
			String s=sc.next();
			dbg(s,mapearConInverso(s) );
		}
		dbg("inversos");
		for (int i = 0; i < map.size(); i++) {
			dbg(i,inverseMap.get(i));
		}
	}

	//MAPER CON INVERSO
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static HashMap<Integer, String> inverseMap = new HashMap<Integer, String>();
	
	static int mapearConInverso(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			inverseMap.put(idx, next);
		}
		return idx;
	}
	//MAPER CON INVERSO END
	
	//MAPER SIN INVERSO
//	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	static int mapear(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
		}
		return idx;
	}
	//MAPER SIN INVERSO END

	
}
