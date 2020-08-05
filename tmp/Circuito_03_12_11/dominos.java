import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class dominos {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException {
		String l=input.readLine();
		if(l!= null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){ return _stk.nextToken();}
	static Integer nextInt(){ return Integer.parseInt(_stk.nextToken());}
	
	static void dbg(Object...o){
		System.out.println(Arrays.deepToString(o));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("dominos.in"));
		readln();
//		TreeSet<Integer> set;
		int num=nextInt();
		
		for (int i = 0; i < num; i++) {
			readln();
//			set= new TreeSet<Integer>();
			int N=nextInt(), M=nextInt();
			initSet(N);//cantidad de fichas
			for (int j = 0; j < M; j++) {
				readln();
				int primero=nextInt();
				int segundo=nextInt();
				unionSet(primero-1, segundo-1);
			}
			
//			int sal=0;
//			for (int j = 0; j < pset.length; j++) {
//				if(!set.contains(pset[j])){
//					set.add(pset[j]);
//					sal++;
//				}
//			}
			
//			dbg(pset);
			System.out.println(numDisjointSets());
//			System.out.println(sal);
		}
		
//		initSet(5);
//		unionSet('A'-'A', 'B'-'A');
//		unionSet('A'-'A', 'C'-'A');
//		unionSet('D'-'A', 'B'-'A');
//		System.out.println(findSet('A'-'A'));
//		System.out.println(findSet('A'-'A'));
//		System.out.println(findSet('B'-'A'));
//		System.out.println(findSet('C'-'A'));
//		System.out.println(findSet('D'-'A'));
//		System.out.println(findSet('E'-'A'));
//		System.out.println(isSameSet('A'-'A', 'E'-'A'));
//		System.out.println(isSameSet('A'-'A', 'B'-'A'));
	}
	
	static int[] pset,setSize; static int _numDisjointSets;
	static LinkedList<Integer> hijos[];
	static void initSet(int N){ setSize=new int[N]; Arrays.fill(setSize, 1);
		_numDisjointSets=N; pset=new int[N];
		hijos=new LinkedList[N];
		for (int i = 0; i < N; i++) {
			pset[i]=i;
			hijos[i]=null;
		}
	}
	static int findSet(int i){
//		return (pset[i]==i)? i : 
//			(pset[i]=findSet(pset[i]));
		return pset[i];
	}
	
	static boolean isSameSet(int i, int j){
		return findSet(i)==findSet(j);
	}
	
	static void unionSet(int i, int j){
		int padreFinal, padreTmp, menor;
//		System.out.println(i+" "+j);
		if(!isSameSet(i, j)){
			_numDisjointSets--;
			if(sizeOfSet(i)>sizeOfSet(j)){
				padreFinal=findSet(i);
				padreTmp=findSet(j);	
//				menor=j;
			}
			else{
				padreFinal=findSet(j);
				padreTmp=findSet(i);
//				menor=i;
			}
			
			if(hijos[padreTmp]!=null){
				if(hijos[padreFinal]==null) hijos[padreFinal]=new LinkedList<Integer>();
				for (int e: hijos[padreTmp]) {
					pset[e]=padreFinal;
					hijos[padreFinal].add(e);
				}
				pset[padreTmp]=padreFinal;
				hijos[padreFinal].add(padreTmp);
				hijos[padreTmp]=null;
			}
			else{
				if(hijos[padreFinal]==null) hijos[padreFinal]=new LinkedList<Integer>();
				pset[padreTmp]=padreFinal;
				hijos[padreFinal].add(padreTmp);
			}
//			pset[findSet(i)]=findSet(j);
//			setSize[findSet(j)] += setSize[findSet(i)];
			setSize[padreFinal] += setSize[padreTmp];
//			System.out.println(Arrays.toString(pset));
//			System.out.println(Arrays.deepToString(hijos));
		}
	}
	static int numDisjointSets(){return _numDisjointSets;}
	
	static int sizeOfSet(int i){return setSize[findSet(i)]; }
	
	
}

	
