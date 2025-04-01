package zEntrenoEquipo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

import maraton2011_DelMonkey.makingbook;

public class ListaEjercicios {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("_template"));
		readln();

		int numCases = nextInt();

		ArrayList<Character> problem=new ArrayList<Character>();
		for (int i = 0; i < numCases; i++) {
			problem.add((char) ('A'+i));
		}
		ArrayList<Character> problemN=new ArrayList<Character>();
		
		while (!problem.isEmpty()) {
			int index=(int) (Math.random()*problem.size());
			char elo=problem.get(index);
			problem.remove(index);
			problemN.add(elo);
		}
//		dbg(problemN);
		for (int i = 0; i < problemN.size(); i++) {
			System.out.println(problemN.get(i));
		}
		// output.close();
	}
}
