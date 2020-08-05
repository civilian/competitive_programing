package herramientas;
/**
 * Toma tiempos
 * @author Oscar Chamat Caicedo (civilian)
 */
public class Timing {
	long lT1;
	long lT2;
	public Timing(){
		lT1=0;
		lT2=0;
	}
	public void reset(){
		lT2=0;
		lT1=0;
	}
	
	public void  tomarTiempo(){
		if(lT1==0)
			lT1=System.nanoTime();
		else if (lT2==0)
			lT2=System.nanoTime();
		else 
			lT1=lT2;
			lT2=System.nanoTime();
//		System.out.println("T1 "+lT1+" T2 "+lT2);
//		System.err.println("ha pasado="+(lT2-lT1));
		System.out.println("ha pasado="+(lT2-lT1));
	}
}
