import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;


public class dominosOld {
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
		
		int num=nextInt();
		
		for (int i = 0; i < num; i++) {
			readln();
			int N=nextInt(), M=nextInt();
			initSet(N);//cantidad de fichas
			for (int j = 0; j < M; j++) {
				readln();
				int primero=nextInt();
				int segundo=nextInt();
				unionSet(primero-1, segundo-1);
			}
			
			System.out.println(numDisjointSets());
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
	static void initSet(int N){ setSize=new int[N]; Arrays.fill(setSize, 1);
		_numDisjointSets=N; pset=new int[N];
		for (int i = 0; i < N; i++) {
			pset[i]=i;
		}
	}
	static int findSet(int i){
		return (pset[i]==i)? i : 
			(pset[i]=findSet(pset[i]));
	}
	static boolean isSameSet(int i, int j){
		return findSet(i)==findSet(j);
	}
	static void unionSet(int i, int j){
		if(!isSameSet(i, j)){
			_numDisjointSets--;
			setSize[findSet(j)] += setSize[findSet(i)];
			pset[findSet(i)]=findSet(j);
		}
	}
	static int numDisjointSets(){return _numDisjointSets;}
	
	static int sizeOfSet(int i){return setSize[findSet(i)]; }
	
	
}

	
