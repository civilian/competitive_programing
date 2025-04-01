package MaratonArtOfProgramingContest;
import java.math.BigInteger;
import java.util.LinkedList;


public class PascalsTriangleofDeath1 {
	public static void main(String[] args) {
		LinkedList<BigInteger> fila= new LinkedList<BigInteger>();
		LinkedList<BigInteger> filaAux= new LinkedList<BigInteger>();
		BigInteger tamannoFila=BigInteger.valueOf(0);
		
		while (true) {
			tamannoFila=tamannoFila.add(BigInteger.valueOf(1));
			BigInteger ultimo=tamannoFila.subtract(BigInteger.ONE);
			
			for(BigInteger i=BigInteger.ZERO
					;i.compareTo(tamannoFila)<0
					;i=i.add(BigInteger.ONE)
				)
			{
				if(i.equals(BigInteger.ZERO))
				{
					fila.add(BigInteger.ONE);
					filaAux.add(BigInteger.ONE);
				}
				else if(i.equals(ultimo))
				{
					fila.add(BigInteger.ONE);
					filaAux.add(BigInteger.ONE);
				}
				else 
				{
					BigInteger tmp=i;
					tmp=tmp.subtract(BigInteger.ONE);
					tmp=filaAux.get(i.intValue()).add(filaAux.get(tmp.intValue()));
					fila.add(tmp);
				}
				System.out.print(fila.get(i.intValue()).toString()+" ");
			}
			System.out.println();
			filaAux=fila;
		}
		
	}

}
