package Maraton2011_19Mar;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;


public class NonIncrDigits {
	
	BigInteger[] largo={new BigInteger("10"),
			new BigInteger("55"),
			new BigInteger("220"),
			new BigInteger("715"),
			new BigInteger("2002"),
			new BigInteger("5005"),
			new BigInteger("11440"),
			new BigInteger("24310"),
			new BigInteger("48620"),
			new BigInteger("92378"),
			new BigInteger("167960"),
			new BigInteger("293930"),
			new BigInteger("497420"),
			new BigInteger("817190"),
			new BigInteger("1307504"),
			new BigInteger("2042975"),
			new BigInteger("3124550"),
			new BigInteger("4686825"),
			new BigInteger("6906900"),
			new BigInteger("10015005"),
			new BigInteger("14307150"),
			new BigInteger("20160075"),
			new BigInteger("28048800"),
			new BigInteger("38567100"),
			new BigInteger("52451256"),
			new BigInteger("70607460"),
			new BigInteger("94143280"),
			new BigInteger("124403620"),
			new BigInteger("163011640"),
			new BigInteger("211915132"),
			new BigInteger("273438880"),
			new BigInteger("350343565"),
			new BigInteger("445891810"),
			new BigInteger("563921995"),
			new BigInteger("708930508"),
			new BigInteger("886163135"),
			new BigInteger("1101716330"),
			new BigInteger("1362649145"),
			new BigInteger("1677106640"),
			new BigInteger("2054455634"),
			new BigInteger("2505433700"),
			new BigInteger("3042312350"),
			new BigInteger("3679075400"),
			new BigInteger("4431613550"),
			new BigInteger("5317936260"),
			new BigInteger("6358402050"),
			new BigInteger("7575968400"),
			new BigInteger("8996462475"),
			new BigInteger("10648873950"),
			new BigInteger("12565671261"),
			new BigInteger("14783142660"),
			new BigInteger("17341763505"),
			new BigInteger("20286591270"),
			new BigInteger("23667689815"),
			new BigInteger("27540584512"),
			new BigInteger("31966749880"),
			new BigInteger("37014131440"),
			new BigInteger("42757703560"),
			new BigInteger("49280065120"),
			new BigInteger("56672074888"),
			new BigInteger("65033528560"),
			new BigInteger("74473879480"),
			new BigInteger("85113005120"),
			new BigInteger("97082021465"),
			new BigInteger("110524147514"),};




	NonIncrDigits()
	{}
	
	void solve(int caso,int digitos){
		System.out.println(caso+" "+ largo[digitos]);
	}
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc=new Scanner(new File("NonIncrDigits"));
//		Scanner sc=new Scanner(System.in);
		int testCases=sc.nextInt();
		NonIncrDigits obj=new NonIncrDigits();
		
		for (int casesId = 0; casesId < testCases; casesId++) {
			int caso=sc.nextInt();
			
			int digitos=sc.nextInt();
			obj.solve(caso,digitos);
		}
	}
}


