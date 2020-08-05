
import java.io.*;
import java.util.*;

public class B{
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
            System.out.println(Arrays.deepToString(o));
        }
	
	public static void main(String[] args ) throws Exception{
            Locale.setDefault(Locale.US);
            input=new BufferedReader(new FileReader("B"));
//            input=new BufferedReader(new InputStreamReader(System.in));
            
            int s,c, p,l,frac,id;
            int idCases=0;
            HashSet <Integer> result=new HashSet<Integer>(200);
            while(true){
                idCases++;
                readln();
                s=nextInt();
                c=nextInt();
                p=nextInt();
                l=nextInt();
                result.clear();
                if(s==0 &&c==0 &&p==0 &&l==0)
                    break;
                
                frac=s-p;
                id=0;
                if(p==0)
                {
                    frac=0;
                }
                else
                {
                    id=c-frac;
                }
                
                
                result.add(id);
                if(id==l){
                    System.out.printf("Case %d: 0 %d/%d\n", idCases, frac,s);
                }
                else{
                    int contador=0;
//                    dbg("s c p l", s, c,p,l);
                    
//                   dbg(" F id",id);
                    while(id!=l){
                        id=id-s;
                        
                        if(id< 0 ){
                            id=c+id;
                        }
                        
                        if(result.contains(id)){
                            break;
                        }
                        else
                        {
                            result.add(id);
                        }
                        
                        contador++;
//                        dbg("id",id);
                    }
                    
                    if (id==l) {
                        System.out.printf("Case %d: %d %d/%d\n", idCases,contador, frac,s);
                    }else{
//                        dbg(contador);
                        System.out.printf("Case %d: Never\n", idCases);
                    }
                }
            }
	}
}
