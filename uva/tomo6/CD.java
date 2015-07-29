package uva.tomo6;
/*Uva 624.*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CD {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	

	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("CD"));

		track=new int[22];
		cd=new int[22];
		optimum_cd=new int[22];
		while (readln()!=null) {
			cd_length=nextInt(); N=nextInt();
			
			for (int i = 0; i < N; i++){
				track[i]=nextInt();
			}
			
			minimum_space = cd_length; 
			sum = 0;
			top=0;
			
			for (int i = 0; i < N; i++)
				max_length(cd_length, i);
			for (int i = 0; i < optimum_top; i++) {
				sum += optimum_cd[i];
				System.out.printf("%d ", optimum_cd[i]);
			}
			System.out.printf("sum:%d\n", sum);
		}
		
	}
	
	static int track[], cd[], optimum_cd[];
	static int optimum_top, top, N, cd_length, minimum_space, sum;

	static void max_length(int minutes, int index) {
		if (index == N || minutes - track[index] < 0) {
			if (minutes < minimum_space) {
				minimum_space = minutes; 
				optimum_top = top;
				for (int i = 0; i < top; i++)
					optimum_cd[i] = cd[i];
			}
		} else {
			cd[top++] = track[index];
			for (int i = index + 1; i <= N; i++)
				max_length(minutes - track[index], i);
			top--;
		}
	}
}
