
import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Locale;
import java.util.StringTokenizer;

public class D{
	static BufferedReader input;
	
	static StringTokenizer _stK;
        
        static String readln() throws IOException{
            String l=input.readLine();
            if(l!=null){
                _stK=new StringTokenizer(l," ");
            }
            return l;
        }
        
        static String next(){
            return _stK.nextToken();
        }
        
        static int nextInt(){
            return Integer.parseInt(next());
        }
        
        static  void dbg(Object... o){
//            System.out.println(Arrays.deepToString(o));
        }
	
	public static void main(String[] args ) throws Exception{
            Locale.setDefault(Locale.US);
//            input=new BufferedReader(new FileReader("D"));
            input=new BufferedReader(new InputStreamReader(System.in));
            int A, B;
            int rA,rB;
            double cuadrado;
            double compareRaiz;
            int id=0;
            while(true){
                id++;
                readln();
                
                A=nextInt();
                B=nextInt();
                if(A==0&& B==0){
                    break;
                }
                int contador=0;
                int bolasTrian=0;
                int sumatoria=0;
                rA=(int) Math.sqrt(A);
                rB=(int) Math.sqrt(B);
                for (int i = rA; i <= rB; i++) {
                    
//                    if(i==1) continue;
                    sumatoria=0;
                    cuadrado=Math.pow(i, 2.0);
                    ///compareRaiz=cuadrado;
                    //dbg(compareRaiz,"raiz",raiz);
                    if(A<cuadrado && cuadrado <B){
                        bolasTrian=((int)(cuadrado))-1;
                        //dbg("i" ,i,"entro" ,raiz, "bolas",bolasTrian);
                        for (int j = i; sumatoria <= bolasTrian; j++) {
                            sumatoria=((j*(j+1))/2);
                            if(bolasTrian==sumatoria){
                            
                                contador++;
                                break;
                            }
                        }
                        
                    }
                }
                System.out.printf("Case %d: %d\n",id,contador);
            }
	}
}
