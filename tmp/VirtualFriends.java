import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;


public class VirtualFriends {
	static BufferedReader input;
    static StringTokenizer _stk;
    
    static String readln() throws IOException {
        String l = input.readLine();
        if(l != null)
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
        input = new BufferedReader(new InputStreamReader(System.in));
        //input = new BufferedReader(new FileReader("template.in"));
        readln();
        int cases = nextInt();
        for(int id = 1; id <= cases; id++) {
        	readln();
        	int f = nextInt();
        	
        	initSet(100000);
        	HashMap<String, Integer> memo = new HashMap<String, Integer>();
        	for(int k = 0; k < f; k++) {
        		readln();
        		String A = next(), B = next();
        		if(memo.get(A) == null) {
        			memo.put(A, memo.size());
        		}
        		if(memo.get(B) == null) {
        			memo.put(B, memo.size());
        		}
        		unionSet(memo.get(A), memo.get(B));
        		int parent = set[memo.get(B)];
        		System.out.println(weigth[parent]);
        	}
        }
    }
    
    static void initSet(int size) {
    	set = new int[size];
    	rank = new int[size];
    	weigth = new int[size];
    	for(int i = 0; i < size; i++) {
    		set[i] = i;
    		rank[i] = 0;
    		weigth[i] = 1;
    	}
    }
    
    static int findSet(int i) {
    	if(set[i] == i)
    		return i;
    	return set[i] = findSet(set[i]);
    }
    
    static void unionSet(int i, int j) {
    	int iRoot = findSet(i);
    	int jRoot = findSet(j);
    	
    	if(iRoot == jRoot)
    		return;
    	
    	if(rank[iRoot] < rank[jRoot]) {
    		set[iRoot] = jRoot;
    	}
    	else {
    		set[jRoot] = iRoot;
    		if(rank[iRoot] == rank[jRoot])
    			rank[iRoot] = rank[iRoot] + 1;
    	}
    	int w = weigth[iRoot] + weigth[jRoot];
    	weigth[iRoot] = w;
    	weigth[jRoot] = w;
    	//set[findSet(i)] = findSet(j);
    }
    
    static boolean isSameSet(int i, int j) {
    	return findSet(i) == findSet(j);
    }
    
    static int[] set, rank, weigth;    
}
