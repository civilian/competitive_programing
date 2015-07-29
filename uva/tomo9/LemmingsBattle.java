package uva.tomo9;

/*Uva 978*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class LemmingsBattle {
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
	
	static void dbg(Object ...o) {
//		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("LemmingsBattle"));
		readln();

		int testCases = nextInt();

		for (int casesId = 0; casesId < testCases; casesId++) {
			readln();
			int B = nextInt(), SG = nextInt(), SB = nextInt();
			gSoldier.clear();
			bSoldier.clear();

			for (int i = 0; i < SG; i++) {
				readln();
				gSoldier.add(nextInt());
			}
			for (int i = 0; i < SB; i++) {
				readln();
				bSoldier.add(nextInt());
			}
			papa: while (true) {
				for (int i = 0; i < B; i++) {
					Integer blue = bSoldier.poll();
					Integer green = gSoldier.poll();
					
					dbg("blue ",blue," green ",green);
					dbg(i);
					if (i == 0) {
						if (blue == null && green == null) {
							System.out.println("green and blue died");
							imprimirEnter(casesId, testCases);
							break papa;
						}
						else if (blue == null) {
							System.out.println("green wins");
							add(green, gSoldier);
							imprimirSoldiers(gSoldier);
							imprimirEnter(casesId, testCases);
							break papa;
						}
						else if (green == null) {
							System.out.println("blue wins");
							add(blue, bSoldier);
							imprimirSoldiers(bSoldier);
							imprimirEnter(casesId, testCases);
							break papa;
						}
					}

					if (green == null || blue == null){
						add(green,gSoldier);
						add(blue, bSoldier);
						break;
					}
					batalla(green, blue);//cuando se sacan solo se reemplazan hasta que todas las batallan hallan sido dadas
				}
			}

		}

	}

	private static void add(Integer elo, PriorityQueue<Integer> solds) {
		if(elo!=null)
			solds.add(elo);
		
	}

	private static void batalla(Integer green, Integer blue) {
		int tmpGreen= green- blue;
		int tmpBlue = blue-green;//claro pedazo de marica
		
		dbg(" batalla blue ",tmpBlue," green ",tmpGreen);
		if (tmpGreen > 0) {
			gSoldier.add(tmpGreen);
		}
		if (tmpBlue > 0) {
			bSoldier.add(tmpBlue);
		}

	}

	private static void imprimirSoldiers(PriorityQueue<Integer> soldiers) {
		// TODO Auto-generated method stub
		while (!soldiers.isEmpty()) {
			Integer s = soldiers.poll();
			System.out.println(s);
		}

	}

	private static void imprimirEnter(int casesId, int testCases) {
		if (casesId != testCases - 1) {
			System.out.println();
		}
	}

	static Comparator<Integer> comp = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2.compareTo(o1);
		}
	};
	static PriorityQueue<Integer> gSoldier = new PriorityQueue<Integer>(100000,
			comp);
	static PriorityQueue<Integer> bSoldier = new PriorityQueue<Integer>(100000,
			comp);
}
