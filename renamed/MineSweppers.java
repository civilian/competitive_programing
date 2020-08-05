package renamed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MineSweppers {
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner sc=new Scanner(System.in);
		Scanner sc=new Scanner(new File("MineSweppers"));
		
//		int testCases=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		for (int casesId = 0;sc.hasNext(); casesId++) {
			if(x==0&&y==0)
			{
				return;
			}
			sc.nextLine();
			char [][] campo=new char [x+2][y+2];
			char [][] salida=new char [x][y];
			
			for (int i = 0; i < campo.length; i++) {
				for (int j = 0; j < campo[0].length; j++) {
					campo[i][j] = '.';
				}	
			}
			
			for (int i = 0; i < x; i++) {
				String linea=sc.nextLine();
				for (int j = 0; j < y; j++) {
					campo[i+1][j+1] = linea.charAt(j);
				}	
			}
			System.out.println("Field #"+(casesId+1)+":");
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					salida[i][j]=contar(campo, i+1,j+1);
					System.out.print(salida[i][j]);
				}
				System.out.println();
			}
			x=sc.nextInt();
			y=sc.nextInt();
			if(x!=0&&y!=0)
			{
				System.out.println();
			}
		}
	}

	private static char contar(char[][] campo, int i, int j) {
		int tmp=0;
		
		if(campo[i][j]=='*')
		{
			return '*';
		}
		for (int i2 = i-1; i2 < i+2; i2++) {
			for (int j2 = j-1; j2 < j+2; j2++) {
				if(campo[i2][j2]=='*')
				{
					tmp++;
				}
			}
			
		}
		// TODO Auto-generated method stub
		String a=tmp+"";
		return a.charAt(0);
	}

}
