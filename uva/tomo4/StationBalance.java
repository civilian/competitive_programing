package uva.tomo4;

//Uva 410
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;

public class StationBalance {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("StationBalance"));
		int caseId = 1;
		while (readln() != null) {
			C = nextInt();
			S = nextInt();
			mass.clear();
			A = 0;
			for (int i = 0; i < S; i++) {
				tmp = nextIntS();
				A += tmp;
				mass.add(tmp);
			}
			A = A / (double) C;
			balanceChambers();

//			enter(caseId);
			System.out.printf("Set #%d\n", caseId);

			for (int i = 0; i < C; i++) {
				System.out.printf(" %d:", i);
				for (int j = 0; j < chambers[i].size(); j++) {
					if (chambers[i].get(j) != 0) {
						System.out.printf(" %d", chambers[i].get(j));
					}
				}
				System.out.println();
			}
//			dbg(A);
			System.out.printf("IMBALANCE = %.5f\n", imbalance());
			System.out.println();
			caseId++;
		}

	}
	
	static int tmp;
	static double A;
	static int S, C;
	static ArrayList<Integer> mass = new ArrayList<Integer>(11);
	static ArrayList<Integer>[] chambers = new ArrayList[6];

	static {
		for (int i = 0; i < chambers.length; i++) {
			chambers[i] = new ArrayList<Integer>(2);
			chambers[i].add(0);
			chambers[i].add(0);
		}
	}

	static double ans;
	static double sumTmp;

	private static double imbalance() {
		ans = 0.0;
		for (int i = 0; i < C; i++) {
			sumTmp = 0.0;
			for (int j = 0; j < chambers[i].size(); j++) {
				sumTmp += chambers[i].get(j);
			}
//			dbg("ans",ans,"sumTmp",sumTmp, "chamber", i ,"pos");
			ans += Math.abs(sumTmp - A);
		}
		return ans;
	}

	static int porCamara = 2;

	private static void balanceChambers() {
		tmp = porCamara * C;
		for (int i = S; i < tmp; i++) {
			mass.add(0);
		}

		Collections.sort(mass);
//		dbg(mass);

		for (int i = 0; i < C; i++) {
			chambers[i].set(0, mass.get(i));
			chambers[i].set(1, mass.get(mass.size() - (i + 1)));
		}

	}

	private static int nextIntS() throws IOException {
		int resultado;
		try {
			resultado = nextInt();
		} catch (Exception e) {
			if (readln() == null)
				System.exit(0);

			resultado = nextIntS();
		}
		return resultado;
	}
}
