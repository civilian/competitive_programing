package ClasesVictorPadilla.Clase3S2;

import java.io.*;
import java.util.*;

public class CostoUniforme {
    static BufferedReader input;
   
    static StringTokenizer _stk;
    static String readln() throws IOException {
        String l = input.readLine();
        if(l!=null) _stk = new StringTokenizer(l," ");
        return l;
    }
    static String next() { return _stk.nextToken(); }
    static int nextInt() { return Integer.parseInt(next()); }
    
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        //input = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new FileReader("grafo.in"));
        
        readln();
		N =  nextInt();
		grafo = new LinkedList[N];
		for(int i=0; i<N; i++) grafo[i]=new LinkedList<Edge>();
		readln();
		int E = nextInt();
		for(int i=0; i<E; i++) {
			readln();
			int from = nextInt(), to = nextInt(), cost = nextInt();
			grafo[from].add(new Edge(to, cost));
		}
		
		int res = costoUniforme(0, 5);
		System.out.println(res);
    }
    
    static int costoUniforme(int inicio, int fin) {
    	PriorityQueue<State> pq=new PriorityQueue<State>();
    	pq.add(new State(inicio, 0));
    	
    	while(!pq.isEmpty()) {
    		State cur = pq.poll();
    		if(cur.node==fin) return cur.cost;
    		
    		for(Edge e: grafo[cur.node]) {
    			pq.add(new State(e.to, cur.cost+e.cost));
    		}
    	}
    	
    	return -1;
    }
    
    static class State implements Comparable<State> {
    	int node;
    	int cost;
		public State(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		public int compareTo(State o) {
			if(cost<o.cost) return -1;
			if(cost>o.cost) return 1;
			return 0;
		}
    }
    
    static int N ;
    static LinkedList<Edge> grafo[];
    
    static class Edge {
    	int to;
    	int cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
    }
}
