package uva.tomo2;

//Uva 299
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class TrainSwappingComp {
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

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		// input=new BufferedReader(new FileReader("TrainSwapping"));
		readln();

		int numCases = nextInt();

		for (int casesId = 0; casesId < numCases; casesId++) {
			readln();

			int itrenes = nextInt();

			Integer trenes[] = new Integer[itrenes];
			readln();
			for (int i = 0; i < itrenes; i++) {
				trenes[i] = nextInt();
			}

			swapsMerge = 0;
			// valuesMerge=trenes;
			// sizeMerge=trenes.length;

			mergesortIni(trenes);
			System.out.printf("Optimal train swapping takes %d swaps.\n",
					swapsMerge);
		}

	}

	static long swapsMerge;

	static Integer[] mergesortIni(Integer[] data) {
		swapsMerge = 0;
		return mergesort(data);
	}

	static Integer[] mergesort(Integer[] data) {
		// dbg("data",data, data.length);
		if (data.length == 1)
			return data;
		int middle = data.length / 2;
		// dbg(middle);
		// dbg("swap",swapsMerge);
		Integer[] left = mergesort(Arrays.copyOfRange(data, 0, middle));
		Integer[] right = mergesort(Arrays.copyOfRange(data, middle,
				data.length));
		Integer[] result = new Integer[data.length];
		int dPtr = 0;
		int lPtr = 0;
		int rPtr = 0;
		// dbg("left",left);
		// dbg("right",right);
		while (dPtr < data.length) {
			if (lPtr == left.length) {
				result[dPtr] = right[rPtr];
				rPtr++;
			} else if (rPtr == right.length) {
				result[dPtr] = left[lPtr];
				lPtr++;
			} else if (left[lPtr].compareTo(right[rPtr]) <= 0) {
				result[dPtr] = left[lPtr];
				lPtr++;
			} else {
				swapsMerge += left.length - lPtr;
				result[dPtr] = right[rPtr];
				rPtr++;
			}
			dPtr++;
		}
		return result;
	}
}
