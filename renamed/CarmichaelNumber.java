package renamed;

import java.util.Arrays;
import java.util.HashSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class CarmichaelNumber {
	
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input=new BufferedReader(new InputStreamReader(System.in));
//		input=new BufferedReader(new FileReader("CarmichaelNumber"));
		
		while (readln()!=null) {
			int n=nextInt();
			if(n==0) return;
			if(numCarmachael.contains(n)){
				System.out.printf("The number %d is a Carmichael number.\n", n);
			}else System.out.println(n+" is normal.");
		}
		
//		LinkedList<Long> salida=new LinkedList<Long>();
//		sieve(1, 12000000, prim);/*hasta el numero que sea el sqrt del numero de
//		 						*charmachael necesite o hasta 24' y para seguros 12'*/
//		listaFactoresPrimos((long)Math.pow(2, 53), salida);
//		
//		for (int i = 0; i < 65001; i++) {
//			if(isCarmachaelNumber(i)){
//				salida.add((long)i);
//			}
//		}
//		System.out.println(salida);
	}
	
	static Integer[] iAnumerosCarmachael={561, 1105, 1729, 2465, 2821, 6601, 7107, 8911, 10585, 15841, 18361, 24073, 29341, 41041, 45451, 46657, 52633, 53131, 57889, 62745, 63973};
	static HashSet<Integer> numCarmachael=new HashSet<Integer>(Arrays.asList(iAnumerosCarmachael));
	
//	static ArrayList<Integer> prim=new ArrayList<Integer>();
	/*y si, no cambia nada*/
//	static int [] prim={.....};
//	
//	/*cogo la lista de factores primos, la pruebo, y luego la vuelvo isCarmachaelNumber*/
//	static boolean isCarmachaelNumber(long numero){
//		int tmp=2;
//		int cantFactPrimos=0;
//		long inicial=numero;
//		papa:
//			while (numero!=1) {
//				int raiz=(int)Math.sqrt(numero);
//				
//				for(Integer elo: prim){
//					tmp=elo;
//					if(elo>raiz)
//						break;
//					if(numero%elo==0){
//						if(!isProbablePrime(inicial, (long)elo))
//							return false;
//						
//						cantFactPrimos++;
//						
//						numero/=elo;
//						if(numero%elo==0)
//							return false;
//						continue papa;
//					}
//				}
//				
//				tmp+=2;
//				for (int i = tmp; i < raiz; i+=2){
//					if(numero%i==0){
//						if(!isProbablePrime(inicial, (long)i))
//							return false;
//						
//						cantFactPrimos++;
//						
//						numero/=i;
//						if(numero%i==0)
//							return false;
//						continue papa;
//					}
//				}
//				cantFactPrimos++;
//				break;
//			}
//		return (cantFactPrimos>2);
//	}
//	
//	private static boolean isProbablePrime(long n, long a){
//		if(a!=bigMod(a,n,n))
//			return false;
//		return true;
//	}
//	
//	static long bigMod(long b, long p, long m) {
//		if(p==0)
//			return 1;
//		else if(p%2==0)
//		{
//			long parcial=bigMod(b, p/2, m)%m;
//			return (parcial*parcial)%m;
//		}
//		else return ((b%m)*bigMod(b, p-1, m))%m;
//		// TODO Auto-generated method stub
//	}
//
//	static void sieve(int L, int U, ArrayList<Integer>salida){
//		
//		int i,j,d;
//		d=U-L+1;
//		
//		boolean[]flag=new boolean[d];
//		for (i = 0; i < d; i++)flag[i]=true;
//		
//		for (i = (L%2!=0?1:0); i < d; i+=2)flag[i]=false;
//		
//		for (i = 3; i*i < U; i+=2) {
//			
//			if(i>L && !flag[i-L]) continue;
//			
//			j=L/i*i; if(j<L) j+=i;
//			if(j==i)j+=i;
//			
//			j-=L;
//			for (; j < d; j+=i) flag[j]=false;
//		}
//		
//		if(L<=1) flag[1-L]=false;
//		if(L<=2) flag[2-L]=true;
//		
//		for (i = 0; i < d; i++)if(flag[i]) salida.add(L+i);
//	}
	
}
