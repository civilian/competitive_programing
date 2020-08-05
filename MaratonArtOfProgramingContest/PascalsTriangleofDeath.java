package MaratonArtOfProgramingContest;
import java.math.BigInteger;
import java.util.ArrayList;


public class PascalsTriangleofDeath {
	public static void main(String[] args) {
		ArrayList<BigInteger> fila= new ArrayList<BigInteger>(1);
		ArrayList<BigInteger> filaAux= new ArrayList<BigInteger>(1);
		filaAux.add(0,BigInteger.valueOf(1));
		fila.add(0,BigInteger.valueOf(1));
		int tamannoFila=0;
		BigInteger masGrande=BigInteger.TEN;
		masGrande=masGrande.pow(60);
		boolean kill=false;
		while (true) {
			tamannoFila++;
			int ultimo=tamannoFila-1;
			
			for(int i=0
					;i<tamannoFila
					;i++)
			{
				if(i==0)
				{
					System.out.print(fila.get((int)i).toString());
					continue;
				}
				else if(i==ultimo)
				{
					fila.add((int)i,BigInteger.ONE);
					filaAux.add((int)i,BigInteger.ONE);
				}
				else 
				{
					//System.out.println(filaAux.toString());
					BigInteger tmpsum=filaAux.get(i-1);
					//System.out.print("// i"+ i+ " i "+filaAux.get(i)+ " i-1 "+ tmpsum + " // ");
					//System.out.print(filaAux.toString());
					BigInteger tmp=filaAux.get(i).add(tmpsum);
					//System.out.println(tmp.toString()+ " i "+filaAux.get((int)i)+ " i-1 "+ filaAux.get((int)(i-1)) );
					fila.remove(i);
					fila.add(i,tmp);
					if(tmp.compareTo(masGrande)>0)
					{
						kill=true;
					}
				}
				System.out.print(" "+fila.get((int)i).toString());
			}

			//System.out.println(fila.toString());
			System.out.println();
			if(kill)
			{return;}
			filaAux=(ArrayList<BigInteger>)fila.clone();
			//System.out.println(filaAux.toString());
		}
		
	}

}
