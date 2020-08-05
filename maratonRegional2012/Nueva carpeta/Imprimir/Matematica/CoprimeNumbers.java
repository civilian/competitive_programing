package maratonBook.matematica;

public class CoprimeNumbers {

	public static void main(String[] args) {
		System.out.println(totient(3000));//800
		System.out.println(totient(97));//96
		
		
		totientSiev(50000);
		
		for(int i=0;i<50001;i++){
		    System.out.printf("%d %d\n", totient(i),nrp[i]);
		    if(totient(i)!=nrp[i])
		    	break;
		   }
	}
	 public static int totient(int num){ //euler's totient function calculator. returns totient
		    int count=0;
		    for(int a=1;a<num;a++){ //definition of totient: the amount of numbers less than num coprime to it
		      if(GCD(num,a)==1){ //coprime
		        count++;
		      }
		    }
		    return(count);
	}
	 
	static int GCD(int a,int b) {
		while (b > 0) {
			a = a % b;
			a ^= b; b ^= a; a ^= b; } return a;
	}
	
	
	private static int[] nrp;
	//el limite es inclusivo
	public static void totientSiev(int limit){
		limit++;
		nrp=new int[limit];
		for(int i=0;i<limit;i++)
		     nrp[i]=i;
		for(int i=2;i<limit;i++)
		     if(nrp[i]==i)
		     {
		          nrp[i]--;
		          for(int j=i<<1;j<limit;j+=i)
		               nrp[j]=nrp[j]*(i-1)/i;
		     }
		
		nrp[1]=0;
	}
}
