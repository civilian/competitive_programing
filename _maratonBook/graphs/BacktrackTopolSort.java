package maratonBook.graphs;
import java.io.*;
import java.util.*;

/*TODO: NO ENTIENDO PARA QUE ES*/
public class BacktrackTopolSort {
	static final boolean F=false, T=true;
	public static void main(String[] args) {
		grafo = new boolean[][] {
				{F,T,T,F,F,F},
				{F,F,F,F,F,F},
				{F,F,F,T,F,F},
				{F,F,F,F,T,T},
				{F,F,F,F,F,F},
				{F,F,F,F,F,F},
		};
		N=6;
		indeg = new int[]{0,1,1,1,1,1};
		backtrackTopol();
	}	
	
	static int N;
	static boolean grafo[][];
	static int indeg[];
	//static LinkedList<Integer> huerfanos;
	
	static LinkedList<Integer> ordenamiento = new LinkedList<Integer>();
	
	static void backtrackTopol() {
		
		if(ordenamiento.size()==N) {
			System.out.println(ordenamiento);
			return;
		}
		
		boolean ok=false;
		for(int h=0; h<N; h++) if(indeg[h]==0 && !ordenamiento.contains(h)) {
			ok=true;
			ordenamiento.add(h);
			
			LinkedList<Integer> modificados = new LinkedList<Integer>();
			
			for(int j=0; j<N; j++) {
				if(grafo[h][j]) {
					modificados.add(j);
					grafo[h][j]=false;
					indeg[j]--;
				}
			}
				
			backtrackTopol();
			
			for(int m : modificados) {
				grafo[h][m]=true;
				indeg[m]++;
			}
			
			ordenamiento.removeLast();
		}
		
		if(!ok) {
			System.err.println("Grafo no ordenable");
		}
	}
}
