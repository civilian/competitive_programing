package libroCompetitive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class e1_2_3_2 {
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		
		ArrayList<Integer> linte=new ArrayList<Integer>(10000);
		for (int i = 0; i < 1000; i++)
			linte.add((int) Math.round(Math.random()*1000));
		Collections.sort(linte);
		
		for (Iterator iterator = linte.iterator(); iterator.hasNext();) {
			Integer elo = (Integer) iterator.next();
			for (int i = 0; i < 1000; i++)
				metBuscar(linte,(int) Math.round(Math.random()*1000));
		}
//		output.close();
	}

	private static void metBuscar(ArrayList<Integer> linte, Integer elo) {
		int i=Collections.binarySearch(linte,elo);
//		dbg(i);
		System.out.println(i>0?"true":"false");
	}
}
