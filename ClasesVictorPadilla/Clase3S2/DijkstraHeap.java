package ClasesVictorPadilla.Clase3S2;

import java.io.*;
import java.util.*;

public class DijkstraHeap {
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
        input = new BufferedReader(new InputStreamReader(System.in));
//        input = new BufferedReader(new FileReader("grafo.in"));
        
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
		

/*6
10
0 1 1
1 0 1
1 2 1
2 1 1
1 4 1
4 1 1
3 1 1
1 3 1
4 5 1
5 4 1
1 5 1
5 1 1*/
		/*R/
		[0, 1, 2, 2, 2, 3]//este en la posicion que se busca es el costo de la ruta
		[-1, 0, 1, 1, 1, 4] 
		[0, 1]*/
		
		int res = dikstra(0, 1);
		System.out.println(Arrays.toString(minDist));
		System.out.println(Arrays.toString(prev));
		System.out.println(ruta(1));
		System.out.println(minDist[1]);
		

    }

    static int minDist[];
    static boolean visited[];
	static int prev[];
	
    /*Se pifea para costos negativos*/
    static int dikstra(int inicio, int fin) {
    	PriorityQueue<State> pq=new PriorityQueue<State>();
    	pq.add(new State(inicio, 0));

    	minDist=new int[N];
    	Arrays.fill(minDist, 1000000000);

    	visited=new boolean[N];
    	prev=new int[N];
    	minDist[inicio]=0;
    	prev[inicio]=-1;
    	
    	while(!pq.isEmpty()) {
    		State cur = pq.poll();
//    		if(cur.node==fin) return cur.cost;//cuando solo quiero saber
    									//el minimo costo de llegar aqui sin
    									//los demas, PERO SOLO SIRVE PARA COSTOS POSITIVOS
    		if(visited[cur.node]) continue;
    		
    		visited[cur.node]=true;
    		
    		for(Edge e: grafo[cur.node]) {
    			if(minDist[cur.node]+e.cost < minDist[e.to]) {
    				minDist[e.to] = minDist[cur.node]+e.cost;
    				prev[e.to]=cur.node;
    				pq.add(new State(e.to, cur.cost+e.cost));
    			}
    		}
    	}
    	
    	return -1;
    }
    
    static LinkedList<Integer> ruta(int fin) {
    	int cur=fin;
    	LinkedList<Integer> res = new LinkedList<Integer>();
    	while(cur!=-1) {
    		res.addFirst(cur);
    		cur=prev[cur];
    	}
    	return res;
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
