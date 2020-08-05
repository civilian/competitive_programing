package uva.tomo110;

//Uva 11060.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BeveragesDeWeb {
	public static void main(String[] args) throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				System.in));
		String in = "";
		int cc = 1;

		while (true) {
			if (cc != 1) {
				buff.readLine();
				System.out.println();
			}
			in = buff.readLine();
			if (in == null)
				break;
			int n = Integer.parseInt(in);
			HashMap<String, Integer> h1 = new HashMap<String, Integer>();
			HashMap<Integer, String> h2 = new HashMap<Integer, String>();

			for (int i = 0; i < n; i++) {
				String input = buff.readLine();
				h1.put(input, i + 1);
				h2.put(i + 1, input);
			}
			int[] indegree = new int[n + 1];
			int edges = Integer.parseInt(buff.readLine());
			LinkedList<Integer>[] list = new LinkedList[n + 1];
			for (int i = 0; i < list.length; i++)
				list[i] = new LinkedList<Integer>();
			for (int i = 0; i < edges; i++) {
				StringTokenizer st = new StringTokenizer(buff.readLine());
				String f = st.nextToken();
				String t = st.nextToken();
				int from = h1.get(f);
				int to = h1.get(t);
				indegree[to]++;
				list[from].add(to);
			}
			Queue<Integer> output = new LinkedList<Integer>();
			boolean[] bools = new boolean[n];

			for (int i = 0; i < n; i++) {
				if (!bools[i] && indegree[i + 1] == 0) {
					bools[i] = true;
					output.add(i + 1);
					for (int j = 0; j < list[i + 1].size(); j++) {
						indegree[list[i + 1].get(j)]--;
					}
					i = -1;
				}
			}

			StringBuilder bd = new StringBuilder("");
			if (output.isEmpty()) {
				bd.append(" ");
			}
			while (!output.isEmpty()) {
				bd.append(h2.get(output.poll()) + " ");
			}
			System.out
					.printf("Case #%d: Dilbert should drink beverages in this order: %s.\n",
							cc++, bd.substring(0, bd.length() - 1));
		}
	}
}
