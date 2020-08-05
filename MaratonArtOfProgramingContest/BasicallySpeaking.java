package MaratonArtOfProgramingContest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

public class BasicallySpeaking {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l," ");
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("BasicallySpeaking"));
		
		for (int casesId = 1;; casesId++) {
			if(readln()==null){
				return;
			}
			
			String numero=next();
			
			int iBaseOrigi=nextInt();
			int iBase2=nextInt();
			
			long iNumeroEnDecimal;
			try {
				iNumeroEnDecimal=deBaseADecimal(numero, iBaseOrigi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("  ERROR");
				continue;
			}
			Vector<Integer> respuesta=deDecimalABase(iNumeroEnDecimal, iBase2);
			String sNumeroEnBase2=deVectorDecimalANumeroEnBase(respuesta);
			int iLargoRespuesta=sNumeroEnBase2.length();
			if(iLargoRespuesta>7){
				System.out.println("  ERROR");
			}
			else{
				for (int i = iLargoRespuesta; i < 7; i++) {
					sNumeroEnBase2=" "+sNumeroEnBase2;
				}
				System.out.println(sNumeroEnBase2);
			}
			
			
		}
	}
	
	static char[] chATodo="0123456789ABCDEF".toCharArray();
	
	private static String deVectorDecimalANumeroEnBase(Vector<Integer> numeroDecimal) {
		String sRespuesta="";
		for (int iI = 0; iI < numeroDecimal.size(); iI++) {
//			System.out.println(numeroDecimal.get(iI));
			sRespuesta+=chATodo[numeroDecimal.get(iI)];
		}
		// TODO Auto-generated method stub
		return sRespuesta;
	}
	
	private static long deBaseADecimal(Vector<Integer> vNumero, long iBase) throws Exception {
		/* base^posicion*DigitoEnLaPosicion+.....*/
		/*el numero esta escrito de derecha a izquierda comenzando 
		 * por el mas significativo  por lo que hay que cogerlo alrevez*/
		long resultado=0;
		for (int iI = vNumero.size()-1,iJ=0; iI >-1 ; iI--,iJ++) {
			/*El digito no puede ser un long*/
			Integer iDigito=vNumero.get(iJ);
			
			/*si es una base ilegal*/
			if(iDigito>=iBase)
				throw new Exception();

			/*el valor de la base si puede ser
			 * un long aunque seria raro*/
			long valorBase=(long)Math.pow((double)iBase,(double)iI);
			resultado+=iDigito*valorBase;
		}
		return resultado;
		// prueba:
	}
	
	private static Vector<Integer> deDecimalABase(long x, int base) {
		/*DigitoDeNumeroEnBase_EnPosicion= 
		 * se hace posicion veces numero%base
		 * o lo que es lo mismo es el residou de las divisiones
		 * consecutivas*/
		Vector<Integer> respuesta=new Vector<Integer>();
		do{
			int tmp=(int)(x%base);
			respuesta.add(0,tmp);

		}while((x/=base)!=0);
		return respuesta;
		// prueba el integer.Max un vector de unos y 10=1,0,1,0
	}
	
	private static long deBaseADecimal(String numero, int base) throws Exception
	{
		/*refinamiento de las versiones con vectores*/
		long resultado=0;
		//System.out.println(numero);
		for (int i = numero.length()-1, K=0; i > -1 ; K++,i--) {
			int digito=deCharAEntero(numero.charAt(i));
			
			if(digito>=base)
				throw new Exception();
			resultado+=digito*Math.pow((double)base, (double)K);
			//System.out.println(resultado);
		}
		return resultado;
	}
	
	private static int deCharAEntero(char letra) {
		return Arrays.binarySearch(chATodo, letra);
	}
}
